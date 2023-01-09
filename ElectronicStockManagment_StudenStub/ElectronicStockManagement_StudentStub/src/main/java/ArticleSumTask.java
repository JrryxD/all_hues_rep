import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ArticleSumTask extends RecursiveTask<Double> {
    private static final int SEQUENTIAL_THRESHOLD = 5;

    public ArticleSumTask(List<ElectronicArticle> articles) {
    }

    @Override
    protected Double compute() {
        return null;
    }
}
