package controllers

import model.Candidato
import model.Empresa
import model.Vagas
import services.CurtidaService

class CurtidaController {

    static void candidatoCurteVaga(Candidato candidato, Vagas vaga) {
        try {
            CurtidaService.candidatoCurteVaga(candidato, vaga)
        } catch (Exception e) {
            println "Erro ao processar curtida do candidato: ${e.message}"
        }
    }

    static void empresaCurteCandidato(Empresa empresa, Candidato candidato, Vagas vaga) {
        try {
            CurtidaService.empresaCurteCandidato(empresa, candidato, vaga)
        } catch (Exception e) {
            println "Erro ao processar curtida da empresa: ${e.message}"
        }
    }

    static void listarCurtidas() {
        try {
            def curtidas = CurtidaService.listarCurtidas()
            if (curtidas.isEmpty()) {
                println "Nenhuma curtida registrada."
            } else {
                println "\nCurtidas registradas:"
                curtidas.each { c ->
                    println c
                }
            }
        } catch (Exception e) {
            println "Erro ao listar curtidas: ${e.message}"
        }
    }
}
