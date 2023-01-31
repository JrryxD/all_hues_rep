package doom.switchtext.com;

public class Location {

    int x, y;

    String doortyp;

    public Location(int x, int y)
    {
     this.x = x;
     this.y = y;
    }

    public Location(int x, int y, String doortyp)
    {
        this.x = x;
        this.y = y;
        this.doortyp = doortyp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDoortyp() {
        return doortyp;
    }
}
