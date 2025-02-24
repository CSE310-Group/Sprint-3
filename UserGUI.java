import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField userIdField;
    private JButton submitButton;
    private JButton loginButton;
    private Users users;

    public UserGUI(Users users) {
        this.users = users;
        setTitle("User Login & Registration");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("User ID:"));
        userIdField = new JTextField();
        panel.add(userIdField);

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        submitButton = new JButton("Register");
        loginButton = new JButton("Login");

        panel.add(submitButton);
        panel.add(loginButton);

        add(panel);

        submitButton.addActionListener(e -> registerUser());
        loginButton.addActionListener(e -> loginUser());
    }

    private void registerUser() {
        try {
            int userId = Integer.parseInt(userIdField.getText());
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            users.addUser(new User(username, password, userId));
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            userIdField.setText("");
            usernameField.setText("");
            passwordField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "User ID must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (users.authenticateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            this.dispose(); // Close the login window
            SwingUtilities.invokeLater(() -> new mainpagegui().setVisible(true)); // Open the main GUI
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
