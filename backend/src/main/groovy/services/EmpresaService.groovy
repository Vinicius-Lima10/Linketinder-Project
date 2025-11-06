package services

import dao.Conexao
import dao.EmpresaDAO
import groovy.sql.Sql
import model.Empresa

class EmpresaService {

    Sql sql
    EmpresaDAO dao

    EmpresaService(Sql sql) {
        this.sql = sql
        this.dao = new EmpresaDAO(sql)
    }

    static void adicionarEmpresa(Empresa empresa) throws Exception {
        try {
            executarComDAO { EmpresaDAO dao ->
                dao.inserir(empresa)
            }
        } catch (Exception e) {
            throw e
        }
    }

    static List<Empresa> listarEmpresas() {
        try {
            return executarComDAO { EmpresaDAO dao ->
                dao.listarTodos()
            } ?: [] as List<Empresa>
        } catch (Exception e) {
            throw e
        }
    }

    static void removerEmpresa(int id) throws Exception{
        try {
            executarComDAO { EmpresaDAO dao ->
                dao.deletar(id)
            }
        } catch (Exception e) {
            throw e
        }
    }

    static void atualizarCampo(int id, String campo, Object novoValor) throws Exception {
        try {
            executarComDAO { EmpresaDAO dao ->
                dao.atualizarCampo(id, campo, novoValor)
            }
        } catch (Exception e) {
            throw e
        }
    }

    private static <T> T  executarComDAO(Closure operacao) throws Exception{
        try {
            return Conexao.withConnection { sql ->
                def dao = new EmpresaDAO(sql)
                return operacao(dao)
            }
        } catch (Exception e) {
            throw e
        }
    }
}
