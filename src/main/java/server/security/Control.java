package server.security;

import server.models.game.Game;
import server.models.user.Player;
import spark.Request;
import spark.Session;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class Control {
    static HashMap<String, Session> authTokens = new HashMap<>();
    static HashMap<Session, Player> playerSessions = new HashMap<>();
    static ArrayList<Game> activeGames = new ArrayList<>();

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static void validateRequest(Request request) {
        // TODO
    }

    public static String generateAuthToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public static void addAuthToken(Session session, String authToken) {
        authTokens.put(authToken, session);
    }

    public static void addPlayer(Session session, Player player) {
        playerSessions.put(session, player);
    }

    public static void removeAuthToken(Session session) {
        // TODO
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
        Session session = authTokens.get(authToken);
        return playerSessions.get(session);
    }
}
