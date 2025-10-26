import groovy.sql.Sql
import java.sql.Date
import java.sql.SQLException

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
        try {
            def candidatoId = inserirCandidato(c)
            associarFormacoes(candidatoId, c.formacao)
            associarCompetencias(candidatoId, c.competencias)
            println "Candidato '${c.nome}' inserido com sucesso"
        } catch (SQLException e) {
            println "Erro ao inserir candidato '${c.nome}': ${e.message}"
        }
    }

    List<Candidato> listarTodos() {
        try {
            def rows = sql.rows("SELECT * FROM Candidato")
            return rows.collect { r ->
                new Candidato(
                        nome: r.nome,
                        sobrenome: r.sobrenome,
                        data_de_nascimento: r.data_de_nascimento?.toString(),
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
        } catch (SQLException e) {
            println "Erro ao listar candidatos: ${e.message}"
            return []
        }
    }

    void atualizarCampo(int id, String campo, Object novoValor) {
        try {
            def camposValidos = [
                    'nome', 'sobrenome', 'data_de_nascimento', 'senha', 'cpf', 'idade',
                    'email', 'estado', 'pais', 'cep', 'descricao', 'telefone', 'linkedin'
            ]
            if (!camposValidos.contains(campo)) {
                throw new IllegalArgumentException("Campo inválido: $campo")
            }

            sql.executeUpdate("UPDATE Candidato SET ${campo} = ? WHERE id = ?", [novoValor, id])
            println "Campo '${campo}' atualizado com sucesso (ID: $id)"
        } catch (IllegalArgumentException e) {
            println "${e.message}"
        } catch (SQLException e) {
            println "Erro ao atualizar candidato ID ${id}: ${e.message}"
        }
    }

    void deletar(int id) {
        try {
            sql.execute("DELETE FROM Candidato WHERE id = ?", [id])
            println "Candidato ID ${id} removido com sucesso!"
        } catch (SQLException e) {
            println "Erro ao remover candidato ID ${id}: ${e.message}"
        }
    }

    private Long inserirCandidato(Candidato c) {
        sql.executeInsert("""
            INSERT INTO candidato 
            (nome, sobrenome, data_de_nascimento, senha, cpf, idade, email, estado, pais, cep, descricao, telefone, linkedin)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """, [
                c.nome,
                c.sobrenome,
                Date.valueOf(c.data_de_nascimento),
                c.senha,
                c.cpf,
                c.idade,
                c.email,
                c.estado,
                c.pais,
                c.cep,
                c.descricao,
                c.telefone,
                c.linkedin
        ])[0][0]
    }

    private void associarFormacoes(Long candidatoId, List<String> formacoes) {
        formacoes?.each { f ->
            def fId = sql.firstRow("SELECT id FROM formacoes WHERE nome = ?", [f])?.id
            if (!fId) {
                formacaoDAO.inserir(new Formacao(nome: f))
                fId = sql.firstRow("SELECT id FROM formacoes WHERE nome = ?", [f]).id
            }
            sql.executeInsert(
                    "INSERT INTO formacaocandidato (candidato_id, formacao_id) VALUES (?, ?)",
                    [candidatoId, fId]
            )
        }
    }

    private void associarCompetencias(Long candidatoId, List<String> competencias) {
        competencias?.each { comp ->
            def compId = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [comp])?.id
            if (!compId) {
                competenciasDAO.inserir(new Competencias(nome: comp))
                compId = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [comp]).id
            }
            sql.executeInsert(
                    "INSERT INTO candidatocompetencias (candidato_id, competencia_id) VALUES (?, ?)",
                    [candidatoId, compId]
            )
        }
    }
}
