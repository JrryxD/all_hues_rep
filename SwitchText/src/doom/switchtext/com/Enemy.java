package doom.switchtext.com;

import java.util.Random;

public class Enemy {

    String element, role;
    Location lEnemy;

    String direction;

    int health, attackpoints;


    public Enemy(String element, Location lEnemy, String role)
    {
        this.element = element;
        this.lEnemy = lEnemy;
        this.role = role;
    }

    public Enemy(String element, Location lEnemy, String role, String direction, int health, int attackpoints)
    {
        this.element = element;
        this.lEnemy = lEnemy;
        this.role = role;
        this.health = health;
        this.attackpoints = attackpoints;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackpoints() {
        return attackpoints;
    }

    public void setAttackpoints(int attackpoints) {
        this.attackpoints = attackpoints;
    }

    public Location getlEnemy() {
        return lEnemy;
    }

    public void setlEnemy(Location lEnemy) {
        this.lEnemy = lEnemy;
    }

    public String getElement() {
        return element;
    }

    public int getLEnemyX() {
        return lEnemy.getX();
    }

    public int getLEnemyY() {
        return lEnemy.getY();
    }

    public String getRole() {
        return role;
    }

    public Location nextMove()
    {
        if (role.equals("runner")) return followMove();
        else if (role.equals("guard")) return guardMove();
        else return randomMove();
    }

    private Location randomMove() {
        Game game  = new Game();

        String[][] tempField = game.getField();

        Random random = new Random();
        int ranInput = random.nextInt(5) + 1;

        switch (ranInput)
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
                if(tempField[getLEnemyX()-1][getLEnemyY()+1].equals(" "))
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
        return null;
        //TODO follow with EnemyKI (EnemyKI.txt in projekt)
    }

    private Location guardMove()
    {
        return null;
        //TODO go only up - down or only right - left;
    }


}
