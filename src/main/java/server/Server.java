package server;

import static spark.Spark.*;

import server.security.SecurityApi;
import spark.Request;
import spark.Response;

public class Server {
    public static Server server;

    public static Server getAnInstance() {
        if (server == null) {
            server = new Server();
        }

        return server;
    }

    public void start(int server_port) {
        port(server_port);

        path("/api", () -> {
            path("/user", () -> {

            });

           path("/game", () -> {

           });

           path("/auth", () -> {
               get("login", SecurityApi.login);
           });

        });

    }
}
