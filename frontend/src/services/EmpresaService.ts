import { Empresa } from "../models/Empresa";
import { cadastrarEmpresa } from "../funcoes/EmpresaFuncoes";
import { listarCandidatos } from "../funcoes/CandidatoFuncoes"
import { Candidato } from "../models/Candidato";
import { graficoCandidatos } from "./Grafico";

export function cadastrarEmpresaS() : void {
  const form = document.getElementById("formEmpresa") as HTMLFormElement;

    const empresa: Empresa = {
      nome: (document.getElementById("nomeEmpresa") as HTMLInputElement).value,
      cnpj: (document.getElementById("cnpj") as HTMLInputElement).value,
      email: (document.getElementById("email") as HTMLInputElement).value,
      pais: (document.getElementById("pais") as HTMLInputElement).value,
      estado: (document.getElementById("estado") as HTMLInputElement).value,
      cep: (document.getElementById("cep") as HTMLInputElement).value,
      descricao: (document.getElementById("descricao") as HTMLInputElement).value,
      competencias: (document.getElementById("competencias") as HTMLInputElement)
        .value.split(",").map(c => c.trim())
    }
    cadastrarEmpresa(empresa)
    form.reset()
}

export function mostrarCandidatosAnonimos() : void {
  const candidatos = listarCandidatos()
  if (candidatos.length != 0 ) {
    const tabela = document.querySelector<HTMLTableSectionElement>("#tabelaCandidatos tbody")!
    tabela.innerHTML = ""
    candidatos.forEach((candidato, index) => {
      const row = document.createElement("tr")

      row.innerHTML = `
        <td>Candidato ${index + 1}</td>
        <td>${candidato.formacao ?? "Não informado"}</td>
        <td>${candidato.competencias.join(", ")}</td>
     `
      tabela.appendChild(row)
    })
    graficoCandidatos(candidatos)
  } else {
    const tabela = document.querySelector<HTMLTableSectionElement>("#tabelaCandidatos")!
    tabela.innerHTML = "<h5 style='color:red'> Lista de Candidatos irá aparecer aqui </h5>"
  }
}