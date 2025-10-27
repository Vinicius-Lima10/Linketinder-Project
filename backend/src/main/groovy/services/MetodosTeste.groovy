package services

import dao.Conexao

class MetodosTeste {
    static def inserirERetornarId(dao, entity, atributoUnico, valorUnico) {
        dao.inserir(entity)
        dao.listarTodos().find { it."$atributoUnico" == valorUnico }.id
    }

    static def atualizarEVerificar(dao, id, campo, valorEsperado) {
        dao.atualizarCampo(id, campo, valorEsperado)
        assert dao.listarTodos().find { it.id == id }."$campo" == valorEsperado
    }

    static def deletarEVerificar(dao, id) {
        dao.deletar(id)
        assert !dao.listarTodos().any { it.id == id }
    }

    static def testarCRUD(daoClass, entity, atributoUnico, valorUnico, campoParaAtualizar = 'descricao', novoValor = null) {
        Conexao.withConnection { sql ->
            def dao = daoClass.newInstance(sql)

            def id = inserirERetornarId(dao, entity, atributoUnico, valorUnico)
            assert dao.listarTodos().any { it.id == id }

            atualizarEVerificar(dao, id, campoParaAtualizar, novoValor ?: (entity."$campoParaAtualizar" + ' atualizado'))

            deletarEVerificar(dao, id)
        }
    }

}
