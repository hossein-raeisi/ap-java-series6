package server.models.game;

import server.models.user.Bot;
import server.models.user.Player;
import server.models.user.User;
import server.security.Control;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Game {
    final ArrayList<Integer> inPlayNumbers = new ArrayList<>();
    final ArrayList<MyThread> threads = new ArrayList<>();
    public volatile int lastNumber;
    public LocalTime time;
    ArrayList<User> users;
    int life;
    int ninjaNumber = 2;
    Semaphore semaphore = new Semaphore(1);
    int level = 0;

    public Game(ArrayList<User> users) {
        Control.addGame(this);
        this.users = users;
        life = users.size();
        int maxLevel = 100 / users.size();
        Thread thread = new Thread(() -> {
            while (++level <= maxLevel && life > 0 && level < 13) {
                time = LocalTime.now();
                if (Arrays.asList(3, 6, 9).contains(level)) life++;
                if (Arrays.asList(2, 5, 8).contains(level)) ninjaNumber++;

                setCards();
                setAndRunThreads();
                resetBotsTiming();
                lastNumber = 0;
            }
            gameOver();
        });
        thread.start();
    }

    public int getLife() {
        return life;
    }

    public int getNinjaNumber() {
        return ninjaNumber;
    }

    public int getLevel() {
        return level;
    }

    void resetBotsTiming() {
        for (User user :
                users) {
            if (user instanceof Player) continue;
            Bot bot = (Bot) user;
            bot.lastNumber = 0;
        }
    }

    void setCards() {
        int size = level * users.size();
        ArrayList<Integer> randomNumbers = new ArrayList<>();

        while (randomNumbers.size() < size) {
            int random = new Random().nextInt(100) + 1;
            if (randomNumbers.contains(random)) continue;
            randomNumbers.add(random);
        }
        for (int i = 0; i < users.size(); i++) {
            users.get(i).numbers = new ArrayList<>(randomNumbers.subList(i * level, (i + 1) * level));
        }
        for (User user :
                users) {
            Collections.sort(user.numbers);
        }
    }

    void setAndRunThreads() {
        threads.clear();

        for (User user : users) {
            if (user instanceof Bot)
                new MyThread(this, user);
        }
        for (MyThread thread : threads) {
            thread.start();
        }
        for (MyThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean update(int number, boolean isNinja) {
        if (number == -1) return false;
        try {
            this.semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean flag = false;

        for (User user : users) {
            user.numbers.removeIf(n -> n == number);
            flag = flag | user.numbers.removeIf(n -> n < number);
        }
        if (flag && (!isNinja)) {
            loseLife();
        }

        inPlayNumbers.add(number);
        lastNumber = number;
        semaphore.release();
        if(allCardAreUsed()) closeThreads();
        return true;
    }

    private boolean allCardAreUsed(){
        for (User user:
             users) {
            if(user.numbers.size() != 0) return false;
        }
        return true;
    }

    public boolean useNinja() {
        if (this.ninjaNumber == 0) return false;
        this.ninjaNumber--;
        int max = 0;
        for (User user : users) {
            if(user.numbers.size() ==0) continue;
            max = Math.max(max, user.numbers.get(0));
            user.numbers.remove(0);
        }
        update(max, true);
        return true;
    }

    void loseLife() {
        life--;

        if (life == 0) {
            gameOver();
        }
    }
    void closeThreads(){
        for (Thread thread :
                threads) {
            if (thread != Thread.currentThread())
                thread.interrupt();
        }
        Thread.currentThread().interrupt();
    }

    void gameOver() {
        closeThreads();
    }


    public ArrayList<User> getUsers() {
        return users;
    }
}
