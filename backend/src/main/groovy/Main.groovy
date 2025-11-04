import controllers.CandidatoController
import controllers.CurtidaController
import controllers.EmpresaController
import controllers.VagaController
import model.Candidato
import model.Empresa
import model.Vagas

println "=== Bem-vindo ao Linketinder ==="
def scanner = new Scanner(System.in)

while (true) {
    println """
    ========== Menu ===========
    [1] Listar candidatos
    [2] Listar empresas
    [3] Adicionar candidato
    [4] Remover candidato
    [5] Adicionar empresa
    [6] Remover empresa
    [7] Candidato curte vaga
    [8] Empresa curte candidato
    [9] Listar curtidas
    [10] Criar vaga
    [11] Listar vagas
    [0] Sair
    =========================
    """

    print "Escolha uma opção: "
    def opcao = scanner.nextLine().trim()

    switch (opcao) {

        case '1':
            CandidatoController.listarCandidatos()
            break

        case '2':
            EmpresaController.listarEmpresas()
            break

        case '3':
            println "=== Cadastro de Candidato ==="
            println "Nome: "; def nome = scanner.nextLine()
            println  "Sobrenome: "; def sobrenome = scanner.nextLine()
            println  "Data de nascimento (AAAA-MM-DD): "; def data = scanner.nextLine()
            println  "Senha: "; def senha = scanner.nextLine()
            println  "CPF: "; def cpf = scanner.nextLine()
            println  "Idade: "; int idade = scanner.nextLine() as int
            println  "Email: "; def email = scanner.nextLine()
            println  "Estado: "; def estado = scanner.nextLine()
            println  "País: "; def pais = scanner.nextLine()
            println  "CEP: "; def cep = scanner.nextLine()
            println  "Descrição: "; def descricao = scanner.nextLine()
            println  "Telefone: "; def telefone = scanner.nextLine()
            println  "Formações (separadas por vírgula): "
            def formacao = scanner.nextLine().split(",")*.trim()
            println  "Competências (separadas por vírgula): "
            def competencias = scanner.nextLine().split(",")*.trim()
            println  "LinkedIn: "; def linkedin = scanner.nextLine()

            def candidato = new Candidato(
                    nome: nome, sobrenome: sobrenome, data_de_nascimento: data,
                    senha: senha, cpf: cpf, idade: idade, email: email,
                    estado: estado, pais: pais, cep: cep, descricao: descricao,
                    telefone: telefone, formacao: formacao, competencias: competencias,
                    linkedin: linkedin
            )
            CandidatoController.adicionarCandidato(candidato)
            break

        case '4':
            println "CPF do candidato a remover: "
            def cpf = scanner.nextLine()
            CandidatoController.removerCandidatoPorCPF(cpf)
            break

        case '5':
            println "=== Cadastro de Empresa ==="
            println "Nome: "; def nome = scanner.nextLine()
            println "CNPJ: "; def cnpj = scanner.nextLine()
            println "Email: "; def email = scanner.nextLine()
            println "Senha: "; def senha = scanner.nextLine()
            println "País: "; def pais = scanner.nextLine()
            println "Estado: "; def estado = scanner.nextLine()
            println "CEP: "; def cep = scanner.nextLine()
            println "Descrição: "; def descricao = scanner.nextLine()

            def empresa = new Empresa(
                    nome: nome, cnpj: cnpj, email: email, senha: senha,
                    pais: pais, estado: estado, cep: cep, descricao: descricao
            )
            EmpresaController.adicionarEmpresa(empresa)
            break

        case '6':
            println "CNPJ da empresa a remover: "
            def cnpj = scanner.nextLine()
            EmpresaController.removerEmpresaPorCNPJ(cnpj)
            break

        case '7':
            println "CPF do candidato: "
            def cpf = scanner.nextLine()
            def candidato = CandidatoController.buscarPorCPF(cpf)
            if (!candidato) {
                println "⚠ Candidato não encontrado."
                break
            }

            println "Nome da vaga a curtir: "
            def nomeVaga = scanner.nextLine()
            def vaga = VagaController.buscarPorNome(nomeVaga)
            if (!vaga) {
                println "⚠ Vaga não encontrada."
                break
            }

            CurtidaController.candidatoCurteVaga(candidato, vaga)
            break

        case '8':
            println "CNPJ da empresa: "
            def cnpj = scanner.nextLine()
            def empresa = EmpresaController.buscarPorCNPJ(cnpj)
            if (!empresa) {
                println "⚠ Empresa não encontrada."
                break
            }

            println "CPF do candidato: "
            def cpfCandidato = scanner.nextLine()
            def candidato = CandidatoController.buscarPorCPF(cpfCandidato)

            println "Nome da vaga relacionada: "
            def nomeVaga = scanner.nextLine()
            def vaga = VagaController.buscarPorNome(nomeVaga)

            CurtidaController.empresaCurteCandidato(empresa, candidato, vaga)
            break

        case '9':
            CurtidaController.listarCurtidas()
            break

        case '10':
            println "=== Criação de Vaga ==="
            println "CNPJ da empresa responsável: "
            def cnpj = scanner.nextLine()

            println "Nome da vaga: "; def nome = scanner.nextLine()
            println "Descrição: "; def descricao = scanner.nextLine()
            println "Endereço: "; def endereco = scanner.nextLine()
            println "Cidade: "; def cidade = scanner.nextLine()
            println "Estado: "; def estado = scanner.nextLine()
            println "País: "; def pais = scanner.nextLine()
            println "Competências (separadas por vírgula): "
            def competencias = scanner.nextLine().split(",")*.trim()

            def vaga = new Vagas(
                    nome: nome, descricao: descricao, endereco: endereco,
                    cidade: cidade, estado: estado, pais: pais,
                    competencias: competencias
            )

            VagaController.adicionarVaga(vaga, cnpj)
            break

        case '11':
            VagaController.listarVagas()
            break

        case '0':
            println "Encerrando o programa..."
            return

        default:
            println "Opção inválida, tente novamente."
            break
    }

    println "\nPressione Enter para continuar..."
    scanner.nextLine()
}
