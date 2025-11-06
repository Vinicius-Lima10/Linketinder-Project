package model

class Empresa extends Pessoa {
    Integer id
    String nome
    String cnpj
    String email
    String senha
    String pais
    String estado
    String cep
    String descricao

    @Override
    String toString() {
        return """
        ----------------------------------------
        Empresa: ${nome}
        CNPJ: ${cnpj}
        Email: ${email}
        Localização: ${estado}, ${pais}
        CEP: ${cep}
        Descrição: ${descricao}
        ----------------------------------------
        """
    }

    @Override
    void exibirPerfil() {
        println toString()
    }
}
