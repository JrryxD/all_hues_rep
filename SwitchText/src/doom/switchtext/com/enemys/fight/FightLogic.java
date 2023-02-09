package doom.switchtext.com.enemys.fight;

import doom.switchtext.com.enemys.*;
import doom.switchtext.com.models.Player;
import doom.switchtext.com.weapons.*;

import java.util.Random;
import java.util.Scanner;

public class FightLogic {


    String youDied = """
            
                          Ohh no,
                          You died  :/
            
                          Press any button to continue.
    
                     """;
    String enemyDied = """
                     
                         The enemy died, press any button to continue.
             
                       """;

    String attacks = """
                     
                     Choose your next attack:
                     'fist'     'onePunch'
                     'sword'    'pistol'
                     'rifle'    'hammer'
                     'bat'      'stone'
                     
                     Press 'i' for attack stats
                     """;

    String stats = """
                     
                	      Damage		  Accuracy
                Fist 		10			    100%          (2 shots possible)
                Sword		45		    	25%
                OnePunch	inf		    	0.1%
                Pistol  	15			    75%          (2 shots possible)
                Rifle   	12 		    	80%          (3 shots possible)
                Hammer  	30		        40%
                Bat		    16		    	60%
                Stone	   5-20	            85%          (4 shots possible)
                
                """;



    public boolean fight(Enemy e, Player p)
    {

        switch (e)
        {
            case Dwarf d -> System.out.println("d");
            case Rogue r -> System.out.println("r");
            case Wizard w -> System.out.println("w");
            case Elf elf -> System.out.println("elf");
            case Enemy enemy -> System.out.println("e");
        }

        Scanner s = new Scanner(System.in);
        System.out.println();
        System.out.println("Your enemy has: " + e.getHealth() + "♥.");
        System.out.println("You have: " + p.getHealth() + "♥.");

            while (true) {
                System.out.println(attacks);
                String input = "";
                boolean bForSwitch = false;
                while (!bForSwitch)
                {
                    input = s.next();
                    switch (input)
                    {
                        case "fist" -> bForSwitch = fist(e);
                        case "sword" -> bForSwitch = sword(e);
                        case "onePunch" -> bForSwitch = onePunch(e);
                        case "pistol" -> bForSwitch = pistol(e);
                        case "rifle" -> bForSwitch = rifle(e);
                        case "hammer" -> bForSwitch = hammer(e);
                        case "bat" -> bForSwitch = bat(e);
                        case "stone" -> bForSwitch = stone(e);
                        case "i" -> bForSwitch = true;
                     default -> System.out.println("'" + input + "' is not a Weapon!");
                    }
                }

                if (!input.equals("i")){
                    if (e.getHealth() <= 0) {
                        System.out.println(enemyDied);
                        s.next();
                        return true;
                    }

                    if (getRandom() >= 750) {
                        System.out.println("You got hit!");
                        System.out.println("Your enemy dealt " + e.getAttackpoints() + " damage!");
                        p.setHealth(p.getHealth() - e.getAttackpoints());
                    } else System.out.println("Enemy missed!");

                    if (e.getSkin().equals("δ"))
                    {
                        if (getRandom() >=750)
                        {
                            e.setHealth(e.getHealth()+15);
                            System.out.println("Your enemy Healed himself /10♥");
                        }
                    }

                    if (p.getHealth() <= 0) {
                        System.out.println(youDied);
                        s.next();
                        return false;
                    }
                    System.out.println();
                    System.out.println("Your Enemy has " + e.getHealth() + "♥ left!");
                    System.out.println("You have " + p.getHealth() + "♥ left!");
                } else
                {
                    System.out.println(stats);
                }
            }
    }

    private boolean fist(Enemy e)
    {

        Fist f = new Fist();
        int tempCounter = 0;
        for (int i = 0; i < f.getPossibleHits(); i++) {
            if (getRandom() <= f.getAccuracy()) tempCounter++;
        }

        if (tempCounter != 0)
        {
            System.out.println("you hit " + tempCounter + " times!");
            System.out.println("You dealt " + (f.getDamage()*tempCounter) + " damage!");
            e.setHealth(e.getHealth()-(f.getDamage()*tempCounter));
        } else System.out.println("You missed them all :/");
        return true;
    }

