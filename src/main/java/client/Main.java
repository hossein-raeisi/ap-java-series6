package client;

import client.security.Control;

public class Main {

    public static void main(String[] args) {
        Config.loadConfig();
        Control.logIn();

        Control.createGame();
        Thread thread = new Thread(() -> {
            while (! Control.isOver()){
                Control.getInputAndSend();
            }
        });
        thread.start();

        while (! Control.isOver()){
            Control.checkUpdate();
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
