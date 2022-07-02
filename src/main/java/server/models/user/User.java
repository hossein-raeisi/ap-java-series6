package server.models.user;

import java.util.ArrayList;


public abstract class User{
    private static int idCounter = 10000;

    public String id;
    public ArrayList<Integer> numbers = new ArrayList<>();

    public User(){
        id = ++idCounter+"";
    }


}