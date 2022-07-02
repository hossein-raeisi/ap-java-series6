package client;

import commons.dataClasses.UserInfo;
import commons.dataClasses.GameInfo;
import client.security.Control;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class Client {
    private static Client client;
    private GameInfo gameInfo;
    private UserInfo userInfo;

    private static final int SERVER_PORT = 5555;
    private static final String baseAddress = "http://localhost:" + SERVER_PORT + "/api";

    public enum Apis {
        Test_Hello,

        User_GetUserInfo,

        Game_CreateGame,
        Game_JoinGame,
        Game_GetUsersInfo,
        Game_IsUpdated,
        Game_GameInfo,

        Play_PlayNumber,
        Play_PlayNinja,

        Auth_Login,
        Auth_Logout,
    }

    private static HashMap<Apis, String> urls = new HashMap<>();

    HttpClient httpClient;

    public static Client getAnInstance() {
        if (client == null) {
            client = new Client();
        }

        return client;
    }

    private Client() {
        httpClient = HttpClient.newHttpClient();
        mapUrls();
    }

    public static void mapUrls() {
        urls.put(Apis.Test_Hello, "/test/hello");

        urls.put(Apis.User_GetUserInfo, "/user/getUserInfo");

        urls.put(Apis.Game_CreateGame, "/game/createGame");
        urls.put(Apis.Game_JoinGame, "/game/joinGame");
        urls.put(Apis.Game_GetUsersInfo, "/game/getUsersInfo");
        urls.put(Apis.Game_IsUpdated, "/game/isUpdated");
        urls.put(Apis.Game_GameInfo, "/game/gameInfo");

        urls.put(Apis.Play_PlayNumber, "/play/playNumber");
        urls.put(Apis.Play_PlayNinja, "/play/playNinja");

        urls.put(Apis.Auth_Login, "/auth/login");
        urls.put(Apis.Auth_Logout, "/auth/logout");
    }

    public HttpResponse<String> sendRequest(Apis api, HttpRequest.Builder requestBuilder) {
        try {
            return httpClient.send(requestBuilder.uri(URI.create(urls.get(api))).build(),
                                   HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpResponse<String> sendSecureRequest(Apis api, HttpRequest.Builder requestBuilder) {
        try {
            return httpClient.send(Control.addSecurityHeaders(requestBuilder.uri(URI.create(urls.get(api)))).build(),
                                   HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public UserInfo getUserInfo() { return userInfo; }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
