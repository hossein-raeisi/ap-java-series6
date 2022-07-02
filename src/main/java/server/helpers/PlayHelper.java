package server.helpers;

import server.models.game.Game;
import server.models.user.Player;

import static spark.Spark.halt;

public class PlayHelper {

    public static boolean playNumber(Player player, Game game, int number) {
        final boolean[] result = new boolean[1];

        Thread thread = new Thread(() -> result[0] = game.update(player.play(number), false));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            halt(500);
        }

        return result[0];
    }

    public static boolean playNinja(Game game) {
        final boolean[] result = new boolean[1];

        Thread thread = new Thread(() -> result[0] = game.useNinja());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            halt(500);
        }

        return result[0];
    }
}
