package server;

import static spark.Spark.*;

import server.api.GameApi;
import server.api.PlayApi;
import server.api.UserApi;
import server.security.SecurityApi;
import server.security.Control;
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
        mapUrls();
    }

    public void mapUrls() {
        path("/api", () -> {
            before("/*", (request, response) -> Control.validateRequest(request));

            path("/test", () -> {
                get("/hello", Server::TestApi);
            });

            path("/user", () -> {
                get("/getUserInfo", UserApi::getUserInfo);
            });

            path("/game", () -> {
                post("/createGame", GameApi::createGame);
                post("/joinGame", GameApi::joinGame);
                get("/getUsersInfo", GameApi::getUsersInfo);
                get("/isUpdated", GameApi::isUpdated);
                get("/gameInfo", GameApi::getGameInfo);
            });

            path("/play", () -> {
                post("/playNumber", PlayApi::playNumber);
                post("/playNinja", PlayApi::playNinja);
            });

            path("/auth", () -> {
                get("/login", SecurityApi::login);
                post("/logout", SecurityApi::logout);
            });
        });
    }

    public static String TestApi(Request request, Response response) {
        System.out.println(request.session().isNew());

        response.body("fuck you");
        return response.body();
    }
}
