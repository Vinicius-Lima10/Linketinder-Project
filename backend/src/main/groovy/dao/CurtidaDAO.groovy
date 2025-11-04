package dao

import groovy.sql.Sql

class CurtidaDAO {
    Sql sql

    CurtidaDAO(Sql sql) {
        this.sql = sql
    }

    void registrarCurtidaCandidato(int idCandidato, int idVaga) {
        sql.execute """
            INSERT INTO candidato_vaga_curtida (id_candidato, id_vaga)
            VALUES (?, ?)
        """, [idCandidato, idVaga]
    }

    void registrarCurtidaEmpresa(int idEmpresa, int idCandidato, int idVaga) {
        sql.execute """
            INSERT INTO empresa_candidato_curtida (id_empresa, id_candidato, id_vaga)
            VALUES (?, ?, ?)
        """, [idEmpresa, idCandidato, idVaga]
    }

    boolean verificarCurtidaEmpresa(int idCandidato, int idVaga) {
        def rows = sql.rows("""
            SELECT 1 FROM empresa_candidato_curtida
            WHERE id_candidato = ? AND id_vaga = ?
        """, [idCandidato, idVaga])
        return !rows.isEmpty()
    }

    boolean verificarCurtidaCandidato(int idCandidato, int idVaga) {
        def rows = sql.rows("""
            SELECT 1 FROM candidato_vaga_curtida
            WHERE id_candidato = ? AND id_vaga = ?
        """, [idCandidato, idVaga])
        return !rows.isEmpty()
    }
}
