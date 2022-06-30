package server;

import static spark.Spark.*;

import spark.Request;
import spark.Response;
import spark.Route;

public class Main {
    public static final int SERVER_PORT = 5555;

    public static void main(String[] args) {
        port(SERVER_PORT);

        get("/hello", Main::handleRequest);
    }

    public static String handleRequest(Request request, Response response) {
        return null;
    }

}
