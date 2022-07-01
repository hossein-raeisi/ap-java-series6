package client.security;

import java.net.http.HttpRequest;

public class Control {
    private static String authToken;

    public static HttpRequest.Builder addSecurityHeaders(HttpRequest.Builder builder) {
        builder.header("Auth-Token", authToken);
        return builder;
    }

    public static void setAuthToken(String authToken) {
        Control.authToken = authToken;
    }
}
