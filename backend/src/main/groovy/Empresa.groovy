import groovy.transform.ToString
@ToString(includeNames = true)
class Empresa extends Pessoa {
    String nome
    String cnpj
    String email
    String senha
    String pais
    String estado
    String cep
    String descricao

    @Override
    void exibirPerfil() {
        println  """
        Nome da empresa: $nome
        CNPJ: $cnpj
        Email corporativo: $email
        País: $pais
        Estado: $estado
        CEP: $cep
        Descrição da empresa: $descricao
        
        """
//        Competências buscadas: ${competencias.join(',')}
    }
}