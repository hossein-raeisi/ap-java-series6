package server.security;

import spark.Request;
import spark.Session;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

public class Control {
    static HashMap<Session, String> authTokens = new HashMap<>();
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
        authTokens.put(session, authToken);
    }

    public static void removeAuthToken(Session session) {
        authTokens.remove(session);
    }
}
