import { Vaga } from "../models/Vagas";
let vagas : Vaga[] = []

export function cadastrarVaga(vaga : Vaga ) : void {
    vagas.push(vaga)
    console.log(vagas)
}

export function listarVagas() : Vaga[] {
    return vagas;
}
