package server.models.game;
import server.models.card.Level;
import server.models.card.Ninja;
import server.models.card.Number;
import server.models.user.User;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    final ArrayList<User> users = new ArrayList<>();
    final ArrayList<Number> outNumbers = new ArrayList<>();
    final ArrayList<Number> inPlayNumbers = new ArrayList<>();
    final ArrayList<Ninja> ninjas = new ArrayList<>(Arrays.asList(new Ninja(), new Ninja()));
    final ArrayList<MyThread> threads = new ArrayList<>();
    int life;
    volatile Number lastNumber;
    Level level;
    public Game(){
        life = users.size();
        int maxLevel = 100/life;
        for (User user :
                users) {
            new MyThread(this, user);
        }
        for (MyThread thread :
                threads) {
            thread.start();
        }
        for (MyThread thread :
                threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    void update(Number number){//number must be removed from its owner list
        ArrayList<Number> usersLowerNumbers = new ArrayList<>();
        boolean flag = false;
        for (User user :
                users) {
            flag = flag || user.numbers.removeIf(n -> n.value < number.value);
        }
        if(flag){
            loseLife();
        }
        lastNumber = number;
    }


    void loseLife(){
        life--;
        if(life == 0){
            gameOver();
        }
    }

    void gameOver(){

    }
}
