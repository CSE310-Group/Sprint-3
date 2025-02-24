import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BreakfastGUI extends JFrame {
    private JPanel panel;
    private JFrame mainPage; // Reference to mainpagegui

    public BreakfastGUI(JFrame mainPage) {
        this.mainPage = mainPage; // Store reference to mainpagegui

        setTitle("Lunch Recipes"); // Correct title
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

        // Add Back Button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToMainPage();
            }
        });
        panel.add(backButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        // Add title
        JLabel title = new JLabel("All Lunch Recipes");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        add(scrollPane);
        loadRecipes();
    }

    private void goBackToMainPage() {
        this.dispose(); // Close the current window
        mainPage.setVisible(true); // Show mainpagegui
    }

    private void loadRecipes() {
        // Dummy recipe data for testing
        String[][] sampleRecipes = {
            {"Caesar Salad", "1. Chop lettuce.\n2. Add dressing and croutons...", null},
            {"Grilled Cheese", "1. Butter bread.\n2. Add cheese and grill...", null},
            {"Pasta Primavera", "1. Cook pasta.\n2. Add veggies and sauce...", null}
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

        JLabel imageLabel = new JLabel("No Image Available", SwingConstants.CENTER);

        recipePanel.add(titleLabel, BorderLayout.NORTH);
        recipePanel.add(imageLabel, BorderLayout.CENTER);
        recipePanel.add(descLabel, BorderLayout.SOUTH);

        recipePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openRecipeWindow(name, description);
            }
        });

        panel.add(recipePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        panel.revalidate();
        panel.repaint();

        // Jbutton CreateRecipeButton = new Jbutton("Create Recipe");
        // CreateRecipeButton.setFont(new Font("Arial", Font.BOLD, 14));
        // CreateRecipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // panel.add(CreateRecipeButton);        
        // panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // CreateRecipeButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         CreateRecipeGUI CreateRecipe = new CreateRecipeGUI();
        //         CreateRecipe.setVisible(true);
        //     }
        // });
    }

    private void openRecipeWindow(String name, String description) {
        JFrame recipeFrame = new JFrame(name);
        recipeFrame.setSize(400, 300);
        recipeFrame.setLocationRelativeTo(null);
        recipeFrame.setLayout(new BorderLayout());
    
        JTextArea textArea = new JTextArea(description);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setEditable(false);
    
        JButton halfButton = new JButton("Half");
        JButton doubleButton = new JButton("Double");
        halfButton.setFont(new Font("Arial", Font.BOLD, 14));
        doubleButton.setFont(new Font("Arial", Font.BOLD, 14));
    
        // halfButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String updatedText =conversions.scaleRecipe(textArea.getText(), 0.5);
        //         textArea.setText(updatedText);
        //     }
        // });
    
        // doubleButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String updatedText = conversions.scaleRecipe(textArea.getText(), 2);
        //         textArea.setText(updatedText);
        //     }
        // });


    
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(halfButton);
        buttonPanel.add(doubleButton);
    
        recipeFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        recipeFrame.add(buttonPanel, BorderLayout.SOUTH);
        recipeFrame.setVisible(true);
    
    
    
        JPanel starPanel = new StarRating().createStarRatingPanel();
        recipeFrame.add(starPanel, BorderLayout.NORTH);
       


    }


    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LunchGUI(null));
    }
}
