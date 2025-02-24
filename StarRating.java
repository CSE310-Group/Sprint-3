import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StarRating {
    private int userRating = 0;  // Stores the selected rating

    public JPanel createStarRatingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create an array to hold the star buttons
        JButton[] stars = new JButton[5];

        for (int i = 0; i < 5; i++) {
            final int rating = i + 1;
            stars[i] = new JButton("*");  // Star Symbol
            stars[i].setFont(new Font("Arial", Font.BOLD, 40));
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
        JFrame frame = new JFrame("Star Rating System");
        StarRating starRating = new StarRating();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());
        frame.add(starRating.createStarRatingPanel(), BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
    }
}
