package server.helpers;

import server.api.LobbyApi;
import server.models.user.Player;

public class LobbyHelper {
    public static String isStarted(Player player){
        if(! LobbyApi.lobbyPlayers.contains(player)) return "2";  //Game has started and player is not host
        if(LobbyApi.lobbyPlayers.get(0).equals(player)) return "1"; ///Player should be the host and create game
        return "0"; //Player should wait for host to create the game
    }
}
