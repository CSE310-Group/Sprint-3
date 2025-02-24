import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            // teaspoons to tablespoon
            if (amount >= 3){
                finalUnit = "tbl";
                finalAmount = teaToTableFraction;
            } 

            // Remains teaspoons
            else{
                finalAmount = teaFraction;
                finalUnit = unit;
            }
        } 

        // tablespoon to teaspoon
        else if (unit == "tbl"){
            if (amount <= 0.25){
                finalUnit = "tsp";
                finalAmount = tableToTeaFraction;
            } 

            // tablespoon to cup
            else if (amount >= 4){
                finalUnit = "cup";
                finalAmount = tableToCupFraction;
            }

            // Remains tablespoon
            else{
                finalUnit = unit;
                finalAmount = tableFraction;
            } 
        } 

        // cup to tablespoon
        else if (unit == "cup"){
            if (amount < 0.25){
                finalUnit = "tbl";
                finalAmount = cupToTableFraction;
            }

            // Remains cup
            else{
                finalUnit = unit;
                finalAmount = cupFraction;
            }
        }

        // Remains origional unit
        else{
            finalAmount = amount + "";
            finalUnit = unit;
        }
        if (finalUnit == "cup" && finalAmount != "1"){
            finalUnit = "cups";
        }
        return new String[]{finalAmount, finalUnit};
    }

    public static String half(String recipeText, double factor) {
        return scaleRecipe(recipeText, factor);
    }
    
    public static String doubleRecipe(String recipeText, double factor) {
        return scaleRecipe(recipeText, factor);
    }
    
  private static String scaleRecipe(String recipeText, double factor) {
    StringBuilder updatedRecipe = new StringBuilder();
    String[] lines = recipeText.split("\n");
    Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)");

    for (String line : lines) {
        Matcher matcher = pattern.matcher(line);
        StringBuffer updatedLine = new StringBuffer();

        while (matcher.find()) {
            double originalValue = Double.parseDouble(matcher.group());
            double newValue = originalValue * factor;
            matcher.appendReplacement(updatedLine, decimalToFraction(newValue));
        }
        matcher.appendTail(updatedLine);
        updatedRecipe.append(updatedLine).append("\n");
    }

    return updatedRecipe.toString().trim();
}

    
       


    public static void main(String[] args) {

        // Demonstrate the rounding and fraction aspect 
        System.out.println("\nDemonstrate the rounding and fraction aspect ");
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

        // Demonstrate teaspoon 
        System.out.println("\nDemo ");
        System.out.println(String.join(" ", getReadableQuantity(3.5, "tsp")));
     







        
    }
}