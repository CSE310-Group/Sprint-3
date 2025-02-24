import java.util.*;

import javax.swing.SwingUtilities;

public class main {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Welcome to the Recipe App!");

        Users users = new Users();
        Recipes recipes = new Recipes();
        System.out.println("Current User: " + users.getCurrentUser());
        SwingUtilities.invokeLater(() -> new UserGUI(users, recipes));
        System.out.println(users.getCurrentUser());
    }
}


