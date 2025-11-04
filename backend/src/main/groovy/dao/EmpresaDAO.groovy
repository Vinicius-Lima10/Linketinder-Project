package dao

import groovy.sql.Sql
import interfaces.IGenericDAO
import model.Empresa

class EmpresaDAO implements IGenericDAO<Empresa> {
    Sql sql

    EmpresaDAO(Sql sql) {
        this.sql = sql
    }

    @Override
    def inserir(Empresa e) {
        try {
            sql.executeInsert("""
                INSERT INTO empresas (nome, cnpj, email, senha, pais, estado, cep, descricao)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """, [e.nome, e.cnpj, e.email, e.senha, e.pais, e.estado, e.cep, e.descricao])
        } catch (Exception ex) {
            println "Erro ao inserir empresa '${e.nome}': ${ex.message}"
        }
    }

    @Override
    List<Empresa> listarTodos() {
        try {
            def rows = sql.rows("SELECT * FROM empresas")

            return rows.collect { row ->
                new Empresa(
                        id: row.id,
                        nome: row.nome,
                        cnpj: row.cnpj,
                        email: row.email,
                        senha: row.senha,
                        pais: row.pais,
                        estado: row.estado,
                        cep: row.cep,
                        descricao: row.descricao
                )
            }
        } catch (Exception ex) {
            println "Erro ao listar empresas: ${ex.message}"
            return []
        }
    }

    @Override
    void atualizarCampo(int id, String campo, Object novoValor) {
        try {
            def camposPermitidos = ["nome", "cnpj", "email", "senha", "pais", "estado", "cep", "descricao"]
            if (!camposPermitidos.contains(campo)) {
                throw new IllegalArgumentException("Campo '${campo}' não é permitido para atualização.")
            }
            sql.executeUpdate("UPDATE empresas SET ${campo} = ? WHERE id = ?", [novoValor, id])
        } catch (Exception ex) {
            println "Erro ao atualizar empresa: ${ex.message}"
        }
    }

    @Override
    void deletar(int id) {
        try {
            int removidos = sql.executeUpdate("DELETE FROM empresas WHERE id = ?", [id])
        } catch (Exception ex) {
            println "Erro ao deletar empresa: ${ex.message}"
        }
    }
}
