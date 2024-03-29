package client.security;

import client.Client;
import client.Console;

import java.net.http.HttpRequest;

public class Control {
    private static String authToken;
    private static String playerId;

    public static HttpRequest.Builder addSecurityHeaders(HttpRequest.Builder builder) {
        builder.header("Auth-Token", authToken);
        builder.header("Player-Id", playerId);
        return builder;
    }

    public static void setAuthToken(String authToken) {
        Control.authToken = authToken;
    }

    public static void setPlayerId(String playerId) {
        Control.playerId = playerId;
    }

    public static void logIn() {
        Client client = Client.getAnInstance();
        String name = Console.getInstance().getName();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(name));
        var response = client.sendRequest(Client.Apis.Auth_Login, requestBuilder);

        authToken = String.valueOf(response.headers().firstValue("auth-token").stream().toArray()[0]);
        playerId = String.valueOf(response.headers().firstValue("player-id").stream().toArray()[0]);

        setAuthToken(authToken);
        setPlayerId(playerId);
    }
}
