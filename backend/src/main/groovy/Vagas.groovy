import groovy.transform.ToString
@ToString(includeNames = true)
class Vagas {
    String nome
    String descricao
    String endereco
    String cidade
    String estado
    String pais
    Integer empresa_id
    List<String> competencias
}