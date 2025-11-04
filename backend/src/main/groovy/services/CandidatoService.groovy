package services

import dao.CandidatoDAO
import dao.Conexao
import model.Candidato
import model.Empresa

class CandidatoService {

    static void adicionarCandidato(Candidato candidato) {
        executarComDAO { dao ->
            dao.inserir(candidato)
        }
    }

    static List<Candidato> listarCandidatos() {
        return executarComDAO { dao ->
            dao.listarTodos()
        } ?: []
    }

    static void removerCandidato(int id) {
        executarComDAO { dao ->
            dao.deletar(id)
        }
    }

    List<Empresa> listarEmpresas() {
        try {
            return empresaDAO?.listarTodos() ?: []
        } catch (Exception e) {
            println "Erro ao listar empresas: ${e.message}"
            return []
        }
    }

    private static <T> T executarComDAO(Closure<T> operacao) {
        try {
            return Conexao.withConnection { sql ->
                def dao = new CandidatoDAO(sql)
                return operacao(dao)
            }
        } catch (Exception ex) {
            println "Erro no serviço de candidato: ${ex.message}"
        }
    }
}
