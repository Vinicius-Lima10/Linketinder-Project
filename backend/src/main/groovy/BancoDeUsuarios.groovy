import java.time.LocalDate

class BancoDeUsuarios {
        static def adicionarCandidato(Candidato candidato) {
                candidatos.add(candidato)
        }
        static def adicionarEmpresa(Empresa empresa) {
                empresas.add(empresa)
        }
        static def listarCandidatos() {
                BancoDeUsuarios.candidatos.each{it.exibirPerfil()}
        }
        static def listarEmpresas() {
                BancoDeUsuarios.empresas.each{it.exibirPerfil()}
        }
        static def removerCandidato(Candidato candidato) {
                BancoDeUsuarios.candidatos.remove(candidato)
        }
        static def removerEmpresa(Empresa empresa) {
                BancoDeUsuarios.empresas.remove(empresa)
        }
    static def candidatos = [
            new Candidato(
                    nome: "Laura",
                    sobrenome: "Martins",
                    data_de_nascimento: LocalDate.of(1999, 5, 10).toString(),
                    senha: "senha123",
                    cpf: "123.456.789-00",
                    idade: 25,
                    email: "laura.martins@email.com",
                    estado: "SP",
                    pais: "Brasil",
                    cep: "01000-000",
                    descricao: "Desenvolvedora full-stack apaixonada por tecnologia e games.",
                    telefone: "(11) 98765-4321", // Mock
                    formacao: ["Engenharia de Software (USP)"],
                    competencias: ["Java", "Groovy", "Spring", "SQL"],
                    linkedin: "linkedin.com/in/lauramartinsdev"
            ),
            new Candidato(
                    nome: "Pedro",
                    sobrenome: "Souza",
                    data_de_nascimento: LocalDate.of(1994, 8, 20).toString(),
                    senha: "senha123",
                    cpf: "987.654.321-11",
                    idade: 30,
                    email: "pedro.souza@email.com",
                    estado: "RJ",
                    pais: "Brasil",
                    cep: "20000-000",
                    descricao: "Engenheiro de software com foco em back-end e APIs.",
                    telefone: "(21) 99876-5432",
                    formacao: ["Ciência da Computação (UERJ)"],
                    competencias: ["Python", "Django", "REST", "PostgreSQL"],
                    linkedin: "linkedin.com/in/pedrosouzadev"
            ),
            new Candidato(
                    nome: "Mariana",
                    sobrenome: "Oliveira",
                    data_de_nascimento: LocalDate.of(1997, 1, 5).toString(),
                    senha: "senha123",
                    cpf: "111.222.333-44",
                    idade: 27,
                    email: "mariana.oliveira@email.com",
                    estado: "MG",
                    pais: "Brasil",
                    cep: "30000-000",
                    descricao: "Especialista em UX/UI, criativa e colaborativa.",
                    telefone: "(31) 97777-6666",
                    formacao: ["Design Digital (UFMG)", "Pós-graduação em UX"],
                    competencias: ["Figma", "Adobe XD", "HTML", "CSS", "JavaScript"],
                    linkedin: "linkedin.com/in/marianaoliveiraux"
            ),
            new Candidato(
                    nome: "Rafael",
                    sobrenome: "Lima",
                    data_de_nascimento: LocalDate.of(1989, 11, 25).toString(),
                    senha: "senha123",
                    cpf: "555.666.777-88",
                    idade: 35,
                    email: "rafael.lima@email.com",
                    estado: "RS",
                    pais: "Brasil",
                    cep: "90000-000",
                    descricao: "Analista de dados com experiência em machine learning.",
                    telefone: "(51) 96666-5555",
                    formacao: ["Estatística (UFRGS)"],
                    competencias: ["Python", "R", "Pandas", "TensorFlow", "PowerBI"],
                    linkedin: "linkedin.com/in/rafaellimaanalista"
            ),
            new Candidato(
                    nome: "Beatriz",
                    sobrenome: "Costa",
                    data_de_nascimento: LocalDate.of(1995, 3, 15).toString(),
                    senha: "senha123",
                    cpf: "999.888.777-66",
                    idade: 29,
                    email: "beatriz.costa@email.com",
                    estado: "PR",
                    pais: "Brasil",
                    cep: "80000-000",
                    descricao: "Gerente de projetos com experiência em metodologias ágeis.",
                    telefone: "(41) 95555-4444",
                    formacao: ["Administração (UFPR)", "Certificação Scrum Master"],
                    competencias: ["Scrum", "Kanban", "Jira", "Gestão de equipes"],
                    linkedin: "linkedin.com/in/beatrizcostapm"
            )
    ]

    static def empresas = [
            new Empresa(
                    nome: "TechNova Solutions",
                    cnpj: "12.345.678/0001-90",
                    email: "contato@technova.com",
                    senha: "corp123",
                    pais: "Brasil",
                    estado: "SP",
                    cep: "01100-000",
                    descricao: "Empresa especializada em desenvolvimento de softwares inovadores."
            ),
            new Empresa(
                    nome: "DataVision Analytics",
                    cnpj: "98.765.432/0001-55",
                    email: "rh@datavision.com",
                    senha: "corp123",
                    pais: "Brasil",
                    estado: "RJ",
                    cep: "20200-000",
                    descricao: "Consultoria em análise de dados e inteligência artificial."
            ),
            new Empresa(
                    nome: "DesignUp Studio",
                    cnpj: "33.222.111/0001-77",
                    email: "jobs@designup.com",
                    senha: "corp123",
                    pais: "Brasil",
                    estado: "MG",
                    cep: "30100-000",
                    descricao: "Agência focada em design digital e experiência do usuário."
            ),
            new Empresa(
                    nome: "GreenFuture Energy",
                    cnpj: "55.444.333/0001-22",
                    email: "talentos@greenfuture.com",
                    senha: "corp123",
                    pais: "Brasil",
                    estado: "RS",
                    cep: "90100-000",
                    descricao: "Startup de soluções energéticas sustentáveis e renováveis."
            ),
            new Empresa(
                    nome: "AgileCorp Consulting",
                    cnpj: "77.888.999/0001-11",
                    email: "vagas@agilecorp.com",
                    senha: "corp123",
                    pais: "Brasil",
                    estado: "PR",
                    cep: "80100-000",
                    descricao: "Consultoria em gestão de projetos ágeis para empresas de tecnologia."
            )
    ]
}




