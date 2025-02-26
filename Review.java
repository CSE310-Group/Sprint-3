import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class Review {
    private int userId;
    private int recipeId;
    private String username;
    private int rating;
    private String comment;

    public Review(int userId, int recipeId, String username, int rating, String comment) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
    }

    public String toFileFormat() {
        return userId + "|" + recipeId + "|" + username + "|" + rating + "|" + comment;
    }

    public static Review fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        return new Review(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), parts[4]);
    }

    public static void saveReview(Review review) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("reviews.txt", true))) {
            writer.println(review.toFileFormat());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getRating() {
        return rating;
    }
    public String getComment() {
        return comment;
    }
    public int getRecipeId() {
        return recipeId;
    }
    public String getUsername() {
        return username;
    }
    
}