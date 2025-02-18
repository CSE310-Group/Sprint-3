import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    // Add a new recipe with a single category
    public void addRecipe(Recipe recipe) {
        String recipeSQL = "INSERT INTO recipes (user_id, name, description, servings, category_id) VALUES (?, ?, ?, ?, ?)";
        String ingredientSQL = "INSERT INTO ingredients (recipe_id, name, quantity) VALUES (?, ?, ?)";
        String stepSQL = "INSERT INTO recipe_steps (recipe_id, step_number, step_description) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);  // Start transaction

            // Insert recipe
            try (PreparedStatement recipeStmt = conn.prepareStatement(recipeSQL, Statement.RETURN_GENERATED_KEYS)) {
                recipeStmt.setInt(1, recipe.getUserID());
                recipeStmt.setString(2, recipe.getName());
                recipeStmt.setString(3, recipe.getDescription());
                recipeStmt.setInt(4, recipe.getServings());

                // Convert category name to category ID
                int categoryID = getCategoryID(recipe.getCategory(), conn);
                recipeStmt.setInt(5, categoryID);

                recipeStmt.executeUpdate();

                // Get generated recipe ID
                ResultSet generatedKeys = recipeStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int recipeID = generatedKeys.getInt(1);

                    // Insert ingredients
                    try (PreparedStatement ingredientStmt = conn.prepareStatement(ingredientSQL)) {
                        for (Ingredient ingredient : recipe.getIngredients()) {
                            ingredientStmt.setInt(1, recipeID);
                            ingredientStmt.setString(2, ingredient.getName());
                            ingredientStmt.setString(3, ingredient.getQuantity());
                            ingredientStmt.executeUpdate();
                        }
                    }

                    // Insert steps
                    try (PreparedStatement stepStmt = conn.prepareStatement(stepSQL)) {
                        int stepNumber = 1;
                        for (String step : recipe.getSteps()) {
                            stepStmt.setInt(1, recipeID);
                            stepStmt.setInt(2, stepNumber++);
                            stepStmt.setString(3, step);
                            stepStmt.executeUpdate();
                        }
                    }

                    conn.commit();  // Commit transaction
                } else {
                    conn.rollback();  // Rollback if recipe insertion fails
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to retrieve category ID from category name
    private int getCategoryID(String categoryName, Connection conn) throws SQLException {
        String sql = "SELECT category_id FROM categories WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("category_id");
            }
        }
        return 1; // Default category ID (if not found)
    }

    // Fetch recipes by name, including category
    public List<Recipe> searchRecipesByName(String name) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT r.recipe_id, r.name, r.description, r.servings, c.name AS category " +
                     "FROM recipes r " +
                     "LEFT JOIN categories c ON r.category_id = c.category_id " +
                     "WHERE r.name LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int recipeID = rs.getInt("recipe_id");
                String recipeName = rs.getString("name");
                String description = rs.getString("description");
                int servings = rs.getInt("servings");
                String category = rs.getString("category");

                recipes.add(new Recipe(recipeID, 0, recipeName, description, servings, new ArrayList<>(), new ArrayList<>(), category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    // Fetch all categories
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT name FROM categories";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Fetch all recipes
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT r.recipe_id, r.name, r.description, r.servings, c.name AS category " +
                     "FROM recipes r " +
                     "LEFT JOIN categories c ON r.category_id = c.category_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int recipeID = rs.getInt("recipe_id");
                String recipeName = rs.getString("name");
                String description = rs.getString("description");
                int servings = rs.getInt("servings");
                String category = rs.getString("category");

                recipes.add(new Recipe(recipeID, 0, recipeName, description, servings, new ArrayList<>(), new ArrayList<>(), category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    // Fetch recipes by category
    public List<Recipe> searchRecipesByCategory(String categoryName) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT r.recipe_id, r.name, r.description, r.servings, c.name AS category " +
                     "FROM recipes r " +
                     "LEFT JOIN categories c ON r.category_id = c.category_id " +
                     "WHERE c.name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoryName);  // Set the category name in the query
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int recipeID = rs.getInt("recipe_id");
                String recipeName = rs.getString("name");
                String description = rs.getString("description");
                int servings = rs.getInt("servings");
                String category = rs.getString("category");

                recipes.add(new Recipe(recipeID, 0, recipeName, description, servings, new ArrayList<>(), new ArrayList<>(), category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
