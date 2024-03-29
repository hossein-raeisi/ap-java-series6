package server.models.user;

import java.util.ArrayList;
import java.util.UUID;


public abstract class User {
    public String name;
    public String id;
    volatile public ArrayList<Integer> numbers = new ArrayList<>();

    public User() {
        id = generateId();
    }

    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}