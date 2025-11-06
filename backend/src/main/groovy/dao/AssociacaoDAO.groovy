package dao
import groovy.sql.Sql
import model.Competencias
import model.Formacao

class AssociacaoDAO {
    Sql sql
    CompetenciasDAO competenciasDAO
    FormacaoDAO formacaoDAO

    AssociacaoDAO(Sql sql) {
        this.sql = sql
        this.competenciasDAO = new CompetenciasDAO(sql)
        this.formacaoDAO = new FormacaoDAO(sql)
    }

    void associarFormacoes(String tabelaAssociacao, String colunaIdReferencia, int idReferencia, List<String> formacoes) throws Exception{
        formacoes?.each { nome ->
            try {
                def formId = sql.firstRow("SELECT id FROM formacoes WHERE nome = ?", [nome])?.id
                if (!formId) {
                    def result = formacaoDAO.inserir(new Formacao(nome: nome))
                    formId = (result instanceof List && result[0] instanceof List) ? result[0][0] : result
                }
                sql.executeInsert(
                        "INSERT INTO ${tabelaAssociacao} (${colunaIdReferencia}, formacao_id) VALUES (?, ?)".toString(),
                        [idReferencia as Integer, formId as Integer]
                )
            } catch (Exception ex) {
                println "foi no associcao"
                throw ex
            }
        }
    }

    void associarCompetencias(String tabelaAssociacao, String colunaIdReferencia, int idReferencia, List<String> competencias) throws Exception{
        competencias?.each { nome ->
            try {
                nome = nome.toString()
                def compId = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [nome])?.id
                if (!compId) {
                    def result = competenciasDAO.inserir(new Competencias(nome: nome))
                    compId = (result instanceof List && result[0] instanceof List) ? result[0][0] : result
                }

                sql.executeInsert(
                        "INSERT INTO ${tabelaAssociacao} (${colunaIdReferencia}, competencia_id) VALUES (?, ?)".toString(),
                        [idReferencia as Integer, compId as Integer]
                )
            } catch (Exception ex) {
                println "foi no associcao"
                throw ex
            }
        }
    }
}
