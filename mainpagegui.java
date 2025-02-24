import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPageGUI extends JFrame {
    private Recipes recipes;
    private Users users;

    public MainPageGUI(Recipes recipes, Users users) {
        this.recipes = recipes;
        this.users = users;
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton viewRecipesButton = new JButton("View Recipes");
        viewRecipesButton.addActionListener(e -> viewRecipes());
        
        JButton createRecipeButton = new JButton("Create Recipe");
        createRecipeButton.addActionListener(e -> {
            RecipieCreate createRecipeGUI = new RecipieCreate(recipes, this);
            createRecipeGUI.setVisible(true);
            setVisible(false);
        });

        JButton switchUserButton = new JButton("Switch User");
        switchUserButton.addActionListener(e -> switchUser());
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> System.exit(0));

        add(viewRecipesButton);
        add(createRecipeButton);
        add(switchUserButton);
        add(logoutButton);
    }

    private void viewRecipes() {
        JFrame viewFrame = new JFrame("View Recipes");
        viewFrame.setSize(400, 500);
        viewFrame.setLocationRelativeTo(null);

        JTextArea recipeText = new JTextArea();
        recipeText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recipeText);
        
        recipes.loadFromFile("recipes.txt");
        for (Recipe recipe : recipes.getRecipes()) {
            recipeText.append("ID: " + recipe.getRecipeId() + "\n");
            recipeText.append("User ID: " + recipe.getUserId() + "\n");
            recipeText.append("Name: " + recipe.getName() + "\n");
            recipeText.append("Description: " + recipe.getDescription() + "\n");
            recipeText.append("Ingredients: " + recipe.getIngredients() + "\n");
            recipeText.append("Instructions: " + recipe.getInstructions() + "\n");
            recipeText.append("-----------------------------\n");
        }
        
        viewFrame.add(scrollPane);
        viewFrame.setVisible(true);
    }

    private void switchUser() {
        dispose();
        new UserGUI(recipes, new Users());
    }


}

