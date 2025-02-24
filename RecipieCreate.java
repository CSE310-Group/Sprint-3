import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// public class RecipieCreate extends JFrame implements ActionListener {
    
//     private JTextField recipeField;
//     private JButton createButton;

//     public RecipieCreate() {
//         setTitle("Create a Recipe");
//         setSize(400, 200);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null); // Centers the frame

//         // Create a label
//         JLabel label = new JLabel("Enter your Recipe:");
        
//         // Create a text field
//         recipeField = new JTextField(16);

//         // Create a button
//         createButton = new JButton("Create Recipe");
//         createButton.setFont(new Font("Arial", Font.BOLD, 18));
//         createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//         createButton.addActionListener(this);

//         // Create a panel and add components
//         JPanel panel = new JPanel();
//         panel.add(label);
//         panel.add(recipeField);
//         panel.add(createButton);

//         // Add panel to frame
//         add(panel);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == createButton) {
//             System.out.println("Recipe created: " + recipeField.getText());
//         }
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             new RecipieCreate().setVisible(true);
//         });
//     }
// }

public class RecipieCreate extends JFrame implements ActionListener {
    
    private JTextField recipeField;
    private JButton createButton;

    public RecipieCreate() {
        setTitle("Create a Recipe");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the frame

        // Create a label
        JLabel label = new JLabel("Enter your Recipe Name:");
        
        // Create a text field
        recipeField = new JTextField(16);
        JLabel label2 = new JLabel("Enter your Recipe Description:");
        JTextField recipeField2 = new JTextField(16);
        JLabel label3 = new JLabel("Enter your Recipe Ingrediants:");
        JTextField recipeField3 = new JTextField(16);
        JLabel label4 = new JLabel("Enter your Recipe Instructions:");
        JTextField recipeField4 = new JTextField(16);

        // Create a button
        createButton = new JButton("Create Recipe");
        createButton.setFont(new Font("Arial", Font.BOLD, 18));
        createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createButton.addActionListener(this);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back button action
                dispose(); // Close the current frame
                new mainpagegui().setVisible(true);
            }
        });

        // Create a panel and add components
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(recipeField);
        panel.add(label2);
        panel.add(recipeField2);
        panel.add(label3);
        panel.add(recipeField3);
        panel.add(label4);
        panel.add(recipeField4);
        panel.add(createButton);

        // Add panel to frame
        add(panel);
    }

    // New constructor accepting mainpagegui
    public RecipieCreate(mainpagegui gui) {
        this(); // Call the default constructor
        // Additional initialization with gui if needed
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            System.out.println("Recipe created: " + recipeField.getText());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RecipieCreate().setVisible(true);
        });
    }
}

