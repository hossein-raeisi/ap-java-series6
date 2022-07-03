package server.api;

import server.helpers.GameHelper;
import server.helpers.LobbyHelper;
import server.models.game.Game;
import server.models.user.Player;
import server.security.Control;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

public class LobbyApi {
    public static ArrayList<Player> lobbyPlayers = new ArrayList<>();

    public static void addPlayerToLobby(Player player){
        lobbyPlayers.add(player);
    }
    public static void removePlayerFromLobby(Player player){
        lobbyPlayers.remove(player);
    }

    public static String isStarted(Request request, Response response){
        Player player = Control.getPlayerFromAuthToken(request.headers("Auth-Token"));
        Game game = Control.getGameFromAuthToken(request.headers("Auth-Token"));
        response.body(LobbyHelper.isStarted(player, game));
        return response.body();
    }
}
