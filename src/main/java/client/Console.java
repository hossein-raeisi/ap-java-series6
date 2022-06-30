package client;

import client.exceptions.WrongUserType;
import commons.dataClasses.GameInfo;
import commons.dataClasses.UserInfo;
import server.models.user.Bot;
import server.models.user.Player;
import server.models.user.User;

import java.util.ArrayList;
import java.util.Arrays;

public class Console {

    void printGame(GameInfo gameInfo){
        System.out.println(
                "level:" + gameInfo.level +
                "players:" + gameInfo.usersNumber +
                "heart:" + gameInfo.life +
                "\tNinja:" + gameInfo.ninjaNumber +
                "\tlast card number:" + gameInfo.lastNumber);

        // TODO users should be printed right after game gets printed
    }

    void printUsers(ArrayList<UserInfo> usersInfo, int clientPlayerId) {
        UserInfo clientPlayerInfo = new UserInfo();

        for (UserInfo userInfo: usersInfo) {
            if (userInfo.id == clientPlayerId) {
                clientPlayerInfo = userInfo;
            }

            printUser(userInfo);
        }

        printClientPlayer(clientPlayerInfo);
    }

    void printUser(UserInfo userInfo) {
        System.out.println(
                "User {" +
                "id: " + userInfo.id +
                "cards number: " + userInfo.cardsNumber +
                "}"
        );
    }

    void printClientPlayer(UserInfo userInfo) {
        System.out.println(
                "your cards: " + Arrays.toString(userInfo.cards)
        );
    }
}
