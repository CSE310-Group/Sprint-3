import java.util.*;

public class conversions {  // Renamed class to follow Java conventions 

    // ----------------------------------
    // Fractions
    // ----------------------------------
    
    // Decimal to Fraction Conversion
    public static String decimalToFraction(double decimal) {

       double roundingAllowance = 0.05;

        // Rounds decimals for cleaner fractions
        if (Math.abs(decimal - (1.0/3)) < roundingAllowance){
            decimal = 1.0 / 3; 
        } else if (Math.abs(decimal - (2.0/3)) < roundingAllowance){
            decimal = 2.0 /3;
        } else{
            decimal = Math.round(decimal * 4) / 4.0;
        }

        // A reasonable denominator 
        int denominator = 48; 
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
    // public static double teaspoonsToHalfTeaspoons (double teaspoons){
    //     return teaspoons / 2;
    // }
    // public static double teaspoonsToTablespoons(double teaspoons) {
    //     return teaspoons / 3;
    // }
    // public static double tablespoonsToTeaspoons(double tablespoons) {
    //     return tablespoons * 3;
    // }
    public static double tablespoonsToCups(double tablespoons){
        return tablespoons * 4;
    }
    // public static double fourthCupsToTablespoons(double wholeCups){
    //     return wholeCups / 16;
    // }
    public static double thirdCupsToWholeCups(double thirdCups){
        return thirdCups * 3;
    }
    public static double wholeCupsToThirdCups(double cups){
        return cups / 3;
    }
    public static double thirdCupsToTablespoons(double thirdCups){
        return thirdCups * 2;
    }

    public static void main(String[] args) {

        // Convert teaspoons to tablespoons when needed
        double teaspoonsIncrease = 3;
        double teaToTable = teaspoonsIncrease / 3;
        String teaToTableFraction = decimalToFraction(teaToTable);  

        // Teaspoon  changes to tablespoon only when they become equivalent: 3 tsp per 1 tbl
        if (teaspoonsIncrease > 2)System.out.println(teaspoonsIncrease + " tsp ----> " + teaToTableFraction + " tbl");
        
        // Otherwise it remains a teaspoon
        else if (teaspoonsIncrease <= 2){
            String teaIncreaseFraction = decimalToFraction(teaspoonsIncrease);
            System.out.println("\n" + teaIncreaseFraction + " tsp");
        }
        else System.out.println("Something is wrong with the teaspoon-tablespoon conversion");

        // Convert tablespoons to teaspoons when needed
        double tablespoonsDecrease = .25;
        double tableToTea = tablespoonsDecrease * 3;
        String tableToTeaFraction = decimalToFraction(tableToTea);
        if (tablespoonsDecrease < 0.5)System.out.println("\n" + tablespoonsDecrease + " tbl ----> " + tableToTeaFraction + " tsp");
        else if (tablespoonsDecrease <= 0.5){
            String tableDecreaseFraction = decimalToFraction(tablespoonsDecrease);
            System.out.println("\n" + tableDecreaseFraction + " tbl");
        }
        else System.out.println("Something is wrong with the tablespoon-teaspoon conversion");

        // Convert cups to tablespoons when needed
        double cups = .62;
        double cupToTable = cups * 16;
        String cupToTableFraction = decimalToFraction(cupToTable);
        if (cups < .25) System.out.println("\n" + cups + " cups ----> " + cupToTableFraction + " tbl");
        else{
            String cupFractions = decimalToFraction(cups);
            System.out.println("\n" + cups + " cups ----> " + cupFractions + " cups");      
        }

        double tablespoonsIncrease = 7;
        double tableToCup = tablespoonsIncrease / 16;
        String tableToCupFraction = decimalToFraction(tableToCup);
        if (tablespoonsIncrease > 3) System.out.println("\n" + tablespoonsIncrease + " tbl ----> " + tableToCupFraction + " cups");
        


        // Testing to see if it rounds decimals to nearest 1/4th or 1/3rd
        System.out.println("testign rounding function. input was 2.37 ");
        System.out.println(decimalToFraction(2.37) + " expected output: 2 1/4"); // Expected output: "2 1/4"
       
     
    }
}