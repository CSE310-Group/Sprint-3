import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class RecipeDetailGUI {
    private JFrame frame;
    private Recipe recipe;
    private JTextArea details;
    private JPanel reviewsPanel;
    private double scaleFactor = 1.0;
    private Users users;

    public RecipeDetailGUI(Recipe recipe) {
        users = new Users();
        this.recipe = recipe;
        frame = new JFrame(recipe.getName());
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        details = new JTextArea();
        details.setEditable(false);
        updateDetails();

        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        loadReviews();

        JButton halveBtn = new JButton("Halve Ingredients");
        JButton doubleBtn = new JButton("Double Ingredients");
        JButton reviewBtn = new JButton("Add Review");

        halveBtn.addActionListener(e -> adjustIngredients(0.5));
        doubleBtn.addActionListener(e -> adjustIngredients(2.0));
        reviewBtn.addActionListener(e -> new ReviewGUI(recipe.getRecipeId(), users.getCurrentUser().getUserId(), users.getCurrentUser().getUsername()));

        JPanel panel = new JPanel();
        panel.add(halveBtn);
        panel.add(doubleBtn);
        panel.add(reviewBtn);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(new JScrollPane(reviewsPanel), BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(details), BorderLayout.CENTER);
        contentPanel.add(panel, BorderLayout.SOUTH);

        frame.add(contentPanel);
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

    private void loadReviews() {
        reviewsPanel.removeAll();
        try (BufferedReader reader = new BufferedReader(new FileReader("reviews.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Review review = Review.fromFileFormat(line);
                if (review.getRecipeId() == recipe.getRecipeId()) {
                    JPanel reviewBox = new JPanel();
                    reviewBox.setLayout(new BorderLayout());
                    reviewBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    
                    JLabel userLabel = new JLabel(review.getUsername() + " rated: ");
                    JPanel starPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    for (int i = 0; i < 5; i++) {
                        JLabel star = new JLabel(i < review.getRating() ? "★" : "☆");
                        star.setFont(new Font("SansSerif", Font.BOLD, 18));
                        starPanel.add(star);
                    }
                    
                    JTextArea commentArea = new JTextArea(review.getComment());
                    commentArea.setEditable(false);
                    commentArea.setLineWrap(true);
                    commentArea.setWrapStyleWord(true);
                    
                    reviewBox.add(userLabel, BorderLayout.NORTH);
                    reviewBox.add(starPanel, BorderLayout.CENTER);
                    reviewBox.add(commentArea, BorderLayout.SOUTH);
                    
                    reviewsPanel.add(reviewBox);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reviewsPanel.revalidate();
        reviewsPanel.repaint();
    }
}
