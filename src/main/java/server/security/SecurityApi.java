package server.security;

import spark.Request;
import spark.Response;

public class SecurityApi {

    public static Response login(Request request, Response response) {
        String authToken = Control.generateAuthToken();
//        request.session(true);
        Control.addAuthToken(request.session(), authToken);

        response.header("Auth-Token", authToken);
        response.status(200);
        response.body("logged in successfully");

        return response;
    }

    public static Response logout(Request request, Response response) {
        Control.removeAuthToken(request.session());

        response.status(200);
        response.body("logged out successfully");

        return response;
    }
}
