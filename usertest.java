import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class usertest {
    private List<User> userList;
    private static final String FILE_NAME = "users.txt";

    public usertest() {
        this.userList = new ArrayList<>();
        loadUsersFromFile();
    }

/*************  ✨ Codeium Command ⭐  *************/
/**
 * Adds a new user to the user list and saves the updated list to a file.
 *
 * @param user the User object to be added to the list
 */

/******  09e66d23-c30f-49e5-9102-f50feaaf686d  *******/
    public void addUser(User user) {
        userList.add(user);
        saveUsersToFile();
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : userList) {
                writer.write(user.getUserId() + "," + user.getUsername() + "," + user.getPassword());
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
                    try {
                        int userId = Integer.parseInt(parts[0].trim());
                        String username = parts[1].trim();
                        String password = parts[2].trim();
                        userList.add(new User(username, password, userId));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid user data format: " + line);
                    }
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
