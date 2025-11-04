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

    private static def executarComDAO(Closure operacao) {
        try {
            return Conexao.withConnection { sql ->
                def dao = new EmpresaDAO(sql)
                return operacao(dao)
            }
        } catch (Exception e) {
            println "Erro de conexão ou operação no banco: ${e.message}"
            return null
        }
    }

    static void adicionarEmpresa(Empresa empresa) {
        executarComDAO { dao ->
            dao.inserir(empresa)
            println "Empresa '${empresa.nome}' adicionada com sucesso."
        }
    }

    static List<Empresa> listarEmpresas() {
        return executarComDAO { dao ->
            dao.listarTodos()
        } ?: []
    }

    static void removerEmpresa(int id) {
        executarComDAO { dao ->
            dao.deletar(id)
            println "Empresa ID ${id} removida com sucesso."
        }
    }

    static void atualizarCampo(int id, String campo, Object novoValor) {
        executarComDAO { dao ->
            dao.atualizarCampo(id, campo, novoValor)
        }
    }
}
