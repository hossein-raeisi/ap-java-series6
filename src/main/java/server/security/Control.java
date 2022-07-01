package server.security;

import server.models.game.Game;
import server.models.user.Player;
import spark.Request;
import spark.Session;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import static spark.Spark.halt;

public class Control {
    static HashMap<String, Player> authTokens = new HashMap<>();
    static ArrayList<Game> activeGames = new ArrayList<>();

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static void validateRequest(Request request) {
        String authToken = request.headers("Auth-Token");

        if (authToken == null) {
            if (request.uri().equals("/api/auth/login")) {
                return;
            }
            halt(403);
        }

        if (request.uri().equals("/api/auth/login")) {
            halt(405);
        }

        if (authTokens.get(authToken).id.equals(request.headers("Player-Id"))) {
            return;
        }

        halt(400);
    }

    public static String generateAuthToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public static void addAuthToken(String authToken, Player player) {
        authTokens.put(authToken, player);
    }

    public static void removeAuthToken(String authToken) {
        authTokens.remove(authToken);
    }

    public static Game getGameFromAuthToken(String authToken) {
        Player player = getPlayerFromAuthToken(authToken);

        for (Game game: activeGames) {
            if (game.getUsers().contains(player)) {
                return game;
            }
        }

        return null;
    }

    public static Player getPlayerFromAuthToken(String authToken) {
        return authTokens.get(authToken);
    }
}
