package commons.dataClasses;

public class GameInfo {
    public int level;
    public int usersNumber;
    public int life;
    public int ninjaNumber;
    public int lastNumber;

    public GameInfo(int level, int usersNumber, int life, int ninjaNumber, int lastNumber) {
        this.level = level;
        this.usersNumber = usersNumber;
        this.life = life;
        this.ninjaNumber = ninjaNumber;
        this.lastNumber = lastNumber;
    }
}
