package services

import model.Vagas

class BancoDeVagas {
    static List<Vagas> vagas = []

    static void adicionarVaga(Vagas vaga) {
        try {
            vagas << vaga
        } catch (Exception ex) {
            println "Erro ao inserir vaga"
        }
    }

    static void listarVagas() {
        if (vagas.isEmpty()) {
            println "Nenhuma vaga cadastrada ainda."
        } else {
            vagas.forEach { v ->
                println "Nome: ${v.nome} - Estado: (${v.estado}) "
            }
        }
    }
}
