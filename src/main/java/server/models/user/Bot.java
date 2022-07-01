package server.models.user;



public class Bot extends User{
    public int lastNumber = 0;

    public int play(int lastNumber) {
        try {
            Thread.sleep((numbers.get(0)-lastNumber)* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int number = this.numbers.get(0);
        this.numbers.remove(Integer.valueOf(number));
        this.lastNumber = number;
        return number;
    }

    @Override
    public String toString() {
        return "Bot{" +
                "id=" + id +
                "cards number=" + numbers.size()+
                '}';
    }
}
