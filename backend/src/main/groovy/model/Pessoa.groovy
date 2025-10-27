package model

import interfaces.IPessoa

abstract class Pessoa implements IPessoa {
    String nome
    String email
    String estado
    String cep
    String descricao
}