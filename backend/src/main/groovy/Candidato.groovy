class Candidato extends Pessoa {
    String nome
    String cpf
    int idade
    String email
    String estado
    String cep
    String descricao
    List<String> competencias

    @Override
    void exibirPerfil() {
        println  """
        Nome do candidato: $nome
        CPF: $cpf
        Idade: $idade
        Email do candidato: $email
        Estado: $estado
        CEP: $cep
        Descrição pessoal: $descricao
        Competências: ${competencias.join(',')}
        """
    }
}