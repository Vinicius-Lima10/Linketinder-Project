package dao

import groovy.sql.Sql
import interfaces.IGenericDAO
import model.Competencias

import java.sql.SQLException

class CompetenciasDAO implements IGenericDAO<Competencias> {
    Sql sql

    CompetenciasDAO(Sql sql) {
        this.sql = sql
    }

    @Override
    def inserir(Competencias c) {
        try {
            def keys = sql.executeInsert("INSERT INTO competencias (nome) VALUES (?)", [c.nome])
            return keys[0][0]
        } catch (Exception ex) {
            throw ex
        }
    }

    @Override
    List<Competencias> listarTodos() {
        try {
            def rows = sql.rows("SELECT * FROM competencias")
            return rows.collect { row ->
                new Competencias(nome: row.nome)
            }
        } catch (Exception ex) {
            throw ex
        }
    }

    @Override
    void atualizarCampo(int id, String campo, Object novoValor) {
        try {
            def camposPermitidos = ["nome"]
            if (!camposPermitidos.contains(campo)) {
                throw new Exception()
            }
            sql.executeUpdate("UPDATE competencias SET ${campo} = ? WHERE id = ?", [novoValor, id])
        } catch (Exception ex) {
            throw ex
        }
    }

    @Override
    void deletar(int id) {
        try {
            sql.execute("DELETE FROM competencias WHERE id = ?", [id])
        } catch (Exception ex) {
            throw ex
        }
    }
}
