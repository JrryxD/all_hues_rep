package doom.switchtext.com;

import doom.switchtext.com.models.Location;
import doom.switchtext.com.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static String[][] field = new String[16][48];

    static Player p = new Player("@", 250, new Location(14, 3));


    // '@' Spieler
    // 'x' Feind
    // '∩' Schatz
    // '#' Wand
    // '-' Tür
    // '/' Tür
    //Wizard σ
    //HolyWizard  δ
    //UndeadWizard ð
    //Rogue ♦
    //Elf ¤
    //Dwarf Ω

    static String sFieldWithAllEnemies = """
                ################################################
                #                /    Ω          ð   #     ∩σ  #
                #      ♦         #                   # ♦       #
                #            ########         ð      #  σ      #
                #            #   σ  /         δ      #######-###
                #            #      #     ð        ð #    ♦    #
                #            #      #         ##############-###
                #         δ  #      ##        #  δ ð  δ        #
                #       ######       #        #  δ  #          #
                #       #            #    ð   #  Ω  #          #
                #       #            #        #  ∩  #          #
                ####-####   ¤    ¤   ################     Ω    #
                #∩      #                     #      Ω         #
                #       #                     /                #
                #  @    #                   σ #                #
                ################################################
                            """;

    static Map<Integer, ArrayList<Location>> rooms = new HashMap<>();

    static ArrayList<Location> r7 = new ArrayList<>();
    static ArrayList<Location> r1 = new ArrayList<>();
    static ArrayList<Location> r2 = new ArrayList<>();
    static ArrayList<Location> r3 = new ArrayList<>();
    static ArrayList<Location> r4 = new ArrayList<>();
    static ArrayList<Location> r5 = new ArrayList<>();
    static ArrayList<Location> r6 = new ArrayList<>();

    static boolean fog = true;

    public static void main(String[] args) {



        Scanner s = new Scanner(System.in);
        int input = -1;
        while (input != 7)
        {
            menue();
            try
            {
                input = Integer.parseInt(s.next());
            } catch (NumberFormatException e) {
                System.out.println("Only Numbers are valid");
                continue;
            }
            switch (input)
            {
                case 1 -> startGame();
                case 2 -> selectskin();
                case 3 -> toggleFog();
                case 7 -> System.out.println("done");
                default -> System.out.println("Input not valid");

            }

        }

    }

    public static void toggleFog()
    {
        if (fog) fog = false;
        else fog = true;
        System.out.println();
        System.out.println("Fog turned: " + fog);
        System.out.println();
    }

    public static boolean isFog() {
        return fog;
    }

    public static void selectskin()
    {
        System.out.println("");
        System.out.println("Not available Skins: ");
        System.out.println("'#' 'x' '-' '/' '_' '|'");
        System.out.print(">");
        Scanner s = new Scanner(System.in);
        setNewSkin(s.next());
    }

    public static void setNewSkin(String input)
    {
       if (!input.equals("#") && !input.equals("x") && !input.equals("-") &&
           !input.equals("/") && !input.equals("_") && !input.equals("|")) p.setSkin(input);
       else {
           System.out.println("Not available :|");
           System.out.println("Default skin selected '@'");
       }
    }

    public static String[][] getField() {
        return field;
    }

    public static Player getPlayer()
    {
        return p;
    }

    public static void setP(Player p) {
        Main.p = p;
    }

    public static void startGame()
    {
        generateField();
        fillRooms();
        Game g = new Game();
        g.start();

    }

    public static void generateField()
    {
        int row = 0, col = 0;
        for (int i = 0; i < sFieldWithAllEnemies.length(); i++) {
            char c = sFieldWithAllEnemies.charAt(i);
            if (c == '\n') {
                row++;
                col = 0;
            } else if (c == '@'){
                field[row][col] = p.getSkin();
                col++;
            }else {
                field[row][col] = Character.toString(c);
                col++;
            }
        }
    }

    public static void printfield()
    {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println("");
        }

    }

    private static void menue() {
        System.out.println("""
                +------------------------------------+
                |    1. Start new Game               |
                |                                    |
                |    2. Select Skin                  |
                |                                    |
                |    3. Toggle Fog                   |
                |                                    |
                |    7. Leave                        |
                +------------------------------------+
                """);
    }

    private static void newmenue() {
        System.out.println("""
                +------------------------------------+
                |    1. Start new Game               |
                |                                    |
                |    2. Load Game (optional yet)     |
                |                                    |
                |    3. Select Skin (optional yet)   |
                |                                    |
                |    7. Leave                        |
                +------------------------------------+
                """);
    }

    public static void fillRooms()
    {
        fillRoom7();
        fillRoom1();
        fillRoom2();
        fillRoom3();
        fillRoom4();
        fillRoom5();
        fillRoom6();
    }

    public static void fillRoom7()
    {
        r7.addAll(fill(1, 7, 12));
        r7.addAll(fill(1, 7, 13));
        r7.addAll(fill(1, 7, 14));
        rooms.put(7, r7);
    }

    public static void fillRoom1()
    {
        r1.addAll(fill(1, 16, 1));
        r1.addAll(fill(1, 16, 2));
        r1.addAll(fill(1, 12, 3));
        r1.addAll(fill(1, 12, 4));
        r1.addAll(fill(1, 12, 5));
        r1.addAll(fill(1, 12, 6));
        r1.addAll(fill(1, 12, 7));
        r1.addAll(fill(1, 7, 8));
        r1.addAll(fill(1, 7, 9));
        r1.addAll(fill(1, 7, 10));
        rooms.put(1, r1);
    }

    public static void fillRoom2()
    {
        r2.addAll(fill(14, 19, 4));
        r2.addAll(fill(14, 19, 5));
        r2.addAll(fill(14, 19, 6));
        r2.addAll(fill(14, 19, 7));
        r2.addAll(fill(14, 20, 8));
        r2.addAll(fill(9, 20, 9));
        r2.addAll(fill(9, 20, 10));
        r2.addAll(fill(9, 20, 11));
        r2.addAll(fill(9, 29, 12));
        r2.addAll(fill(9, 29, 13));
        r2.addAll(fill(9, 29, 14));
        rooms.put(2, r2);
    }

    public static void fillRoom3()
    {
        r3.addAll(fill(18, 36, 1));
        r3.addAll(fill(18, 36, 2));
        r3.addAll(fill(21, 36, 3));
        r3.addAll(fill(21, 36, 4));
        r3.addAll(fill(21, 36, 5));
        r3.addAll(fill(21, 29, 6));
        r3.addAll(fill(22, 29, 7));
        r3.addAll(fill(22, 29, 8));
        r3.addAll(fill(22, 29, 9));
        r3.addAll(fill(22, 29, 10));
        rooms.put(3, r3);
    }

    public static void fillRoom4()
    {
        r4.addAll(fill(38, 46, 5));
        rooms.put(4, r4);
    }

    public static void fillRoom5()
    {
        r5.addAll(fill(38, 46, 1));
        r5.addAll(fill(38, 46, 2));
        r5.addAll(fill(38, 46, 3));
        rooms.put(5, r5);
    }

    public static void fillRoom6()
    {
        r6.addAll(fill(31, 46, 7));
        r6.addAll(fill(31, 35, 8));
        r6.addAll(fill(37, 46, 8));
        r6.addAll(fill(31, 35, 9));
        r6.addAll(fill(37, 46, 9));
        r6.addAll(fill(31, 35, 10));
        r6.addAll(fill(37, 46, 10));
        r6.addAll(fill(37, 46, 11));
        r6.addAll(fill(31, 46, 12));
        r6.addAll(fill(31, 46, 13));
        r6.addAll(fill(31, 46, 14));
        rooms.put(6, r6);
    }



    public static ArrayList<Location> fill(int start, int end, int x)
    {
        ArrayList<Location> roomLine = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            roomLine.add(new Location(x, i));
        }
        return roomLine;
    }

    public static Map<Integer, ArrayList<Location>> getRooms()
    {
        return rooms;
    }

}