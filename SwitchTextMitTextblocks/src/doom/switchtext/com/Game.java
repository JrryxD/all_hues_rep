package doom.switchtext.com;

import java.util.*;

public class Game {

    //String[][] field;

    StringBuilder sb;

    String field = Main.getTheRealField();

    public int getFieldLength() {
        return fieldLength;
    }

    int fieldLength = 49;

    String input; //Player input

    Map<Integer, String> doors = new HashMap<>(); //All Doors Locations
    List<Enemy> enemies = new ArrayList<>(); //All Enemys with Locations

    boolean isOver = false;

    public void start()
    {
        Scanner in = new Scanner(System.in);
        getDoorLocations();
        getEnemyLocations();
        String keybinds = """
                     
                     
                     
                     
                     
                     +---------------------- Keybinds ---------------------+
                     |                                                     |
                     |           w  -> up                                  |
                     |           a  -> left                                |
                     |           s  -> down                                |
                     |           d  -> right                               |
                     |           e  -> open/interaction                    |
                     |                '-', '/' these are doors             |
                     |           l  -> leave game                          |
                     |          (Enemy movement is not working rn :/)      |
                     +-----------------------------------------------------+
                     """;
        System.out.println(keybinds);
        while (!isOver)
        {
            printField();
            input = in.next();
            move(input);
            if (isOver) System.out.println("Noooooo........ you died ;(");
            System.out.println(" ");
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

                use();


            }
            case "l" -> {
                System.out.println("You Left the Game");
                isOver = true;
            }
            default -> System.out.println("failed to move lol xD");
        }
        if (!input.equals("e")) closeAllDoors(); //if last input was not 'e' => try's to close all doors
        returnsOpenDoors(); //after player leaves door position this returns the door (leafs the door open)

        //moveEnemy();
        //replaceAllEnemies();

