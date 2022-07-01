package server.security;

import server.models.user.Player;
import spark.Request;
import spark.Response;

public class SecurityApi {

    public static Response login(Request request, Response response) {
        String authToken = Control.generateAuthToken();
        Player player = new Player();
        Control.addAuthToken(authToken, player);

        response.header("Auth-Token", authToken);
        response.status(200);
        response.body("logged in successfully");

        return response;
    }

    public static Response logout(Request request, Response response) {
        Control.removeAuthToken(request.headers("Auth-Token"));

        response.status(200);
        response.body("logged out successfully");

        return response;
    }
}
