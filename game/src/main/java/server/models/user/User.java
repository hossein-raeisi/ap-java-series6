package server.models.user;

import java.util.ArrayList;


public abstract class User{
    public int id;
    private static int idCounter = 10000;
    public ArrayList<Integer> numbers = new ArrayList<>();

    public User(){
        id = ++idCounter;
    }


    public int play(int lastNumber){
        return 0;
    }

}