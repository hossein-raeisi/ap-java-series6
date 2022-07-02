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
    public boolean isOver(){
        if(life == 0) return true;
        return level > 100 / usersNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInfo gameInfo = (GameInfo) o;
        return level == gameInfo.level && usersNumber == gameInfo.usersNumber && life == gameInfo.life && ninjaNumber == gameInfo.ninjaNumber && lastNumber == gameInfo.lastNumber;
    }
}
