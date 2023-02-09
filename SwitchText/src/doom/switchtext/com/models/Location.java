package doom.switchtext.com.models;

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

    @Override
    public String toString() {
        return "x " + getX() + "   y " + getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }
}
