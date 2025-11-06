package model

class Vagas {
    Integer id
    String nome
    String descricao
    String endereco
    String cidade
    String estado
    String pais
    Integer empresa_id
    List<String> competencias

    @Override
    String toString() {
        return """
        ----------------------------------------
        Vaga: ${nome}
        Descrição: ${descricao}
        Endereço: ${endereco}, ${cidade} - ${estado}, ${pais}
        Competências requeridas: ${competencias?.join(', ') ?: 'Nenhuma especificada'}
        Empresa ID: ${empresa_id}
        ----------------------------------------
        """
    }
}
