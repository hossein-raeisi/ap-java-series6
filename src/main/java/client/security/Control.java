package client.security;

import client.Client;
import client.Console;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import commons.dataClasses.GameInfo;
import commons.dataClasses.UserInfo;

import java.lang.reflect.Type;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;

public class Control {
    private static String authToken;
    private static String playerId;

    static Gson gson;
    public static HttpRequest.Builder addSecurityHeaders(HttpRequest.Builder builder) {
        builder.header("Auth-Token", authToken);
        builder.header("Player-Id", playerId);
        return builder;
    }

    public static void setAuthToken(String authToken) {
        Control.authToken = authToken;
    }

    public static void setPlayerId(String playerId) {
        Control.playerId = playerId;
    }

    public static void logIn() {
        Client client = Client.getAnInstance();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().GET();
        var response = client.sendRequest(Client.Apis.Auth_Login, requestBuilder);

        authToken = String.valueOf(response.headers().firstValue("auth-token").stream().toArray()[0]);
        playerId = String.valueOf(response.headers().firstValue("player-id").stream().toArray()[0]);
    }

    public static void createGame(){
        int botsNumber = Console.getInstance().getBotNumber();
        Client client = Client.getAnInstance();
        var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(""+botsNumber));
        var response = client.sendSecureRequest(Client.Apis.Game_CreateGame, request);
        client.setGameInfo(gson.fromJson(response.body(), GameInfo.class));
        update();
    }

    public static void getInputAndSend(){//number or ninja
        Client client = Client.getAnInstance();
        int input = Console.getInstance().getNumberOrNinja();
        if(input == -1){
            var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(""));
            var response = client.sendSecureRequest(Client.Apis.Play_PlayNinja,request);
            if(! Boolean.parseBoolean(response.body())) System.out.println("no ninja card");
        }else {
            var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(input+""));
            var response = client.sendSecureRequest(Client.Apis.Play_PlayNumber,request);
            if(! Boolean.parseBoolean(response.body())) System.out.println("card not found");
        }
    }

    public static boolean isOver(){
        return Client.getAnInstance().getGameInfo().isOver();
    }

    public static void checkUpdate(){
        Client client = Client.getAnInstance();
        var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(""));
        var response = client.sendSecureRequest(Client.Apis.Game_IsUpdated,request);
        boolean isUpdated = Boolean.parseBoolean(response.body());
        if(! isUpdated) return;
        update();
    }
    public static void update(){
        Client client = Client.getAnInstance();
        GameInfo gameInfo = getGameInfo();
        UserInfo playerInfo = getPlayerInfo();
        client.setGameInfo(gameInfo);
        client.setUserInfo(playerInfo);
        Console.getInstance().print(gameInfo,usersInfo(),playerInfo.id);
    }


    public static GameInfo getGameInfo(){
        Client client = Client.getAnInstance();
        var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(""));
        var response = client.sendSecureRequest(Client.Apis.Game_GameInfo,request);
        return gson.fromJson(response.body(),GameInfo.class);
    }

    public static UserInfo getPlayerInfo(){
        Client client = Client.getAnInstance();
        var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(""));
        var response = client.sendSecureRequest(Client.Apis.User_GetUserInfo,request);
        return gson.fromJson(response.body(),UserInfo.class);
    }

    public static ArrayList<UserInfo> usersInfo(){
        Client client = Client.getAnInstance();
        var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(""));
        var response = client.sendSecureRequest(Client.Apis.Game_GetUsersInfo,request);
        Type listOfUserInfoClassObject = new TypeToken<ArrayList<UserInfo>>() {}.getType();

        return new ArrayList<>( Arrays.asList(gson.fromJson(response.body(),listOfUserInfoClassObject)));
    }
}
