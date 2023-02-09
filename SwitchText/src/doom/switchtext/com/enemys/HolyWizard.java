package doom.switchtext.com.enemys;

import doom.switchtext.com.Game;
import doom.switchtext.com.models.Location;

import java.util.Random;

public final class HolyWizard extends Wizard{
    String role, skin;
    Location lEnemy;

    int health, attackpoints;

    Game g = new Game();
    String[][] tempfield = g.getField();

    public HolyWizard(Location lEnemy, String role, int health, int attackpoints, String skin) {
        super(lEnemy, role, health, attackpoints, skin);
    }
}
