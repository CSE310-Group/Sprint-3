import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class ReviewGUI extends JFrame {
    private int userRating = 0;
    private int recipeId;
    private int userId;
    private JTextField textField;

    public ReviewGUI(int recipeId, int userId) {
        super("Review GUI");
        this.recipeId = recipeId;
        this.userId = userId;

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(350, 30));
        mainPanel.add(textField);
        mainPanel.add(createStarRatingPanel());

        JButton submitButton = new JButton("Submit Review");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> saveReview());

        mainPanel.add(submitButton);
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createStarRatingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.WHITE);

        JButton[] stars = new JButton[5];
        for (int i = 0; i < 5; i++) {
            final int rating = i + 1;
            stars[i] = new JButton("★");
            stars[i].setFont(new Font("SansSerif", Font.BOLD, 40));
            stars[i].setFocusPainted(false);
            stars[i].setBorderPainted(false);
            stars[i].setContentAreaFilled(false);
            stars[i].setOpaque(false);
            stars[i].setForeground(Color.BLACK);
            stars[i].addActionListener(e -> {
                userRating = rating;
                updateStarColors(stars);
            });
            panel.add(stars[i]);
        }
        return panel;
    }

    private void updateStarColors(JButton[] stars) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].setForeground(i < userRating ? Color.YELLOW : Color.BLACK);
        }
    }

    private void saveReview() {
        String comment = textField.getText().trim();
        Review review = new Review(userId, recipeId, userRating, comment);
        Review.saveReview(review);
        dispose();
    }
}
