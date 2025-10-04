import groovy.sql.Sql

class FormacaoDAO {
    Sql sql

    FormacaoDAO(Sql sql) {
        this.sql = sql
    }

    def inserir(Formacao f) {
        def keys = sql.executeInsert("INSERT INTO formacoes(nome) VALUES (?)", [f.nome])
        println "Formação inserida com sucesso"
        return keys[0][0]
    }

    List<Formacao> listarTodos() {
        def rows = sql.rows("SELECT * FROM formacoes")
        return rows.collect { row ->
            new Formacao(nome: row.nome)
        }
    }

    void atualizarCampo(int id, String campo, Object novoValor) {
        sql.executeUpdate("UPDATE formacoes SET ${campo} = ? WHERE id = ?", [novoValor, id])
        println "✏ Campo '${campo}' atualizado com sucesso"
    }

    void deletar(int id) {
        sql.execute "DELETE FROM formacoes WHERE id = $id"
        println "Formação removida com sucesso!"
    }
}
