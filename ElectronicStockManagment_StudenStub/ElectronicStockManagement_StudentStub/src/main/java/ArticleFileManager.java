import javafx.util.converter.LocalDateStringConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArticleFileManager {
    public static List<ElectronicArticle> readStockFile(String filename) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(filename));


        List<ElectronicArticle> list = new ArrayList<>();
                br.lines()
                .skip(1)
                .map(s -> s.split(";"))
                .collect(Collectors.toList())
                .forEach((a) -> list.add(new ElectronicArticle(Integer.parseInt(a[0].substring(6)), a[1], Integer.parseInt(a[2]),
                                         Float.parseFloat(a[3].replace(',', '.')), LocalDate.parse(a[4], DateTimeFormatter.ofPattern("yyyy-MM-dd")))));



        return list;
    }

}
