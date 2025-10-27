package dao

import groovy.sql.Sql
import interfaces.IGenericDAO
import model.Vagas

import java.sql.SQLException

class VagasDAO implements IGenericDAO<Vagas> {
    Sql sql
    CompetenciasDAO competenciasDAO

    VagasDAO(Sql sql) {
        this.sql = sql
        this.competenciasDAO = new CompetenciasDAO(sql)
    }

    @Override
    def inserir(Vagas v) {
        try {
            int vagaID = inserirVaga(v)
            associacaoService.associarCompetencias("vagacompetencias", "vaga_id", vagaID, v.competencias)
            println "Vaga '${v.nome}' inserido com sucesso."
        } catch (Exception ex) {
            println "Erro ao inserir vaga '${v.nome}': ${ex.message}"
        }
    }

    @Override
    List<Vagas> listarTodos() {
        try {
            def rows = sql.rows("""
                SELECT v.*, e.nome AS empresa_nome
                FROM vagas v
                JOIN empresas e ON v.empresa_id = e.id
            """)

            return rows.collect { row ->
                def comps = sql.rows("""
                    SELECT c.nome 
                    FROM competencias c
                    JOIN vagacompetencias vc ON c.id = vc.competencia_id
                    WHERE vc.vaga_id = ?
                """, [row.id]).collect { it.nome }

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
        } catch (Exception ex) {
            println "Erro ao listar vagas: ${ex.message}"
            return []
        }
    }

    @Override
    void atualizarCampo(int id, String campo, Object novoValor) {
        try {
            def camposPermitidos = ["nome", "descricao", "endereco", "cidade", "estado", "pais"]
            if (!camposPermitidos.contains(campo)) {
                throw new IllegalArgumentException("Campo '${campo}' não é permitido para atualização.")
            }
            sql.executeUpdate("UPDATE vagas SET ${campo} = ? WHERE id = ?", [novoValor, id])
            println "Campo '${campo}' da vaga ID ${id} atualizado com sucesso."
        } catch (Exception ex) {
            println "Erro ao atualizar vaga: ${ex.message}"
        }
    }

    @Override
    void deletar(int id) {
        try {
            int removidos = sql.executeUpdate("DELETE FROM vagas WHERE id = ?", [id])
            if (removidos > 0) {
                println "Vaga removida com sucesso."
            } else {
                println "Nenhuma vaga encontrada com o ID ${id}."
            }
        } catch (Exception ex) {
            println "Erro ao deletar vaga: ${ex.message}"
        }
    }

    private def inserirVaga(Vagas v) {
        try {
            def keys = sql.executeInsert("""
                INSERT INTO vagas (nome, descricao, endereco, cidade, estado, pais, empresa_id)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """, [v.nome, v.descricao, v.endereco, v.cidade, v.estado, v.pais, v.empresa_id])
            return keys[0][0]
        } catch (Exception ex) {
            println "Erro ao inserir vaga '${v?.nome}': ${ex.message}"
        }
    }
}
