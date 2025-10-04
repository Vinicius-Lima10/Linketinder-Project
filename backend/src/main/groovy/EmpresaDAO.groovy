import groovy.sql.Sql

class EmpresaDAO {
    Sql sql

    EmpresaDAO(Sql sql) {
        this.sql = sql
    }

    void inserir(Empresa e) {
        sql.execute """
            INSERT INTO empresas (nome, cnpj, email, senha, pais, estado, cep, descricao)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """, [e.nome, e.cnpj, e.email, e.senha, e.pais, e.estado, e.cep, e.descricao]
        println "Empresa inserida com sucesso"
    }

    List<Empresa> listarTodos() {
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
                    descricao: row.descricao,
            )
        }
    }

    void atualizarCampo(int id, String campo, Object novoValor) {
        sql.executeUpdate("UPDATE empresas SET ${campo} = ? WHERE id = ?", [novoValor, id])
        println "Campo '${campo}' atualizado com sucesso"
    }

    void deletar(int id) {
        sql.execute "DELETE FROM empresas WHERE id = $id"
        println "Empresa removida com sucesso"
    }
}
