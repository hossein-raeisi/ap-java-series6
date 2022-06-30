package server.models.game;

import server.models.user.User;

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
            int number = user.play(game.lastNumber);
            try {
                game.semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(number != 1)
                game.update(number,false);
            else game.useNinja();
        }
    }
}
