package commons.dataClasses;

public class UserInfo {

    public enum Type {
        user,
        bot,
    }

    public int id;
    public Type type;
    public int[] cards;
    public int cardsNumber;
}
