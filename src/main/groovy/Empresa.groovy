class Empresa extends Pessoa {
    String nome
    String cnpj
    String email
    String pais
    String estado
    String cep
    String descricao
    List<String> competencias

    @Override
    void exibirPerfil() {
        println  """
        Nome do candidato: $nome
        CNPJ: $cnpj
        Email corporativo: $email
        País: $pais
        Estado: $estado
        CEP: $cep
        Descrição da empresa: $descricao
        Competências buscadas: ${competencias.join(',')}
        """
    }
}