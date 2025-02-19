import java.util.List;

public class Recipe {
    private int recipeID;
    private int userID;
    private String name;
    private String description;
    private int servings;
    private List<Ingredient> ingredients;
    private List<String> steps;
    private List<Review> reviews;
    private String category;

    public Recipe(int recipeID, int userID, String name, String description, int servings, 
                  List<Ingredient> ingredients, List<String> steps, String category) {
        this.recipeID = recipeID;
        this.userID = userID;
        this.name = name;
        this.description = description;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
        this.category = category;
    }

    public int getRecipeID() { return recipeID; }
    public int getUserID() { return userID; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getServings() { return servings; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public List<String> getSteps() { return steps; }
    public String getCategory() { return category; }

    public void addReview(Review review) {
        // add review to list of reviews
        reviews.add(review);
    }
}
