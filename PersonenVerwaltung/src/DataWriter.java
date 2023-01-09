import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataWriter {

    static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter()).create();
     public static void save(ArrayList<Main.Person> persons)
    {

        try {
            gson.toJson(persons, new FileWriter("src/data.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<Main.Person> load()
    {
        try {
            if (gson.fromJson(new FileReader("src/data.json"), ArrayList.class) == null) return new ArrayList<>();
            else return gson.fromJson(new FileReader("src/data.json"), ArrayList.class);
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
