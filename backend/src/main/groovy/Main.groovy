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
            try {
                def candidatos = CandidatoController.listarCandidatos()
                candidatos.each { it.exibirPerfil() }
            } catch (Exception e) {
                println "Erro ao listar candidatos."
            }
            break

        case '2':
            try {
                List<Empresa> empresas = EmpresaController.listarEmpresas()
                if (empresas.isEmpty()) {
                    println "Nenhuma empresa cadastrada."
                } else {
                    println "\n=== Lista de empresas ==="
                    empresas.each { it.exibirPerfil() }
                }
            } catch (Exception e) {
                println "Erro ao listar empresas."
            }
            break

        case '3':
            try {
                println "=== Cadastro de Candidato ==="
                println "Nome: "; def nome = scanner.nextLine()
                println "Sobrenome: "; def sobrenome = scanner.nextLine()
                println "Data de nascimento (AAAA-MM-DD): "; def data = scanner.nextLine()
                println "Senha: "; def senha = scanner.nextLine()
                println "CPF: "; def cpf = scanner.nextLine()
                println "Idade: "; int idade = scanner.nextLine() as int
                println "Email: "; def email = scanner.nextLine()
                println "Estado: "; def estado = scanner.nextLine()
                println "País: "; def pais = scanner.nextLine()
                println "CEP: "; def cep = scanner.nextLine()
                println "Descrição: "; def descricao = scanner.nextLine()
                println "Telefone: "; def telefone = scanner.nextLine()
                println "Formações (separadas por vírgula): "; def formacao = scanner.nextLine().split(",")*.trim()
                println "Competências (separadas por vírgula): "; def competencias = scanner.nextLine().split(",")*.trim()
                println "LinkedIn: "; def linkedin = scanner.nextLine()

                def candidato = new Candidato(
                        nome: nome, sobrenome: sobrenome, data_de_nascimento: data,
                        senha: senha, cpf: cpf, idade: idade, email: email,
                        estado: estado, pais: pais, cep: cep, descricao: descricao,
                        telefone: telefone, formacao: formacao, competencias: competencias,
                        linkedin: linkedin
                )
                CandidatoController.adicionarCandidato(candidato)
                println "Candidato inserido com sucesso!"
            } catch (Exception e) {
                println "Erro ao adicionar candidato."
            }
            break

        case '4':
            try {
                println "CPF do candidato a remover: "
                def cpfRemover = scanner.nextLine()
                CandidatoController.removerCandidatoPorCPF(cpfRemover)
                println "Candidato removido com sucesso!"
            } catch (Exception e) {
                println "Erro ao remover candidato."
            }
            break

        case '5':
            try {
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
                println "Empresa inserida com sucesso!"
            } catch (Exception e) {
                println "Erro ao adicionar empresa."
            }
            break

        case '6':
            try {
                println "CNPJ da empresa a remover: "
                def cnpjRemover = scanner.nextLine()
                EmpresaController.removerEmpresaPorCNPJ(cnpjRemover)
                println "Empresa removida com sucesso!"
            } catch (Exception e) {
                println "Erro ao remover empresa."
            }
            break

        case '7':
            try {
                println "CPF do candidato: "
                def cpfCandidato = scanner.nextLine()
                def candidato = CandidatoController.buscarPorCPF(cpfCandidato)
                if (!candidato) {
                    println "Candidato não encontrado."
                    break
                }

                println "Nome da vaga a curtir: "
                def nomeVaga = scanner.nextLine()
                def vaga = VagaController.buscarPorNome(nomeVaga)
                if (!vaga) {
                    println "Vaga não encontrada."
                    break
                }

                CurtidaController.candidatoCurteVaga(candidato, vaga)
                println "O candidato ${candidato.nome} curtiu a vaga ${vaga.nome}!"
            } catch (Exception e) {
                println "Erro ao processar curtida do candidato."
            }
            break

        case '8':
            try {
                println "CNPJ da empresa: "
                def cnpjEmpresa = scanner.nextLine()
                def empresa = EmpresaController.buscarIDPorCNPJ(cnpjEmpresa)
                if (!empresa) {
                    println "Empresa não encontrada."
                    break
                }

                println "CPF do candidato: "
                def cpfCand = scanner.nextLine()
                def candidato = CandidatoController.buscarPorCPF(cpfCand)
                if (!candidato) {
                    println "Candidato não encontrado."
                    break
                }

                println "Nome da vaga relacionada: "
                def nomeVaga = scanner.nextLine()
                def vaga = VagaController.buscarPorNome(nomeVaga)
                if (!vaga) {
                    println "Vaga não encontrada."
                    break
                }

                CurtidaController.empresaCurteCandidato(empresa, candidato, vaga)
                println "A empresa ${empresa.nome} curtiu o candidato ${candidato.nome} na vaga ${vaga.nome}"
            } catch (Exception e) {
                println "Erro ao processar curtida da empresa."
            }
            break

        case '9':
            try {
                println CurtidaController.listarCurtidas()
            } catch (Exception e) {
                println "Erro ao listar curtidas."
            }
            break

        case '10':
            try {
                println "=== Criação de Vaga ==="
                println "CNPJ da empresa responsável: "
                def cnpjVaga = scanner.nextLine()

                def empresa = EmpresaController.buscarIDPorCNPJ(cnpjVaga)
                if (!empresa) {
                    println "Empresa não encontrada."
                    break
                }

                println "Nome da vaga: "; def nomeVaga = scanner.nextLine()
                println "Descrição: "; def descricaoVaga = scanner.nextLine()
                println "Endereço: "; def endereco = scanner.nextLine()
                println "Cidade: "; def cidade = scanner.nextLine()
                println "Estado: "; def estadoVaga = scanner.nextLine()
                println "País: "; def paisVaga = scanner.nextLine()
                println "Competências (separadas por vírgula): "
                def competenciasVaga = scanner.nextLine().split(",")*.trim()

                def vaga = new Vagas(
                        nome: nomeVaga,
                        descricao: descricaoVaga,
                        endereco: endereco,
                        cidade: cidade,
                        estado: estadoVaga,
                        pais: paisVaga,
                        competencias: competenciasVaga,
                        empresa_id: empresa
                )

                VagaController.adicionarVaga(vaga)
                println "Vaga ${vaga.nome} adicionada com sucesso!"
            } catch (Exception e) {
                println "Erro ao criar vaga."
            }
            break

        case '11':
            try {
                println VagaController.listarVagas()
            } catch (Exception e) {
                println "Erro ao listar vagas."
            }
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
