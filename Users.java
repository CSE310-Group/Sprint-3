import java.io.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Users {
    private List<User> userList;
    private static final String FILE_NAME = "users.txt";

    public Users() {
        userList = new ArrayList<>();
        loadUsersFromFile();
    }

    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String username = parts[1];
                    String password = parts[2];
                    userList.add(new User(username, password, id));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User file not found, starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(null, "User already exists, please login.");
                return;
            }
        }
        int newUserId = userList.isEmpty() ? 1 : userList.get(userList.size() - 1).getUserId() + 1;
        User newUser = new User(username, password, newUserId);
        userList.add(newUser);
        saveUserToFile(newUser);
        JOptionPane.showMessageDialog(null, "Registration Successful!");
    }

    private void saveUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(user.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAllUsers() {
        for (User user : userList) {
            System.out.println(user);
        }
    }
}