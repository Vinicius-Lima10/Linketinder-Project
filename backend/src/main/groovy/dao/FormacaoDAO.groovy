package dao

import groovy.sql.Sql
import interfaces.IGenericDAO
import model.Formacao

import java.sql.SQLException

class FormacaoDAO implements IGenericDAO<Formacao> {
    Sql sql

    FormacaoDAO(Sql sql) {
        this.sql = sql
    }

    @Override
    def inserir(Formacao f) {
        try {
            def keys = sql.executeInsert("INSERT INTO formacoes (nome) VALUES (?)", [f.nome])
            println "Formação inserida com sucesso."
            return keys[0][0]
        } catch (Exception ex) {
            println "Erro ao inserir formação: ${ex.message}"
            return null
        }
    }

    @Override
    List<Formacao> listarTodos() {
        try {
            def rows = sql.rows("SELECT * FROM formacoes")
            return rows.collect { row ->
                new Formacao(nome: row.nome)
            }
        } catch (Exception ex) {
            println "Erro ao listar formações: ${ex.message}"
            return []
        }
    }

    @Override
    void atualizarCampo(int id, String campo, Object novoValor) {
        try {
            def camposPermitidos = ["nome"]
            if (!camposPermitidos.contains(campo)) {
                throw new IllegalArgumentException("Campo '${campo}' não é permitido para atualização.")
            }
            sql.executeUpdate("UPDATE formacoes SET ${campo} = ? WHERE id = ?", [novoValor, id])
            println "Campo '${campo}' atualizado com sucesso."
        } catch (Exception ex) {
            println "Erro ao atualizar formação: ${ex.message}"
        }
    }

    @Override
    void deletar(int id) {
        try {
            int removidos = sql.executeUpdate("DELETE FROM formacoes WHERE id = ?", [id])
            if (removidos > 0) {
                println "Formação removida com sucesso."
            } else {
                println "Nenhuma formação encontrada com o ID ${id}."
            }
        } catch (Exception ex) {
            println "Erro ao deletar formação: ${ex.message}"
        }
    }
}
