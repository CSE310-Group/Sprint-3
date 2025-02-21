import java.awt.*;

import javax.swing.*;

 public class CreateRecipeG extends JFrame {

private JFrame frame;
private JLabel instructionlabel;
private JTextField inputUML;
private JButton upButton;
private String Message;

 public void ClassCreation(){

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
 }

   public Frame getFrame() {
       return frame;
   }
}