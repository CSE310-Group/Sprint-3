import java.util.ArrayList;
import java.util.List;
public class Recipes {
    private List<Recipe> recipes;
    public Recipes() {
        recipes = new ArrayList<Recipe>();
    }
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    public List<Recipe> getRecipes() {
        return recipes;
    }
   
    
}
