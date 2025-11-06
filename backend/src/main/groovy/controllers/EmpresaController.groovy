package controllers

import model.Empresa
import services.EmpresaService

class EmpresaController {

    static void adicionarEmpresa(Empresa empresa) throws Exception {
        try {
            EmpresaService.adicionarEmpresa(empresa)
        } catch (Exception e) {
            throw e
        }
    }

    static List<Empresa> listarEmpresas() throws Exception {
        try {
            return EmpresaService.listarEmpresas()
        } catch (Exception e) {
            throw e
        }
    }

    static void removerEmpresa(int id) throws Exception {
        try {
            EmpresaService.removerEmpresa(id)
        } catch (Exception e) {
            throw e
        }
    }

    static void atualizarCampo(int id, String campo, Object novoValor) throws Exception {
        try {
            EmpresaService.atualizarCampo(id, campo, novoValor)
        } catch (Exception e) {
            throw e
        }
    }

    static void removerEmpresaPorCNPJ(String cnpj) throws Exception {
        try {
            int empresa_id = buscarIDPorCNPJ(cnpj)
            removerEmpresa(empresa_id)
        } catch (Exception e) {
            throw e
        }
    }

    static Integer buscarIDPorCNPJ(String cnpj) throws Exception {
        try {
            List<Empresa> empresas = EmpresaService.listarEmpresas() as List<Empresa>
            Empresa empresa = empresas.find { it.cnpj == cnpj }
            if (!empresa) throw new Exception()
            return empresa.id
        } catch (Exception e) {
            throw e
        }
    }
}
