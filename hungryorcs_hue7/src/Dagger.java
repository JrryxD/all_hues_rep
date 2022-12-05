import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Dagger implements Lock {

    boolean dagger;

    public Dagger(boolean dagger) {
        this.dagger = dagger;
    }

    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        if (dagger) {
            dagger = false;
            return true;
        } else {
            System.out.println(Thread.currentThread().getName()  + " is sad because this dagger is in use ); at [Sec:Nanosec]" + getTime());
            return false;
        }

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if (!dagger) dagger = true;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ss:ns");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
