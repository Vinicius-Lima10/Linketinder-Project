package model

class Candidato extends Pessoa {
    Integer id
    String sobrenome
    String data_de_nascimento
    String senha
    String cpf
    int idade
    String pais
    String telefone
    List<String> formacao
    List<String> competencias
    String linkedin

    @Override
    String toString() {
        return """
        ----------------------------------------
        Candidato: ${nome} ${sobrenome}
        CPF: ${cpf}
        Idade: ${idade}
        Email: ${email}
        Localização: ${estado}, ${pais}
        Descrição: ${descricao}
        Competências: ${competencias?.join(', ') ?: 'Nenhuma'}
        Formação: ${formacao?.join(', ') ?: 'Nenhuma'}
        LinkedIn: ${linkedin}
        ----------------------------------------
        """
    }

    @Override
    void exibirPerfil() {
        println toString()
    }
}