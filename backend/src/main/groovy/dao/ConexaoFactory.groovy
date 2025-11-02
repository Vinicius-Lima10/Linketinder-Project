package dao

import groovy.sql.Sql

class ConexaoFactory {
    static Sql criarConexao(String tipoBanco, String url, String user = null, String password = null) {
        switch (tipoBanco.toLowerCase()) {
            case "postgresql":
                return Sql.newInstance(url, user, password, "org.postgresql.Driver")
            case "mysql":
                return Sql.newInstance(url, user, password, "com.mysql.cj.jdbc.Driver")
            default:
                throw new IllegalArgumentException("Tipo de banco de dados não suportado: $tipoBanco")
        }
    }
}
