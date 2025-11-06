package dao

import groovy.sql.Sql
import java.sql.SQLException

@Grab('org.postgresql:postgresql:42.7.3')
@GrabConfig(systemClassLoader = true)
class Conexao {

    private static Conexao instancia
    private Sql sql

    private Conexao(String tipoBanco, String url, String user, String password) {
        this.sql = ConexaoFactory.criarConexao(tipoBanco, url, user, password)
    }

    static synchronized Conexao getInstancia(
            String tipoBanco = "postgresql",
            String url = "jdbc:postgresql://localhost:5432/linketinder",
            String user = "postgres",
            String password = "12345678"
    ) {
        if (instancia == null) {
            instancia = new Conexao(tipoBanco, url, user, password)
        }
        return instancia
    }

    static def withConnection(Closure codeBlock) {
        def conexao = getInstancia().sql
        try {
            return codeBlock(conexao)
        } catch (Exception e) {
            println "${e.message}"
            throw new RuntimeException("Erro de conexão ou operação de banco de dados.", e)
        }
    }
    Sql getSql() {
        return sql
    }
}
