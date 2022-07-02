package server.helpers;

import com.google.gson.Gson;
import commons.dataClasses.GameInfo;
import commons.dataClasses.UserInfo;
import server.models.game.Game;
import server.models.user.Bot;
import server.models.user.Player;
import server.models.user.User;

import java.util.ArrayList;

public class GameHelper {
    public static String createGame(Player player, int botNumbers) {
        ArrayList<User> users = new ArrayList<>();
        users.add(player);
        for (int i = 0; i < botNumbers; i++) {
            users.add(new Bot());
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
        boolean isTheSame = gameInfo.equals(clientGameInfo);
        return isTheSame + "";
    }


}
