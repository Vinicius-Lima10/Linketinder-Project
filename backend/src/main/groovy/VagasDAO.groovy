import groovy.sql.Sql

class VagasDAO {
    Sql sql
    CompetenciasDAO competenciasDAO

    VagasDAO(Sql sql) {
        this.sql = sql
        this.competenciasDAO = new CompetenciasDAO(sql)
    }

    void inserir(Vagas v) {
        def vagaId = sql.executeInsert("""
            INSERT INTO vagas (nome, descricao, endereco, cidade, estado, pais, empresa_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """, [v.nome, v.descricao, v.endereco, v.cidade, v.estado, v.pais, v.empresa_id])[0][0]

        v.competencias?.each { comp ->
            def compId = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [comp])?.id
            if (!compId) {
                competenciasDAO.inserir(new Competencias(nome: comp))
                compId = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [comp]).id
            }
            sql.executeInsert("INSERT INTO vagacompetencias (vaga_id, competencia_id) VALUES (?, ?)", [vagaId, compId])
        }

        println "Vaga '${v.nome}' inserida com sucesso"
    }

    List<Vagas> listarTodos() {
        def rows = sql.rows("""
            SELECT v.*, e.nome AS empresa_nome
            FROM vagas v
            JOIN empresas e ON v.empresa_id = e.id
        """)
        rows.collect { row ->
            def comps = sql.rows("""
                SELECT c.nome 
                FROM competencias c
                JOIN vagacompetencias vc ON c.id = vc.competencia_id
                WHERE vc.vaga_id = ?
            """, [row.id]).collect { it.nome }

            new Vagas(
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
    }

    void atualizarCampo(int id, String campo, Object novoValor) {
        sql.executeUpdate("UPDATE vagas SET ${campo} = ? WHERE id = ?", [novoValor, id])
        println "Campo '${campo}' da vaga ID ${id} atualizado com sucesso"
    }

    void deletar(int id) {
        sql.execute "DELETE FROM vagas WHERE id = $id"
        println "Vaga removida com sucesso!"
    }
}
