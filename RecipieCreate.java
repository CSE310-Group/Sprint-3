import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;
public class RecipieCreate extends JFrame {
    private JTextField recipeNameField, recipeDescField;
    private DefaultListModel<String> ingredientListModel, stepListModel;
    private JList<String> ingredientList, stepList;
    private JButton addIngredientButton, addStepButton, saveButton, backButton;
    private Recipes recipes;
    private JFrame mainFrame;

    public RecipieCreate(Recipes recipes, JFrame mainFrame) {
        this.recipes = recipes;
        this.mainFrame = mainFrame;

        setTitle("Create a Recipe");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        recipeNameField = new JTextField();
        recipeDescField = new JTextField();
        inputPanel.add(new JLabel("Recipe Name:"));
        inputPanel.add(recipeNameField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(recipeDescField);
        
        add(inputPanel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel(new GridLayout(1, 2));
        
        ingredientListModel = new DefaultListModel<>();
        ingredientList = new JList<>(ingredientListModel);
        addIngredientButton = new JButton("Add Ingredient");
        addIngredientButton.addActionListener(e -> addIngredient());
        
        JPanel ingredientPanel = new JPanel(new BorderLayout());
        ingredientPanel.add(new JLabel("Ingredients"), BorderLayout.NORTH);
        ingredientPanel.add(new JScrollPane(ingredientList), BorderLayout.CENTER);
        ingredientPanel.add(addIngredientButton, BorderLayout.SOUTH);
        
        stepListModel = new DefaultListModel<>();
        stepList = new JList<>(stepListModel);
        addStepButton = new JButton("Add Step");
        addStepButton.addActionListener(e -> addStep());
        
        JPanel stepPanel = new JPanel(new BorderLayout());
        stepPanel.add(new JLabel("Steps"), BorderLayout.NORTH);
        stepPanel.add(new JScrollPane(stepList), BorderLayout.CENTER);
        stepPanel.add(addStepButton, BorderLayout.SOUTH);

        listPanel.add(ingredientPanel);
        listPanel.add(stepPanel);
        add(listPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save Recipe");
        saveButton.addActionListener(e -> saveRecipe());
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
            mainFrame.setVisible(true);
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addStep() {
        String step = JOptionPane.showInputDialog(this, "Enter step:");
        if (step != null && !step.trim().isEmpty()) {
            stepListModel.addElement(step);
        }
    }

    private void saveRecipe() {
        String name = recipeNameField.getText().trim();
        String description = recipeDescField.getText().trim();
        if (name.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        Users users = new Users(); // Load users to get the current user
        User currentUser = users.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "No user logged in.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int userId = currentUser.getUserId();
    
        List<String> steps = Collections.list(stepListModel.elements());
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientListModel.size(); i++) {
            String[] parts = ingredientListModel.getElementAt(i).split(" ", 3);
            ingredients.add(new Ingredient(parts[2], parts[1], Integer.parseInt(parts[0])));
        }
    
        Recipe recipe = new Recipe(userId, name, description, ingredients, steps);
        recipes.addRecipe(recipe);
        recipes.saveToFile("recipes.txt");
        
        JOptionPane.showMessageDialog(this, "Recipe saved successfully!");
        recipeNameField.setText("");
        recipeDescField.setText("");
        ingredientListModel.clear();
        stepListModel.clear();
    }
    
    private void addIngredient() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField nameField = new JTextField();
        JTextField quantityField = new JTextField();
        String[] measurements = {"cup", "teaspoon", "tablespoon", "pinch"};
        JComboBox<String> measurementDropdown = new JComboBox<>(measurements);
        
        panel.add(new JLabel("Ingredient Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Measurement:"));
        panel.add(measurementDropdown);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Ingredient", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                int quantity = Integer.parseInt(quantityField.getText().trim());
                String measurement = (String) measurementDropdown.getSelectedItem();
                ingredientListModel.addElement(quantity + " " + measurement + " " + name);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

