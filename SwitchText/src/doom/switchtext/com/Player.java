package doom.switchtext.com;

public class Player {
    String skin;
    int health;

    Location pLocation;

    public Player(String skin, int health, Location pLocation) {
        this.skin = skin;
        this.health = health;
        this.pLocation = pLocation;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Location getpLocation() {
        return pLocation;
    }

    public void setpLocation(Location pLocation) {
        this.pLocation = pLocation;
    }
}
