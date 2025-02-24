import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> userList;
    private static final String FILE_NAME = "users.txt";

    public Users() {
        this.userList = new ArrayList<>();
        loadUsersFromFile();
    }

    public void addUser(User user) {
        userList.add(user);
        saveUsersToFile();
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : userList) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int userId = Integer.parseInt(parts[0]);
                    String username = parts[1];
                    String password = parts[2];
                    userList.add(new User(username, password, userId));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean authenticateUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // Successful login
            }
        }
        return false; // Login failed
    }
    
}