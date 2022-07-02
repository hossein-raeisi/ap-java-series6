package server.api;

import commons.dataClasses.GameInfo;
import server.helpers.GameHelper;
import server.models.game.Game;
import server.models.user.Player;
import server.security.Control;
import spark.Request;
import spark.Response;

public class GameApi {

    public static String createGame(Request request, Response response) {

        Player player = Control.getPlayerFromAuthToken(request.headers("Auth-Token"));
        int botNumbers = Integer.parseInt(request.body());

        response.body(GameHelper.createGame(player, botNumbers));
        return response.body();
    }

    public static String joinGame(Request request, Response response) {
        // TODO
        return response.body();
    }

    public static String getUsersInfo(Request request, Response response) {
        Game game = Control.getGameFromAuthToken(request.headers("Auth-Token"));

        assert game != null;
        response.body(GameHelper.getUsersInfo(game));
        return response.body();
    }

    public static String isUpdated(Request request, Response response) {
        Game game = Control.getGameFromAuthToken(request.headers("Auth-Token"));
        String clientGameInfo = request.body();
        response.body(GameHelper.isUpdated(game, clientGameInfo));
        return response.body();
    }

    public static String getGameInfo(Request request, Response response) {
        Game game = Control.getGameFromAuthToken(request.headers("Auth-Token"));
        assert game != null;
        GameInfo gameInfo = GameHelper.getGameInfo(game);
        response.body(GameHelper.toString(gameInfo));
        return response.body();
    }
}
