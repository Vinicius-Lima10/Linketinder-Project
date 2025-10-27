package dao // Alterado de 'services' para 'dao'

@Grab('org.postgresql:postgresql:42.7.3')
@GrabConfig(systemClassLoader=true)

import groovy.sql.Sql

class Conexao {

    static void withConnection(Closure codeBlock) {
        def url = 'jdbc:postgresql://localhost:5432/linketinder'
        def user = 'postgres'
        def password = '12345678'
        def driver = 'org.postgresql.Driver'

        try {
            Sql.withInstance(url, user, password, driver) { sql ->
                codeBlock(sql)
            }
        } catch (Exception e) {
            println "ERRO de DB: Falha ao conectar ou executar operação: ${e.message}"
            throw new RuntimeException("Erro de conexão ou operação de banco de dados.", e)
        }
    }
}
