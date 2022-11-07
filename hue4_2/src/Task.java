import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {

    int zahl;

    public Task(int zahl) {
        this.zahl = zahl;
    }


    @Override
    public Integer call() throws Exception {

    int result = 0;

        ArrayList<Integer> temp = new ArrayList();
        System.out.println("zahl: " + zahl);
        if (zahl-100 >= 0 ) {
            for (int i = zahl; i != zahl - 100; i--) {
                temp.add(i);
            }
            System.out.println("end");

            for (int i = 0; i < temp.size(); i++) {
                result = result + temp.get(i);
            }
    /*
        for (int i = 0; i <= zahl; i++) {
            result = result + i;
        }
*/
        }
        return result;
    }
}
