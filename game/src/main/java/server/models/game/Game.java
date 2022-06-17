package models.game;

import models.card.Ninja;
import models.user.User;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Number> outNumbers = new ArrayList<>();
    ArrayList<Number> inPlayNumbers = new ArrayList<>();
    ArrayList<Ninja> ninjas = new ArrayList<>(Arrays.asList(new Ninja(), new Ninja()));
}
