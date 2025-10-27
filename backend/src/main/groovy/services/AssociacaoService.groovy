package services

import dao.CompetenciasDAO
import dao.FormacaoDAO
import groovy.sql.Sql
import model.Competencias
import model.Formacao

class AssociacaoService {
    Sql sql
    CompetenciasDAO competenciasDAO
    FormacaoDAO formacaoDAO

    AssociacaoService(Sql sql) {
        this.sql = sql
        this.competenciasDAO = new CompetenciasDAO(sql)
        this.formacaoDAO = new FormacaoDAO(sql)
    }

    void associarCompetencias(String tabelaAssociacao, String colunaIdReferencia, int idReferencia, List<String> competencias) {
        competencias?.each { nome ->
            try {
                def compId = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [nome])?.id
                if (!compId) {
                    compId = competenciasDAO.inserir(new Competencias(nome: nome))
                }

                sql.executeInsert("INSERT INTO ${tabelaAssociacao} (${colunaIdReferencia}, competencia_id) VALUES (?, ?)",
                        [idReferencia, compId])
            } catch (Exception ex) {
                println "Erro ao associar competência '${nome}' em ${tabelaAssociacao}: ${ex.message}"
            }
        }
    }


    void associarFormacoes(String tabelaAssociacao, String colunaIdReferencia, int idReferencia, List<String> formacoes) {
        formacoes?.each { nome ->
            try {
                def formId = sql.firstRow("SELECT id FROM formacoes WHERE nome = ?", [nome])?.id
                if (!formId) {
                    formId = formacaoDAO.inserir(new Formacao(nome: nome))
                }

                sql.executeInsert("INSERT INTO ${tabelaAssociacao} (${colunaIdReferencia}, formacao_id) VALUES (?, ?)",
                        [idReferencia, formId])
            } catch (Exception ex) {
                println "Erro ao associar formação '${nome}' em ${tabelaAssociacao}: ${ex.message}"
            }
        }
    }
}
