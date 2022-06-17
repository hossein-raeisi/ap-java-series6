package models.game;

import models.user.User;

public class MyThread extends Thread{
    Game game;
    User user;
    public MyThread(Game game, User user){
        game.threads.add(this);
        this.game = game;
        this.user = user;
    }
    @Override
    public void run() {
        super.run();
        while (user.numbers.size()!=0){
            user.play(game.lastNumber);
        }
    }
}
