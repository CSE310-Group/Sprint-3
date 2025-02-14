import java.util.*;

public class conversions {  // Renamed class to follow Java conventions

    // Conversion method: teaspoons to tablespoons
    public static double teaspoonsToTablespoons(double teaspoons) {
        return teaspoons / 3.0;
    }

    // Conversion method: tablespoons to teaspoons
    public static double tablespoonsToTeaspoons(double tablespoons) {
        return tablespoons * 3.0;
    }

    // Decimal to Fraction Conversion
    public static String decimalToFraction(double decimal) {
        int denominator = 1000; // A reasonable denominator for precision
        int numerator = (int) Math.round(decimal * denominator);

        int gcd = getGCD(numerator, denominator); // Simplify the fraction

        numerator /= gcd;
        denominator /= gcd;

        if (denominator == 1){
            return numerator + "";
        }

        return numerator + "/" + denominator;
    }

    // Euclidean Algorithm to find the Greatest Common Divisor (GCD)
    private static int getGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {


        // Convert tablespoons to teaspoons and in decimal form
        double tablespoons = .25;
        double teaspoons = 3;
        double toTeaspoons = tablespoonsToTeaspoons(tablespoons);
        double reverseConversion = tablespoonsToTeaspoons(teaspoons);
        String tablespoonsFraction = decimalToFraction(reverseConversion);
        String teaspoonsFraction = decimalToFraction(toTeaspoons);

        // Converts table to tea when necessary 
        if (tablespoons < 0.5){
            
            System.out.println(teaspoonsFraction + " Teaspoons garlic powder");
        }
        else{
            System.out.println(tablespoonsFraction + " Tablespoons garlic powder");
        }

        // Converts tea to table when necessary
        if (teaspoons > 2){
            System.out.println(tablespoonsFraction + " Tablespoons sugar");
        }

    }
}
