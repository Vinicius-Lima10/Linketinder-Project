package services

import dao.CurtidaDAO
import dao.MatchDAO
import dao.Conexao
import model.Candidato
import model.Empresa
import model.Vagas

class CurtidaService {

    static void candidatoCurteVaga(Candidato candidato, Vagas vaga) {
        executarComDAO { curtidaDAO, matchDAO ->
            if (!candidato || !vaga) {
                println "Dados inválidos."
                return
            }
            curtidaDAO.registrarCurtidaCandidato(candidato.id, vaga.id)
            println "${candidato.nome} curtiu a vaga ${vaga.nome}"

            if (curtidaDAO.verificarCurtidaEmpresa(candidato.id, vaga.id)) {
                matchDAO.registrarMatch(vaga.empresa.id, candidato.id, vaga.id)
                println "Match entre ${candidato.nome} e ${vaga.empresa.nome}"
            }
        }
    }

    static void empresaCurteCandidato(Empresa empresa, Candidato candidato, Vagas vaga) {
        executarComDAO { curtidaDAO, matchDAO ->
            if (!empresa || !candidato || !vaga) {
                println "Dados inválidos."
                return
            }

            curtidaDAO.registrarCurtidaEmpresa(empresa.id, candidato.id, vaga.id)
            println "${empresa.nome} curtiu ${candidato.nome}"

            if (curtidaDAO.verificarCurtidaCandidato(candidato.id, vaga.id)) {
                matchDAO.registrarMatch(empresa.id, candidato.id, vaga.id)
                println "Match entre ${empresa.nome} e ${candidato.nome}"
            }
        }
    }

    static List listarCurtidas() {
        return []
    }

    private static <T> T executarComDAO(Closure<T> operacao) {
        try {
            return Conexao.withConnection { sql ->
                def curtidaDAO = new CurtidaDAO(sql)
                def matchDAO = new MatchDAO(sql)
                return operacao(curtidaDAO, matchDAO)
            }
        } catch (Exception ex) {
            println "Erro no serviço de curtidas: ${ex.message}"
        }
    }
}
