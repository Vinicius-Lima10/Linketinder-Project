package controllers

import model.Candidato
import services.CandidatoService

class CandidatoController {

    static void adicionarCandidato(Candidato candidato) throws Exception{
        try {
             CandidatoService.adicionarCandidato(candidato)
        } catch (Exception e) {
            throw e
        }
    }

    static List<Candidato> listarCandidatos() throws Exception {
        try {
            return CandidatoService.listarCandidatos()
        } catch (Exception e) {
            throw e
        }
    }

    static void removerCandidato(int id) throws Exception {
        try {
            CandidatoService.removerCandidato(id)
        } catch (Exception e) {
            throw e
        }
    }

    static void removerCandidatoPorCPF(String cpf) throws Exception{
        try {
            def candidato = buscarPorCPF(cpf)
            CandidatoService.removerCandidato(candidato.id)
        } catch (Exception e) {
            throw e
        }
    }

    static Candidato buscarPorCPF(String cpf) throws Exception{
        try {
            def resultado = CandidatoService.listarCandidatos()
            def candidato = resultado.find { it.cpf == cpf }
            return candidato
        } catch (Exception e) {
            throw e
        }
    }
}
