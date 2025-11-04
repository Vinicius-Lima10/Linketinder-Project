package services

import dao.VagasDAO
import dao.Conexao
import model.Vagas

class VagasService {

    static void adicionarVaga(Vagas vaga) {
        executarComDAO { dao ->
            dao.inserir(vaga)
            println "Vaga adicionada com sucesso."
        }
    }

    static List<Vagas> listarVagas() {
        return executarComDAO { dao ->
            dao.listarTodos()
        } ?: []
    }

    static void removerVaga(int id) {
        executarComDAO { dao ->
            dao.deletar(id)
            println "Vaga removida com sucesso."
        }
    }


    private static <T> T executarComDAO(Closure<T> operacao) {
        try {
            return Conexao.withConnection { sql ->
                def dao = new VagasDAO(sql)
                return operacao(dao)
            }
        } catch (Exception ex) {
            println "${ex.message}"
        }
    }
}
