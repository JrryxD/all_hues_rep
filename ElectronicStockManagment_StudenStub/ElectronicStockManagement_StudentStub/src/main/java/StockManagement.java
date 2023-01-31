import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class StockManagement {
    private List<ElectronicArticle> articles;

    public StockManagement(String stockFileName) {
    }

    /* Dieser Konstruktor ist nur für die Tests relevant! */
    public StockManagement(List<ElectronicArticle> articles) {
        this.articles = articles;
    }

    public void loadStockFiles() throws IOException {
        articles = ArticleFileManager.readStockFile("stock.csv");
    }

    /* Diese Methode wird zum Testen benötigt */
    public List<ElectronicArticle> getArticles() {
        return this.articles;
    }

    public void printArticles(List<ElectronicArticle> articlesToPrint) {
        articlesToPrint.forEach((n) -> System.out.println(n.toString()));
    }

    public void printAllArticles() {
        printArticles(articles);
    }

    public List<ElectronicArticle> selectSoldArticles() {
        return articles.stream().filter((n) -> n.getStock() != 0).collect(Collectors.toList());
    }

    public List<ElectronicArticle> selectArticlesWherePriceIsLessThan(float price) {
        return articles.stream().filter((n) -> n.getPrice() <= price).collect(Collectors.toList());
    }

    public List<ElectronicArticle> sortArticlesDependingOnDate() {
        return articles.stream().sorted(Comparator.comparing(ElectronicArticle::getOrderDate).reversed()).collect(Collectors.toList());
    }

    public List<ElectronicArticle> selectArticlesAfterDate(int date) {
        return articles.stream().filter((n) -> n.getOrderDate()
                .isAfter(LocalDate.of(Integer
                        .parseInt(String.valueOf(date).substring(0, 3)),
                        Integer.parseInt(String.valueOf(date).substring(4, 5)),
                        Integer.parseInt(String.valueOf(date).substring(6, 7))))
                        || n.getOrderDate()
                .isEqual(LocalDate.of(Integer
                                .parseInt(String.valueOf(date).substring(0, 3)),
                        Integer.parseInt(String.valueOf(date).substring(4, 5)),
                        Integer.parseInt(String.valueOf(date).substring(6, 7)))))
                .collect(Collectors.toList());
    }

    public double calculateAverageArticlePrice() {
        return articles.stream().mapToDouble((ElectronicArticle::getPrice)).average().getAsDouble();
    }

    public double calculateStockValueSerial() {
        return 0;
    }

    public double calculateStockValueParallel() {
        return 0;
    }

    public List<ElectronicArticle> selectTopNArticlesDependingOnPrice(int n) {
        return articles.stream().limit(n).sorted(Comparator.comparing(ElectronicArticle::getPrice).reversed()).collect(Collectors.toList());
    }

    public String selectLongestArticleName() {
        return null;
    }

    public ElectronicArticle findArticleByArticleNumber(int articleNumber) {
        return null;
    }

    public void printDaysBetweenArticles(ElectronicArticle articleA, ElectronicArticle articleB) {
    }

    public int calculateMaxStoreCountParallel(int taskCount) throws InterruptedException {
        return 0;
    }
}
