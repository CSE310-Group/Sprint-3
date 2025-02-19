import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BreakfastGUI extends JFrame {
    private JPanel panel;
    private JFrame mainPage;

    public BreakfastGUI(JFrame mainPage) {
        this.mainPage = mainPage;

        setTitle("Breakfast Recipes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#f5deb3"));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.decode("#f5deb3"));

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> goBackToMainPage());
        panel.add(backButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel title = new JLabel("All Breakfast Recipes");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        add(scrollPane);
        loadRecipes();
    }

    private void goBackToMainPage() {
        this.dispose();
        mainPage.setVisible(true);
    }

    private void loadRecipes() {
        String[][] sampleRecipes = {
            {"Pancakes", "1. Mix ingredients.\n2. Cook on skillet...", null},
            {"Omelette", "1. Beat eggs.\n2. Cook with fillings...", null},
            {"Smoothie", "1. Blend fruits.\n2. Serve chilled...", null}
        };

        for (String[] recipe : sampleRecipes) {
            addRecipeToPanel(recipe[0], recipe[1], null);
        }
    }

    private void addRecipeToPanel(String name, String description, byte[] imageData) {
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BorderLayout());
        recipePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        recipePanel.setBackground(Color.WHITE);
        recipePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel(name, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel descLabel = new JLabel("<html><p style='width:300px;'>" + description + "</p></html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        recipePanel.add(titleLabel, BorderLayout.NORTH);
        recipePanel.add(descLabel, BorderLayout.CENTER);

        recipePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openRecipeWindow(name, description);
            }
        });

        panel.add(recipePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.revalidate();
        panel.repaint();
    }

    private void openRecipeWindow(String name, String description) {
        JFrame recipeFrame = new JFrame(name);
        recipeFrame.setSize(400, 300);
        recipeFrame.setLocationRelativeTo(null);
        recipeFrame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(description);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setEditable(false);

        JButton convertButton = new JButton("Convert Ingredient");
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        convertButton.addActionListener(e -> {
            double ingredientAmount = 3.0; // Example amount
            double convertedAmount = conversions.tablespoonsToTeaspoons(ingredientAmount);
            String result = conversions.decimalToFraction(convertedAmount);
            JOptionPane.showMessageDialog(recipeFrame, "Converted Amount: " + result + " Teaspoons");
        });

        recipeFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        recipeFrame.add(convertButton, BorderLayout.SOUTH);
        recipeFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BreakfastGUI(null));
    }
}
