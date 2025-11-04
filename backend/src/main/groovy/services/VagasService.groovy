package services

import dao.VagasDAO
import dao.Conexao
import model.Vagas

class VagasService {

    static void adicionarVaga(Vagas vaga) {
        try {
            if (!vaga?.nome || !vaga?.descricao) {
                println "Nome e descrição são obrigatórios."
                return
            }

            Conexao.withConnection { sql ->
                def dao = new VagasDAO(sql)
                dao.inserir(vaga)
            }

            println "Vaga adicionada com sucesso."
        } catch (Exception ex) {
            println "Erro ao adicionar vaga: ${ex.message}"
        }
    }

    static List<Vagas> listarVagas() {
        try {
            Conexao.withConnection { sql ->
                def dao = new VagasDAO(sql)
                return dao.listarTodos()
            }
        } catch (Exception ex) {
            println "Erro ao listar vagas: ${ex.message}"
            return []
        }
    }

    static void removerVaga(int id) {
        try {
            Conexao.withConnection { sql ->
                def dao = new VagasDAO(sql)
                dao.deletar(id)
            }
        } catch (Exception ex) {
            println "Erro ao remover vaga: ${ex.message}"
        }
    }

}
