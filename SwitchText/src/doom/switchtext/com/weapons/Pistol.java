package doom.switchtext.com.weapons;

public final class Pistol extends Weapons {
    String name;
    int damage, accuracy, possibleHits;
    public Pistol() {
        this.name = "pistol";
        this.damage = 15;
        this.accuracy = 750;
        this.possibleHits = 2;
    }
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getPossibleHits() {
        return possibleHits;
    }
}
