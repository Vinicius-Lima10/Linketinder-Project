package controllers

import model.Vagas
import services.VagasService

class VagaController {

    static void adicionarVaga(Vagas vaga) throws Exception {
        try {
            VagasService.adicionarVaga(vaga)
        } catch (Exception e) {
            throw e
        }
    }

    static List<Vagas> listarVagas() throws Exception{
        try {
            return VagasService.listarVagas()
        } catch (Exception e) {
            throw e
        }
    }
    static void removerVagaPorId(int id) throws Exception{
        try {
            VagasService.removerVaga(id)
        } catch (Exception e) {
            throw e
        }
    }

    static void removerVagaPorNome(String nome) throws Exception{
        try {
            def lista = VagasService.listarVagas()
            def vaga = lista.find { it.nome.equalsIgnoreCase(nome) }
            VagasService.removerVaga(vaga.id)
        } catch (Exception e) {
            throw e
        }
    }

    static Object buscarPorNome(String nome) throws Exception{
        try {
            def lista = VagasService.listarVagas()
            return lista.find { it.nome.equalsIgnoreCase(nome) }
        } catch (Exception e) {
            throw e
        }
    }
}
