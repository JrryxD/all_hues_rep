package doom.switchtext.com;

import doom.switchtext.com.enemys.*;
import doom.switchtext.com.enemys.fight.FightLogic;
import doom.switchtext.com.models.Location;
import doom.switchtext.com.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game {

    String[][] field = Main.getField(); // the field

    Map<Integer, ArrayList<Location>> rooms = Main.getRooms(); // all rooms

    Player p = Main.getPlayer(); // player object

    int defeatedEnemyCounter = 0; //Counter to count how many enemies the player defeated

    int px; //Player Location x
    int py; //Player Location Y

    List<Location> doors = new ArrayList<>(); //All Doors Locations
    List<Enemy> enemies = new ArrayList<>(); //All Enemys with Locations

    boolean isOver = false; // if true => game over

    public void start()
    {
        Scanner in = new Scanner(System.in);
        getDoorLocations();
        getEnemyLocations();
        px = p.getpLocation().getX();
        py = p.getpLocation().getY();

        explainStuff();
        System.out.println("Answer with 'yes' if you want to read the Story");
        if (in.next().equals("yes")) {
            story();
            in.next();
        }

        while (!isOver)
        {
            if (Main.isFog()) printnormalField();
            else printFoglessField();
            move(in.next());
            p.setpLocation(new Location(px, py));
        }
    }

    private static void story() {
        System.out.println("""
                           
                           Welcome to Doom
                           
                           Story:
                           
                           You woke up inside a small dungeon.
                           There is nothing but a door in front of you.
                           The only thing you remember, is that there is only 1 way out...
                           The portal.
                           
                           As you went through those doors, you noticed other species.
                           But they seems to held hostility towards you.
                           You don't know why, but all you can do is defeat them and find the portal to survive.
                           
                           Good luck!
                           
                           
                           Press anything to continue!
                           
                           
                           
                           """);
    }
        private static void explainStuff() {
            System.out.println("""
                                     
                         Small Explanation
                                               
                    Blue  => Player/ You
                    Purple => Portal
                    Brown => Doors
                    Red   => Enemies
                        Wizard 'σ'
                        HolyWizard  'δ'
                        UndeadWizard 'ð'
                        Rogue '♦'
                        Elf '¤'
                        Dwarf 'Ω'
                               
                                               
                                               
                    """);
        }


    private void move(String s)
    {
        switch (s)
        {
            case "w" -> up();
            case "a" -> left();
            case "s" -> down();
            case "d" -> right();
            case "e" -> openDoor();
            case "l" -> leave();
            case "k" -> keybinds();
            case "g" -> System.out.println(p.getpLocation().toString());
            case "tp" -> tpPlayer();
            default -> System.out.println("failed to move lol xD");
        }
        if (!s.equals("k") && !s.equals("g")){ //if input was Keybinds or coords (for debuging) => leaves the game/ field as it was befor
            if (!s.equals("e")) closeAllDoors(); //if last input was not 'e' => try's to close all doors
            returnsOpenDoors(); //after player leaves door position this returns the door (leafs the door open)

            moveEnemys();

            if ((field[px - 1][py].equals("σ") ||
                 field[px - 1][py].equals("δ") ||
                 field[px - 1][py].equals("ð") ||
                 field[px - 1][py].equals("♦") ||
                 field[px - 1][py].equals("¤") ||
                 field[px - 1][py].equals("Ω")) ||     // checks if enemy is nearby
                 (field[px + 1][py].equals("σ") ||
                  field[px + 1][py].equals("δ") ||
                  field[px + 1][py].equals("ð") ||
                  field[px + 1][py].equals("♦") ||
                  field[px + 1][py].equals("¤") ||
                  field[px + 1][py].equals("Ω")) ||
                  (field[px][py - 1].equals("σ") ||
                   field[px][py - 1].equals("δ") ||
                   field[px][py - 1].equals("ð") ||
                   field[px][py - 1].equals("♦") ||
                   field[px][py - 1].equals("¤") ||
                   field[px][py - 1].equals("Ω")) ||
                   (field[px][py + 1].equals("σ") ||
                    field[px][py + 1].equals("δ") ||
                    field[px][py + 1].equals("ð") ||
                    field[px][py + 1].equals("♦") ||
                    field[px][py + 1].equals("¤") ||
                    field[px][py + 1].equals("Ω"))) {
                System.out.println("""
                          
                        fight ;o
                          
                          
                          
                          
                                                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          """);
                fight();
            }
        }
    }

    private static void keybinds() {
        System.out.println("""
                        
                                Movement
                        w  ... up   | a  ... left
                        s  ... down | d  ... right
                     
                                Interaction
                        e  ... open doors / collect treasure
                        
                                  Others
                        l  ... leave Game
                        
                                 Debug/Developer
                        g  ... get Coordinates
                        tp ... Teleport
                      
                        Press anything to continue!
                      """);
        Scanner sc = new Scanner(System.in);
        sc.next();
    }

    private void leave() {
        System.out.println("You Left the Game");
        isOver = true;
        Main.setP(new Player("@", 250, new Location(14, 3))); //resets Player
    }

    private void openDoor() {
        if (field[px - 1][py].equals("-")) field[px - 1][py] = "|"; //if door is close => opens door
        if (field[px + 1][py].equals("-")) field[px + 1][py] = "|";

        if (field[px][py - 1].equals("/")) field[px][py - 1] = "_";
        if (field[px][py + 1].equals("/")) field[px][py + 1] = "_";
    }

    private void fight()
    {
        if (field[px][py + 1].equals("σ") ||
            field[px][py + 1].equals("δ") ||
            field[px][py + 1].equals("ð") ||
            field[px][py + 1].equals("♦") ||
            field[px][py + 1].equals("¤") ||
            field[px][py + 1].equals("Ω"))
        {
            isOver(getEnemyFromPosition(new Location(px, py + 1)));
        }
        if (field[px][py - 1].equals("σ") ||
            field[px][py - 1].equals("δ") ||
            field[px][py - 1].equals("ð") ||
            field[px][py - 1].equals("♦") ||
            field[px][py - 1].equals("¤") ||
            field[px][py - 1].equals("Ω"))
        {
            isOver(getEnemyFromPosition(new Location(px, py - 1)));
        }
        if (field[px + 1][py].equals("σ") ||
            field[px + 1][py].equals("δ") ||
            field[px + 1][py].equals("ð") ||
            field[px + 1][py].equals("♦") ||
            field[px + 1][py].equals("¤") ||
            field[px + 1][py].equals("Ω"))
        {
            isOver(getEnemyFromPosition(new Location(px+1, py)));
        }
        if (field[px - 1][py].equals("σ") ||
            field[px - 1][py].equals("δ") ||
            field[px - 1][py].equals("ð") ||
            field[px - 1][py].equals("♦") ||
            field[px - 1][py].equals("¤") ||
            field[px - 1][py].equals("Ω"))
        {
            isOver(getEnemyFromPosition(new Location(px-1, py)));
        }
    }

    private void isOver(Enemy e)
    {
        FightLogic fl = new FightLogic();
        if (fl.fight(e, p))
        {
            field[e.getLEnemyX()][e.getLEnemyY()] = " ";
            enemies.remove(e);
            defeatedEnemyCounter++;

        }else
        {
            end();
        }
    }

    private void end() {
        isOver = true;
        System.out.println("");
        System.out.println("You killed " + defeatedEnemyCounter + " enemies.");
        System.out.println("");
        Main.setP(new Player("@", 250, new Location(14, 3)));
    }

    private Enemy getEnemyFromPosition(Location position)
    {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getlEnemy().getX() == position.getX() &&
                enemies.get(i).getlEnemy().getY() == position.getY()) {
                return enemies.get(i);
            }
        }
        return null;
    }

    private void up() {
        if (field[px - 1][py].equals(" ")) //checks if place is free
        {
            field[px - 1][py] = p.getSkin(); //place player on free place
            field[px][py] = " "; //makes old place free
            px = px-1; //updates player position

        }else if (field[px - 1][py].equals("|")) //checks if open door
        {
            field[px - 1][py] = p.getSkin();
            field[px][py] = " ";
            px = px-1;
        } else if (field[px - 1][py].equals("∩"))
        {
            System.out.println("You survived :D");
            end();
        }
    }

    private void right() {
        if (field[px][py + 1].equals(" "))
        {
            field[px][py + 1] = p.getSkin();
            field[px][py] = " ";
            py = py+1;
        } else if (field[px][py + 1].equals("_"))
        {
            field[px][py + 1] = p.getSkin();
            field[px][py] = " ";
            py = py+1;
        } else if (field[px][py + 1].equals("∩"))
        {
            System.out.println("You survived :D");
            end();
        }
    }

    private void down() {
        if (field[px + 1][py].equals(" "))
        {
            field[px + 1][py] = p.getSkin();
            field[px][py] = " ";
            px = px+1;
        } else if (field[px + 1][py].equals("|"))
        {
            field[px + 1][py] = p.getSkin();
            field[px][py] = " ";
            px = px+1;
        } else if (field[px + 1][py].equals("∩"))
        {
            System.out.println("You survived :D");
            end();
        }
    }

    private void left() {
        if (field[px][py - 1].equals(" "))
        {
            field[px][py - 1] = p.getSkin();
            field[px][py] = " ";
            py = py-1;
        } else if (field[px][py - 1].equals("_"))
        {
            field[px][py - 1] = p.getSkin();
            field[px][py] = " ";
            py = py-1;
        } else if (field[px][py - 1].equals("∩"))
        {
            System.out.println("You survived :D");
            end();
        }
    }

    private void colorField(int i, int j)
    {
                    if (field[i][j].equals(p.getSkin())) {
                        System.out.print("\u001B[34m" + field[i][j] + "\u001B[0m");
                    } else if (field[i][j].equals("-") || field[i][j].equals("/")) {
                        System.out.print("\u001b[38;5;94m" + field[i][j] + "\u001B[0m");
                    } else if (field[i][j].equals("|") || field[i][j].equals("_")) {
                        System.out.print("\u001b[38;5;94m" + field[i][j] + "\u001B[0m");
                    } else if (field[i][j].equals("∩")) {
                        System.out.print("\u001b[38;5;129m" + field[i][j] + "\u001B[0m");
                    } else if (field[i][j].equals("σ") ||
                               field[i][j].equals("δ") ||
                               field[i][j].equals("ð") ||
                               field[i][j].equals("♦") ||
                               field[i][j].equals("¤") ||
                               field[i][j].equals("Ω")) {
                        System.out.print("\u001b[38;5;1m" + field[i][j] + "\u001B[0m");
                    } else System.out.print(field[i][j]);
    }

    private void printFoglessField()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                colorField(i, j);
                }
            System.out.println();
            }
        System.out.println("Press 'k' for Keybindings");
    }

    private void printnormalField()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                boolean stillprint = true;
                if (isDoorAtLoc(p.getpLocation()) && (field[i][j].equals("-") || field[i][j].equals("/")))
                {
                    if (field[i][j].equals("-"))
                    {
                        if ((getRoomByLoc(new Location(i-1, j)) == getRoomByLoc(new Location(px-1, py)) ||
                                getRoomByLoc(new Location(i-1, j)) == getRoomByLoc(new Location(px+1, py)) ||
                                getRoomByLoc(new Location(i-1, j)) == getRoomByLoc(new Location(px, py-1)) ||
                                getRoomByLoc(new Location(i-1, j)) == getRoomByLoc(new Location(px, py+1)))
                                ||
                                (getRoomByLoc(new Location(i+1, j)) == getRoomByLoc(new Location(px-1, py)) ||
                                        getRoomByLoc(new Location(i+1, j)) == getRoomByLoc(new Location(px+1, py)) ||
                                        getRoomByLoc(new Location(i+1, j)) == getRoomByLoc(new Location(px, py-1)) ||
                                        getRoomByLoc(new Location(i+1, j)) == getRoomByLoc(new Location(px, py+1)))) {
                            colorField(i,j);
                            stillprint = false;
                        }

                    }

                    if (field[i][j].equals("/"))
                    {
                        if ((getRoomByLoc(new Location(i, j-1)) == getRoomByLoc(new Location(px-1, py)) ||
                                getRoomByLoc(new Location(i, j-1)) == getRoomByLoc(new Location(px+1, py)) ||
                                getRoomByLoc(new Location(i, j-1)) == getRoomByLoc(new Location(px, py-1)) ||
                                getRoomByLoc(new Location(i, j-1)) == getRoomByLoc(new Location(px, py+1)))
                                ||
                                (getRoomByLoc(new Location(i, j+1)) == getRoomByLoc(new Location(px-1, py)) ||
                                        getRoomByLoc(new Location(i, j+1)) == getRoomByLoc(new Location(px+1, py)) ||
                                        getRoomByLoc(new Location(i, j+1)) == getRoomByLoc(new Location(px, py-1)) ||
                                        getRoomByLoc(new Location(i, j+1)) == getRoomByLoc(new Location(px, py+1)))) {
                            colorField(i,j);
                            stillprint = false;
                        }
                    }
                }
                if  (isDoorAtLoc(p.getpLocation()) && stillprint)
                {
                    Location lDoor = doors.get(getDoorAtPLoc(p.getpLocation()));

                        if (lDoor.getDoortyp().equals("-")) {
                            if (isLocInRoom(i, j, rooms.get(getRoomByLoc(new Location(lDoor.getX()-1, lDoor.getY())))) ||
                                    isLocInRoom(i, j, rooms.get(getRoomByLoc(new Location(lDoor.getX()+1, lDoor.getY())))))
                                colorField(i,j);
                            else if (isLocInAnyRoom(i, j) ||
                                    isDoorAtLoc(new Location(i,j)) && !field[i][j].equals(p.getSkin())) System.out.print("#");
                            else colorField(i,j);
                        }
                        else
                        {
                            if (isLocInRoom(i, j, rooms.get(getRoomByLoc(new Location(lDoor.getX(), lDoor.getY()-1)))) ||
                                    isLocInRoom(i, j, rooms.get(getRoomByLoc(new Location(lDoor.getX(), lDoor.getY()+1)))))
                                colorField(i,j);
                            else if (isLocInAnyRoom(i, j) ||
                                    isDoorAtLoc(new Location(i,j)) && !field[i][j].equals(p.getSkin())) System.out.print("#");
                            else colorField(i,j);
                        }

                }else
                if (field[i][j].equals("-") || field[i][j].equals("/"))
                {
                   if (field[i][j].equals("-"))
                   {


                       if (isPlayerinRoom(rooms.get(getRoomByLoc(new Location(i-1, j)))) ||
                           isPlayerinRoom(rooms.get(getRoomByLoc(new Location(i+1, j))))) colorField(i, j);
                       else if (stillprint) System.out.print("#");
                   } else if (field[i][j].equals("/"))
                   {


                       if (isPlayerinRoom(rooms.get(getRoomByLoc(new Location(i, j-1)))) ||
                               isPlayerinRoom(rooms.get(getRoomByLoc(new Location(i, j+1))))) colorField(i, j);
                       else if (stillprint) System.out.print("#");
                   }
                } else
                {
                    boolean stillPrint = true;
                    for (int k = 1; k <= rooms.size(); k++) {
                        if (!isPlayerinRoom(rooms.get(k)))
                        {
                            if (isLocInRoom(i, j, rooms.get(k))) {
                                System.out.print("#");
                                stillPrint = false;
                            }
                        }
                    }
                    if (stillPrint) colorField(i, j);
                }
            }
            System.out.println("");
        }

        System.out.println("Press 'k' for Keybindings");
    }

    private int getRoomByLoc(Location l)
    {
        for (int i = 1; i <= rooms.size(); i++) {
            for (int j = 0; j < rooms.get(i).size(); j++) {
               if (rooms.get(i).get(j).equals(l)) return i;
            }
        }
        return -1;
    }

    private int getDoorAtPLoc(Location l)
    {
        for (int i = 0; i < doors.size(); i++) {
                if (doors.get(i).equals(l)) return i;
        }
        return -1;
    }

    private boolean isDoorAtLoc(Location l)
    {
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i).equals(l)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPlayerinRoom(ArrayList<Location> room)
    {
        for (int i = 0; i < room.size(); i++) {
            if (p.getpLocation().getX() == room.get(i).getX() && p.getpLocation().getY() == room.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    private boolean isLocInRoom(int x, int y, ArrayList<Location> room)
    {
        for (int i = 0; i < room.size(); i++) {
            if (room.get(i).getX() == x && room.get(i).getY() == y) return true;
        }
        return false;
    }

    private boolean isLocInAnyRoom(int x, int y)
    {
        for (int i = 1; i <= rooms.size(); i++) {
            for (int j = 0; j < rooms.get(i).size(); j++) {
                if (rooms.get(i).get(j).getX() == x && rooms.get(i).get(j).getY() == y) return true;
            }

        }
        return false;
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
                if (field[i][j].equals("/")) {
                    doors.add(new Location(i, j, "/"));
                }
                if (field[i][j].equals("-")) {
                    doors.add(new Location(i, j, "-"));
                }
            }
        }
    }

    private void getEnemyLocations()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                switch (field[i][j])
                {
                    case "σ" -> enemies.add(new Wizard(new Location(i, j), "rand", 120, 15, "σ"));
                    case "δ" -> enemies.add(new HolyWizard(new Location(i, j), "rand", 80, 8, "δ"));
                    case "ð" -> enemies.add(new UndeadWizard(new Location(i, j), "rand", 120, 17, "ð"));
                    case "♦" -> enemies.add(new Rogue(new Location(i, j), "runner", 90, 16, "♦"));
                    case "¤" -> enemies.add(new Elf(new Location(i, j), "runner", 70, 20, "¤"));
                    case "Ω" -> enemies.add(new Dwarf(new Location(i, j), "runner", 150, 10, "Ω"));
                }
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

    public String[][] getField() {
        return field;
    }

    public Player getP() {
        return p;
    }

    private void moveEnemys()
    {
        for (int i = 0; i < enemies.size(); i++) {
            field[enemies.get(i).getLEnemyX()][enemies.get(i).getLEnemyY()] = " ";
            enemies.get(i).setlEnemy(enemies.get(i).nextMove());
            field[enemies.get(i).getLEnemyX()][enemies.get(i).getLEnemyY()] = enemies.get(i).getSkin();
        }
    }

    private void tpPlayer()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("x>");
        int tpx = Integer.parseInt(s.next());
        System.out.print("y>");
        int tpy = Integer.parseInt(s.next());
        if (field[tpx][tpy].equals(" "))
        {
            field[px][py] = " ";
            field[tpx][tpy] = p.getSkin();
            px = tpx;
            py = tpy;
        } else {
            System.out.println("failed to teleport!");
            System.out.println("'" + "\uD83D\uDC01" + "' was in the way!");
        }
    }
    }
