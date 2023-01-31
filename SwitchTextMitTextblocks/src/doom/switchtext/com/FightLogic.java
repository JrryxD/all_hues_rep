package doom.switchtext.com;

import java.util.Random;
import java.util.Scanner;

public class FightLogic {

    int playerHealth = 20;
    boolean booster = false;

    String enemyHit = """
                                  
                                  You got hit!
                                  
                      """,
           enemymissed = """
                                 
                                  Enemy missed xD
                                  
                         """,
           attacks = """
                          
                          Your Attacks:
                          
                          'Fist': 2 Damage; Accuracy: 100%
                          'Gun': 5 Damage; Accuracy: 50%
                          'Sword': 7 Damage; Accuracy: 20%
                          'One_Punch': infinity Damage; Accuracy: 1%
                          'Booster': Doubles the next hit; Probability: 2%
                          
                          """,
           playerMissed = """
                          
                                You missed lol xD
                          
                          """,
           punchLine = """
                       
                       O_o
                       
                       1% chance !!!
                       
                       HIT!!!!!!!!!!!!!!!!
                       ONE HIT DEATH!
                       infinity damage!
                       
                       """;

    public boolean fight(Enemy enemy)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Enemy has: " + enemy.getHealth() + " health.");
        System.out.println("You have: " + playerHealth + " health.");
        while (true)
        {
            System.out.println(attacks);

            boolean bForSwitch = false;
            while (!bForSwitch)
            {
                switch (s.next())
                {
                    case "Fist" ->
                    {
                        if (booster)
                        {
                            System.out.println("2x damage :D");
                            enemy.setHealth(enemy.getHealth()-4);
                            System.out.println("You dealt 4 Damage!");
                            booster = false;
                        } else {
                            System.out.println("You dealt 2 Damage!");
                            enemy.setHealth(enemy.getHealth() - 2);
                        }

                        bForSwitch = true;
                    }
                    case "Gun" ->
                    {
                        enemy.setHealth(enemy.getHealth()-gun());
                        bForSwitch = true;
                    }
                    case "Sword" ->
                    {
                        enemy.setHealth(enemy.getHealth()-sword());
                        bForSwitch = true;
                    }
                    case "One_Punch" ->
                    {
                        if (booster) {
                            System.out.println("Booster has no effect on One Punch,");
                            System.out.println("weird I wonder why :|");
                            booster = false;
                        }
                        enemy.setHealth(punch(enemy));
                        bForSwitch = true;
                    }
                    case "Booster" ->
                    {
                        booster();
                        bForSwitch = true;
                    }
                    default -> System.out.println("thats not an attack! D:");
                }
            }

        if (enemy.getHealth() <= 0) {
            System.out.println("You defeated the Enemy :D");
            System.out.println(" ");
            System.out.println("Write 'c' to continue!");
            s.next();
            return true;
        }

            Random random = new Random();
            int ranInput = random.nextInt(4) + 1;
            if (ranInput == 3) {
                playerHealth = playerHealth - enemy.getAttackpower();
                System.out.println(enemyHit);

            } else System.out.println(enemymissed);

        if (playerHealth <= 0) {
            System.out.println("You got defeated D;");
            System.out.println(" ");
            System.out.println("Write 'c' to continue!");
            s.next();
            return false;
        }

            System.out.println("Enemy has: " + enemy.getHealth() + " health left.");
            System.out.println("You have: " + playerHealth + " health left.");
        }
    }

    private int gun()
    {
        if (booster) {
            System.out.println("2x damage :D");
        }
        Random random = new Random();
        int ranInput = random.nextInt(100) + 1;
        if (ranInput %2 == 0) {
            System.out.println("Hit!");
            if (booster)
            {
                System.out.println("You dealt 10 Damage!");
                booster = false;
                return 10;
            } else
            {
                System.out.println("You dealt 5 Damage!");
                return 5;
            }

        }
        else
        {
            System.out.println(playerMissed);
            return 0;
        }
    }

    private int sword()
    {
        if (booster) {
            System.out.println("2x damage :D");
        }
        Random random = new Random();
        int ranInput = random.nextInt(100) + 1;
        if (ranInput >= 20) {
            System.out.println("Hit!");
            if (booster)
            {
                System.out.println("You dealt 14 Damage!");
                booster = false;
                return 14;
            } else
            {
                System.out.println("You dealt 7 Damage!");
                return 7;
            }
        }
        else
        {
            System.out.println(playerMissed);
            return 0;
        }
    }

    private int punch(Enemy enemy)
    {
        Random random = new Random();
        int ranInput = random.nextInt(100) + 1;
        if (ranInput == 1) {
            System.out.println(punchLine);
            return 0;
        }
        else
        {
            System.out.println(playerMissed);
            return enemy.getHealth();
        }
    }

    private void booster()
    {
        Random random = new Random();
        int ranInput = random.nextInt(100) + 1;
        if (ranInput <= 2) {
            System.out.println("Booster activated");
            booster = true;
        }
        else
        {
            System.out.println("Booster failed :/");
        }
    }
}
