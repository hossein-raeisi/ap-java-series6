package server.api;

import server.helpers.PlayHelper;
import server.models.game.Game;
import server.models.user.Player;
import server.security.Control;
import spark.Request;
import spark.Response;

public class PlayApi {

    public static String playNumber(Request request, Response response) {
        Player player = Control.getPlayerFromAuthToken(request.headers("Auth-Token"));
        Game game = Control.getGameFromAuthToken(request.headers("Auth-Token"));
        int number = Integer.parseInt(request.body());

        response.body(String.valueOf(PlayHelper.playNumber(player, game, number)));

        return response.body();
    }

    public static String playNinja(Request request, Response response) {
        Game game = Control.getGameFromAuthToken(request.headers("Auth-Token"));

        response.body(String.valueOf(PlayHelper.playNinja(game)));

        return response.body();
    }
}
