package client;

import commons.dataClasses.UserInfo;
import commons.dataClasses.GameInfo;

public class Client {
    public static Client client;

    public static Client getAnInstance() {
        if (client == null) {
            client = new Client();
        }

        return client;
    }

    public GameInfo getGameInfo() {
        return null;
    }

    public UserInfo getUserInfo() {
        return null;
    }
}
