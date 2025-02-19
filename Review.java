
public class Review {
    private int rating;
    private String comment;
    private int userID;
    private int recipeID;

}
public Review(int rating, String comment, int userID, int recipeID) {
    this.rating = rating;
    this.comment = comment;
    this.userID = userID;
    this.recipeID = recipeID;
}
public int getRating() { return rating; }
public String getComment() { return comment; }
public int getUserID() { return userID; }
public int getRecipeID() { return recipeID; }

public void setRating(int rating) { this.rating = rating; }
public void setComment(String comment) { this.comment = comment; }
public void setUserID(int userID) { this.userID = userID; }
public void setRecipeID(int recipeID) { this.recipeID = recipeID; }

