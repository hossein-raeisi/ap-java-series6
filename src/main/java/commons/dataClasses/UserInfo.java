package commons.dataClasses;

public class UserInfo {

    public enum Type {
        user,
        bot,
    }

    public String id;
    public String name;
    public Type type;
    public Integer[] cards;
    public int cardsNumber;

    public UserInfo(String id,String name, Type type, Integer[] cards, int cardsNumber) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cards = cards;
        this.cardsNumber = cardsNumber;
    }
}
