import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;

public class Main {

   static ArrayList<Person> persons = new ArrayList<>();

    record Adresse(String Strasse, int Strnr,
                   String Stadt, int Postleitzahl, String Land)
    {
        Adresse(String Strasse, int Strnr, String Stadt, int Postleitzahl, String Land) {
            this.Strasse = Strasse;
            this.Strnr = Strnr;
            this.Stadt = Stadt;
            this.Postleitzahl = Postleitzahl;
            this.Land = Land;
        }
    }

    record Person(String PersonID, String Vorname, String Nachname,
                  LocalDate Geburtstag, String Geschlecht, int Gehalt,
                  Adresse Adresse, String Telefonnr, String Email,
                  String Jobtitel, String Abteilung, LocalTime Arbeitsstart,
                  LocalTime Arbeitsende, String[] Anmerkungen)
    {
        Person(String PersonID, String Vorname, String Nachname, LocalDate Geburtstag, String Geschlecht, int Gehalt, Adresse Adresse, String Telefonnr, String Email, String Jobtitel, String Abteilung, LocalTime Arbeitsstart, LocalTime Arbeitsende, String[] Anmerkungen) {
            this.PersonID = PersonID;
            this.Vorname = Vorname;
            this.Nachname = Nachname;
            this.Geburtstag = Geburtstag;
            this.Geschlecht = Geschlecht;
            this.Gehalt = Gehalt;
            this.Adresse = Adresse;
            this.Telefonnr = Telefonnr;
            this.Email = Email;
            this.Jobtitel = Jobtitel;
            this.Abteilung = Abteilung;
            this.Arbeitsstart = Arbeitsstart;
            this.Arbeitsende = Arbeitsende;
            this.Anmerkungen = Anmerkungen;
        }

        @Override
        public String toString() {
            /*
            "max müller with the id maxi1, birthday at 1.1.2002, male, works as ceo and gets 5000€ a month. \n" +
                        "Works in Managment as a manager, contactable with as@gmial.com and 4353957373. \n" +
                        "Starts working at 6:00 and ends at 13:00. \n " +
                        "More infos: anmerkiungen"
             */
            return Vorname() + " " + Nachname() + " mit der ID " + PersonID() + ", hat am " + Geburtstag() + " Geburtstag und ist " + Geschlecht() + ". Arbeitet als " + Jobtitel() +
                    " und verdient " + Gehalt() + " Euro im Monat. \n" + "Arbeitet im Abteil " + Abteilung() + ", erreichbar per " + Email() + " oder " + Telefonnr() + ". \n" +
                    " Faengt um  " + Arbeitsstart() + " an und hoert um " + Arbeitsende() + " auf. \n" + "Mehr infos: " + notes();

        }

        private String notes()
        {
            System.out.println("");
            for (int i = 0; i < Anmerkungen.length; i++) {
                System.out.print(Anmerkungen[i] + ", ");
                if (i == 4) System.out.print("\n");
            }
            return " ";
        }

    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);


        int input = -1;

        //load old persons


        while (input != 7)
        {
            menue();
            try
            {
                input = Integer.parseInt(s.nextLine());
                if (input == 0 || input > 8) throw new Exception();
            }
            catch (Exception e)
            {
                System.out.println("only Numbers between 1 and 8 are valid!");
                input = -1;
            }

            switch (input)
            {
                case 1: addPerson();
                break;
                case 2: editPerson();
                break;
                case 3: deletePerson();
                break;
                case 4: searchPerson();
                break;
                case 5: printPerson();
                break;
                case 6: analysPersons();
                break;
                case 7: break;
            }
        }

