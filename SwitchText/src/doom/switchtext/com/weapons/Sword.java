package doom.switchtext.com.weapons;

public final class Sword extends Weapons {
    String name;
    int damage, accuracy, possibleHits;
    public Sword() {
        this.name = "sword";
        this.damage = 450;
        this.accuracy = 250;
        this.possibleHits = 1;
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
