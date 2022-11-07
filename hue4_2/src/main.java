import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class main {



    public static void main(String[] args) {

        int n = -1;
        Scanner sc = new Scanner(System.in);
        while(n == -1)
        {
            System.out.print("n>");
            try
            {
                n = Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Not an valid number");
            }
        }

        int nForMath = n;
        int counter = 0;

        ArrayList<Task> tList = new ArrayList<>();

            for (int i = 0; i < n; i = i + 100) {
                if (n > i) tList.add(new Task(i));
                counter = i;
            }
            if ((nForMath-counter) > 0) tList.add(new Task(nForMath-counter));

        final ExecutorService pool = Executors.newFixedThreadPool(2);
         final List<Task> callables = tList;
         try
         {
             int result = 0;
             for (final Future<Integer> future : pool.invokeAll(callables)) {

                 System.out.println(future.get());
                 result = result + future.get();
             }
             System.out.println("");
             System.out.println(result);
         } catch (ExecutionException e) {
             throw new RuntimeException(e);
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
         pool.shutdown();


    }
}
