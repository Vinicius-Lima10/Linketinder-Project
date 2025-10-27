package dao

import groovy.sql.Sql
import interfaces.IGenericDAO
import model.Candidato
import services.AssociacaoService

import java.sql.Date

class CandidatoDAO implements IGenericDAO<Candidato> {
    Sql sql
    FormacaoDAO formacaoDAO
    CompetenciasDAO competenciasDAO
    AssociacaoService associacaoService

    CandidatoDAO(Sql sql) {
        this.sql = sql
        this.formacaoDAO = new FormacaoDAO(sql)
        this.competenciasDAO = new CompetenciasDAO(sql)
        this.associacaoService = new AssociacaoService(sql)
    }

    @Override
    def inserir(Candidato c) {
        try {
            int candidatoId = inserirCandidato(c)

            associacaoService.associarFormacoes("formacaocandidato", "candidato_id", candidatoId, c.formacao)
            associacaoService.associarCompetencias("candidatocompetencias", "candidato_id", candidatoId, c.competencias)
            println "Candidato '${c.nome}' inserido com sucesso."
        } catch (Exception ex) {
            println "Erro ao inserir candidato '${c.nome}': ${ex.message}"
            throw ex
        }
    }

    @Override
    List<Candidato> listarTodos() {
        try {
            def rows = sql.rows("SELECT * FROM candidato")

            return rows.collect { r ->
                new Candidato(
                        id: r.id,
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
        } catch (Exception ex) {
            println "Erro ao listar candidatos: ${ex.message}"
            return []
        }
    }

    @Override
    void atualizarCampo(int id, String campo, Object novoValor) {
        try {
            def camposValidos = [
                    'nome', 'sobrenome', 'data_de_nascimento', 'senha', 'cpf', 'idade',
                    'email', 'estado', 'pais', 'cep', 'descricao', 'telefone', 'linkedin'
            ]
            if (!camposValidos.contains(campo)) {
                throw new IllegalArgumentException("Campo inválido: $campo")
            }

            sql.executeUpdate("UPDATE candidato SET ${campo} = ? WHERE id = ?", [novoValor, id])
            println "Campo '${campo}' atualizado com sucesso (ID: $id)."
        } catch (Exception ex) {
            println "Erro ao atualizar candidato ID ${id}: ${ex.message}"
        }
    }

    @Override
    void deletar(int id) {
        try {
            sql.execute("DELETE FROM candidato WHERE id = ?", [id])
            println "Candidato ID ${id} removido com sucesso."
        } catch (Exception ex) {
            println "Erro ao remover candidato ID ${id}: ${ex.message}"
        }
    }

    private int inserirCandidato(Candidato c) {
        def keys = sql.executeInsert("""
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
        ])
        return keys[0][0]
    }
}
