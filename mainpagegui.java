import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainpagegui extends JFrame {
    private JPanel panel;
    private Recipes recipes; // Shared Recipes object

    public mainpagegui(Recipes recipes) {
        this.recipes = recipes; // Store the shared Recipes instance

        setTitle("Recipe Categories");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set layout to BoxLayout for vertical stacking
        panel.setBackground(Color.decode("#f5deb3"));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.decode("#F5FFda"));

        // Add title at the top
        JLabel title = new JLabel("Meal Categories");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing between title and categories

        // Add the category panels (e.g., Breakfast, Lunch, etc.)
        panel.add(createCategoryPanel("Breakfast", BreakfastGUI.class));
        panel.add(createCategoryPanel("Lunch", LunchGUI.class));
        panel.add(createCategoryPanel("Dinner", DinnerGUI.class));
        panel.add(createCategoryPanel("Dessert", DessertGUI.class));

        panel.add(Box.createVerticalGlue()); // Push the "Create Recipe" button to the bottom

        // ADD create recipe button
        JLabel createRecipeButton = new JLabel("Create Recipe");
        createRecipeButton.setFont(new Font("Arial", Font.BOLD, 18));
        createRecipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createRecipeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createRecipeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the CreateRecipeGUI and pass the shared recipes
                RecipieCreate createRecipeGUI = new RecipieCreate(recipes);
                createRecipeGUI.setVisible(true);
            }
        });
        panel.add(createRecipeButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Extra space after the button (optional)

        add(scrollPane); // Add scroll pane to frame
    }

    // Create a category panel with reference to the target class
    private JPanel createCategoryPanel(String categoryName, Class<? extends JFrame> targetClass) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BorderLayout());
        categoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        categoryPanel.setBackground(Color.WHITE);
        categoryPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel label = new JLabel(categoryName, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        categoryPanel.add(label, BorderLayout.CENTER);

        categoryPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    JFrame targetFrame;
                    if (targetClass == BreakfastGUI.class) {
                        targetFrame = new BreakfastGUI(mainpagegui.this);
                    } else if (targetClass == LunchGUI.class) {
                        targetFrame = new LunchGUI(mainpagegui.this);
                    } else if (targetClass == DinnerGUI.class) {
                        targetFrame = new DinnerGUI(mainpagegui.this);
                    } else if (targetClass == DessertGUI.class) {
                        targetFrame = new DessertGUI(mainpagegui.this);
                    } else {
                        targetFrame = targetClass.getDeclaredConstructor(mainpagegui.class).newInstance(mainpagegui.this);
                    }
                    targetFrame.setVisible(true);
                    setVisible(false); // Hide main page when opening a new window
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        categoryPanel.setPreferredSize(new Dimension(350, 50)); // Set size for each category panel
        return categoryPanel;
    }

    public static void main(String[] args) {
        Recipes recipes = new Recipes(); // Create shared Recipes object
        SwingUtilities.invokeLater(() -> {
            mainpagegui frame = new mainpagegui(recipes); // Pass the shared Recipes object
            frame.setVisible(true);
        });
    }
}
