import groovy.sql.Sql
import java.sql.SQLException

class EmpresaDAO {
    Sql sql

    EmpresaDAO(Sql sql) {
        this.sql = sql
    }

    void inserir(Empresa e) {
        try {
            sql.execute """
                INSERT INTO empresas (nome, cnpj, email, senha, pais, estado, cep, descricao)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """, [e.nome, e.cnpj, e.email, e.senha, e.pais, e.estado, e.cep, e.descricao]
            println "Empresa inserida com sucesso."
        } catch (Exception ex) {
            println "Erro ao inserir empresa: ${ex.message}"
        }
    }

    List<Empresa> listarTodos() {
        try {
            def rows = sql.rows("SELECT * FROM empresas")
            return rows.collect { row ->
                new Empresa(
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

    void atualizarCampo(int id, String campo, Object novoValor) {
        try {
            def camposPermitidos = ["nome", "cnpj", "email", "senha", "pais", "estado", "cep", "descricao"]
            if (!camposPermitidos.contains(campo)) {
                throw new IllegalArgumentException("Campo '${campo}' não é permitido para atualização.")
            }
            sql.executeUpdate("UPDATE empresas SET ${campo} = ? WHERE id = ?", [novoValor, id])
            println "Campo '${campo}' atualizado com sucesso."
        } catch (Exception ex) {
            println "Erro ao atualizar empresa: ${ex.message}"
        }
    }

    void deletar(int id) {
        try {
            int removidos = sql.executeUpdate("DELETE FROM empresas WHERE id = ?", [id])
            if (removidos > 0) {
                println "Empresa removida com sucesso."
            } else {
                println "Nenhuma empresa encontrada com o ID ${id}."
            }
        } catch (Exception ex) {
            println "Erro ao deletar empresa: ${ex.message}"
        }
    }
}
