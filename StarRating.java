import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StarRating {
    private int userRating = 0;  // Stores the selected rating

    public JPanel createStarRatingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        ButtonGroup starGroup = new ButtonGroup();
        JRadioButton[] stars = new JRadioButton[5];

        for (int i = 0; i < 5; i++) {
            final int rating = i + 1;
            stars[i] = new JRadioButton("*");  // Unicode Star Symbol
            stars[i].setFont(new Font("Arial", Font.BOLD, 20));
            stars[i].setFocusPainted(false);
            stars[i].setBorderPainted(false);
            stars[i].setContentAreaFilled(false);

            // Action Listener to get selected rating
            stars[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userRating = rating;
                    System.out.println("User rated: " + userRating + " stars");
                }
            });

            starGroup.add(stars[i]);
            panel.add(stars[i]);
        }

        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Star Rating System");
        StarRating starRating = new StarRating();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());
        frame.add(starRating.createStarRatingPanel(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
