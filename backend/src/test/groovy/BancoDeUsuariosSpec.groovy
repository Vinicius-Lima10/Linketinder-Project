import dao.CandidatoDAO
import dao.EmpresaDAO
import dao.Conexao
import model.Candidato
import model.Empresa
import spock.lang.Specification
import java.time.LocalDate

class BancoDeUsuariosSpec extends Specification {

//    def setup() {
//        Conexao.withConnection { sql ->
//            sql.execute("TRUNCATE TABLE formacaocandidato, candidatocompetencias, formacoes, candidato, empresas RESTART IDENTITY CASCADE")
//        }
//    }

    def "Deve inserir e remover um novo candidato no banco"() {
        Conexao.withConnection { sql ->
            def dao = new CandidatoDAO(sql)
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

            when: "insere no banco"
            def idInserido = dao.inserir(candidato)

            then: "deve ser possível listar e encontrar"
            def lista = dao.listarTodos()
            lista.any { it.id == idInserido }

            when: "remove o candidato"
            dao.remover(idInserido)

            then: "não deve estar mais na lista"
            !dao.listarTodos().any { it.id == idInserido }
        }
    }

    def "Deve inserir e remover uma nova empresa no banco"() {
        Conexao.withConnection { sql ->
            def dao = new EmpresaDAO(sql)
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

            when: "insere no banco"
            def idInserido = dao.inserir(empresa)

            then: "deve estar na lista"
            dao.listarTodos().any { it.id == idInserido }

            when: "remove"
            dao.remover(idInserido)

            then: "não deve estar mais presente"
            !dao.listarTodos().any { it.id == idInserido }
        }
    }
}
