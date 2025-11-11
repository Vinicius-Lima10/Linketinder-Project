package server

import com.sun.net.httpserver.HttpServer
import controllers.CandidatoController
import controllers.EmpresaController
import controllers.VagaController



class Server {

    static HttpServer server

    static void start() {
        server = HttpServer.create(new InetSocketAddress(8080), 0)

        server.createContext("/candidatos") { exchange ->
            CandidatoController.handleRequest(exchange)
        }

        server.createContext("/empresas") { exchange ->
            EmpresaController.handleRequest(exchange)
        }

        server.createContext("/vagas") { exchange ->
            VagaController.handleRequest(exchange)
        }

        server.start()
        println "Servidor iniciado em http://localhost:8080/"
    }

    static void stop() {
        if (server != null) {
            server.stop(0)
            println "Servidor HTTP encerrado"
        }
    }
}
