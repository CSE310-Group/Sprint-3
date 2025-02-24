import javax.swing.SwingUtilities;
public class UserManagementApp {
    public static void main(String[] args) {
        Users users = new Users();
        SwingUtilities.invokeLater(() -> {
            new UserGUI(users).setVisible(true);
        });
    }
}
