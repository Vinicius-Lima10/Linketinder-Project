import groovy.sql.Sql
import java.sql.SQLException

class CompetenciasDAO {
    Sql sql

    CompetenciasDAO(Sql sql) {
        this.sql = sql
    }

    def inserir(Competencias c) {
        try {
            def keys = sql.executeInsert("INSERT INTO competencias (nome) VALUES (?)", [c.nome])
            println "Competência '${c.nome}' inserida com sucesso."
            return keys[0][0]
        } catch (Exception ex) {
            println "Erro ao inserir competência '${c?.nome}': ${ex.message}"
            return null
        }
    }

    List<Competencias> listarTodos() {
        try {
            def rows = sql.rows("SELECT * FROM competencias")
            return rows.collect { row ->
                new Competencias(nome: row.nome)
            }
        } catch (Exception ex) {
            println "Erro ao listar competências: ${ex.message}"
            return []
        }
    }

    void atualizarCampo(int id, String campo, Object novoValor) {
        try {
            def camposPermitidos = ["nome"]
            if (!camposPermitidos.contains(campo)) {
                throw new IllegalArgumentException("Campo '${campo}' não é permitido para atualização.")
            }

            sql.executeUpdate("UPDATE competencias SET ${campo} = ? WHERE id = ?", [novoValor, id])
            println "Campo '${campo}' atualizado com sucesso para a competência ID ${id}."
        } catch (Exception ex) {
            println "Erro ao atualizar competência: ${ex.message}"
        }
    }

    void deletar(int id) {
        try {
            int removidos = sql.executeUpdate("DELETE FROM competencias WHERE id = ?", [id])
            if (removidos > 0) {
                println "Competência removida com sucesso."
            } else {
                println "Nenhuma competência encontrada com o ID ${id}."
            }
        } catch (Exception ex) {
            println "Erro ao deletar competência: ${ex.message}"
        }
    }
}
