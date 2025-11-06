package services

import dao.CurtidaDAO
import dao.MatchDAO
import dao.Conexao
import model.Candidato
import model.Curtida
import model.Empresa
import model.Vagas

class CurtidaService {

    static void candidatoCurteVaga(Candidato candidato, Vagas vaga) throws Exception{
        try {
            executarComDAO { curtidaDAO, matchDAO ->
                if (!candidato || !vaga) throw Exception()

                curtidaDAO.registrarCurtidaCandidato(candidato.id, vaga.id)

                if (curtidaDAO.verificarCurtidaEmpresa(candidato.id, vaga.id)) {
                    matchDAO.registrarMatch(vaga.empresa.id, candidato.id, vaga.id)
                }
            }
        } catch (Exception e) {
            throw e
        }
    }

    static void empresaCurteCandidato(Empresa empresa, Candidato candidato, Vagas vaga)throws Exception {
        try {
            executarComDAO { curtidaDAO, matchDAO ->
                if (!empresa || !candidato || !vaga) throw Exception()

                curtidaDAO.registrarCurtidaEmpresa(empresa.id, candidato.id, vaga.id)

                if (curtidaDAO.verificarCurtidaCandidato(candidato.id, vaga.id)) {
                    matchDAO.registrarMatch(empresa.id, candidato.id, vaga.id)
                }
            }
        } catch (Exception e) {
            throw e
        }
    }

    static List<Curtida> listarCurtidas() throws Exception{
        try {
            executarComDAO { curtidaDAO, _ ->
                curtidaDAO.listarCurtidas() ?: []
            }
        } catch (Exception e) {
            throw e
        }
    }

    private static <T> T executarComDAO(Closure<T> operacao) throws Exception{
        try {
            return Conexao.withConnection { sql ->
                def curtidaDAO = new CurtidaDAO(sql)
                def matchDAO = new MatchDAO(sql)
                return operacao(curtidaDAO, matchDAO)
            }
        } catch (Exception e) {
            throw e
        }
    }
}
