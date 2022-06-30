package server.models.game;
import server.models.user.Bot;
import server.models.user.Player;
import server.models.user.User;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Game {
    ArrayList<User> users;
    final ArrayList<Integer> inPlayNumbers = new ArrayList<>();
    final ArrayList<MyThread> threads = new ArrayList<>();
    int life;
    int ninjaNumber = 2;
    Semaphore semaphore = new Semaphore(1);
    volatile int lastNumber;
    int level = 0;
    public LocalTime time;
    public Game(ArrayList<User> users){
        this.users = users;
        life = users.size();
        int maxLevel = 100/life;
        while (++level <= maxLevel) {
            time = LocalTime.now();
            if(Arrays.asList(3,6,9).contains(level)) life++;
            if(Arrays.asList(2,5,8).contains(level)) ninjaNumber++;

            setCards();
            setAndRunThreads();
        }
    }
    void setCards(){
        int size = level * users.size();
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() <= size){
            int random = new Random().nextInt(100) + 1;
            if(randomNumbers.contains(random)) continue;
            randomNumbers.add(random);
        }
        for (int i = 0; i < users.size(); i++) {
            users.get(i).numbers = new ArrayList<>(randomNumbers.subList(i*level,(i+1)*level));
        }
    }
    void setAndRunThreads(){
        threads.clear();
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
    void update(int number,boolean isNinja){//number must be removed from its owner list
        boolean flag = false;
        for (User user :
                users) {
            flag = flag || user.numbers.removeIf(n -> n < number);
        }
        if(flag&&(!isNinja)){
            loseLife();
        }
        inPlayNumbers.add(number);
        lastNumber = number;
        semaphore.release();
        print();
    }

    void useNinja(){
        int max =0;
        for (User user :
                users) {
            int min = Math.max(max,user.numbers.get(0));
            user.numbers.remove(Integer.valueOf(min));
            max = Math.max(min,max);
        }
        update(max,true);
    }

    void print(){
        System.out.println("level:" + level +
                "players:" + users.size() +
                "heart:" + life +
                "\tNinja:" + ninjaNumber +
                "\tlast card number:"+lastNumber);
        Player player = null;
        for (User user:
             users) {
            if(user instanceof Bot){
                System.out.println(user);
            }else player = (Player) user;
        }
        assert player != null;
        System.out.println("Your cards:" + player.numbers );
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
