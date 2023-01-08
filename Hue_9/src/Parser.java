import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    List<String> slist = new ArrayList<>();
    public Parser() {}

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
            System.out.println(parser(slist.get(i)));
        }

    }

    public String parser(String str)
    {
        String currentTag = getTag(str);
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.indexOf("<"+currentTag+">") + " -- " + currentTag);
        }

        return "";
    }

    private String getTag(String str)
    {
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) == '<' && str.charAt(j+1) != '/')
            {
                int whilecouter = 0;
                while (true)
                {
                    if (str.charAt(whilecouter) == '>')
                    {
                        return str.substring(j+1, whilecouter);
                    }
                    whilecouter++;
                }
            }
        }
        return str;
    }


    public String notworkingdecode(String str)
    {
        String printable = "";

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
