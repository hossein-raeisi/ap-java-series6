package client;

import client.security.Control;

public class Main {

    public static void main(String[] args) {
        Config.loadConfig();
        Control.logIn();

        Controller.checkForCreateOrJoin();

        Thread thread = new Thread(() -> {
            while (Controller.isNotOver()) {
                Controller.getInputAndSend();
            }
        });
        thread.start();

        while (Controller.isNotOver()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Controller.checkUpdate();
        }
        Console.getInstance().printGameResult(Client.getAnInstance().getGameInfo().life != 0);
    }
}

/*
        Client client = Client.getAnInstance();
        var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(""));
        var response = client.sendSecureRequest(Client.Apis.Game_CreateGame, request);

        response.body()
 */
