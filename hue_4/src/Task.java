import java.util.ArrayList;

public class Task implements Runnable{

    private String name;
     private ArrayList list;

    public Task(String name, ArrayList list)
    {
        this.name = name;
        this.list = list;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public void run() {
        //TODO work
    }
}
