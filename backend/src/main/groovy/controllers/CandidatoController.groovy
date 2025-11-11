package controllers

import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import model.Candidato
import services.CandidatoService

class CandidatoController {

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
            Candidato candidato = gson.fromJson(body, Candidato)

            adicionarCandidato(candidato)

            respond(exchange, 201, """{"message":"Candidato criado"}""")
        } catch (Exception e) {
            respond(exchange, 500, """{"error":"${e.message}"}""")
        }
    }

    private static void handleGet(HttpExchange exchange) {
        try {
            List<Candidato> lista = listarCandidatos()
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
            removerCandidato(id)

            respond(exchange, 200, """{"message":"Candidato removido"}""")
        } catch (Exception e) {
            respond(exchange, 500, """{"error":"${e.message}"}""")
        }
    }

    private static void respond(HttpExchange exchange, int status, String body) {
        exchange.responseHeaders.add("Content-Type", "application/json")
        exchange.sendResponseHeaders(status, body.bytes.length)
        exchange.responseBody.withStream { it.write(body.bytes) }
    }

    static void adicionarCandidato(Candidato candidato) throws Exception{
        try {
             CandidatoService.adicionarCandidato(candidato)
        } catch (Exception e) {
            throw e
        }
    }

    static List<Candidato> listarCandidatos() throws Exception {
        try {
            return CandidatoService.listarCandidatos()
        } catch (Exception e) {
            throw e
        }
    }

    static void removerCandidato(int id) throws Exception {
        try {
            CandidatoService.removerCandidato(id)
        } catch (Exception e) {
            throw e
        }
    }

    static void removerCandidatoPorCPF(String cpf) throws Exception{
        try {
            def candidato = buscarPorCPF(cpf)
            CandidatoService.removerCandidato(candidato.id)
        } catch (Exception e) {
            throw e
        }
    }

    static Candidato buscarPorCPF(String cpf) throws Exception{
        try {
            def resultado = CandidatoService.listarCandidatos()
            def candidato = resultado.find { it.cpf == cpf }
            return candidato
        } catch (Exception e) {
            throw e
        }
    }
}
