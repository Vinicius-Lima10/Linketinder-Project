export const regex = {
  nome: /^[A-Za-zÀ-ÿ\s'\-]{2,50}$/,
  sobrenome: /^[A-Za-zÀ-ÿ\s'\-]{2,50}$/,
  cpf: /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/,
  cnpj: /^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/,
  email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
  telefone: /^(\+55\s?)?(\(?\d{2}\)?\s?)?\d{4,5}\-?\d{4}$/,
  linkedin: /^https?:\/\/(www\.)?linkedin\.com\/in\/[a-zA-Z0-9_-]+\/?$/,
  cep: /^\d{5}\-?\d{3}$/,
  competencia: /^[A-Za-zÀ-ÿ0-9\s\+\#\.\-]{1,30}$/
}

export function validarCampo(valor: string, tipo: keyof typeof regex): boolean {
  return regex[tipo].test(valor)
}
