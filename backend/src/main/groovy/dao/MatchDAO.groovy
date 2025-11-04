package dao

import groovy.sql.Sql

class MatchDAO {
    Sql sql

    MatchDAO(Sql sql) {
        this.sql = sql
    }

    void registrarMatch(int idEmpresa, int idCandidato, int idVaga) {
        sql.execute """
            INSERT INTO historico_match (id_empresa, id_candidato, id_vaga)
            VALUES (?, ?, ?)
        """, [idEmpresa, idCandidato, idVaga]
    }

    List<Map> listarMatches() {
        sql.rows("SELECT * FROM historico_match")
    }
}
