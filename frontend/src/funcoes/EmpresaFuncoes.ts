import type { Empresa } from "../models/Empresa.js"
let empresas : Empresa[] = []
export function cadastrarEmpresa(empresa : Empresa ) : void {
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