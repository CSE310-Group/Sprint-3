import java.util.*;

public class conversions {  // Renamed class to follow Java conventions 

    // ----------------------------------
    // Fractions
    // ----------------------------------
    
    // Decimal to Fraction Conversion
    public static String decimalToFraction(double decimal) {

       double roundingAllowance = 0.08;
       double wholeNum = Math.floor(decimal);
        double fraction = decimal - wholeNum;
        // Rounds decimals for cleaner fractions
        if (Math.abs(fraction - (1.0/3)) < roundingAllowance){
            decimal = wholeNum + 1.0 / 3; 
        } else if (Math.abs(fraction - (2.0/3)) < roundingAllowance){
            decimal = wholeNum + 2.0 /3;
        } else{
            decimal = wholeNum + Math.round(fraction * 4) / 4.0;
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

   

    public static String[] getReadableQuantity(double amount, String unit) {

        // Converts to fraction when unit stays the same
        double tea = amount;
        String teaFraction = decimalToFraction(tea);

        double table = amount;
        String tableFraction = decimalToFraction(table);

        double cup = amount;
        String cupFraction = decimalToFraction(cup);

        // Converts to new unit and fraction
        double teaToTable = amount / 3;
        String teaToTableFraction = decimalToFraction(teaToTable);
        
        double tableToTea = amount * 3;
        String tableToTeaFraction = decimalToFraction(tableToTea);

        double tableToCup = amount / 16;
        String tableToCupFraction = decimalToFraction(tableToCup);

        double cupToTable = amount * 16;
        String cupToTableFraction = decimalToFraction(cupToTable);

        String finalAmount;
        String finalUnit;

        // Determines what type of conversion to do
        if (unit.equals("tsp")){
            if (amount >= 3){
                finalUnit = "tbl";
                finalAmount = teaToTableFraction;
            } 
            else{
                finalAmount = teaFraction;
                finalUnit = unit;
            }
        } 
        else if (unit == "tbl"){
            if (amount <= 0.25){
                finalUnit = "tsp";
                finalAmount = tableToTeaFraction;
            } 
            else if (amount >= 4){
                finalUnit = "cup";
                finalAmount = tableToCupFraction;
            }
            else{
                finalUnit = unit;
                finalAmount = tableFraction;
            } 
        } 
        else if (unit == "cup"){
            if (amount < 0.25){
                finalUnit = "tbl";
                finalAmount = cupToTableFraction;
            }
            else{
                finalUnit = unit;
                finalAmount = cupFraction;
            }
        }
        else{
            finalAmount = amount + "";
            finalUnit = unit;
        }
        if (finalUnit == "cup" && finalAmount != "1"){
            finalUnit = "cups";
        }
        return new String[]{finalAmount, finalUnit};
    }
    
       


    public static void main(String[] args) {

        System.out.println(String.join(" ", getReadableQuantity(1.00, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.05, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.1, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.15, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.20, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.25, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.30, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.35, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.40, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.45, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.50, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.55, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.65, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.70, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.75, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.80, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.85, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.90, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(1.95, "cup")));
        System.out.println(String.join(" ", getReadableQuantity(2.00, "cup")));
        
    }
}