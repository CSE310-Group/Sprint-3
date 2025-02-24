import java.util.*;

import javax.swing.SwingUtilities;

public class main {
    public static void main(String args[]) {
        Recipes recipes = new Recipes(); // Shared recipe storage
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Welcome to the Recipe App!");

        // Pass recipes object to mainpagegui
        Users users = new Users();
        SwingUtilities.invokeLater(() -> {
            new UserGUI(users).setVisible(true);
        });
    }
}
