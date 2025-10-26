import groovy.transform.ToString
@ToString(includeNames = true)
class Candidato extends Pessoa {
    String nome
    String sobrenome
    String data_de_nascimento
    String senha
    String cpf
    int idade
    String email
    String estado
    String pais
    String cep
    String descricao
    String telefone
    List<String> formacao
    List<String> competencias
    String linkedin

    @Override
    void exibirPerfil() {
        println  """
        Nome do candidato: $nome
        Sobrenome: $sobrenome
        CPF: $cpf
        Data de nascimento: $data_de_nascimento
        Idade: $idade
        Email do candidato: $email
        Estado: $estado
        CEP: $cep
        Descrição pessoal: $descricao
        Competências: ${competencias.join(',')}
        Formação: ${formacao.join(',')}
        Linkedin: $linkedin
        """
    }
}