import type { Candidato } from "../models/Candidato.js"

let candidatos: Candidato[] = []

export function cadastrarCandidato(candidato: Candidato): void {
  if (!candidato.nome.match(/^[A-Za-zÀ-ÿ\s'\-]{2,50}$/)) {
    throw new Error("Nome inválido");
  }
    if (!candidato.sobrenome.match(/^[A-Za-zÀ-ÿ\s'\-]{2,50}$/)) {
        throw new Error("Sobrenome inválido");
      }
  if (!candidato.cpf.match(/^\d{3}\.\d{3}\.\d{3}\-\d{2}$/)) {
    throw new Error("CPF inválido. Use o formato 000.000.000-00");
  }

  if (!candidato.email.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
    throw new Error("E-mail inválido");
  }

  if (!candidato.telefone.match(/^(\+55\s?)?(\(?\d{2}\)?\s?)?\d{4,5}\-?\d{4}$/)) {
    throw new Error("Telefone inválido");
  }

  if (!candidato.linkedin.match(/^https?:\/\/(www\.)?linkedin\.com\/in\/[a-zA-Z0-9_-]+\/?$/)) {
    throw new Error("LinkedIn inválido. Use o formato https://www.linkedin.com/in/seu-perfil");
  }

  if (!candidato.cep.match(/^\d{5}\-?\d{3}$/)) {
    throw new Error("CEP inválido");
  }

  candidato.competencias = candidato.competencias.filter(c => c.trim() !== "");

  for (const c of candidato.competencias) {
    if (!c.match(/^[A-Za-zÀ-ÿ0-9\s\+\#\.\-]{1,30}$/)) {
      throw new Error("Competência inválida: " + c);
    }
  }

  candidatos.push(candidato);
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
