package doom.switchtext.com.enemys;

import doom.switchtext.com.Game;
import doom.switchtext.com.models.Location;

public final class UndeadWizard extends Wizard{
    String role, skin;
    Location lEnemy;

    int health, attackpoints;

    Game g = new Game();
    String[][] tempfield = g.getField();

    public UndeadWizard(Location lEnemy, String role, int health, int attackpoints, String skin) {
        super(lEnemy, role, health, attackpoints, skin);
    }
}
