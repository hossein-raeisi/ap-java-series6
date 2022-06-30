package client.security;

import java.net.http.HttpRequest;

public class Control {
    private static String token;

    public static HttpRequest.Builder addSecurityHeaders(HttpRequest.Builder builder) {
        builder.header("token", token);
        return builder;
    }

    public static void setToken(String token) {
        Control.token = token;
    }
}
