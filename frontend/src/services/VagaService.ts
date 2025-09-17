import { cadastrarVaga, listarVagas } from "../funcoes/VagasFuncoes";
import { Vaga } from "../models/Vagas";

export function cadastrarVagaS() : void {
      const form = document.getElementById("formVagas") as HTMLFormElement;

    const vaga: Vaga = {
      nome: (document.getElementById("nomeVaga") as HTMLInputElement).value,
      descricao: (document.getElementById("descricao") as HTMLInputElement).value,
      competenciasNecessarias: (document.getElementById("competencias") as HTMLInputElement)
        .value.split(",").map(c => c.trim())
    }
    cadastrarVaga(vaga)
    form.reset()
}


export function listarVagasS() : void {
  const vagas = listarVagas()
  if (vagas.length != 0 ) {
    const tabela = document.querySelector<HTMLTableSectionElement>("#tabelaVagas tbody")!
    tabela.innerHTML = ""
    vagas.forEach((vaga, index) => {
      const row = document.createElement("tr")
      row.innerHTML = `
        <td>${vaga.nome ?? "Não informado"}</td>
        <td>${vaga.descricao ?? "Não informado"}</td>
        <td>${vaga.competenciasNecessarias.join(", ")}</td>
     `
      tabela.appendChild(row)
    })
  } else {
    const tabela = document.querySelector<HTMLTableSectionElement>("#tabelaVagas")!
    tabela.innerHTML = "<h5 style='color:red'> Lista de Vagas irá aparecer aqui </h5>"
  }
}