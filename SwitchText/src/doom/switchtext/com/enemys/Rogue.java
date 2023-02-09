package doom.switchtext.com.enemys;

import doom.switchtext.com.Game;
import doom.switchtext.com.models.Location;

public final class Rogue extends Enemy{
    String role, skin;
    Location lEnemy;

    int health, attackpoints;

    Game g = new Game();
    String[][] tempfield = g.getField();

    public Rogue(Location lEnemy, String role, int health, int attackpoints, String skin) {
        super(lEnemy, role, health, attackpoints, skin);
    }

}
