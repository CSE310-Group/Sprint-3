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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class mainpagegui extends JFrame {
    private JPanel panel;

    public mainpagegui() {
        setTitle("Recipe Categories");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#f5deb3"));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.decode("#f5deb3"));

        // ADD create recipe button
        JLabel createRecipeButton = new JLabel("Create Recipe");
        createRecipeButton.setFont(new Font("Arial", Font.BOLD, 18));
        createRecipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createRecipeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createRecipeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the CreateRecipeGUI
                CreateRecipeGUI createRecipeGUI = new CreateRecipeGUI();
                createRecipeGUI.setVisible(true);
            }
        });
        panel.add(createRecipeButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Add title
        JLabel title = new JLabel("Meal Categories");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        // Add clickable categories
        panel.add(createCategoryPanel("Breakfast", BreakfastGUI.class));
        panel.add(createCategoryPanel("Lunch", LunchGUI.class));
        panel.add(createCategoryPanel("Dinner", DinnerGUI.class));
        panel.add(createCategoryPanel("Dessert", DessertGUI.class));
        
        add(scrollPane);
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
                    targetFrame = new LunchGUI(mainpagegui.this); // Pass mainpagegui reference
                } else if (targetClass == DinnerGUI.class) {
                    targetFrame = new DinnerGUI(mainpagegui.this); // Update for DinnerGUI
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

        categoryPanel.setPreferredSize(new Dimension(350, 50)); // Set size
        return categoryPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mainpagegui frame = new mainpagegui();
            frame.setVisible(true);
        });
    }
}
