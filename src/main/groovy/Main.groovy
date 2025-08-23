println "Bem-vindo ao Linketinder"
def scanner = new Scanner(System.in)
while(true) {
    println """
    =============Menu=============
    [1] Listar todos os candidatos
    [2] Listar todas as empresas
    [0] Sair
    ==============================
"""
    println "Escolha uma opção:\n"
    def opcao = scanner.nextLine()
    switch (opcao) {
        case '1':
            listarCandidatos()
        break
        case '2':
            listarEmpresas()
        break
        case '0':
            return
        break
        default:
            println "Erro: Opção inválida, tente novamente... \n"
            break
    }
    println "Pressione Enter para continuar"
    scanner.nextLine()

}

def listarCandidatos() {
    BancoDeUsuarios.candidatos.each{it.exibirPerfil()}
}


def listarEmpresas() {
    BancoDeUsuarios.empresas.each{it.exibirPerfil()}
}

