class BancoDeVagas {
    static List<Vagas> vagas = []

    static void adicionarVaga(Vagas vaga) {
        vagas << vaga
    }

    static void listarVagas() {
        if (vagas.isEmpty()) {
            println "Nenhuma vaga cadastrada ainda."
        } else {
            vagas.forEach { v ->
                println "${v.nome} (${v.estado})"
            }
        }
    }
}
