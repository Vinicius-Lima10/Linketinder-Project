import type { Candidato } from "../models/Candidato.js"
import { regex, validarCampo } from "../utils/regex"

let candidatos: Candidato[] = []

export function cadastrarCandidato(candidato: Candidato): void {
  const erros: string[] = []

  if (!validarCampo(candidato.nome, "nome")) erros.push("Nome inválido")
  if (!validarCampo(candidato.sobrenome, "sobrenome")) erros.push("Sobrenome inválido")
  if (!validarCampo(candidato.cpf, "cpf")) erros.push("CPF inválido. Use o formato 000.000.000-00")
  if (!validarCampo(candidato.email, "email")) erros.push("E-mail inválido")
  if (!validarCampo(candidato.telefone, "telefone")) erros.push("Telefone inválido")
  if (!validarCampo(candidato.linkedin, "linkedin")) erros.push("LinkedIn inválido. Use o formato https://www.linkedin.com/in/seu-perfil")
  if (!validarCampo(candidato.cep, "cep")) erros.push("CEP inválido")

  candidato.competencias = candidato.competencias.filter(c => c.trim() !== "")
  for (const c of candidato.competencias) {
    if (!validarCampo(c, "competencia")) erros.push("Competência inválida: " + c)
  }

  if (erros.length > 0) throw new Error(erros.join(" | "))
  candidatos.push(candidato)
}

export function listarCandidatos(): Candidato[] {
  return candidatos;
}

export function mostrarCandidato(nome: string): Candidato | string {
  const candidato = candidatos.find(c => c.nome === nome)
  return candidato ? candidato : "Usuário não encontrado"
}

export function deletarCandidato(nome: string): boolean {
  const index = candidatos.findIndex(c => c.nome === nome)
  if (index !== -1) {
    candidatos.splice(index, 1);
    return true
  }
  return false
}
