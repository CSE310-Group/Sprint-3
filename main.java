import java.util.*;

public class main {
    public static void main(String args[]) {
        Recipes recipes = new Recipes(); // Shared recipe storage
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Welcome to the Recipe App!");

        // Pass recipes object to mainpagegui
        mainpagegui mainGui = new mainpagegui(recipes);
        mainGui.setVisible(true); 

        scan.nextLine(); // Wait for user input
        recipes.printAllRecipes(); // Print all stored recipes
    }
}