    private boolean pistol(Enemy e)
    {
        Pistol p = new Pistol();
        int tempCounter = 0;
        for (int i = 0; i < p.getPossibleHits(); i++) {
            if (getRandom() <= p.getAccuracy()) tempCounter++;
        }

        if (tempCounter != 0)
        {
            System.out.println("you hit " + tempCounter + " times!");
            System.out.println("You dealt " + (p.getDamage()*tempCounter) + " damage!");
            e.setHealth(e.getHealth()-(p.getDamage()*tempCounter));
        } else System.out.println("You missed them all :/");
        return true;
    }

    private boolean sword(Enemy e)
    {

        Sword s = new Sword();
        int tempCounter = 0;
        for (int i = 0; i < s.getPossibleHits(); i++) {
            if (getRandom() <= s.getAccuracy()) tempCounter++;
        }

        if (tempCounter != 0)
        {
            System.out.println("you hit " + tempCounter + " times!");
            System.out.println("You dealt " + (s.getDamage()*tempCounter) + " damage!");
            e.setHealth(e.getHealth()-(s.getDamage()*tempCounter));
        } else System.out.println("You missed them all :/");
        return true;
    }

    private boolean rifle(Enemy e)
    {
        Rifle r = new Rifle();
        int tempCounter = 0;
        for (int i = 0; i < r.getPossibleHits(); i++) {
            if (getRandom() <= r.getAccuracy()) tempCounter++;
        }

        if (tempCounter != 0)
        {
            System.out.println("you hit " + tempCounter + " times!");
            System.out.println("You dealt " + (r.getDamage()*tempCounter) + " damage!");
            e.setHealth(e.getHealth()-(r.getDamage()*tempCounter));
        } else System.out.println("You missed them all :/");
        return true;
    }

    private boolean hammer(Enemy e)
    {
        Hammer h = new Hammer();
        int tempCounter = 0;
        for (int i = 0; i < h.getPossibleHits(); i++) {
            if (getRandom() <= h.getAccuracy()) tempCounter++;
        }

        if (tempCounter != 0)
        {
            System.out.println("you hit " + tempCounter + " times!");
            System.out.println("You dealt " + (h.getDamage()*tempCounter) + " damage!");
            e.setHealth(e.getHealth()-(h.getDamage()*tempCounter));
        } else System.out.println("You missed them all :/");
        return true;
    }

    private boolean onePunch(Enemy e)
    {
        OnePunch op = new OnePunch();
        if (getRandom() == op.getAccuracy()) {
            System.out.println("O_o");
            wait2();
            System.out.println("You dealt ∞ damage!");
            e.setHealth(0);
        }else System.out.println("You missed :/");
        return true;
    }

    private boolean bat(Enemy e)
    {
        Bat b = new Bat();
        int tempCounter = 0;
        for (int i = 0; i < b.getPossibleHits(); i++) {
            if (getRandom() <= b.getAccuracy()) tempCounter++;
        }

        if (tempCounter != 0)
        {
            System.out.println("you hit " + tempCounter + " times!");
            System.out.println("You dealt " + (b.getDamage()*tempCounter) + " damage!");
            e.setHealth(e.getHealth()-(b.getDamage()*tempCounter));
        } else System.out.println("You missed them all :/");
        return true;
    }

    private boolean stone(Enemy e)
    {

        Stone s = new Stone();
        int tempCounter = 0;
        for (int i = 0; i < s.getPossibleHits(); i++) {
            if (getRandom() <= s.getAccuracy()) tempCounter++;
        }

        if (tempCounter != 0)
        {
            System.out.println("you hit " + tempCounter + " times!");
            int tempAllDamageDone = 0;
            for (int i = 0; i < tempCounter; i++) {
                int stone = getRandom(s.getMinDamage(), s.getMaxDamage());
                System.out.println("You dealt " + stone + " damage!");
                tempAllDamageDone = tempAllDamageDone + stone;
            }
            e.setHealth(e.getHealth()-(tempAllDamageDone));
        } else System.out.println("You missed them all :/");
        return true;
    }




    private int getRandom()
    {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    int getRandom(int from, int to) {
        Random random = new Random();
        return random.nextInt(to - from + 1) + from;
    }

    private void wait2()
    {
        synchronized (Thread.currentThread()) {
            try {
                Thread.currentThread().wait(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
