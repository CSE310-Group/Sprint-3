import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewGUI extends JFrame {
    private int userRating = 0;  // Stores the selected rating

    public ReviewGUI() {
        super("Review GUI");
        
        // Set the size of the frame
        setSize(400, 300);
        // Set the default close operation to EXIT_ON_CLOSE
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the location of the frame to the center of the screen
        setLocationRelativeTo(null);

        // Create a panel for the review text field and star rating
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create the text field
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(350, 30));
        mainPanel.add(textField);

        // Add star rating panel
        mainPanel.add(createStarRatingPanel());

        // Create the submit button
        JButton submitButton = new JButton("Submit Review");
        mainPanel.add(submitButton);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String review = textField.getText();
                System.out.println("User Review: " + review);
                System.out.println("User Rated: " + userRating + " stars");
            }
        });

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
    }

    public JPanel createStarRatingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton[] stars = new JButton[5];

        for (int i = 0; i < 5; i++) {
            final int rating = i + 1;
            stars[i] = new JButton("★");  // Unicode star symbol
            stars[i].setFont(new Font("SansSerif", Font.BOLD, 40)); // Use a font that supports stars
            stars[i].setFocusPainted(false);
            stars[i].setBorderPainted(false);
            stars[i].setContentAreaFilled(false);
            stars[i].setOpaque(false); // Make the button transparent
            stars[i].setForeground(Color.LIGHT_GRAY); // Set default color

            // Action Listener to get selected rating
            stars[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userRating = rating;
                    updateStarColors(stars);
                    System.out.println("User rated: " + userRating + " stars");
                }
            });

            panel.add(stars[i]);
        }

        return panel;
    }

    // Method to update star colors based on the selected rating
    private void updateStarColors(JButton[] stars) {
        for (int i = 0; i < stars.length; i++) {
            if (i < userRating) {
                stars[i].setForeground(Color.YELLOW); // Highlight selected stars
            } else {
                stars[i].setForeground(Color.LIGHT_GRAY); // Default color
            }
        }
    }

    public static void main(String[] args) {
        new ReviewGUI();
    }
}
