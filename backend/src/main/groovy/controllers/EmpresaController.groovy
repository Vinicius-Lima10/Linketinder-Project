package controllers

import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import model.Empresa
import services.EmpresaService

class EmpresaController {

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
            Empresa empresa = gson.fromJson(body, Empresa)

            adicionarEmpresa(empresa)

            respond(exchange, 201, """{"message":"Empresa criada"}""")
        } catch (Exception e) {
            respond(exchange, 500, """{"error":"${e.message}"}""")
        }
    }

    private static void handleGet(HttpExchange exchange) {
        try {
            List<Empresa> empresas = listarEmpresas()
            respond(exchange, 200, gson.toJson(empresas))
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
            removerEmpresa(id)

            respond(exchange, 200, """{"message":"Empresa removida"}""")
        } catch (Exception e) {
            respond(exchange, 500, """{"error":"${e.message}"}""")
        }
    }

    private static void respond(HttpExchange exchange, int status, String body) {
        exchange.responseHeaders.add("Content-Type", "application/json")
        exchange.sendResponseHeaders(status, body.bytes.length)
        exchange.responseBody.withStream { it.write(body.bytes) }
    }

    static void adicionarEmpresa(Empresa empresa) throws Exception {
        try {
            EmpresaService.adicionarEmpresa(empresa)
        } catch (Exception e) {
            throw e
        }
    }

    static List<Empresa> listarEmpresas() throws Exception {
        try {
            return EmpresaService.listarEmpresas()
        } catch (Exception e) {
            throw e
        }
    }

    static void removerEmpresa(int id) throws Exception {
        try {
            EmpresaService.removerEmpresa(id)
        } catch (Exception e) {
            throw e
        }
    }

    static void atualizarCampo(int id, String campo, Object novoValor) throws Exception {
        try {
            EmpresaService.atualizarCampo(id, campo, novoValor)
        } catch (Exception e) {
            throw e
        }
    }

    static void removerEmpresaPorCNPJ(String cnpj) throws Exception {
        try {
            int empresa_id = buscarIDPorCNPJ(cnpj)
            removerEmpresa(empresa_id)
        } catch (Exception e) {
            throw e
        }
    }

    static Integer buscarIDPorCNPJ(String cnpj) throws Exception {
        try {
            List<Empresa> empresas = EmpresaService.listarEmpresas() as List<Empresa>
            Empresa empresa = empresas.find { it.cnpj == cnpj }
            if (!empresa) throw new Exception()
            return empresa.id
        } catch (Exception e) {
            throw e
        }
    }
}
