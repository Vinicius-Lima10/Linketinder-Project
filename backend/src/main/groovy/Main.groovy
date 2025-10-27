import model.Candidato
import model.Empresa
import model.Vagas

import java.util.Scanner
import java.time.LocalDate

println "Bem-vindo ao Linketinder"
def scanner = new Scanner(System.in)

while (true) {
    println """
    =============Menu=============
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
    ==============================
"""

    println "Escolha uma opção: "
    def opcao = scanner.nextLine()

    switch (opcao) {
        case '1':
            BancoDeUsuarios.listarCandidatos()
            break
        case '2':
            BancoDeUsuarios.listarEmpresas()
            break
        case '3':
            println "Digite o nome:"
            def nome = scanner.nextLine()
            println "Digite o sobrenome:"
            def sobrenome = scanner.nextLine()
            println "Digite a data de nascimento (AAAA-MM-DD):"
            def data_de_nascimento = scanner.nextLine()
            println "Defina a senha:"
            def senha = scanner.nextLine()
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
            println "Digite o país:"
            def pais = scanner.nextLine()
            println "Digite o CEP:"
            def cep = scanner.nextLine()
            println "Digite a descrição:"
            def descricao = scanner.nextLine()
            println "Digite o telefone:"
            def telefone = scanner.nextLine()
            println "Digite a(s) formação(ões) separada(s) por vírgula:"
            def formacao = scanner.nextLine().split(",")*.trim()
            println "Digite as competências separadas por vírgula:"
            def competencias = scanner.nextLine().split(",")*.trim()
            println "Digite o link do LinkedIn:"
            def linkedin = scanner.nextLine()

            def candidato = new Candidato(
                    nome: nome, sobrenome: sobrenome, data_de_nascimento: data_de_nascimento,
                    senha: senha, cpf: cpf, idade: idade, email: email, estado: estado,
                    pais: pais, cep: cep, descricao: descricao, telefone: telefone,
                    formacao: formacao, competencias: competencias, linkedin: linkedin
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
            println "Defina a senha:"
            def senha = scanner.nextLine()
            println "Digite o país:"
            def pais = scanner.nextLine()
            println "Digite o estado:"
            def estado = scanner.nextLine()
            println "Digite o CEP:"
            def cep = scanner.nextLine()
            println "Digite a descrição:"
            def descricao = scanner.nextLine()


            def empresa = new Empresa(
                    nome: nome, cnpj: cnpj, email: email, senha: senha, pais: pais,
                    estado: estado, cep: cep, descricao: descricao
            )
            BancoDeUsuarios.adicionarEmpresa(empresa)
            println "Empresa adicionada com sucesso!"
            break
        case '6':
            println "Digite o CNPJ da empresa a remover:"
            def cnpj = scanner.nextLine()
            def empresa = BancoDeUsuarios.empresas.find { it.cnpj == cnpj }
            if (empresa) {
                BancoDeUsuarios.removerEmpresa(empresa)
                println "Empresa removida com sucesso!"
            } else {
                println "⚠Nenhuma empresa encontrada com esse CNPJ."
            }
            break
        case '7':
            println "Digite o CPF do candidato:"
            def cpf = scanner.nextLine()
            def candidato = BancoDeUsuarios.candidatos.find { it.cpf == cpf }

            if (!candidato) {
                println "Candidato não encontrado."
                break
            }

            println "Digite o nome da vaga que ele quer curtir:"
            def nomeVaga = scanner.nextLine()
            def vaga = BancoDeVagas.vagas.find { it.nome == nomeVaga }

            if (!vaga) {
                println "Vaga não encontrada."
                break
            }

            GerenciadorDeCurtidas.candidatoCurteVaga(candidato, vaga)
            break

        case '8':
            println "Digite o CNPJ da empresa:"
            def cnpj = scanner.nextLine()
            def empresa = BancoDeUsuarios.empresas.find { it.cnpj == cnpj }

            if (!empresa) {
                println "Empresa não encontrada."
                break
            }

            println "Digite o CPF do candidato que a empresa curtiu:"
            def cpfCandidato = scanner.nextLine()
            def candidato = BancoDeUsuarios.candidatos.find { it.cpf == cpfCandidato }

            println "Digite o nome da vaga relacionada:"
            def nomeVaga = scanner.nextLine()
            def vaga = BancoDeVagas.vagas.find { it.nome == nomeVaga }

            GerenciadorDeCurtidas.empresaCurteCandidato(empresa, candidato, vaga)
            break

        case '9':
            GerenciadorDeCurtidas.listarCurtidas()
            break
        case '10':
            try {
                println "Digite o ID da empresa responsável (ou 0 se desconhecido):"
                def empresaId = scanner.nextLine().toInteger()
            } catch (Exception ex) {
                println "ID não encontrado"
                break
            }
            println "Digite o nome da vaga:"
            def nome = scanner.nextLine()
            println "Digite a descrição da vaga:"
            def descricao = scanner.nextLine()
            println "Digite o endereço:"
            def endereco = scanner.nextLine()
            println "Digite a cidade:"
            def cidade = scanner.nextLine()
            println "Digite o estado:"
            def estado = scanner.nextLine()
            println "Digite o país:"
            def pais = scanner.nextLine()
            println "Digite as competências da vaga (separadas por vírgula):"
            def competencias = scanner.nextLine().split(",")*.trim()

            def vaga = new Vagas(
                    nome: nome,
                    descricao: descricao,
                    endereco: endereco,
                    cidade: cidade,
                    estado: estado,
                    pais: pais,
                    empresa_id: empresaId,
                    competencias: competencias
            )
            BancoDeVagas.adicionarVaga(vaga)
            println "Vaga criada com sucesso!"
            break

        case '11':
            BancoDeVagas.listarVagas()
            break
        case '0':
            println "Encerrando o programa..."
            return
        default:
            println "Opção inválida, tente novamente..."
            break
    }

    println "\nPressione Enter para continuar..."
    scanner.nextLine()
}