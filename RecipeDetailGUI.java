import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class RecipeDetailGUI {
    private JFrame frame;
    private Recipe recipe;
    private JTextArea details;
    private double scaleFactor = 1.0;
    private Users users;

    public RecipeDetailGUI(Recipe recipe) {
        Users users = new Users();
        this.recipe = recipe;
        frame = new JFrame(recipe.getName());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        details = new JTextArea();
        updateDetails();
        details.setEditable(false);

        JButton halveBtn = new JButton("Halve Ingredients");
        JButton doubleBtn = new JButton("Double Ingredients");
        JButton reviewBtn = new JButton("Add Review");

        halveBtn.addActionListener(e -> adjustIngredients(0.5));
        doubleBtn.addActionListener(e -> adjustIngredients(2.0));
        reviewBtn.addActionListener(e -> new ReviewGUI(recipe.getRecipeId(), users.getCurrentUser().getUserId()));

        JPanel panel = new JPanel();
        panel.add(halveBtn);
        panel.add(doubleBtn);
        panel.add(reviewBtn);

        frame.add(new JScrollPane(details), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void adjustIngredients(double factor) {
        scaleFactor *= factor;
        updateDetails();
    }

    private void updateDetails() {
        details.setText(recipe.getDescription() + "\n\nIngredients:\n");
        for (Ingredient ing : recipe.getIngredients()) {
            double newQty = ing.getQuantity() * scaleFactor;
            if (newQty < 1 && newQty > 0) {
                details.append(String.format("%.1f %s %s\n", newQty, ing.getMeasurement(), ing.getName()));
            } else {
                details.append((int) newQty + " " + ing.getMeasurement() + " " + ing.getName() + "\n");
            }
        }
        details.append("\nInstructions:\n" + String.join("\n", recipe.getInstructions()));
    }
}
