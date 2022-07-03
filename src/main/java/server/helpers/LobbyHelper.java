package server.helpers;

import server.api.LobbyApi;
import server.models.game.Game;
import server.models.user.Player;

public class LobbyHelper {
    public static String isStarted(Player player, Game game){
        if(! LobbyApi.lobbyPlayers.contains(player)) {
            String gameInfoString = GameHelper.toString(GameHelper.getGameInfo(game));
            return "2"+gameInfoString;  //Game has started and player is not host
        }
        if(LobbyApi.lobbyPlayers.get(0).equals(player)) return "1"; ///Player should be the host and create game
        return "0"; //Player should wait for host to create the game
    }
}
