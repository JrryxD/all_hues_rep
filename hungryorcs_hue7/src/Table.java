import java.util.concurrent.locks.ReentrantLock;

public class Table {

    public static void main(String[] args) {

        Dagger[] daggers = new Dagger[5];

        fillTableWithDagger(daggers);
        System.out.println("Table with all Dagger");
        System.out.println("");

            Thread orc1 =  new Thread(new Orc(daggers[0], daggers[1]), "Orc 1");
            Thread orc2 =  new Thread(new Orc(daggers[1], daggers[2]), "Orc 2");
            Thread orc3 =  new Thread(new Orc(daggers[2], daggers[3]), "Orc 3");
            Thread orc4 =  new Thread(new Orc(daggers[3], daggers[4]), "Orc 4");
            Thread orc5 =  new Thread(new Orc(daggers[4], daggers[0]), "Orc 5");

            orc1.start();
            orc2.start();
            orc3.start();
            orc4.start();
            orc5.start();
    }

    private static void fillTableWithDagger(Dagger[] daggers) {
        for (int i = 0; i < daggers.length; i++) {
            daggers[i] = new Dagger(true);
        }
    }
}
