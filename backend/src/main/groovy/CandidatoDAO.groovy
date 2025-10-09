import groovy.sql.Sql
import java.sql.Date

class CandidatoDAO {
    Sql sql
    FormacaoDAO formacaoDAO
    CompetenciasDAO competenciasDAO

    CandidatoDAO(Sql sql) {
        this.sql = sql
        this.formacaoDAO = new FormacaoDAO(sql)
        this.competenciasDAO = new CompetenciasDAO(sql)
    }

    void inserir(Candidato c) {
        def candidatoId = sql.executeInsert("""
            INSERT INTO Candidato 
            (nome, sobrenome, data_de_nascimento, senha, cpf, idade, email, estado, pais, cep, descricao, telefone, linkedin)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """, [c.nome, c.sobrenome, Date.valueOf(c.data_de_nascimento), c.senha, c.cpf, c.idade, c.email,
              c.estado, c.pais, c.cep, c.descricao, c.telefone, c.linkedin])[0][0]

        c.formacao.each { f ->
            def fId = sql.firstRow("SELECT id FROM formacoes WHERE nome = ?", [f])?.id
            if (!fId) {
                formacaoDAO.inserir(new Formacao(nome: f))
                fId = sql.firstRow("SELECT id FROM formacoes WHERE nome = ?", [f]).id
            }
            sql.executeInsert("INSERT INTO formacaocandidato (candidato_id, formacao_id) VALUES (?, ?)", [candidatoId, fId])
        }

        c.competencias.each { comp ->
            def compId = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [comp])?.id
            if (!compId) {
                competenciasDAO.inserir(new Competencias(nome: comp))
                compId = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [comp]).id
            }
            sql.executeInsert("INSERT INTO candidatocompetencias (candidato_id, competencia_id) VALUES (?, ?)", [candidatoId, compId])
        }

        println "Candidato inserido com sucesso"
        }

    List<Candidato> listarTodos() {
        def rows = sql.rows("SELECT * FROM Candidato")
        rows.collect { r ->
            new Candidato(
                    nome: r.nome,
                    sobrenome: r.sobrenome,
                    data_de_nascimento: r.data_de_nascimento.toString(),
                    cpf: r.cpf,
                    idade: r.idade,
                    email: r.email,
                    estado: r.estado,
                    pais: r.pais,
                    cep: r.cep,
                    descricao: r.descricao,
                    formacao: [],
                    competencias: [],
                    linkedin: r.linkedin
            )
        }
    }

    void atualizarCampo(int id, String campo, Object novoValor) {
        sql.executeUpdate("UPDATE Candidato SET ${campo} = ? WHERE id = ?", [novoValor, id])
        println "Campo '${campo}' atualizado com sucesso"
    }

    void deletar(int id) {
        sql.execute "DELETE FROM Candidato WHERE id = $id"
        println "Candidato removido com sucesso!"
    }
}
