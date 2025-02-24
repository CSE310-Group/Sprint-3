import java.util.*;
public class main {

    public static void main(String args[]) {

        Recipes recipes = new Recipes();
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Welcome to the Recipe App!");

        mainpagegui.main(args);
        scan.nextLine();
        recipes.printAllRecipes();


        
        

    }
}

// hello
 