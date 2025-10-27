package model

import groovy.transform.ToString

@ToString(includeNames = true)
class Curtida {
    Candidato candidato
    Vagas vaga
    Empresa empresa

    boolean candidatoCurtiu = false
    boolean empresaCurtiu = false

    boolean isMatch() {
        return candidatoCurtiu && empresaCurtiu
    }
}