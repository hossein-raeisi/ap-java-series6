package server;

public class Main {
    public static final int SERVER_PORT = 5555;

    public static void main(String[] args) {
        Server server = Server.getAnInstance();
        server.start(SERVER_PORT);
    }
}
