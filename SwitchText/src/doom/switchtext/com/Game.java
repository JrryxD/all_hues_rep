package doom.switchtext.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    String[][] field = Main.getField();

    Player p;

    int px;
    int py;

    Location lPlayer; //player Location
    String input; //Player input

    List<Location> doors = new ArrayList<>(); //All Doors Locations
    List<Enemy> enemies = new ArrayList<>(); //All Enemys with Locations

    boolean isOver = false;

    public void start()
    {
        Scanner in = new Scanner(System.in);
        getDoorLocations();
        getEnemyLocations();
        p = Main.getPlayer();
        lPlayer = getPlayerStartLocation();
        while (!isOver)
        {
            printField();
            input = in.next();
            move(input);
        }

    }

    private void move(String s)
    {
        switch (s)
        {
            case "w" ->
            {
                up();
            }
            case "a" -> {
                left();
            }
            case "s" -> {
                down();
            }
            case "d" -> {
                right();
            }
            case "e" -> {
                if (field[lPlayer.getX() - 1][lPlayer.getY()].equals("-")) field[lPlayer.getX() - 1][lPlayer.getY()] = "|"; //if door is close => opens door
                if (field[lPlayer.getX() + 1][lPlayer.getY()].equals("-")) field[lPlayer.getX() + 1][lPlayer.getY()] = "|";

                if (field[lPlayer.getX()][lPlayer.getY() - 1].equals("/")) field[lPlayer.getX()][lPlayer.getY() - 1] = "_";
                if (field[lPlayer.getX()][lPlayer.getY() + 1].equals("/")) field[lPlayer.getX()][lPlayer.getY() + 1] = "_";
            }
            case "l" -> {
                System.out.println("You Left the Game");
                isOver = true;
            }
            default -> System.out.println("failed to move lol xD");
        }
        if (!input.equals("e")) closeAllDoors(); //if last input was not 'e' => try's to close all doors
        returnsOpenDoors(); //after player leaves door position this returns the door (leafs the door open)

        moveEnemy();
        replaceAllEnemies();

        if (field[lPlayer.getX() - 1][lPlayer.getY()].equals("x")||
            field[lPlayer.getX() + 1][lPlayer.getY()].equals("x")||
            field[lPlayer.getX()][lPlayer.getY() - 1].equals("x")||
            field[lPlayer.getX()][lPlayer.getY() + 1].equals("x"))
        {
            String temp = """
                                    
                                  fight ;o
                                    
                                    
                                    
                                    
                                  
                                    
                                    
                                    
                                    
                                    
                                    
                                    """;
            System.out.println(temp);
            fight();
        }

    }

    private void fight()
    {
        if (field[lPlayer.getX()][lPlayer.getY() + 1].equals("x"));
        {
            isOver(new Location(lPlayer.getX(), lPlayer.getY() + 1));
        }
        if (field[lPlayer.getX()][lPlayer.getY() - 1].equals("x"));
        {
            isOver(new Location(lPlayer.getX(), lPlayer.getY() - 1));
        }
        if (field[lPlayer.getX() + 1][lPlayer.getY()].equals("x"));
        {
            isOver(new Location(lPlayer.getX()+1, lPlayer.getY()));
        }
        if (field[lPlayer.getX() - 1][lPlayer.getY()].equals("x"));
        {
            isOver(new Location(lPlayer.getX()-1, lPlayer.getY()));
        }
    }

    private void isOver(Location lEnemy)
    {
        FightLogic fl = new FightLogic();
        if (fl.fight(getEnemyFromPosition(lEnemy)))
        {
            field[lEnemy.getX()][lEnemy.getY()] = " ";
            enemies.remove(getEnemyFromPosition(lEnemy));

        }else
        {
            isOver = true;
        }
    }

    private Enemy getEnemyFromPosition(Location position)
    {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getlEnemy().getX() == position.getX() &&
                enemies.get(i).getlEnemy().getY() == position.getY())
                    return enemies.get(i);
        }
        return null;
    }


    private void right() {
        if (field[lPlayer.getX()][lPlayer.getY() + 1].equals(" "))
        {
            field[lPlayer.getX()][lPlayer.getY() + 1] = p.skin;
            field[lPlayer.getX()][lPlayer.getY()] = " ";
            lPlayer.setY(lPlayer.getY() + 1); //TODO py = py+1;
        } else if (field[lPlayer.getX()][lPlayer.getY() + 1].equals("_"))
        {
            field[lPlayer.getX()][lPlayer.getY() + 1] = p.skin;
            field[lPlayer.getX()][lPlayer.getY()] = " ";
            lPlayer.setY(lPlayer.getY() + 1); //TODO py = py+1;
        } else if (field[lPlayer.getX()][lPlayer.getY() + 1].equals("S"))
        {
            System.out.println("You won :D");
            isOver = true;
        }
    }

    private void down() {
        if (field[lPlayer.getX() + 1][lPlayer.getY()].equals(" "))
        {
            field[lPlayer.getX() + 1][lPlayer.getY()] = p.skin;
            field[lPlayer.getX()][lPlayer.getY()] = " ";
            lPlayer.setX(lPlayer.getX() + 1); //TODO px = px+1;
        } else if (field[lPlayer.getX() + 1][lPlayer.getY()].equals("|"))
        {
            field[lPlayer.getX() + 1][lPlayer.getY()] = p.skin;
            field[lPlayer.getX()][lPlayer.getY()] = " ";
            lPlayer.setX(lPlayer.getX() + 1);
        } else if (field[lPlayer.getX() + 1][lPlayer.getY()].equals("S"))
        {
            System.out.println("You won :D");
            isOver = true;
        }
    }

    private void left() {
        if (field[lPlayer.getX()][lPlayer.getY() - 1].equals(" "))
        {
            field[lPlayer.getX()][lPlayer.getY() - 1] = p.skin;
            field[lPlayer.getX()][lPlayer.getY()] = " ";
            lPlayer.setY(lPlayer.getY() - 1);
        } else if (field[lPlayer.getX()][lPlayer.getY() - 1].equals("_"))
        {
            field[lPlayer.getX()][lPlayer.getY() - 1] = p.skin;
            field[lPlayer.getX()][lPlayer.getY()] = " ";
            lPlayer.setY(lPlayer.getY() - 1);
        } else if (field[lPlayer.getX()][lPlayer.getY() - 1].equals("S"))
        {
            System.out.println("You won :D");
            isOver = true;
        }
    }

    private void up() {
        if (field[lPlayer.getX() - 1][lPlayer.getY()].equals(" ")) //checks if place is free
        {
            field[lPlayer.getX() - 1][lPlayer.getY()] = p.skin; //place player on free place
            field[lPlayer.getX()][lPlayer.getY()] = " "; //makes old place free
            lPlayer.setX(lPlayer.getX() - 1); //updates player position

        }else if (field[lPlayer.getX() - 1][lPlayer.getY()].equals("|")) //checks if open door
        {
            field[lPlayer.getX() - 1][lPlayer.getY()] = p.skin;
            field[lPlayer.getX()][lPlayer.getY()] = " ";
            lPlayer.setX(lPlayer.getX() - 1);
        } else if (field[lPlayer.getX() - 1][lPlayer.getY()].equals("S"))
        {
            System.out.println("You won :D");
            isOver = true;
        }
    }

    private void printField()
    {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println("");
        }

    }

    private Location getPlayerStartLocation()
    {
        return p.getpLocation();
    }

    private void closeAllDoors()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j].equals("_")) field[i][j] = "/";
                if (field[i][j].equals("|")) field[i][j] = "-";
            }

        }
    }

    private void getDoorLocations()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j].equals("/")) doors.add(new Location(i, j, "/"));
                if (field[i][j].equals("-")) doors.add(new Location(i, j, "-"));
            }

        }
    }

    private void getEnemyLocations()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j].equals("x")) enemies.add(new Enemy("Normal", new Location(i, j), "Normie"));
            }
        }
    }

    private void returnsOpenDoors()
    {
        for (int i = 0; i < doors.size(); i++) {
            if (field[doors.get(i).getX()][doors.get(i).getY()].equals(" "))
            {
                if (doors.get(i).getDoortyp().equals("/")) field[doors.get(i).getX()][doors.get(i).getY()] = "_";
                else if (doors.get(i).getDoortyp().equals("-")) field[doors.get(i).getX()][doors.get(i).getY()] = "|";
            }
        }
    }

    public Location getLPlayer() {
        return lPlayer;
    }

    public String[][] getField() {
        return field;
    }

    private void moveEnemy()
    {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.add(new Enemy(enemies.get(i).element, enemies.get(i).nextMove(), enemies.get(i).getRole()));
            enemies.remove(i);
        }
    }

    private void replaceAllEnemies()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j].equals("x")) field[i][j] = " ";
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            field[enemies.get(i).getLEnemyX()][enemies.get(i).getLEnemyY()] = "x";
        }

    }
}
