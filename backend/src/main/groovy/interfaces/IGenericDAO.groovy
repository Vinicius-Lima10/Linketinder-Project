package interfaces
interface IGenericDAO<T> {
    def inserir(T entidade)
    List<T> listarTodos()
    void atualizarCampo(int id, String campo, Object novoValor)
    void deletar(int id)
}
