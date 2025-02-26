import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class ViewRecipesGUI {
    private JFrame frame;
    private JPanel recipePanel;
    private Recipes recipes;

    public ViewRecipesGUI(Recipes recipes) {
        this.recipes = recipes;
        frame = new JFrame("View Recipes");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
        loadRecipes();

        JScrollPane scrollPane = new JScrollPane(recipePanel);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private void loadRecipes() {
        recipePanel.removeAll();
        for (Recipe recipe : recipes.getRecipes()) {
            JPanel recipeBox = new JPanel();
            recipeBox.setLayout(new BorderLayout());
            recipeBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            recipeBox.setMaximumSize(new Dimension(450, 100));
            
            JLabel nameLabel = new JLabel(recipe.getName());
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            recipeBox.add(nameLabel, BorderLayout.NORTH);

            JButton viewButton = new JButton("View");
            viewButton.addActionListener(e -> new RecipeDetailGUI(recipe));
            recipeBox.add(viewButton, BorderLayout.SOUTH);

            recipePanel.add(recipeBox);
        }
        recipePanel.revalidate();
        recipePanel.repaint();
    }
}
