package server.helpers;

import com.google.gson.Gson;
import commons.dataClasses.GameInfo;
import commons.dataClasses.UserInfo;
import server.api.LobbyApi;
import server.models.game.Game;
import server.models.user.Bot;
import server.models.user.Player;
import server.models.user.User;

import java.util.ArrayList;

public class GameHelper {
    public static String createGame(Player player, int otherUsersNumber) {
        int size = otherUsersNumber + 1;

        ArrayList<User> users = new ArrayList<>();
        users.add(player);
        LobbyApi.removePlayerFromLobby(player);
        while (users.size() < size){
            if(LobbyApi.lobbyPlayers.size() > 0){
                Player player1 = LobbyApi.lobbyPlayers.get(0);
                users.add(player1);
                LobbyApi.removePlayerFromLobby(player1);
            }else {
                users.add(new Bot());
            }
        }
        Game game = new Game(users);
        return toString(getGameInfo(game));
    }

    public static GameInfo getGameInfo(Game game) {
        return new GameInfo(game.getLevel(), game.getUsers().size(), game.getLife(), game.getNinjaNumber(), game.lastNumber);
    }

    public static <T> String toString(T t) {
        return new Gson().toJson(t);
    }

    public static String getUsersInfo(Game game) {
        ArrayList<UserInfo> usersInfo = new ArrayList<>();
        for (User user :
                game.getUsers()) {
            usersInfo.add(UserHelper.getUserInfo(user));
        }
        return toString(usersInfo);
    }

    public static String isUpdated(Game game, String clientGameInfo) {
        String gameInfo = toString(getGameInfo(game));
        boolean isUpdated = ! gameInfo.equals(clientGameInfo);
        return isUpdated + "";
    }


}
