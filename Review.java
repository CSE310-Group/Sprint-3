import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class Review {
    private int userId;
    private int recipeId;
    private int rating;
    private String comment;

    public Review(int userId, int recipeId, int rating, String comment) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.rating = rating;
        this.comment = comment;
    }
    public int getUserId() {
        return userId;
    }
    public int getRecipeId() {
        return recipeId;
    }
    public int getRating() {
        return rating;
    }
    public String getComment() {
        return comment;
    }

    public String toFileFormat() {
        return userId + "|" + recipeId + "|" + rating + "|" + comment;
    }

    public static Review fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        return new Review(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
    }

    public static void saveReview(Review review) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("reviews.txt", true))) {
            writer.println(review.toFileFormat());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
