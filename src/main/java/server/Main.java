package server;

public class Main {

    public static void main(String[] args) {
        Server server = Server.getAnInstance();
        Config.loadConfig();
        server.start(Config.SERVER_PORT);
    }
}
