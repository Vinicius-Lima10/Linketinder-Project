package controllers

import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import model.Vagas
import services.VagasService

class VagaController {

    private static final Gson gson = new Gson()

    static void handleRequest(HttpExchange exchange) {
        String method = exchange.requestMethod

        if (method == "POST") {
            handlePost(exchange)
        } else if (method == "GET") {
            handleGet(exchange)
        } else if (method == "DELETE") {
            handleDelete(exchange)
        } else {
            respond(exchange, 405, """{"error":"Método não permitido"}""")
        }
    }

    private static void handlePost(HttpExchange exchange) {
        try {
            String body = exchange.requestBody.text
            Vagas vaga = gson.fromJson(body, Vagas)

            adicionarVaga(vaga)

            respond(exchange, 201, """{"message":"Vaga criada"}""")
        } catch (Exception e) {
            respond(exchange, 500, """{"error":"${e.message}"}""")
        }
    }

    private static void handleGet(HttpExchange exchange) {
        try {
            List<Vagas> lista = listarVagas()
            respond(exchange, 200, gson.toJson(lista))
        } catch (Exception e) {
            respond(exchange, 500, """{"error":"${e.message}"}""")
        }
    }

    private static void handleDelete(HttpExchange exchange) {
        try {
            String query = exchange.requestURI.query
            if (!query || !query.contains("id=")) {
                respond(exchange, 400, """{"error":"Parâmetro id obrigatório"}""")
                return
            }

            def id = query.split("id=")[1] as Integer
            removerVagaPorId(id)

            respond(exchange, 200, """{"message":"Vaga removida"}""")
        } catch (Exception e) {
            respond(exchange, 500, """{"error":"${e.message}"}""")
        }
    }

    private static void respond(HttpExchange exchange, int status, String body) {
        exchange.responseHeaders.add("Content-Type", "application/json")
        exchange.sendResponseHeaders(status, body.bytes.length)
        exchange.responseBody.withStream { it.write(body.bytes) }
    }
    static void adicionarVaga(Vagas vaga) throws Exception {
        try {
            VagasService.adicionarVaga(vaga)
        } catch (Exception e) {
            throw e
        }
    }

    static List<Vagas> listarVagas() throws Exception{
        try {
            return VagasService.listarVagas()
        } catch (Exception e) {
            throw e
        }
    }
    static void removerVagaPorId(int id) throws Exception{
        try {
            VagasService.removerVaga(id)
        } catch (Exception e) {
            throw e
        }
    }

    static void removerVagaPorNome(String nome) throws Exception{
        try {
            def lista = VagasService.listarVagas()
            def vaga = lista.find { it.nome.equalsIgnoreCase(nome) }
            VagasService.removerVaga(vaga.id)
        } catch (Exception e) {
            throw e
        }
    }

    static Object buscarPorNome(String nome) throws Exception{
        try {
            def lista = VagasService.listarVagas()
            return lista.find { it.nome.equalsIgnoreCase(nome) }
        } catch (Exception e) {
            throw e
        }
    }
}
