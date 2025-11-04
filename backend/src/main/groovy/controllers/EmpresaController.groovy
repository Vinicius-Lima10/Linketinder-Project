package controllers

import model.Empresa
import services.EmpresaService

class EmpresaController {

    static void adicionarEmpresa(Empresa empresa) {
        EmpresaService.adicionarEmpresa(empresa)
    }

    static void listarEmpresas() {
        def lista = EmpresaService.listarEmpresas()
        if (lista.isEmpty()) {
            println "Nenhuma empresa cadastrada."
        } else {
            println "\n=== Lista de Empresas ==="
            lista.each { e ->
                println "• ${e.nome} ${e.cnpj} - ${e.email}, ${e.pais}"
            }
        }
    }

    static void removerEmpresa(int id) {
        EmpresaService.removerEmpresa(id)
    }

    static void atualizarCampo(int id, String campo, Object novoValor) {
        EmpresaService.atualizarCampo(id, campo, novoValor)
    }
    static void removerEmpresaPorCNPJ(String cnpj) {
        def empresa = buscarPorCNPJ(cnpj)
        if (empresa) {
            EmpresaService.removerEmpresa(empresa.id)
        } else {
            println "Nenhuma empresa encontrada com cnpj ${cnpj}"
        }
    }

    static def buscarPorCNPJ(String cnpj) {
        def lista = EmpresaService.listarEmpresas()
        return lista.find { it.cnpj == cnpj }
    }

}
