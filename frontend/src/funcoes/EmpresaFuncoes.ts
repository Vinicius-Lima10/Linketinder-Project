import type { Empresa } from "../models/Empresa.js"
let empresas : Empresa[] = []
export function cadastrarEmpresa(empresa: Empresa): void {
  if (!empresa.nome.match(/^[A-Za-zÀ-ÿ\s]{2,30}$/)) {
    throw new Error("Nome da empresa inválido");
  }

  if (!empresa.cnpj.match(/^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/)) {
    throw new Error("CNPJ inválido. Use o formato 00.000.000/0000-00")
  }

  if (!empresa.email.match(/^[\w.-]+@[a-zA-Z\d.-]+\.[a-zA-Z]{2,}$/)) {
    throw new Error("E-mail inválido")
  }

  if (!empresa.cep.match(/^\d{5}\-?\d{3}$/)) {
    throw new Error("CEP inválido")
  }

  for (const c of empresa.competencias) {
    if (!c.match(/^[A-Za-zÀ-ÿ0-9\s\+\#\.\-]{1,30}$/)) {
      throw new Error("Há competência(s) inválidas" + c)
    }
  }

  empresas.push(empresa);
}


export function listarEmpresas() : Empresa[] {
    return empresas
}

export function mostrarEmpresa(nome: string): Empresa | string {
    const Empresa = empresas.find(c => c.nome === nome)
    return Empresa ? Empresa : "Usuário não encontrado"
}

export function deletarEmpresa(nome: string): boolean {
    const index = empresas.findIndex(c => c.nome === nome)
    if (index !== -1) {
        empresas.splice(index, 1)
        return true
    }
    return false
}