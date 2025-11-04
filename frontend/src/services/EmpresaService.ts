import type { Empresa } from "../models/Empresa.js"
import { validarCampo } from "../utils/regex"

let empresas: Empresa[] = []

export function cadastrarEmpresa(empresa: Empresa): void {
  const erros: string[] = []

  if (!validarCampo(empresa.nome, "nome")) erros.push("Nome da empresa inválido")
  if (!validarCampo(empresa.cnpj, "cnpj")) erros.push("CNPJ inválido. Use o formato 00.000.000/0000-00")
  if (!validarCampo(empresa.email, "email")) erros.push("E-mail inválido")
  if (!validarCampo(empresa.cep, "cep")) erros.push("CEP inválido")

  for (const c of empresa.competencias) {
    if (!validarCampo(c, "competencia")) erros.push("Competência inválida: " + c)
  }

  if (erros.length > 0) throw new Error(erros.join(" | "))
  empresas.push(empresa)
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