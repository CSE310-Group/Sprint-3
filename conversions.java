import java.util.*;

public class conversions {  // Renamed class to follow Java conventions 

    // ----------------------------------
    // Fractions
    // ----------------------------------
    
    // Decimal to Fraction Conversion
    public static String decimalToFraction(double decimal) {

        // Rounds number to nearest .25 for cleaner fractions
       

        double cups = 0.29;
        if (cups > 0.3 && cups < 0.36 || cups > 0.63 && cups < 0.69){
            System.out.println("\nWe made it here");
            
        } else{ 
            decimal = Math.round(decimal * 4) / 4.0;
            System.out.println("\nthis is the else");
        }


        int denominator = 100; // A reasonable denominator for precision
        int numerator = (int) Math.round(decimal * denominator);

        // Simplify the fraction to get greatest common denominator 
        int gcd = getGCD(numerator, denominator); 
        numerator /= gcd;
        denominator /= gcd;

        // Returns whole number plus fraction if needed
        if (denominator == 1){
            return numerator + "";
        } else if ( numerator > denominator){
            int whole = numerator / denominator;
            numerator = numerator % denominator;
            return whole + " " + numerator + "/" + denominator;
        } 
       // return (denominator == 1) ? String.valueOf(numerator) : numerator + "/" + denominator;
        return numerator + "/" + denominator;
    }

    // Find the Greatest Common Divisor (GCD)
    private static int getGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Conversion method: teaspoons, tablespoons, and cups
    public static double teaspoonsToHalfTeaspoons (double teaspoons){
        return teaspoons / 2;
    }
    public static double teaspoonsToTablespoons(double teaspoons) {
        return teaspoons / 3;
    }
    public static double tablespoonsToTeaspoons(double tablespoons) {
        return tablespoons * 3;
    }
    public static double tablespoonsToCups(double tablespoons){
        return tablespoons * 4;
    }
    public static double fourthCupsToTablespoons(double fourthCups){
        return fourthCups / 4;
    }
    public static double fourthCupsToHalfCups(double fourthCups){
        return fourthCups * 2;
    }
    public static double halfCupsToFourthCups(double halfCups){
        return halfCups / 2;
    }
    public static double halfCupsToWholeCups(double halfCups){
        return halfCups * 2;
    }
    public static double wholeCupsToHalfCups(double wholeCups){
        return wholeCups / 2;
    }
    public static double thirdCupsToWholeCups(double thirdCups){
        return thirdCups * 3;
    }
    public static double wholeCupsToThirdCups(double wholeCups){
        return wholeCups / 3;
    }
    public static double thirdCupsToTablespoons(double thirdCups){
        return thirdCups / 5.3;
    }


    public static void main(String[] args) {


        // Convert tablespoons to teaspoons in fraction form
        double tablespoons = .25;
        double toTeaspoons = tablespoonsToTeaspoons(tablespoons);
        String teaspoonsFraction = decimalToFraction(toTeaspoons);

        // Convert teaspoons to tablespoons 
        double teaspoons = 5;
        double toTablespoon = teaspoonsToTablespoons(teaspoons);
        String tablespoonsFraction = decimalToFraction(toTablespoon);

        // Convert cups within cups
        double wholeCups = .33;
        // double toHalfCups = wholeCupsToHalfCups(wholeCups);
        String cupFractions = decimalToFraction(wholeCups);

        // Testing to see if it rounds decimals to nearest 25th
        System.out.println("testign rounding function. input was 2.37 ");
        System.out.println(decimalToFraction(2.37)); // Expected output: "2 1/4"
       
        // Only convert table to tea when needed 
        if (tablespoons < 0.5){
            
            System.out.println(teaspoonsFraction + " Teaspoons garlic powder");
        }
        else{
            System.out.println(tablespoonsFraction + " Tablespoons garlic powder");
        }

        // Only convert tea to table when needed
        if (teaspoons > 2){
            System.out.println(tablespoonsFraction + " Tablespoons sugar");
        }

        // if (wholeCups < 1){
        System.out.println(wholeCups + "cups = " + cupFractions + " cups");
        // }

    }
}
