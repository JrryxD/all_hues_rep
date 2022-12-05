import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Orc implements Runnable, Tablerules{

    Dagger right;
    Dagger left;

    public Orc(Dagger right, Dagger left) {
        this.right = right;
        this.left = left;
    }

    @Override
    public void run() {
        while (true)
        {
            drinking();

            boolean isDaggerRightFree = grabRightDagger();
            boolean isDaggerLeftFree = grabLeftDagger();

            if (isDaggerRightFree && isDaggerLeftFree) {
                feasting();

                releaseRightDagger();
                releaseLeftDagger();
            }else {
                if (isDaggerLeftFree) releaseLeftDagger();
                if (isDaggerRightFree) releaseRightDagger();
            }
            }
    }

    @Override
    public void drinking() {
        randomtime("drinking");

    }

    private void randomtime(String str) {
        int random = (int) Math.round(Math.random()*1000);
        try {
            System.out.println(Thread.currentThread().getName() + " is " + str + " at [Sec:Nanosec] " + getTime());
            Thread.sleep(random);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ss:ns");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @Override
    public boolean grabRightDagger() {
        boolean res = right.tryLock();
        if (res) {
            System.out.println(Thread.currentThread().getName() + " took dagger right");
            return true;
        }
        else return false;
    }

    @Override
    public boolean grabLeftDagger() {
        boolean res = left.tryLock();
        if (res) {
            System.out.println(Thread.currentThread().getName() + " took dagger left");
            return true;
        }
        else return false;
    }

    @Override
    public void feasting() {
        randomtime("feasting");
    }

    @Override
    public void releaseRightDagger() {
        right.unlock();
        System.out.println(Thread.currentThread().getName() + " released dagger right");
    }

    @Override
    public void releaseLeftDagger() {
        left.unlock();
        System.out.println(Thread.currentThread().getName() + " released dagger left");
    }
}
