package server.security;

import spark.Request;
import spark.Session;

import java.util.HashMap;

import static spark.Spark.halt;

public class Control {
    static HashMap<Session, String> tokens;

    public static void validateRequest(Request request) {
        if (request.session().isNew() && request.contextPath().equals("api/auth/login")) {
            return;
        }

        if (tokens.get(request.session()).equals(request.headers("token"))) {
            return;
        }

        halt(401);

    }
}
