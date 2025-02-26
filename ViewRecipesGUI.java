import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class ViewRecipesGUI {
    private JFrame frame;
    private JList<String> recipeList;
    private DefaultListModel<String> listModel;
    private Recipes recipes;

    public ViewRecipesGUI(Recipes recipes) {
        this.recipes = recipes;
        frame = new JFrame("View Recipes");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        listModel = new DefaultListModel<>();
        for (Recipe recipe : recipes.getRecipes()) {
            listModel.addElement(recipe.getName());
        }
        
        recipeList = new JList<>(listModel);
        recipeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = recipeList.getSelectedIndex();
                    if (index != -1) {
                        new RecipeDetailGUI(recipes.getRecipes().get(index));
                    }
                }
            }
        });
        
        frame.add(new JScrollPane(recipeList));
        frame.setVisible(true);
    }
}