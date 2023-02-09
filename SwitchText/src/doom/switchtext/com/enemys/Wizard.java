package doom.switchtext.com.enemys;

import doom.switchtext.com.Game;
import doom.switchtext.com.models.Location;

public sealed class Wizard extends Enemy permits HolyWizard, UndeadWizard{
    String role, skin;
    Location lEnemy;

    int health, attackpoints;

    Game g = new Game();
    String[][] tempfield = g.getField();

    public Wizard(Location lEnemy, String role, int health, int attackpoints, String skin) {
        super(lEnemy, role, health, attackpoints, skin);
    }
}
