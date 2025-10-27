package groovy

import dao.CandidatoDAO
import dao.EmpresaDAO
import dao.VagasDAO
import model.Candidato
import model.Empresa
import model.Vagas
import spock.lang.Specification
import java.time.LocalDate
import dao.Conexao
import services.MetodosTeste

class DaoSpec extends Specification {

    def setup() {
        Conexao.withConnection { sql ->
            sql.execute("DELETE FROM formacaocandidato")
            sql.execute("DELETE FROM candidatocompetencias")
            sql.execute("DELETE FROM vagacompetencias")
            sql.execute("DELETE FROM formacoes")
            sql.execute("DELETE FROM candidato")
            sql.execute("DELETE FROM vagas")
            sql.execute("DELETE FROM empresas")
        }
    }

    def "Testar CRUD da EmpresaDAO"() {
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
        MetodosTeste.testarCRUD(EmpresaDAO, empresa, 'nome')
    }

    def "Testar CRUD do CandidatoDAO"() {
        def candidato = new Candidato(
                nome: 'João',
                sobrenome: 'Silva',
                data_de_nascimento: LocalDate.of(1992, 10, 20).toString(),
                senha: 'senha123',
                cpf: '11122233344',
                idade: 32,
                email: 'joao@email.com',
                estado: 'RJ',
                pais: 'Brasil',
                cep: '01000-111',
                descricao: 'Desenvolvedor Groovy',
                formacao: ['Ciência da Computação'],
                competencias: ['Groovy', 'PostgreSQL'],
                telefone: '(11)98765-4321',
                linkedin: 'linkedin.com/in/joaosilva'
        )
        MetodosTeste.testarCRUD(CandidatoDAO, candidato, 'cpf', '11122233344')
    }

    def "Testar CRUD do VagasDAO"() {
        Conexao.withConnection { sql ->
            def empresaDAO = new EmpresaDAO(sql)
            def dummyEmpresa = new Empresa(
                    nome: 'DummyVagaComp',
                    cnpj: '00.000.000/0000-00',
                    email: 'dummy@vaga.com',
                    senha: 'pass',
                    pais: 'US',
                    estado: 'NY',
                    cep: '10001',
                    descricao: 'Dummy for VagasDAO test'
            )
            empresaDAO.inserir(dummyEmpresa)
            def empresaId = empresaDAO.listarTodos().find { it.nome == 'DummyVagaComp' }.id

            def vaga = new Vagas(
                    nome: 'Programador',
                    descricao: 'Frontend Senior',
                    endereco: 'Rua das Américas',
                    cidade: 'Rio de Janeiro',
                    estado: 'RJ',
                    pais: 'Brasil',
                    empresa_id: empresaId,
                    competencias: ['HTML', 'CSS', 'JS']
            )

            MetodosTeste.testarCRUD(VagasDAO, vaga, 'nome', 'Programador')
        }
    }
}
