package client;

import client.security.Control;

public class Main {

    public static void main(String[] args) {
        Control.logIn();


    }
}

/*
        Client client = Client.getAnInstance();
        var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(""));
        var response = client.sendSecureRequest(Client.Apis.Game_CreateGame, request);

        response.body()
 */
