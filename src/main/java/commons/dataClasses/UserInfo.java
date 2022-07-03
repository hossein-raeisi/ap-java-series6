package commons.dataClasses;

public class UserInfo {

    public enum Type {
        user,
        bot,
    }

    public String id;
    public Type type;
    public Integer[] cards;
    public int cardsNumber;

    public UserInfo(String id, Type type, Integer[] cards, int cardsNumber) {
        this.id = id;
        this.type = type;
        this.cards = cards;
        this.cardsNumber = cardsNumber;
    }
}
