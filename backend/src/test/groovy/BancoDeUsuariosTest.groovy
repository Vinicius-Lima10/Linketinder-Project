import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*

class BancoDeUsuariosTest {
    @Test
    void deveInserirEApagarNovoCandidato() {
        def candidato = new Candidato(
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

        int tamanhoAntes = BancoDeUsuarios.candidatos.size()
        BancoDeUsuarios.adicionarCandidato(candidato)
        int tamanhoDepois = BancoDeUsuarios.candidatos.size()

        assertEquals(tamanhoAntes + 1, tamanhoDepois)
        assertTrue(BancoDeUsuarios.candidatos.contains(candidato))

        BancoDeUsuarios.removerCandidato(candidato)

        assertEquals(tamanhoAntes, tamanhoDepois-1)
        assertFalse(BancoDeUsuarios.candidatos.contains(candidato))
    }
    @Test
    void deveInserirEApagarNovaEmpresa() {
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

        int tamanhoAnterior = BancoDeUsuarios.empresas.size()
        BancoDeUsuarios.adicionarEmpresa(empresa)
        int tamanhoSeguinte = BancoDeUsuarios.empresas.size()

        assertEquals(tamanhoAnterior + 1, tamanhoSeguinte)
        assertTrue(BancoDeUsuarios.empresas.contains(empresa))

        BancoDeUsuarios.removerEmpresa(empresa)
        assertEquals(tamanhoAnterior, tamanhoSeguinte-1)
        assertFalse(BancoDeUsuarios.empresas.contains(empresa))
    }
}
