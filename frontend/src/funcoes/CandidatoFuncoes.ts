import type { Candidato } from "../models/Candidato.js"
let candidatos : Candidato[] = []
export function cadastrarCandidato(candidato : Candidato ) : void {
    candidatos.push(candidato)
}

export function listarCandidatos() : Candidato[] {
    return candidatos
}

export function mostrarCandidato(nome: string): Candidato | string {
    const candidato = candidatos.find(c => c.nome === nome)
    return candidato ? candidato : "Usuário não encontrado"
}

export function deletarCandidato(nome: string): boolean {
    const index = candidatos.findIndex(c => c.nome === nome)
    if (index !== -1) {
        candidatos.splice(index, 1)
        return true
    }
    return false
}