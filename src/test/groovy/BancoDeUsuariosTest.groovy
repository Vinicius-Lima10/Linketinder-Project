import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BancoDeUsuariosTest {
    @Test
    void deveInserirEApagarNovoCandidato() {
        def candidato = new Candidato(
                nome: "Novo Candidato",
                cpf: "000.111.222-33",
                idade: 28,
                email: "novo@email.com",
                estado: "MG",
                cep: "12345-678",
                descricao: "QA tester apaixonado por automação.",
                competencias: ["Selenium", "JUnit", "Groovy"]
        )

        int tamanhoAntes = BancoDeUsuarios.candidatos.size()
        BancoDeUsuarios.adicionarCandidato(candidato)
        int tamanhoDepois = BancoDeUsuarios.candidatos.size()

        assertEquals(tamanhoAntes + 1, tamanhoDepois)
        assertTrue(BancoDeUsuarios.candidatos.contains(candidato))

        BancoDeUsuarios.removerCandidato(candidato);
        assertEquals(tamanhoAntes, tamanhoDepois-1);
        assertFalse(BancoDeUsuarios.candidatos.contains(candidato));
    }
    @Test
    void deveInserirEApagarNovaEmpresa() {
        def empresa = new Empresa(
                nome: "NovaTech Ltda",
                cnpj: "00.111.222/0001-33",
                email: "contato@novatech.com",
                pais: "Brasil",
                estado: "SP",
                cep: "04567-000",
                descricao: "Empresa nova de tecnologia.",
                competencias: ["Java", "DevOps"]
        )

        int tamanhoAnterior = BancoDeUsuarios.empresas.size()
        BancoDeUsuarios.adicionarEmpresa(empresa)
        int tamanhoSeguinte = BancoDeUsuarios.empresas.size()

        assertEquals(tamanhoAnterior + 1, tamanhoSeguinte)
        assertTrue(BancoDeUsuarios.empresas.contains(empresa))

        BancoDeUsuarios.removerEmpresa(empresa);
        assertEquals(tamanhoAnterior, tamanhoSeguinte-1);
        assertFalse(BancoDeUsuarios.empresas.contains(empresa));
    }
}
