package doom.switchtext.com;

import com.sun.source.tree.TryTree;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static String[][] field = new String[16][48];

    static Player p = new Player("@", 100, new Location(14, 3));


    // '@' Spieler
    // 'x' Feind
    // 'S' Schatz
    // '#' Wand
    // '-' Tür
    // '/' Tür

    static String sField = """
                ################################################
                #                #                   #     S   #
                #      x         # x        x        # x       #
                #            ########                #         #
                #            #   x  #                #######-###
                #            #      #                /         #
                #            /      #         ##############-###
                #         x  #      ##        #       x        #
                #       ######       #        #     #          #
                #       #            #        /     #          #
                #       #            #        #     #          #
                ####-####   x    x   ######-#########     x    #
                #S      #                     #      x         #
                #       #                     #          S     #
                #  @    #                     #                #
                ################################################
                """;

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
                case 2 -> generateField();
                case 3 -> selectskin();
                case 7 -> System.out.println("done");
                default -> System.out.println("Input not valid");

            }

        }

    }

    public static void selectskin()
    {
        System.out.println("");
        System.out.println("Please select a Skin:");
        System.out.println(" @         ?        $    ");
        Scanner s = new Scanner(System.in);
        setNewSkin(s.next());
    }

    public static void setNewSkin(String input)
    {
        switch (input)
        {
            case "@", "?", "$" -> p.setSkin(input);
            default -> {
                System.out.println("Input is not a Skin.");
                System.out.println("Default skin got selected ('@')");
            }
        }
    }

    public static String[][] getField() {
        return field;
    }

    public static Player getPlayer()
    {
        return p;
    }

    public static void startGame()
    {
        generateField();
        Game g = new Game();
        g.start();

    }

    public static void generateField()
    {
        int row = 0, col = 0;
        for (int i = 0; i < sField.length(); i++) {
            char c = sField.charAt(i);
            if (c == '\n') {
                row++;
                col = 0;
            } else {
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
}