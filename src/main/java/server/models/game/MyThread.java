package server.models.game;

import server.models.user.Bot;
import server.models.user.User;

public class MyThread extends Thread{
    Game game;
    Bot bot;
    public MyThread(Game game, User user){
        game.threads.add(this);
        this.game = game;
        this.bot = (Bot) user;
    }
    @Override
    public void run() {
        super.run();
        while (bot.numbers.size()!=0){
            int number = bot.play(game.lastNumber);
            game.update(number,false);
        }
    }
}
