package doom.switchtext.com.weapons;

import doom.switchtext.com.weapons.*;

public sealed class Weapons permits Fist, Sword, OnePunch, Pistol, Rifle, Hammer, Bat, Stone{
    String name;
    int damage, accuracy, possibleHits;

    public Weapons() {
    }
}
