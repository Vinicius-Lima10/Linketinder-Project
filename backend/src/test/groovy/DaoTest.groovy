import db.ConnectionFactory
import dao.CandidatoDAO
import dao.EmpresaDAO
import dao.VagasDAO
import model.Candidato
import model.Empresa
import model.Vagas

import java.time.LocalDate

Operacoes.withConnection { sql ->

    def empresaDAO = new EmpresaDAO(sql)
    def novaEmpresa = new Empresa(
            nome: 'TechCorp',
            cnpj: '12.345.678/0001-99',
            email: 'contato@techcorp.com',
            senha: '123456',
            pais: 'Brasil',
            estado: 'SP',
            cep: '01000-000',
            descricao: 'Empresa de tecnologia'
    )
    empresaDAO.inserir(novaEmpresa)
    println "\n=== Empresas cadastradas ==="
    empresaDAO.listarTodos().each { println it }
    empresaDAO.atualizarCampo(1, 'descricao', 'Empresa de tecnologia avançada')
    empresaDAO.deletar(2)

    def vagaDAO = new VagasDAO(sql)
    def novaVaga = new Vagas(
            nome: 'Programador',
            descricao: 'Frontend Senior',
            endereco: 'Rua das Américas',
            cidade: 'Rio de Janeiro',
            estado: 'RJ',
            pais: 'Brasil',
            empresa_id: 1,
            competencias: ['HTML', 'CSS', 'JS']
    )
    vagaDAO.inserir(novaVaga)
    println "\n=== Vagas cadastradas ==="
    vagaDAO.listarTodos().each { println it }
    vagaDAO.atualizarCampo(1, 'descricao', 'Programador Backend')
    vagaDAO.deletar(2)

    def candidatoDAO = new CandidatoDAO(sql)
    def novoCandidato = new Candidato(
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
    candidatoDAO.inserir(novoCandidato)
    println "\n=== Candidatos cadastrados ==="
    candidatoDAO.listarTodos().each { println it }
    candidatoDAO.atualizarCampo(1, 'descricao', 'Desenvolvedor Full Stack')
    candidatoDAO.deletar(2)
}
