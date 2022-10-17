import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class main {
    public static void main(String[] args) {

        File file = new File("src/numbers.csv");
        ArrayList<Integer> list = new ArrayList();

        try
        {
            Scanner s = new Scanner(file);
            s.useDelimiter(":");
            while(s.hasNext())
            {
                try {
                    int num = Integer.parseInt(s.next());
                    list.add(num);
                } catch (NumberFormatException e) {
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        Scanner in_s = new Scanner(System.in);
        int in_chunks = -1;

        System.out.print("chunks>");
        while(in_chunks == -1)
        {
            try
            {
                in_chunks = Integer.parseInt(in_s.next());
            } catch (NumberFormatException e) {
                System.out.println("Not valid number!");
            }
        }

        System.out.print("divider>");
        int in_divider = -1;

        while(in_divider == -1)
        {
            try
            {
                in_divider = Integer.parseInt(in_s.next());
            } catch (NumberFormatException e) {
                System.out.println("Not valid number!");
            }
        }





        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(in_chunks);
        for (int i = 0; i < in_chunks; i++) {
            int divider = list.size() / in_divider;
            Task t = new Task("Task" + (i+1), putintoTaskList(i, (divider*i), divider));

            System.out.println(t.getName() + " " + divider*i + " " + divider + " " + putintoTaskList(i, (divider*i), divider).size());

            executor.execute(t);
        }
        executor.shutdown();

    }

    private static ArrayList putintoTaskList(int index, int end, int divider) {
        ArrayList t_list = new ArrayList();
        if (index == 0)
        {

        } else
        {
            for (int j = end; j < (divider*index); j++)
            {
                t_list.add(t_list.get(j));
            }
        }

        return t_list;
    }
}
