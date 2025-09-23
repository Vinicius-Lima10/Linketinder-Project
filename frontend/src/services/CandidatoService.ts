import { Candidato } from "../models/Candidato";
import { cadastrarCandidato, listarCandidatos } from "../funcoes/CandidatoFuncoes";
export function cadastrarCandidatoS() {
  const form = document.getElementById("formCandidato") as HTMLFormElement;

    const candidato: Candidato = {
      nome: (document.getElementById("nomeCandidato") as HTMLInputElement).value,
      cpf: (document.getElementById("cpf") as HTMLInputElement).value,
      idade: Number((document.getElementById("idade") as HTMLInputElement).value),
      email: (document.getElementById("email") as HTMLInputElement).value,
      estado: (document.getElementById("estado") as HTMLInputElement).value,
      cep: (document.getElementById("cep") as HTMLInputElement).value,
      descricao: (document.getElementById("descricao") as HTMLInputElement).value,
      formacao: (document.getElementById("formacao") as HTMLInputElement).value,
      telefone: (document.getElementById("telefone") as HTMLInputElement).value,
      linkedin: (document.getElementById("linkedin") as HTMLInputElement).value,
      competencias: (document.getElementById("competencias") as HTMLInputElement)
        .value.split(",").map(c => c.trim())
    }

    cadastrarCandidato(candidato)
    form.reset()
}