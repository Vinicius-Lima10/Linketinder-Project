package services

import dao.CandidatoDAO
import dao.Conexao
import model.Candidato

class CandidatoService {

    static void adicionarCandidato(Candidato candidato) throws Exception {
        try {
             executarComDAO { CandidatoDAO dao ->
                dao.inserir(candidato)
            }
        } catch (Exception e) {
            throw e
        }
    }

    static List<Candidato> listarCandidatos() throws Exception {
        try {
            return executarComDAO { CandidatoDAO dao ->
                dao.listarTodos()
            } ?: []
        } catch (Exception e) {
            throw e
        }
    }

    static void removerCandidato(int id) throws Exception{
        try {
            executarComDAO { CandidatoDAO dao ->
                dao.deletar(id)
            }
        } catch (Exception e) {
            throw e
        }
    }

    private static <T> T  executarComDAO(Closure operacao) throws Exception {
        try {
            return Conexao.withConnection { sql ->
                def dao = new CandidatoDAO(sql)
                return operacao(dao)
            }
        } catch (Exception e) {
            throw e
        }
    }
}
