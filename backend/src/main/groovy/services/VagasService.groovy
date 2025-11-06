package services

import dao.VagasDAO
import dao.Conexao
import model.Vagas

class VagasService {

    static void adicionarVaga(Vagas vaga) throws Exception {
        try {
            executarComDAO { VagasDAO dao ->
                dao.inserir(vaga)
            }
        } catch (Exception e) {
            throw e
        }
    }

    static List<Vagas> listarVagas() throws Exception {
        try {
            return executarComDAO { VagasDAO dao ->
                dao.listarTodos()
            } as List<Vagas>
        } catch (Exception e) {
            throw e
        }
    }

    static void removerVaga(int id) throws Exception {
        try {
            executarComDAO { VagasDAO dao ->
                dao.deletar(id)
            }
        } catch (Exception e) {
            throw e
        }
    }

    private static <T> T  executarComDAO(Closure operacao) throws Exception {
        try {
            return Conexao.withConnection { sql ->
                def dao = new VagasDAO(sql)
                return operacao(dao)
            }
        } catch (Exception e) {
            throw e
        }
    }
}
