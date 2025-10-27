import model.Candidato
import model.Empresa
import services.BancoDeUsuarios
import spock.lang.*
import java.time.LocalDate

class BancoDeUsuariosSpec extends Specification {

    def setup() {
        BancoDeUsuarios.candidatos.clear()
        BancoDeUsuarios.empresas.clear()
    }

    def "Deve inserir e remover um novo candidato"() {
        given: "um novo candidato válido"
        def candidato = new Candidato(
                nome: 'João2',
                sobrenome: 'Silva',
                data_de_nascimento: LocalDate.of(1992, 10, 20).toString(),
                senha: 'dfg',
                cpf: '11122244477',
                idade: 32,
                email: 'joao2@email.com',
                estado: 'RJ',
                pais: 'Brasil',
                cep: '01000-111',
                descricao: 'Desenvolvedor Groovy',
                formacao: ['Ciência da Computação', 'Sistemas de Informação'],
                competencias: ['Groovy', 'PostgreSQL', 'Spring Boot'],
                telefone: '(11)98765-4321',
                linkedin: 'linkedin.com/in/jose'
        )

        and: "o tamanho atual de candidatos"
        def tamanhoAntes = BancoDeUsuarios.candidatos.size()

        when: "o candidato é adicionado"
        BancoDeUsuarios.adicionarCandidato(candidato)

        then: "a lista de candidatos aumenta em 1"
        BancoDeUsuarios.candidatos.size() == tamanhoAntes + 1
        BancoDeUsuarios.candidatos.contains(candidato)

        when: "o candidato é removido"
        BancoDeUsuarios.removerCandidato(candidato)

        then: "a lista volta ao tamanho original"
        BancoDeUsuarios.candidatos.size() == tamanhoAntes
        !BancoDeUsuarios.candidatos.contains(candidato)
    }

    def "Deve inserir e remover uma nova empresa"() {
        given: "uma nova empresa válida"
        def empresa = new Empresa(
                nome: 'TechCorp',
                cnpj: '12.345.678/0001-99',
                email: 'contato@techcorp.com',
                senha: '123456',
                pais: 'Brasil',
                estado: 'SP',
                cep: '01000-000',
                descricao: 'Empresa de tecnologia'
        )

        and: "o tamanho atual de empresas"
        def tamanhoAntes = BancoDeUsuarios.empresas.size()

        when: "a empresa é adicionada"
        BancoDeUsuarios.adicionarEmpresa(empresa)

        then: "a lista de empresas aumenta em 1"
        BancoDeUsuarios.empresas.size() == tamanhoAntes + 1
        BancoDeUsuarios.empresas.contains(empresa)

        when: "a empresa é removida"
        BancoDeUsuarios.removerEmpresa(empresa)

        then: "a lista volta ao tamanho original"
        BancoDeUsuarios.empresas.size() == tamanhoAntes
        !BancoDeUsuarios.empresas.contains(empresa)
    }
}
