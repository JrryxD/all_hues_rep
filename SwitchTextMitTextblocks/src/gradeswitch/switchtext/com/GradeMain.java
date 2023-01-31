package gradeswitch.switchtext.com;

public class GradeMain {
    public static void main(String[] args) {

        for (double i = 0; i <= 120; i = i + 0.5) {
            getGrade(i);
        }
    }
        public static void getGrade(double points) {
        switch ((int) (points / 5)) {
                case 0, 1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 -> printGrade(5.0, points);
                case 10, 11 -> printGrade(4.7, points);
                case 12 -> printGrade(4.0, points);
                case 13 -> printGrade(3.7, points);
                case 14 -> printGrade(3.3, points);
                case 15 -> printGrade(3.0, points);
                case 16 -> printGrade(2.7, points);
                case 17 -> printGrade(2.3, points);
                case 18 -> printGrade(2.0, points);
                case 19 -> printGrade(1.7, points);
                case 20 -> printGrade(1.3, points);
                case 21, 22, 23, 24 -> printGrade(1.0, points);
            };
        }

        private static void printGrade(double grade, double points)
        {
            System.out.println("You got a " + grade + " with: " + points + " Points");
        }
}
