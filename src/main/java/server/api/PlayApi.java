package server.api;

import server.models.game.Game;
import server.models.game.MyThread;
import server.models.user.Player;
import spark.Request;
import spark.Response;

public class PlayApi {

    public static Response playNumber(Request request, Response response) {
        // TODO
        Player player = null;//TODO get from request
        Game game = null; //TODO get from request
        int number = 0;//TODO get from request

        final boolean[] result = new boolean[1];

        Thread thread = new Thread(() -> result[0] = game.update(player.play(number),false));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;//TODO return result as response
    }

    public static Response playNinja(Request request, Response response) {
        // TODO
        Game game = null;//TODO get from request
        final boolean[] result = new boolean[1];
        Thread thread = new Thread(() -> result[0] = game.useNinja());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;//TODO return result as response
    }
}
