package doom.switchtext.com.weapons;

public final class Rifle extends Weapons {
    String name;
    int damage, accuracy, possibleHits;
    public Rifle() {
        this.name = "rifle";
        this.damage = 12;
        this.accuracy = 80;
        this.possibleHits = 3;
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
