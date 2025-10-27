package services

import model.Candidato
import model.Curtida
import model.Empresa
import model.Vagas

class GerenciadorDeCurtidas {
    static List<Curtida> curtidas = []

    static void candidatoCurteVaga(Candidato candidato, Vagas vaga) {
        if (!candidato) {
            println "Candidato inválido"
            return
        }
        if (!vaga) {
            println "Vaga inválida"
            return
        }
        def curtida = curtidas.find { it.candidato == candidato && it.vaga == vaga }
        if (!curtida) {
            curtida = new Curtida(candidato: candidato, vaga: vaga, candidatoCurtiu: true)
            curtidas << curtida
            println "${candidato.nome} curtiu a vaga ${vaga.nome}"
        } else {
            curtida.candidatoCurtiu = true
            println "${candidato.nome} já havia curtido essa vaga."
        }
        verificarMatch(curtida)
    }

    static void empresaCurteCandidato(Empresa empresa, Candidato candidato, Vagas vaga) {
        if (!empresa) {
            println "Empresa inválida"
            return
        }
        if (!candidato) {
            println "Candidato inválido"
            return
        }
        if (!vaga) {
            println "Vaga inválida"
            return
        }
        def curtida = curtidas.find { it.candidato == candidato && it.vaga == vaga }
        if (!curtida) {
            curtida = new Curtida(empresa: empresa, candidato: candidato, vaga: vaga, empresaCurtiu: true)
            curtidas << curtida
            println "🏢 ${empresa.nome} curtiu o candidato ${candidato.nome}"
        } else {
            curtida.empresa = empresa
            curtida.empresaCurtiu = true
            println "🏢 ${empresa.nome} curtiu o candidato ${candidato.nome}"
        }
        verificarMatch(curtida)
    }

    private static void verificarMatch(Curtida curtida) {
        if (curtida.isMatch()) {
            println "Match encontrado entre ${curtida.candidato.nome} e ${curtida.empresa?.nome ?: 'a empresa da vaga ' + curtida.vaga.nome}!"
        }
    }

    static void listarCurtidas() {
        if (curtidas.isEmpty()) {
            println "Nenhuma curtida registrada ainda."
        }
        curtidas.each { c ->
            println c
        }
    }
}
