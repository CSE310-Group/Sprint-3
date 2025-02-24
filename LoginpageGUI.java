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
public class LoginpageGUI extends JFrame {

    public LoginpageGUI() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#f5deb3"));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.decode("#f5deb3"));

        // Add title
        JLabel title = new JLabel("Login Page");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        // Add login button
        JLabel loginButton = new JLabel("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Login button clicked");
            }
        });
        panel.add(loginButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        // Add signup button
        JLabel signupButton = new JLabel("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 18));
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Sign Up button clicked");
            }
        });
        panel.add(signupButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        // Add logout button
        JLabel logoutButton = new JLabel("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 18));
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Logout button clicked");    
            }
            }
        );
        panel.add(logoutButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        // Add back button
        JLabel backButton = new JLabel("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            goBackToMainPage();
      
            }
        });
    }
}
