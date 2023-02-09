package doom.switchtext.com.enemys;

import doom.switchtext.com.Game;
import doom.switchtext.com.models.Location;

import java.util.Random;

public sealed class Enemy permits Dwarf, Elf, Wizard, Rogue{

    String role, skin;
    Location lEnemy;

    int health, attackpoints;

    Game g = new Game();
    String[][] tempfield = g.getField();

    public Enemy(Location lEnemy, String role, int health, int attackpoints, String skin)
    {
        this.lEnemy = lEnemy;
        this.role = role;
        this.health = health;
        this.attackpoints = attackpoints;
        this.skin = skin;
    }

    public int getHealth() {
        return health;
    }

    public String getRole() {
        return role;
    }

    public String getSkin() {
        return skin;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackpoints() {
        return attackpoints;
    }

    public Location getlEnemy() {
        return lEnemy;
    }

    public void setlEnemy(Location lEnemy) {
        this.lEnemy = lEnemy;
    }

    public int getLEnemyX() {
        return lEnemy.getX();
    }

    public int getLEnemyY() {
        return lEnemy.getY();
    }

    public Location nextMove()
    {
        if (role.equals("runner")) return followMove();
        else return randomMove();
    }

    private Location randomMove() {
        Game game  = new Game();

        String[][] tempField = game.getField();

        switch (getRandom(5))
        {
            case 1 ->
            {
                if(tempField[getLEnemyX()-1][getLEnemyY()].equals(" "))
                    return new Location(getLEnemyX() - 1, getLEnemyY());
                else return nextMove();
            }
            case 2 ->
            {
                if(tempField[getLEnemyX()][getLEnemyY()-1].equals(" "))
                    return new Location(getLEnemyX(), getLEnemyY() - 1);
                else return nextMove();
            }
            case 3 ->
            {
                if(tempField[getLEnemyX()+1][getLEnemyY()].equals(" "))
                    return new Location(getLEnemyX() + 1, getLEnemyY());
                else return nextMove();
            }
            case 4 ->
            {
                if(tempField[getLEnemyX()][getLEnemyY()+1].equals(" "))
                    return new Location(getLEnemyX(), getLEnemyY() + 1);
                else return nextMove();
            }
            default -> {
                return new Location(getLEnemyX(), getLEnemyY());
            }
        }
    }

    private Location followMove()
    {

        if (getLEnemyX() == g.getP().getpLocation().getX()) return checkY();
        else  if (getLEnemyY() == g.getP().getpLocation().getY()) return checkX();
        else {
            if (getRandom(2) == 2) return checkY();
            else return checkX();
        }
    }

    private Location checkX()
    {
        if (getLEnemyX() > g.getP().getpLocation().getX() && tempfield[getLEnemyX()-1][getLEnemyY()].equals(" ")) return new Location(getLEnemyX()-1, getLEnemyY());
        else if (getLEnemyX() < g.getP().getpLocation().getX() && tempfield[getLEnemyX()+1][getLEnemyY()].equals(" ")) return new Location(getLEnemyX()+1, getLEnemyY());
        else return getlEnemy();
    }

    private Location checkY()
    {
        if (getLEnemyY() > g.getP().getpLocation().getY() && tempfield[getLEnemyX()][getLEnemyY()-1].equals(" ")) return new Location(getLEnemyX(), getLEnemyY()-1);
        else if (getLEnemyY() < g.getP().getpLocation().getY() && tempfield[getLEnemyX()][getLEnemyY()+1].equals(" ")) return new Location(getLEnemyX(), getLEnemyY()+1);
        else return getlEnemy();
    }

    private int getRandom(int max)
    {
        Random random = new Random();
        return (random.nextInt(max) + 1);
    }
}
