import groovy.sql.Sql

class CompetenciasDAO {
    Sql sql

    CompetenciasDAO(Sql sql) {
        this.sql = sql
    }

    def inserir(Competencias c) {
        def keys = sql.executeInsert("INSERT INTO competencias (nome) VALUES (?)", [c.nome])
        println "Competência inserida com sucesso"
        return keys[0][0]
    }

    List<Competencias> listarTodos() {
        def rows = sql.rows("SELECT * FROM competencias")
        return rows.collect { row ->
            new Competencias(nome: row.nome)
        }
    }

    void atualizarCampo(int id, String campo, Object novoValor) {
        sql.executeUpdate("UPDATE competencias SET ${campo} = ? WHERE id = ?", [novoValor, id])
        println "✏ Campo '${campo}' atualizado com sucesso"
    }


    void deletar(int id) {
        sql.execute "DELETE FROM competencias WHERE id = $id"
        println "Competência removida com sucesso!"
    }
}