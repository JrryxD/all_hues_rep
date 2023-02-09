package doom.switchtext.com.weapons;

public final class Stone extends Weapons {
    String name;
    int minDamage, maxDamage, accuracy, possibleHits;
    public Stone() {
        this.name = "stone";
        this.accuracy = 850;
        this.minDamage = 5;
        this.maxDamage = 20;
        this.possibleHits = 4;
    }
    public String getName() {
        return name;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
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
