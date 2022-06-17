package server.models.user;
import server.models.card.Number;

public class Bot extends User{


    @Override
    public Number play(Number lastNumber) {
        super.play(lastNumber);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.numbers.get(0);
    }
}
