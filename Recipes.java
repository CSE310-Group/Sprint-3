import java.util.ArrayList;
import java.util.List;

 class Recipes {
    private List<Recipe> recipeList;

    public Recipes() {
        recipeList = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
        System.out.println("Recipe added: " + recipe.getName());
    }

    public void printAllRecipes() {
        System.out.println("\nAll Recipes:");
        for (Recipe recipe : recipeList) {
            recipe.printRecipe();
            System.out.println("-------------------");
        }
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
