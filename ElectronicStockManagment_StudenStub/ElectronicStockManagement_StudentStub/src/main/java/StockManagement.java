import java.io.IOException;
import java.time.LocalDate;
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
    }

    /* Diese Methode wird zum Testen benötigt */
    public List<ElectronicArticle> getArticles() {
        return this.articles;
    }

    public void printArticles(List<ElectronicArticle> articlesToPrint) {
    }

    public void printAllArticles() {
    }

    public List<ElectronicArticle> selectSoldArticles() {
        return null;
    }

    public List<ElectronicArticle> selectArticlesWherePriceIsLessThan(float price) {
        return null;
    }

    public List<ElectronicArticle> sortArticlesDependingOnDate() {
        return null;
    }

    public List<ElectronicArticle> selectArticlesAfterDate(int date) {
        return null;
    }

    public double calculateAverageArticlePrice() {
        return 0;
    }

    public double calculateStockValueSerial() {
        return 0;
    }

    public double calculateStockValueParallel() {
        return 0;
    }

    public List<ElectronicArticle> selectTopNArticlesDependingOnPrice(int n) {
        return null;
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
