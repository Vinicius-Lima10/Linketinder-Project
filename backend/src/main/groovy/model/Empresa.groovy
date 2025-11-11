package model

class Empresa extends Pessoa {
    Integer id
    String cnpj
    String senha
    String pais

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
