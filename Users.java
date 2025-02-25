
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Users {
    private List<User> userList;
    private static final String FILE_NAME = "users.txt";
    private User currentUser;

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
                    User user = new User(username, password, id);
                    userList.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User file not found, starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadCurrentUser(); // Load the last logged-in user
    }

    public void addUser(String username, String password) {
        int newUserId = userList.isEmpty() ? 1 : userList.get(userList.size() - 1).getUserId() + 1;
        User newUser = new User(username, password, newUserId);
        userList.add(newUser);
        saveUsersToFile();
        JOptionPane.showMessageDialog(null, "Registration Successful!");
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : userList) {
                writer.write(user.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveCurrentUser(); // Ensure current user is saved
    }

    private void saveCurrentUser() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("current_user.txt"))) {
            if (currentUser != null) {
                writer.write(currentUser.getUsername());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCurrentUser() {
        try (BufferedReader reader = new BufferedReader(new FileReader("current_user.txt"))) {
            String username = reader.readLine();
            if (username != null) {
                for (User user : userList) {
                    if (user.getUsername().equals(username)) {
                        currentUser = user;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previously logged-in user.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        saveCurrentUser();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getUserList() {
        return userList;
    }

    public boolean userExists(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}