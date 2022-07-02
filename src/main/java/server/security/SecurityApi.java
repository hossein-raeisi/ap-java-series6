package server.security;

import server.models.user.Player;
import spark.Request;
import spark.Response;

public class SecurityApi {

    public static String login(Request request, Response response) {
        String authToken = Control.generateAuthToken();
        Player player = new Player();
        Control.addAuthToken(authToken, player);

        response.header("Auth-Token", authToken);
        response.header("Player-Id", player.id);
        response.status(200);
        response.body("logged in successfully");

        return response.body();
    }

    public static String logout(Request request, Response response) {
        Control.removeAuthToken(request.headers("Auth-Token"));

        response.status(200);
        response.body("logged out successfully");

        return response.body();
    }
}