        if (field.charAt(field.indexOf('@')+fieldLength) == 'x' || field.charAt(field.indexOf('@')+1) == 'x' ||
            field.charAt(field.indexOf('@')-fieldLength) == 'x' || field.charAt(field.indexOf('@')-1) == 'x')
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
        if (field.charAt(field.indexOf('@')+fieldLength) == 'x')
        {
            isOver((field.indexOf('@')+fieldLength));
        }
        if (field.charAt(field.indexOf('@')-fieldLength) == 'x')
        {
            isOver((field.indexOf('@')-fieldLength));
        }
        if (field.charAt(field.indexOf('@')+1) == 'x')
        {
            isOver((field.indexOf('@')+1));
        }
        if (field.charAt(field.indexOf('@')-1) == 'x')
        {
            isOver((field.indexOf('@')-1));
        }
    }

    private Enemy getEnemyFromPosition(int position)
    {
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).getLocation() == position) return enemies.get(i);
            }
            return null;
    }

    private void isOver(int position)
    {
        FightLogic fl = new FightLogic();
        if (fl.fight(getEnemyFromPosition(position)))
        {
            sb = new StringBuilder(field);
            sb.setCharAt(position, ' ');
            field = sb.toString();
            enemies.remove(getEnemyFromPosition(position));

        } else {
            isOver = true;
        }
    }

    private void use() {

        if (field.charAt(field.indexOf('@')+fieldLength) == '-')
        {
            sb = new StringBuilder(field);
            sb.setCharAt(field.indexOf('@')+fieldLength, '|');
            field = sb.toString();
        }
        if (field.charAt(field.indexOf('@')-fieldLength) == '-')
        {
            sb = new StringBuilder(field);
            sb.setCharAt(field.indexOf('@')-fieldLength, '|');
            field = sb.toString();
        }

        if (field.charAt(field.indexOf('@')+1) == '/')
        {
            sb = new StringBuilder(field);
            sb.setCharAt(field.indexOf('@')+1, '_');
            field = sb.toString();
        }
        if (field.charAt(field.indexOf('@')-1) == '/')
        {
            sb = new StringBuilder(field);
            sb.setCharAt(field.indexOf('@')-1, '_');
            field = sb.toString();
        }
    }

    private void right() {

        int temp = field.indexOf('@');
        if (field.charAt(temp+1) == ' ' || field.charAt(temp+1) == '_')
        {
            sb = new StringBuilder(field);
            sb.setCharAt(temp, ' ');
            sb.setCharAt(temp+1, '@');
            field = sb.toString();
        }
    }

    private void down() {

        int temp = field.indexOf('@');
        if (field.charAt(temp+fieldLength) == ' ' || field.charAt(temp+fieldLength) == '|')
        {
            sb = new StringBuilder(field);
            sb.setCharAt(temp, ' ');
            sb.setCharAt(temp+fieldLength, '@');
            field = sb.toString();
        }
    }

    private void left() {

        int temp = field.indexOf('@');
        if (field.charAt(temp-1) == ' ' || field.charAt(temp-1) == '_')
        {
            sb = new StringBuilder(field);
            sb.setCharAt(temp, ' ');
            sb.setCharAt(temp-1, '@');
            field = sb.toString();
        }
    }

    private void up() {

        int temp = field.indexOf('@');
        if (field.charAt(temp-fieldLength) == ' ' || field.charAt(temp-fieldLength) == '|')
        {
            sb = new StringBuilder(field);
            sb.setCharAt(temp, ' ');
            sb.setCharAt(temp-fieldLength, '@');
            field = sb.toString();
        }
    }

    private void printField()
    {
            System.out.println(field.toString());
    }

    private void closeAllDoors()
    {
        for (int i = 0; i < field.length(); i++) {

                if (field.charAt(i) == '_')
                {
                    sb = new StringBuilder(field);
                    sb.setCharAt(i, '/');
                    field = sb.toString();
                }
                if (field.charAt(i) == '|')
                {
                    sb = new StringBuilder(field);
                    sb.setCharAt(i, '-');
                    field = sb.toString();
                }
        }
    }

    private void getDoorLocations()
    {
        for (int i = 0; i < field.length(); i++) {
            if (field.charAt(i) == '-') doors.put(i, "-");
            if ( field.charAt(i) == '/') doors.put(i, "/");

        }
    }

    private void getEnemyLocations()
    {
        for (int i = 0; i < field.length(); i++) {
            if (field.charAt(i) == 'X') enemies.add(new Enemy("Normal", i, "Robin"));
            if (field.charAt(i) == 'x') enemies.add(new Enemy("Normal", i, "Steve", "l", 30, 2));
        }
    }

    private void returnsOpenDoors()
    {
        List<Integer> temp =  doors.keySet().stream().toList();
        for (int i = 0; i < temp.size(); i++) {
            if (field.charAt(temp.get(i)) == ' ')
            {
                if (doors.get(temp.get(i)).equals("-"))
                {
                    sb = new StringBuilder(field);
                    sb.setCharAt(temp.get(i), '|');
                    field = sb.toString();
                }
                else if (doors.get(temp.get(i)).equals("/"))
                {
                    sb = new StringBuilder(field);
                    sb.setCharAt(temp.get(i), '_');
                    field = sb.toString();
                }
            }
        }
    }

    public String getField() {
        return field;
    }

    private void moveEnemy()
    {
        for (int i = 0; i < enemies.size(); i++) {
            int temp;
            temp = enemies.get(i).nextMove();
            System.out.println("New Enemy Location: " + temp);
            enemies.get(i).setLocation(temp);
        }
    }

    private void replaceAllEnemies()
    {
        for (int i = 0; i < field.length(); i++) {
            if (field.charAt(i) == 'x')
            {
                sb = new StringBuilder(field);
                sb.setCharAt(i, ' ');
                field = sb.toString();
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            System.out.println("Enemies location "+ i +": " + enemies.get(i).getLocation());
        }

        for (int i = 0; i < field.length(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if (enemies.get(j).getLocation() == i)
                {
                    sb = new StringBuilder(field);
                    sb.setCharAt(i, 'x');
                    field = sb.toString();
                }
            }
        }
    }




}
