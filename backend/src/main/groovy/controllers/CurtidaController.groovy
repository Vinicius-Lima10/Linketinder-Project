package controllers

import model.Candidato
import model.Empresa
import model.Vagas
import services.CurtidaService

class CurtidaController {

    static Object candidatoCurteVaga(Candidato candidato, Vagas vaga) throws Exception{
        try {
            CurtidaService.candidatoCurteVaga(candidato, vaga)
        } catch (Exception e) {
            throw e
        }
    }

    static Object empresaCurteCandidato(Empresa empresa, Candidato candidato, Vagas vaga) throws Exception {
        try {
            CurtidaService.empresaCurteCandidato(empresa, candidato, vaga)
        } catch (Exception e) {
            throw e
        }
    }

    static Object listarCurtidas() throws Exception{
        try {
            def resultado = CurtidaService.listarCurtidas()
            return resultado ?: []
        } catch (Exception e) {
            throw e
        }
    }
}
