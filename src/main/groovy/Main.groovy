import java.util.Scanner

println "Bem-vindo ao Linketinder"
def scanner = new Scanner(System.in)

while (true) {
    println """
    =============Menu=============
    [1] Listar todos os candidatos
    [2] Listar todas as empresas
    [3] Adicionar candidato
    [4] Remover candidato
    [5] Adicionar empresa
    [6] Remover empresa
    [0] Sair
    ==============================
"""
    print "Escolha uma opção: "
    def opcao = scanner.nextLine()

    switch (opcao) {
        case '1':
            BancoDeUsuarios.listarCandidatos()
            break
        case '2':
            BancoDeUsuarios.listarEmpresas()
            break
        case '3':
            println "Digite o nome do candidato:"
            def nome = scanner.nextLine()
            println "Digite o CPF:"
            def cpf = scanner.nextLine()
            println "Digite a idade:"
            def idade = 0
            try {
                idade = scanner.nextLine().toInteger()
            } catch(Exception e) {
                println "Idade inválida. Definindo 0."
                idade = 0
            }
            println "Digite o email:"
            def email = scanner.nextLine()
            println "Digite o estado:"
            def estado = scanner.nextLine()
            println "Digite o CEP:"
            def cep = scanner.nextLine()
            println "Digite a descrição:"
            def descricao = scanner.nextLine()
            println "Digite competências separadas por vírgula:"
            def competencias = scanner.nextLine().split(",")*.trim()

            def candidato = new Candidato(
                    nome: nome, cpf: cpf, idade: idade, email: email,
                    estado: estado, cep: cep, descricao: descricao,
                    competencias: competencias
            )
            BancoDeUsuarios.adicionarCandidato(candidato)
            println "Candidato adicionado com sucesso!"
            break
        case '4':
            println "Digite o CPF do candidato a remover:"
            def cpf = scanner.nextLine()
            def candidato = BancoDeUsuarios.candidatos.find { it.cpf == cpf }
            if (candidato) {
                BancoDeUsuarios.removerCandidato(candidato)
                println "Candidato removido com sucesso!"
            } else {
                println "Nenhum candidato encontrado com esse CPF."
            }
            break
        case '5':
            println "Digite o nome da empresa:"
            def nome = scanner.nextLine()
            println "Digite o CNPJ:"
            def cnpj = scanner.nextLine()
            println "Digite o email:"
            def email = scanner.nextLine()
            println "Digite o país:"
            def pais = scanner.nextLine()
            println "Digite o estado:"
            def estado = scanner.nextLine()
            println "Digite o CEP:"
            def cep = scanner.nextLine()
            println "Digite a descrição:"
            def descricao = scanner.nextLine()
            println "Digite competências separadas por vírgula:"
            def competencias = scanner.nextLine().split(",")*.trim()

            def empresa = new Empresa(
                    nome: nome, cnpj: cnpj, email: email, pais: pais,
                    estado: estado, cep: cep, descricao: descricao,
                    competencias: competencias
            )
            BancoDeUsuarios.adicionarEmpresa(empresa)
            println "✅ Empresa adicionada com sucesso!"
            break
        case '6':
            println "Digite o CNPJ da empresa a remover:"
            def cnpj = scanner.nextLine()
            def empresa = BancoDeUsuarios.empresas.find { it.cnpj == cnpj }
            if (empresa) {
                BancoDeUsuarios.removerEmpresa(empresa)
                println "✅ Empresa removida com sucesso!"
            } else {
                println "⚠️ Nenhuma empresa encontrada com esse CNPJ."
            }
            break
        case '0':
            println "Encerrando o programa..."
            return
        default:
            println "❌ Opção inválida, tente novamente..."
            break
    }

    println "\nPressione Enter para continuar..."
    scanner.nextLine()
}
