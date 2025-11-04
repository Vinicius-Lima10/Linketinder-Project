package controllers

import model.Vagas
import services.VagasService
import services.EmpresaService
import dao.Conexao

class VagaController {

    static void adicionarVaga(Vagas vaga, String cnpj) {
        if (!cnpj || cnpj.trim().isEmpty()) {
            println "CNPJ da empresa não pode estar vazio."
            return
        }

        try {
            def empresaId = 0

            Conexao.withConnection { sql ->
                def empresaService = new EmpresaService(sql)
                def empresa = empresaService.listarEmpresas().find { it.cnpj == cnpj }
                if (!empresa) {
                    println "Nenhuma empresa encontrada com o CNPJ '${cnpj}'."
                    return
                }
                empresaId = empresa.id
            }

            if (empresaId == 0) {
                println "Não foi possível associar a vaga a uma empresa válida."
                return
            }

            vaga.empresa_id = empresaId
            VagasService.adicionarVaga(vaga)
            println "Vaga '${vaga.nome}' adicionada com sucesso para a empresa de CNPJ ${cnpj}."

        } catch (Exception e) {
            println "Erro ao adicionar vaga: ${e.message}"
        }
    }

    static void listarVagas() {
        try {
            def vagas = VagasService.listarVagas()
            if (vagas.isEmpty()) {
                println "Nenhuma vaga cadastrada."
            } else {
                println "\nLista de vagas:"
                vagas.each { v ->
                    println "• ${v.nome} (${v.estado}, ${v.pais}) — Empresa ID: ${v.empresa_id}"
                }
            }
        } catch (Exception e) {
            println "Erro ao listar vagas: ${e.message}"
        }
    }

    static void removerVagaPorNome(String nome) {
        if (!nome || nome.trim().isEmpty()) {
            println "O nome da vaga não pode estar vazio."
            return
        }

        try {
            def vaga = buscarPorNome(nome)
            if (vaga) {
                VagasService.removerVaga(vaga.id)
                println "Vaga '${nome}' removida com sucesso."
            } else {
                println "Nenhuma vaga encontrada com o nome '${nome}'."
            }
        } catch (Exception e) {
            println "Erro ao remover vaga: ${e.message}"
        }
    }

    static def buscarPorNome(String nome) {
        try {
            def lista = VagasService.listarVagas()
            return lista.find { it.nome.equalsIgnoreCase(nome) }
        } catch (Exception e) {
            println "Erro ao buscar vaga: ${e.message}"
        }
    }
}
