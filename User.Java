import java.io.*;

public class User {
    private String username;
    private String password;
    private int userId;

    public User(String username, String password, int userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return userId + "," + username + "," + password;
    }
}