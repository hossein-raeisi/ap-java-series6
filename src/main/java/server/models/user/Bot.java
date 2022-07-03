package server.models.user;


import server.models.game.MyThread;

public class Bot extends User {
    public int lastNumber = 0;
    public Bot(){
        super();
        this.name = id;
    }
    public int play(int lastNumber) {
        try {
            Thread.sleep((numbers.get(0) - lastNumber) * 1000L);
        } catch (InterruptedException e) {
            MyThread thread = (MyThread) Thread.currentThread();
            thread.running = false;
            return -1;
        }
        if(this.numbers.size() == 0) return -1;
        int number = this.numbers.get(0);
        this.numbers.remove(Integer.valueOf(number));
        this.lastNumber = number;
        return number;
    }

    @Override
    public String toString() {
        return "Bot{" +
                "id=" + id +
                "cards number=" + numbers.size() +
                '}';
    }
}
