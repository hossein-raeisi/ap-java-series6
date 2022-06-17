package server.models.user;

import java.util.ArrayList;
import server.models.card.Number;

public abstract class User{
    public int id;
    public ArrayList<Number> numbers = new ArrayList<>();

    public Number play(Number lastNumber){
        return null;
    }

}