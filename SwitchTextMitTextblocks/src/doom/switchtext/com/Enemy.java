package doom.switchtext.com;

import java.util.Random;

public class Enemy {

    String element, role;
    int location, health, attackpower;

    String direction;

    public Enemy(String element, int location, String role)
    {
        this.element = element;
        this.location = location;
        this.role = role;
    }

    public Enemy(String element, int location, String role, String direction)
    {
        this.element = element;
        this.location = location;
        this.role = role;
        this.direction = direction;
    }

    public Enemy(String element, int location, String role, String direction, int health, int attackpower)
    {
        this.element = element;
        this.location = location;
        this.role = role;
        this.direction = direction;
        this.health = health;
        this.attackpower = attackpower;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getElement() {
        return element;
    }

    public String getRole() {
        return role;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackpower() {
        return attackpower;
    }

    public void setAttackpower(int attackpower) {
        this.attackpower = attackpower;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int nextMove()
    {
        //TODO rename/create all enemys, make steve walk horizontal, ....; change enemys on map; redo randommove bc its shit;
        if (role.equals("Runner")) return followMove();
        else if (role.equals("Steve")) return guardMove();
        else if (role.equals("Robin")) return randomMove();
        else return 0;
    }

    private int randomMove() {
        Game game  = new Game();

        String tempField = game.getField();

        Random random = new Random();
        int ranInput = random.nextInt(4) + 1;

        switch (ranInput)
        {
            case 1 ->
            {
                System.out.println("case1");
                if ((getLocation() + game.getFieldLength()) > 784) return nextMove();
                if(tempField.charAt(getLocation() + game.getFieldLength()) == ' ')
                {
                    System.out.println(getLocation() + " - " + (tempField.charAt(getLocation()) + game.getFieldLength()));
                    return (tempField.charAt(getLocation()) + game.getFieldLength());
                }
                else return nextMove();
            }
            case 2 ->
            {
                System.out.println("case2");
                if ((getLocation() - game.getFieldLength()) < 0) return nextMove();
                if(tempField.charAt(getLocation() - game.getFieldLength()) == ' ')
                {
                    System.out.println(getLocation() + " - " + (tempField.charAt(getLocation()) - game.getFieldLength()));
                    return (tempField.charAt(getLocation()) - game.getFieldLength());
                }
                else return nextMove();

            }
            case 3 ->
            {
                System.out.println("case3");
                if ((getLocation() + 1) > 784) return nextMove();
                if(tempField.charAt(getLocation() + 1) == ' ') {
                    {
                        System.out.println(getLocation() + " - " + (tempField.charAt(getLocation()) + 1));
                        return (tempField.charAt(getLocation()) + 1);
                    }
                }
                else return nextMove();
            }
            case 4 ->
            {
                System.out.println("case4");
                if ((getLocation() - 1) < 0) return nextMove();
                if(tempField.charAt(getLocation() - 1) == ' ')
                {
                    System.out.println(getLocation() + " - " + (tempField.charAt(getLocation()) - 1));
                    return (tempField.charAt(getLocation()) - 1);
                }
                else return nextMove();
            }
            default -> {
                System.out.println("case5");
                return getLocation();
            }
        }
    }

    private int followMove()
    {
        return 0;
        //TODO follow with EnemyKI (EnemyKI.txt in projekt)
    }

    private int guardMove()
    {

        Game game = new Game();
        String tempfield = game.getField();
        //TODO go only up;

        if (getDirection().equals("l")) {
            if(tempfield.charAt(getLocation() - 1) == ' ') return (getLocation() - 1);
            else setDirection("r");
        }
        if (getDirection().equals("r")) {
            if (tempfield.charAt(getLocation() + 1) == ' ') return (getLocation() + 1);
            else setDirection("l");
        }
            return getLocation();
    }


}
