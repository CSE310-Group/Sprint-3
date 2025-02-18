import java.util.List;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecipeDAO recipeDAO = new RecipeDAO();
        boolean running = true;

        while (running) {
            // Show menu options
            System.out.println("\nRecipe Management System");
            System.out.println("1. Add a Recipe");
            System.out.println("2. Search Recipes by Category");
            System.out.println("3. Search Recipes by Name");
            System.out.println("4. Exit");

            // Get user choice
            System.out.print("\nChoose an option (1-4): ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Add a recipe
                    addRecipe(scanner, recipeDAO);
                    break;
                case 2:
                    // Search recipes by category
                    searchRecipesByCategory(scanner, recipeDAO);
                    break;
                case 3:
                    // Search recipes by name
                    searchRecipesByName(scanner, recipeDAO);
                    break;
                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please choose a valid option.");
                    break;
            }
        }

        scanner.close();
    }

    // Method to add a recipe
    private static void addRecipe(Scanner scanner, RecipeDAO recipeDAO) {
        // Show available categories
        List<String> categories = recipeDAO.getCategories();
        System.out.println("\nAvailable Categories:");
        for (String category : categories) {
            System.out.println("- " + category);
        }

        // Collect recipe information
        System.out.println("\nAdd a new recipe:");
        System.out.print("Enter Recipe Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Servings: ");
        int servings = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Category (choose one): ");
        String category = scanner.nextLine();

        // Collect ingredients
        System.out.println("Enter ingredients (type 'done' when finished):");
        List<Ingredient> ingredients = new java.util.ArrayList<>();
        String ingredientInput;
        while (true) {
            System.out.print("Ingredient (name, quantity): ");
            ingredientInput = scanner.nextLine();
            if (ingredientInput.toLowerCase().equals("done")) break;
            String[] parts = ingredientInput.split(",");
            if (parts.length == 2) {
                ingredients.add(new Ingredient(parts[0].trim(), parts[1].trim()));
            }
        }

        // Collect steps
        System.out.println("Enter steps (type 'done' when finished):");
        List<String> steps = new java.util.ArrayList<>();
        String stepInput;
        while (true) {
            System.out.print("Step: ");
            stepInput = scanner.nextLine();
            if (stepInput.toLowerCase().equals("done")) break;
            steps.add(stepInput);
        }

        // Create Recipe object and add to the database
        Recipe newRecipe = new Recipe(0, 0, name, description, servings, ingredients, steps, category);
        recipeDAO.addRecipe(newRecipe);
        System.out.println("Recipe added successfully!");
    }

    // Method to search recipes by category
    private static void searchRecipesByCategory(Scanner scanner, RecipeDAO recipeDAO) {
        System.out.print("Enter a category to search for recipes: ");
        String searchCategory = scanner.nextLine();

        List<Recipe> recipesByCategory = recipeDAO.searchRecipesByCategory(searchCategory);
        System.out.println("\nRecipes in the category '" + searchCategory + "':");
        if (recipesByCategory.isEmpty()) {
            System.out.println("No recipes found in this category.");
        } else {
            for (Recipe recipe : recipesByCategory) {
                System.out.println(recipe.getName() + " (" + recipe.getCategory() + ")");
            }
        }
    }

    // Method to search recipes by name
    private static void searchRecipesByName(Scanner scanner, RecipeDAO recipeDAO) {
        System.out.print("Enter a name to search for recipes: ");
        String searchName = scanner.nextLine();

        List<Recipe> recipesByName = recipeDAO.searchRecipesByName(searchName);
        System.out.println("\nRecipes with the name '" + searchName + "':");
        if (recipesByName.isEmpty()) {
            System.out.println("No recipes found with this name.");
        } else {
            for (Recipe recipe : recipesByName) {
                System.out.println(recipe.getName() + " (" + recipe.getCategory() + ")");
            }
        }
    }
}
