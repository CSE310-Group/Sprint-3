import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UserGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private Users users;
    private Recipes recipes;

    public UserGUI() {
        users = new Users();
        recipes = new Recipes();
        setTitle("User Login/Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(e -> loginUser());
        registerButton.addActionListener(e -> registerUser());

        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        for (User user : users.getUserList()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                this.dispose(); // Close login window
                users.setCurrentUser(user); // Set current user after login
                SwingUtilities.invokeLater(() -> {
                    MainPageGUI mainPage = new MainPageGUI(recipes);
                    mainPage.setVisible(true);
                });
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid Credentials");
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (users.userExists(username)) {
            JOptionPane.showMessageDialog(this, "User already exists, please login.");
        } else {
            users.addUser(username, password);
        }
    }
}

