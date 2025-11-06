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
    def inserir(Formacao f) throws Exception{
        try {
            def keys = sql.executeInsert("INSERT INTO formacoes (nome) VALUES (?)", [f.nome])
            return keys[0][0]
        } catch (Exception ex) {
            println "foi no formacaodao"
            throw ex
        }
    }

    @Override
    List<Formacao> listarTodos() throws Exception{
        try {
            def rows = sql.rows("SELECT * FROM formacoes")
            return rows.collect { row ->
                new Formacao(nome: row.nome)
            }
        } catch (Exception ex) {
            throw ex
        }
    }

    @Override
    void atualizarCampo(int id, String campo, Object novoValor) throws Exception{
    }

    @Override
    void deletar(int id) throws Exception{
        try {
            sql.execute("DELETE FROM formacoes WHERE id = ?", [id])
        } catch (Exception ex) {
            throw ex
        }
    }
}
