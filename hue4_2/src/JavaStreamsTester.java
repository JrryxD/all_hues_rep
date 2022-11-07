import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaStreamsTester {
    public static void main(String[] args) {

        List<String> count_empty_string = new ArrayList<>();
        count_empty_string.add("");
        count_empty_string.add("");
        count_empty_string.add("");
        count_empty_string.add("");

        List<String> count_length_3 = new ArrayList<>();
        count_empty_string.add("sfdhsngasg");
        count_empty_string.add("sa");
        count_empty_string.add("asdgfaf");
        count_empty_string.add("ddd");

        List<String> delete_empty_string = new ArrayList<>();
        count_empty_string.add("agafg");
        count_empty_string.add("a");
        count_empty_string.add("");
        count_empty_string.add("asfea");
        count_empty_string.add("");

        List<String> merge_strings = new ArrayList<>();
        merge_strings.add("Hallo");
        merge_strings.add("Welt");
        merge_strings.add(";D");

        List<Integer> squares = new ArrayList<>();
        squares.add(2);
        squares.add(4);
        squares.add(6);
        squares.add(8);
        squares.add(10);

        List<Integer> max = new ArrayList<>();
        max.add(1);
        max.add(3);
        max.add(14);
        max.add(16);
        max.add(12);
        max.add(19);

        List<Integer> min = new ArrayList<>();
        min.add(17);
        min.add(3);
        min.add(2);
        min.add(16);
        min.add(3);

        List<Integer> sum = new ArrayList<>();
        sum.add(5);
        sum.add(5);
        sum.add(5);
        sum.add(5);
        sum.add(2);

        List<Integer> average = new ArrayList<>();
        average.add(20);
        average.add(10);
        average.add(30);
        average.add(5);
        average.add(15);
        average.add(25);

        try
        {
            System.out.println("Anzahl der leeren Strings: " + getCountEmptyString(count_empty_string));
        } catch (Exception e) {
            System.out.println("geht wohl nicht :0");
        }
        try
        {
            System.out.println("Anzahl der Strings mit Length 3: " + getCountLength3(count_empty_string));
        } catch (Exception e) {
            System.out.println("geht wohl nicht :0");
        }
        try
        {
            System.out.println("Löschung aller leeren Strings: " + deletEmptyStrings(delete_empty_string));
        } catch (Exception e) {
            System.out.println("geht wohl nicht :0");
        }
        try
        {
            System.out.println("Verbindung der Strings: " + getMergedString(merge_strings, " "));
        } catch (Exception e) {
            System.out.println("geht wohl nicht :0");
        }
        try
        {
            System.out.println("Quadrate aller Zahlen von der Liste: " + getSquares(squares));
        } catch (Exception e) {
            System.out.println("geht wohl nicht :0");
        }
        try
        {
            System.out.println("Maximum: " + getMax(max));
        } catch (Exception e) {
            System.out.println("geht wohl nicht :0");
        }
        try
        {
            System.out.println("Minimum: " + getMin(min));
        } catch (Exception e) {
            System.out.println("geht wohl nicht :0");
        }
        try
        {
            System.out.println("Summe: " + getSum(sum));
        } catch (Exception e) {
            System.out.println("geht wohl nicht :0");
        }
        try
        {
            System.out.println("Mittelwert: " + getAverage(average));
        } catch (Exception e) {
            System.out.println("geht wohl nicht :0");
        }




    }

    private static int getCountEmptyString(List<String> strings)
    {
       return (int) strings.stream().filter(s -> s.isEmpty()).count();
    }

    private static int getCountLength3(List<String> strings)
    {
       return (int) strings.stream().filter(s -> s.length() == 3).count();
    }

    private static List<String> deletEmptyStrings(List<String> strings)
    {
        strings.stream().forEach(s -> {if (s.isEmpty()) strings.remove(s);});
        return strings;
    }

    private static String getMergedString(List<String> strings, String seperator)
    {
        return strings.stream().reduce("", (s, s2) -> strings + seperator);
    }

    private static List<Integer> getSquares(List<Integer> numbers)
    {
      return numbers.stream().map(n -> n * n).collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers)
    {
        return numbers.stream().max(Integer::compare).get();
    }
    private static int getMin(List<Integer> numbers)
    {
        return numbers.stream().min(Integer::compare).get();
    }
    private static int getSum(List<Integer> numbers)
    {
        return numbers.stream().reduce(0, Integer::sum);
    }
    private static int getAverage(List<Integer> numbers)
    {
        return (int) numbers.stream().mapToInt(n -> n.intValue()).average().getAsDouble();
    }



}
