package client;

import client.security.Control;

public class Main {

    public static void main(String[] args) {
        Config.loadConfig();
        Control.logIn();

        Controller.createGame();
        Thread thread = new Thread(() -> {
            while (!Controller.isOver()) {
                Controller.getInputAndSend();
            }
        });
        thread.start();

        while (!Controller.isOver()) {
            Controller.checkUpdate();
        }
        Console.getInstance().printGameResult(Client.getAnInstance().getGameInfo().life == 0);
    }
}

/*
        Client client = Client.getAnInstance();
        var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(""));
        var response = client.sendSecureRequest(Client.Apis.Game_CreateGame, request);

        response.body()
 */
