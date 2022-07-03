package client;

import commons.dataClasses.GameInfo;
import commons.dataClasses.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Console {
    private static Console console;
    private final Scanner sc = new Scanner(System.in);

    public static Console getInstance() {
        if (console == null) console = new Console();
        return console;
    }

    public void print(GameInfo gameInfo, ArrayList<UserInfo> usersInfo, String clientPlayerId) {
        printGame(gameInfo);
        printUsers(usersInfo, clientPlayerId);
    }

    public int getBotNumber() {
        System.out.println("enter bot players number");
        return sc.nextInt();
    }

    void printGame(GameInfo gameInfo) {
        System.out.println(
                "level:" + gameInfo.level +
                        "\tplayers:" + gameInfo.usersNumber +
                        "\theart:" + gameInfo.life +
                        "\tNinja:" + gameInfo.ninjaNumber +
                        "\tlast card number:" + gameInfo.lastNumber);

    }


    void printUsers(ArrayList<UserInfo> usersInfo, String clientPlayerId) {
        UserInfo clientPlayerInfo = null;

        for (UserInfo userInfo : usersInfo) {
            if (userInfo.id.equals(clientPlayerId)) {
                clientPlayerInfo = userInfo;
            }

            printUser(userInfo);
        }

        assert clientPlayerInfo != null;
        printClientPlayer(clientPlayerInfo);
    }

    void printUser(UserInfo userInfo) {
        System.out.println(
                "User {" +
                        "name: " + userInfo.name +
                        "\tcards number: " + userInfo.cardsNumber +
                        "}"
        );
    }

    void printClientPlayer(UserInfo userInfo) {
        System.out.println(
                "your cards: " + Arrays.toString(userInfo.cards)
        );
    }

    public int getNumberOrNinja() {
        Scanner sc = new Scanner(System.in);
        int number;
        while (true) {
            String input = sc.next();
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                continue;
            }
            break;
        }
        return number;
    }

    void printGameResult(boolean won) {
        String result = won ? "won" : "lost";
        System.out.println("You " + result);
    }
    public String getName(){
        System.out.println("Please enter your name");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
