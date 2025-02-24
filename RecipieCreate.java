import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RecipieCreate extends JFrame implements ActionListener {
    
    private JTextField recipeField, recipeField2, recipeField3, recipeField4;
    private JButton createButton;
    private Recipes recipes; // Shared Recipes object

    public RecipieCreate(Recipes recipes) {
        this.recipes = recipes; // Store reference to shared Recipes object

        setTitle("Create a Recipe");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Recipe Name:"));
        recipeField = new JTextField(16);
        panel.add(recipeField);

        panel.add(new JLabel("Recipe Description:"));
        recipeField2 = new JTextField(16);
        panel.add(recipeField2);

        panel.add(new JLabel("Ingredients (comma-separated):"));
        recipeField3 = new JTextField(16);
        panel.add(recipeField3);

        panel.add(new JLabel("Instructions (comma-separated):"));
        recipeField4 = new JTextField(16);
        panel.add(recipeField4);

        createButton = new JButton("Create Recipe");
        createButton.setFont(new Font("Arial", Font.BOLD, 14));
        createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createButton.addActionListener(this);
        panel.add(createButton);

        add(panel);

       
        
    }



    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String name = recipeField.getText();
            String description = recipeField2.getText();
            String ingredients = recipeField3.getText();
            String instructions = recipeField4.getText();

            // Create Recipe object
            Recipe recipe = new Recipe(name, description, ingredients, instructions);

            // Add to the shared Recipes object
            recipes.addRecipe(recipe);
            
            // Print all recipes to confirm
            recipes.printAllRecipes();

            // Clear text fields after adding
            recipeField.setText("");
            recipeField2.setText("");
            recipeField3.setText("");
            recipeField4.setText("");
        }
    }

    public static void main(String[] args) {
        Recipes recipes = new Recipes(); // Shared Recipes object
        SwingUtilities.invokeLater(() -> new RecipieCreate(recipes).setVisible(true));
    }
}

