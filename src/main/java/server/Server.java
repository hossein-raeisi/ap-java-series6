package server;

import server.api.GameApi;
import server.api.LobbyApi;
import server.api.PlayApi;
import server.api.UserApi;
import server.security.Control;
import server.security.SecurityApi;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

import static spark.Spark.*;

public class Server {
    public static Server server;

    public static Server getAnInstance() {
        if (server == null) {
            server = new Server();
        }

        return server;
    }

    public static String TestApi(Request request, Response response) {
        System.out.println(request.session().isNew());

        response.body("fuck you");
        return response.body();
    }

    public void start(int server_port) {
        port(server_port);
        mapUrls();
    }

    public void mapUrls() {
        path("/api", () -> {
            before("/*", (request, response) -> Control.validateRequest(request));

            path("/test", () -> get("/hello", Server::TestApi));

            path("/user", () -> get("/getUserInfo", UserApi::getUserInfo));

            path("/game", () -> {
                post("/createGame", GameApi::createGame);
                post("/joinGame", GameApi::joinGame);
                get("/getUsersInfo", GameApi::getUsersInfo);
                post("/isUpdated", GameApi::isUpdated);
                get("/gameInfo", GameApi::getGameInfo);
            });

            path("/lobby", () -> get("/isStarted", LobbyApi::isStarted));

            path("/play", () -> {
                post("/playNumber", PlayApi::playNumber);
                post("/playNinja", PlayApi::playNinja);
            });

            path("/auth", () -> {
                post("/login", SecurityApi::login);
                post("/logout", SecurityApi::logout);
            });
        });
    }
}
