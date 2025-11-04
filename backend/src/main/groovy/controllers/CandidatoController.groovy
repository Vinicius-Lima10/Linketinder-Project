package controllers

import model.Candidato
import services.CandidatoService

class CandidatoController {

    static void adicionarCandidato(Candidato candidato) {
        try {
            CandidatoService.adicionarCandidato(candidato)
            println "Candidato '${candidato.nome}' cadastrado com sucesso!"
        } catch (Exception e) {
            println "Erro ao cadastrar candidato: ${e.message}"
        }
    }

    static void listarCandidatos() {
        def lista = CandidatoService.listarCandidatos()
        if (lista.isEmpty()) {
            println "Nenhum candidato cadastrado."
        } else {
            println "\n Lista de candidatos:"
            lista.each { c ->
                println "• ${c.nome} ${c.sobrenome} ${c.cpf} - ${c.estado}, ${c.pais}"
            }
        }
    }
    static void removerCandidato(int id) {
        try {
            CandidatoService.removerCandidato(id)
            println "Candidato ID ${id} removido com sucesso."
        } catch (Exception e) {
            println "Erro ao remover candidato: ${e.message}"
        }
    }

    static void removerCandidatoPorCPF(String cpf) {
        def candidato = buscarPorCPF(cpf)
        if (candidato) {
            CandidatoService.removerCandidato(candidato.id)
        } else {
            println "Nenhum candidato encontrado com cpf ${cpf}"
        }
    }

    static def buscarPorCPF(String cpf) {
        def lista = CandidatoService.listarCandidatos()
        return lista.find { it.cpf == cpf }
    }
}