        System.out.println("Thats it :D");

    }

   public static void addPerson()
   {
       Scanner scanner = new Scanner(System.in);
       String personID, firstname, lastname, sex, phoneNr, email, jobtitle, department;
       int salary;
       LocalDate birthday;
       LocalTime starttime, endtime;
       Adresse adresse;
       String[] notes;

       //start getting inputs :/
       System.out.print("PersonalID:");
       personID = scanner.next();
       System.out.print("Vorname:");
       firstname = scanner.next();
       System.out.print("Nachname:");
       lastname = scanner.next();
       System.out.print("Geschlecht:");
       sex = scanner.next();
       System.out.print("Telefonnummer:");
       phoneNr = scanner.next();
       System.out.print("E-Mail:");
       email = scanner.next();
       System.out.print("Jobtitel:");
       jobtitle = scanner.next();
       System.out.print("Abteilung:");
       department = scanner.next();
       System.out.print("Gehalt:");
       salary = Integer.parseInt(scanner.next());
       System.out.print("Geburtstag (Dieses Format benutzen: yyyy/mm/dd):");
       birthday = setdate(scanner.next());
       System.out.print("Startzeit (Dieses Format benutzen: hh:mm):");
       starttime = settime(scanner.next());
       System.out.print("Endzeit (Dieses Format benutzen: hh:mm):");
       endtime = settime(scanner.next());
       adresse = setAdresse();
       notes = setNotes();

       persons.add(new Person(personID, firstname, lastname, birthday, sex, salary, adresse, phoneNr, email, jobtitle,
                              department, starttime, endtime, notes));

   }

    public static void editPerson()
    {
        String input = suche();

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).Nachname.equals(input)) {

                System.out.println("Was moechten Sie aendern?");
                System.out.println("PersonID|Vorname|Nachname|Geburtstag|Geschlecht|Gehalt|Adresse| \n" +
                                   "TelefonNr|Email|Jobtitel|Abteilung|Arbeisstart|Arbeitsende|Anmerkungen");
                System.out.print(">");
                Scanner s = new Scanner(System.in);
                String output = s.next();

                Person temp = persons.get(i);
                persons.remove(i);

                switch (output)
                {
                    case "PersonID":
                        System.out.println("PersonID:");
                        System.out.print(">");
                        persons.add(new Person(s.next(), temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                               temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                               temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Vorname":
                        System.out.println("Vorname:");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, s.next(), temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Nachname":
                        System.out.println("Nachname:");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, s.next(), temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Geburtstag":System.out.println("Geburtstag (Dieses Format benutzen: yyyy/mm/dd)");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, setdate(s.next()), temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Geschlecht":System.out.println("Geschlecht:");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, s.next(), temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Gehalt":System.out.println("Gehalt:");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, Integer.parseInt(s.next()),
                                temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Adresse":
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                setAdresse(), temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "TelefonNr":System.out.println("TelefonNr:");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, s.next(), temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Email":System.out.println("Email:");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, s.next(), temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Jobtitel":System.out.println("Jobtitel:");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, temp.Email, s.next(), temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Abteilung":System.out.println("Abteilung:");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, s.next(), temp.Arbeitsstart,
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Arbeitsstart":System.out.println("Arbeitsstart: (Dieses Format benutzen: hh:mm)");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, settime(s.next()),
                                temp.Arbeitsende, temp.Anmerkungen));
                        break;
                    case "Arbeitsende":System.out.println("Arbeitsende: (Dieses Format benutzen: hh:mm)");
                        System.out.print(">");
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                settime(s.next()), temp.Anmerkungen));
                        break;
                    case "Anmerkungen":
                        persons.add(new Person(temp.PersonID, temp.Vorname, temp.Nachname, temp.Geburtstag, temp.Geschlecht, temp.Gehalt,
                                temp.Adresse, temp.Telefonnr, temp.Email, temp.Jobtitel, temp.Abteilung, temp.Arbeitsstart,
                                temp.Arbeitsende, setNotes()));
                        break;
                    default:
                        System.out.println("Input nicht gefunden!");
                        break;
                }

                return;
            }
        }
        System.out.println(input + " nicht gefunden!");
    }

    public static void deletePerson()
    {
        String input = suche();

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).Nachname.equals(input)) persons.remove(i);
            System.out.println("Person wurde gefunden und entfernt");
            return;
        }
        System.out.println(input + " nicht gefunden!");
    }

    private static String suche() {
        System.out.println("Welche Person? (Eingabe nur Nachname möglich!)");
        System.out.print("Nachname:");
        Scanner s = new Scanner(System.in);
        String input = s.next();
        return input;
    }

    public static void searchPerson()
    {
        String input = suche();
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).Nachname.equals(input)) {
                System.out.println("Person gefunden!");
                return;
            }
        }
        System.out.println(input + " nicht gefunden!");
    }

    public static void printPerson()
    {

        String input = suche();

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).Nachname.equals(input)) System.out.println(persons.get(i).toString());;
            return;
        }
        System.out.println(input + " nicht gefunden!");
    }

    public static void analysPersons()
    {
       if (persons.isEmpty()) {
           System.out.println("Keine Personen Vorhanden!");
           return;
       }

        System.out.println("In dieser Liste sind:");

        Map<String, Integer> gender = new HashMap<>();

        for (int i = 0; i < persons.size(); i++) {
            if (!gender.containsKey(persons.get(i).Geschlecht)) gender.put(persons.get(i).Geschlecht, 0);
            else gender.replace(persons.get(i).Geschlecht, gender.get(persons.get(i).Geschlecht), gender.get(persons.get(i).Geschlecht) + 1);
        }

        for (Map.Entry<String, Integer> entry: gender.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }

        System.out.println("");
        System.out.println("Das Durchschnittsalter aller Personen in der Liste ist: ");

        int average_Age = 0;
        int counter = 0;

        for (int i = 0; i < persons.size(); i++) {
                average_Age = average_Age + getAge(persons.get(i).Geburtstag);
                counter = i;
        }

        System.out.print((average_Age/counter));

        System.out.println("");

        System.out.println("Das höchste Gehalt ist: ");

        int highest = 0;

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).Gehalt > highest) highest = persons.get(i).Gehalt;
        }

        System.out.print(highest);
        System.out.println("");

        System.out.println("Das durchschnitts Gehalt ist: ");

        int average_salary = 0;
        int counter_for_salary = 0;

        for (int i = 0; i < persons.size(); i++) {
            average_salary = average_salary + persons.get(i).Gehalt;
            counter_for_salary = i;
        }

        System.out.print((average_salary/counter_for_salary));
        System.out.println("");

        System.out.println("Das niedrigste Gehalt ist: ");

        int lowest = 0;

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).Gehalt > lowest) lowest = persons.get(i).Gehalt;
        }

        System.out.print(lowest);
        System.out.println("");

    }

    private static int getAge(LocalDate birthday)
    {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    private static void menue()
    {
        System.out.println("+------------------------------------------------+");
        System.out.println("|         PERSONENVERWALTUNG                     |");
        System.out.println("|                                                |");
        System.out.println("|   1.  Neue Person hinzufuegen                  |");
        System.out.println("|   2.  Person bearbeiten                        |");
        System.out.println("|   3.  Person leoschen                          |");
        System.out.println("|   4.  Person suchen                            |");
        System.out.println("|   5.  Person anzeigen                          |");
        System.out.println("|   6.  Analysefunktionen                        |");
        System.out.println("|   7.  Beenden                                  |");
        System.out.println("+------------------------------------------------+");
        System.out.print(" Eingabe>");

    }

    private static LocalDate setdate(String str)
    {
        String[] parts = str.split("/");
        return LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    private static LocalTime settime(String str)
    {
        String[] parts = str.split(":");
        return LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    private static Adresse setAdresse()
    {
        Scanner s = new Scanner(System.in);
        String street, city, country;
        int streetnumber, postleitzahl;

        System.out.print("Strasse:");
        street = s.next();
        System.out.print("Strassen Nummer:");
        streetnumber = Integer.parseInt(s.next());
        System.out.print("Stadt(Keine leerzeichen/ benutze statdessen '_'):");
        city = s.next();
        System.out.print("Postleitzahl:");
        postleitzahl = Integer.parseInt(s.next());
        System.out.print("Land:");
        country = s.next();

        return new Adresse(street, streetnumber, city, postleitzahl, country);

    }

    private static String[] setNotes()
    {
        int counter = 0;
        String[] notes = {""};
        Scanner s = new Scanner(System.in);
        while (true)
        {
            System.out.print("Anmerkung:");
            notes[counter] = s.next() + "";
        System.out.println("Mehr? (ja/nein)");
            System.out.print(">");
        String input = s.next();
        if (input.equals("nein") || input.equals("Nein") || input.equals("NEIN")) break;
        }
        return notes;
    }

}