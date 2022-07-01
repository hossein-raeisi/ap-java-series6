package server.models.user;

public class Player extends User{

    public int play(int number) {
        if(this.numbers.contains(number))
            return number;
        return -1;
    }
}
