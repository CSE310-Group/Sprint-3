import java.awt.*;
import javax.swing.*;

 public class CreateRecipeGUI extends JFrame {

private JFrame frame;
private JLabel instructionlabel;
private JTextField inputUML;
private JButton upButton;
private String Message;

 public void CreateRecipeGUi(){

   frame = new JFrame();
   frame.setSize(300, 400);
   frame.setLocationRelativeTo(null);
   frame.setVisible(true);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setTitle("Class Design");

   JPanel CreationPanel = new JPanel();
   CreationPanel.setLayout(new BorderLayout());

   instructionlabel = new JLabel("Fill Class details in using UML");
   CreationPanel.add(instructionlabel,BorderLayout.NORTH);

   inputUML = new JTextField("",20);
   CreationPanel.add(inputUML,BorderLayout.CENTER);
  
   frame.add(CreationPanel);

   JTextField recipeName = new JTextField();
   recipeName.setBounds(50, 50, 150, 20);

   JTextField recipeDescription = new JTextField();
   recipeDescription.setBounds(50, 100, 150, 20);

   JButton createRecipeButton = new JButton("Create Recipe");
   createRecipeButton.setBounds(50, 150, 150, 20);

   createRecipeButton.addActionListener(e -> {
       String name = recipeName.getText();
       String description = recipeDescription.getText();
       String uml = inputUML.getText();
       Message = "Name: " + name + "\nDescription: " + description + "\nUML: " + uml;
       JOptionPane.showMessageDialog(frame, Message);
   });

  


 }

   public Frame getFrame() {
       return frame;
   }
}
