import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;

class Recipes {
    private List<Recipe> recipeList;

    public Recipes() {
        recipeList = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Recipe recipe : recipeList) {
                writer.println(recipe.toFileFormat());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        recipeList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recipeList.add(Recipe.fromFileFormat(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
