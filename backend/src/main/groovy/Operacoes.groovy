@Grab('org.postgresql:postgresql:42.7.3')
@GrabConfig(systemClassLoader=true)

import groovy.sql.Sql

def connectPostgres(Closure codeBlock) {
    def url = 'jdbc:postgresql://localhost:5432/linketinder'
    def user = 'postgres'
    def password = '12345678'
    def driver = 'org.postgresql.Driver'

    try {
        Sql.withInstance(url, user, password, driver) { sql ->
            codeBlock(sql)
        }
    } catch (Exception e) {
        println "ERRO de DB: ${e.message}"
    }
}

def novoCandidato = new Candidato(
        nome: 'João2',
        sobrenome: 'Silva',
        data_de_nascimento: '1992-10-20',
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
def novaVaga = new Vagas (
        nome: 'Programador',
        descricao: 'Programador Frontend Senior',
         endereco: 'Rua das américas',
         cidade: 'Rio de Janeiro',
         estado: 'Rio de Janeiro',
         pais: 'Brasil',
         empresa_id: 1,
        competencias: ['HTML', 'CSS', 'JS'],
)
connectPostgres { sql ->
//    def dao = new CandidatoDAO(sql)
//    dao.inserir(novoCandidato)
//    def todos = dao.listarTodos()
//    if (todos) {
//        println "\n=== Lista de Candidatos ==="
//        todos.each { println it }
//        println "Total: ${todos.size()}"
//    } else {
//        println "Nenhum candidato encontrado."
//    }
//    dao.atualizarCampo(1, 'descricao', 'Desenvolvedor Full Stack Senior')
//    dao.deletar(2)

//    def empresaDAO = new EmpresaDAO(sql)
//
//    empresaDAO.inserir(novaEmpresa)
//
//    def todasEmpresas = empresaDAO.listarTodos()
//    if (todasEmpresas) {
//        println "\n=== Lista de Empresas ==="
//        todasEmpresas.each { println it }
//        println "Total: ${todasEmpresas.size()}"
//    } else {
//        println "Nenhuma empresa encontrada."
//    }
//
//    empresaDAO.atualizarCampo(1, 'descricao', 'Empresa de tecnologia avançada')
//    empresaDAO.deletar(2)

//    def vagaDAO = new VagasDAO(sql)
//    vagaDAO.inserir(novaVaga)
//    def todasVagas = vagaDAO.listarTodos()
//    if (todasVagas) {
//        println "\n=== Lista de Empresas ==="
//        todasVagas.each { println it }
//    } else {
//        println "Nenhuma vaga encontrada."
//    }

//    vagaDAO.atualizarCampo(2, 'descricao', 'Programador Backend')
//    vagaDAO.deletar(1)

}
