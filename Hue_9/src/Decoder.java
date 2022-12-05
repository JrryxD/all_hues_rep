import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Decoder {
    List<String> slist = new ArrayList<>();
    public Decoder() {}

    public void read(File file)
    {
        Scanner s = null;
        try {
             s = new Scanner(file);
        } catch (FileNotFoundException e) {throw new RuntimeException(e);}

        while (s.hasNext()) {
            slist.add(s.next());
        }
        for (int i = 0; i < slist.size(); i++) {
            System.out.println(decode(slist.get(i)));
        }

    }

    public String decode(String str)
    {
        List<String> l = new ArrayList<>();
        String printable = "";
        boolean insideTag = false;

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '<' && str.charAt(j+1) != '/')
                {
                    int whilecouter = 0;
                    while (true)
                    {
                        if (str.charAt(whilecouter) == '>')
                        {
                            j = whilecouter;
                            break;
                        }
                        whilecouter++;
                    }
                } else if (str.charAt(j) != '<'|| str.charAt(j) != '>' || str.charAt(j) != '/') printable = printable + str.charAt(j);
            else if (str.charAt(j) == '<' && str.charAt(j+1) == '/')
                {
                    int whilecouter = 0;
                    while (true)
                    {
                        if (str.charAt(whilecouter) == '>')
                        {
                           return printable;
                        }
                        whilecouter++;
                    }
                }


            }
        return printable;
    }


}
