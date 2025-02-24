import java.awt.*;
// import java.awt.List; // Removed incorrect import
import java.util.List; // Added correct import
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class Recipe {
    private static final AtomicInteger recipeIdCounter = new AtomicInteger(1);
    private int recipeId;
    private int userId;
    private String name;
    private String description;
    private List<Ingredient> ingredients;
    private List<String> instructions;

    public Recipe(int userId, String name, String description, List<Ingredient> ingredients, List<String> instructions) {
        this.recipeId = recipeIdCounter.getAndIncrement();
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.ingredients = new ArrayList<>(ingredients);
        this.instructions = new ArrayList<>(instructions);
    }

    public int getRecipeId() { return recipeId; }
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public List<String> getInstructions() { return instructions; }

    public String toFileFormat() {
        String ingredientStr = ingredients.stream().map(Ingredient::toString).collect(Collectors.joining(","));
        return recipeId + "|" + userId + "|" + name + "|" + description + "|" + ingredientStr + "|" + String.join(",", instructions);
    }

    public static Recipe fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        int recipeId = Integer.parseInt(parts[0]);
        int userId = Integer.parseInt(parts[1]);
        List<Ingredient> ingredients = Arrays.stream(parts[4].split(","))
                .map(str -> {
                    String[] ingParts = str.split(" ", 3);
                    return new Ingredient(ingParts[2], ingParts[1], Integer.parseInt(ingParts[0]));
                }).collect(Collectors.toList());
        List<String> steps = Arrays.asList(parts[5].split(","));
        Recipe recipe = new Recipe(userId, parts[2], parts[3], ingredients, steps);
        recipe.recipeId = recipeId; // Maintain original recipe ID
        return recipe;
    }
}