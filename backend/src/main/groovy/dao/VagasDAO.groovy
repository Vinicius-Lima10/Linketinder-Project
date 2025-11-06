package dao

import groovy.sql.Sql
import interfaces.IGenericDAO
import model.Vagas

class VagasDAO implements IGenericDAO<Vagas> {

    private final Sql sql
    private final CompetenciasDAO competenciasDAO
    private final AssociacaoDAO associacaoDAO

    VagasDAO(Sql sql) {
        this.sql = sql
        this.competenciasDAO = new CompetenciasDAO(sql)
        this.associacaoDAO = new AssociacaoDAO(sql)
    }

    @Override
    def inserir(Vagas vaga) throws Exception {
        try {
            int vagaID = inserirVaga(vaga)
            associacaoDAO.associarCompetencias("vagacompetencias", "vaga_id", vagaID, vaga.competencias)
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    List<Vagas> listarTodos() throws Exception {
        try {
            List<Map> rows = sql.rows("""
                SELECT v.*, e.nome AS empresa_nome
                FROM vagas v
                JOIN empresas e ON v.empresa_id = e.id
            """)

            return rows.collect { row ->
                List<Map> compsRows = sql.rows("""
                    SELECT c.nome 
                    FROM competencias c
                    JOIN vagacompetencias vc ON c.id = vc.competencia_id
                    WHERE vc.vaga_id = ?
                """, [row.id])

                List<String> comps = compsRows.collect { it.nome }

                new Vagas(
                        id: row.id,
                        nome: row.nome,
                        descricao: row.descricao,
                        endereco: row.endereco,
                        cidade: row.cidade,
                        estado: row.estado,
                        pais: row.pais,
                        empresa_id: row.empresa_id,
                        competencias: comps
                )
            }
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    void atualizarCampo(int id, String campo, Object novoValor) throws Exception {
        try {
            List<String> camposPermitidos = ["nome", "descricao", "endereco", "cidade", "estado", "pais"]
            if (!camposPermitidos.contains(campo)) {
                throw new Exception()
            }

            sql.executeUpdate("UPDATE vagas SET ${campo} = ? WHERE id = ?", [novoValor, id])
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    void deletar(int id) throws Exception {
        try {
            sql.executeUpdate("DELETE FROM vagas WHERE id = ?", [id])
        } catch (Exception e) {
            throw e
        }
    }

    private int inserirVaga(Vagas vaga) throws Exception {
        try {
            List<List<Object>> keys = sql.executeInsert("""
                INSERT INTO vagas (nome, descricao, endereco, cidade, estado, pais, empresa_id)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """, [vaga.nome, vaga.descricao, vaga.endereco, vaga.cidade, vaga.estado, vaga.pais, vaga.empresa_id])

            return (int) keys[0][0]
        } catch (Exception e) {
            throw e
        }
    }
}
