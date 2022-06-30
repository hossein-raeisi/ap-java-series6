package server;

import static spark.Spark.*;

import server.api.GameApi;
import server.api.UserApi;
import server.security.SecurityApi;

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
        mapUrls();
    }

    public void mapUrls() {
        path("/api", () -> {
            path("/user", () -> {
                get("/getUserInfo", (request, response) -> UserApi.getUserInfo(request));
            });

            path("/game", () -> {
                post("/createGame", (request, response) -> GameApi.createGame(request));
                post("/joinGame", (request, response) -> GameApi.joinGame(request));
                get("/getUsersInfo", (request, response) -> GameApi.getUsersInfo(request));
                get("isUpdated", (request, response) -> GameApi.isUpdated(request));
            });

            path("/play", () -> {

            });

            path("/auth", () -> {
                get("/login", (request, response) -> SecurityApi.login(request));
                post("/logout", (request, response) -> SecurityApi.logout(request));
            });

        });
    }
}
