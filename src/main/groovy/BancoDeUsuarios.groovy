class BancoDeUsuarios {
        static def adicionarCandidato(Candidato candidato) {
                candidatos.add(candidato);
        }
        static def adicionarEmpresa(Empresa empresa) {
                empresas.add(empresa);
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
                nome: "Laura Martins",
                cpf: "123.456.789-00",
                idade: 25,
                email: "laura.martins@email.com",
                estado: "SP",
                cep: "01000-000",
                descricao: "Desenvolvedora full-stack apaixonada por tecnologia e games.",
                competencias: ["Java", "Groovy", "Spring", "SQL"]
        ),
        new Candidato(
                nome: "Pedro Souza",
                cpf: "987.654.321-11",
                idade: 30,
                email: "pedro.souza@email.com",
                estado: "RJ",
                cep: "20000-000",
                descricao: "Engenheiro de software com foco em back-end e APIs.",
                competencias: ["Python", "Django", "REST", "PostgreSQL"]
        ),
        new Candidato(
                nome: "Mariana Oliveira",
                cpf: "111.222.333-44",
                idade: 27,
                email: "mariana.oliveira@email.com",
                estado: "MG",
                cep: "30000-000",
                descricao: "Especialista em UX/UI, criativa e colaborativa.",
                competencias: ["Figma", "Adobe XD", "HTML", "CSS", "JavaScript"]
        ),
        new Candidato(
                nome: "Rafael Lima",
                cpf: "555.666.777-88",
                idade: 35,
                email: "rafael.lima@email.com",
                estado: "RS",
                cep: "90000-000",
                descricao: "Analista de dados com experiência em machine learning.",
                competencias: ["Python", "R", "Pandas", "TensorFlow", "PowerBI"]
        ),
        new Candidato(
                nome: "Beatriz Costa",
                cpf: "999.888.777-66",
                idade: 29,
                email: "beatriz.costa@email.com",
                estado: "PR",
                cep: "80000-000",
                descricao: "Gerente de projetos com experiência em metodologias ágeis.",
                competencias: ["Scrum", "Kanban", "Jira", "Gestão de equipes"]
        )
        ]
        static def empresas = [
        new Empresa(
                nome: "TechNova Solutions",
                cnpj: "12.345.678/0001-90",
                email: "contato@technova.com",
                pais: "Brasil",
                estado: "SP",
                cep: "01100-000",
                descricao: "Empresa especializada em desenvolvimento de softwares inovadores.",
                competencias: ["Java", "Spring", "SQL", "Cloud"]
        ),
        new Empresa(
                nome: "DataVision Analytics",
                cnpj: "98.765.432/0001-55",
                email: "rh@datavision.com",
                pais: "Brasil",
                estado: "RJ",
                cep: "20200-000",
                descricao: "Consultoria em análise de dados e inteligência artificial.",
                competencias: ["Python", "Machine Learning", "PowerBI", "R"]
        ),
        new Empresa(
                nome: "DesignUp Studio",
                cnpj: "33.222.111/0001-77",
                email: "jobs@designup.com",
                pais: "Brasil",
                estado: "MG",
                cep: "30100-000",
                descricao: "Agência focada em design digital e experiência do usuário.",
                competencias: ["Figma", "Adobe XD", "UX/UI", "JavaScript"]
        ),
        new Empresa(
                nome: "GreenFuture Energy",
                cnpj: "55.444.333/0001-22",
                email: "talentos@greenfuture.com",
                pais: "Brasil",
                estado: "RS",
                cep: "90100-000",
                descricao: "Startup de soluções energéticas sustentáveis e renováveis.",
                competencias: ["Engenharia Elétrica", "IoT", "Automação", "Sustentabilidade"]
        ),
        new Empresa(
                nome: "AgileCorp Consulting",
                cnpj: "77.888.999/0001-11",
                email: "vagas@agilecorp.com",
                pais: "Brasil",
                estado: "PR",
                cep: "80100-000",
                descricao: "Consultoria em gestão de projetos ágeis para empresas de tecnologia.",
                competencias: ["Scrum", "Kanban", "Gestão de Projetos", "Jira"]
        )
        ]
}




