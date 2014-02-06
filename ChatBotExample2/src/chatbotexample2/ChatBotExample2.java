/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotexample2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class ChatBotExample2 implements ActionListener, KeyListener{
    //Declare the JFrame components
    private JTextArea Display;
    private JTextField Input;
    private JButton Send;
    private JScrollPane ScrollPane;
    private JFrame Frame;
    public JPanel textareaPanel,textfieldPanel;
    private Brain myBrain;
    private Vocabulary botVocab;
    
    String input;
    String output;
    //Create the constructor
    public ChatBotExample2() {
        //Create the JFrame
        Frame = new JFrame("OntBot");
        Frame.setSize(600, 600);
        Frame.setLayout(new FlowLayout());
        textareaPanel=new JPanel();
        textfieldPanel=new JPanel();;
        //Set the JFrame's default close operation to EXIT_ON_CLOSE
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLocationRelativeTo(null);
        //Initialize the JTextArea
        Display = new JTextArea(25, 50);
        
        //Set the JTextArea so it can't be edited
        Display.setEditable(false);
        
        //Set the JTextArea to wrap lines
        Display.setLineWrap(true);
        
        //Create a new JScrollPane based on the JTextArea
        ScrollPane = new JScrollPane(Display);
        textareaPanel.add(ScrollPane,BorderLayout.CENTER);
        //Initialize the JTextField
        Input = new JTextField(35);
        Input.addKeyListener(this);
        textfieldPanel.add(Input,BorderLayout.EAST);
        
        //Initialize the JButton
        Send = new JButton("Send");
        
        //Set the JButton's Action Listener to this (ChatBotExample1 class)
        Send.addActionListener(this);
        textfieldPanel.add(Send,BorderLayout.WEST);
        //Add all components to the JFrame's content pane
        Frame.add(textareaPanel,BorderLayout.NORTH);
        Frame.add(textfieldPanel,BorderLayout.SOUTH);
  
        
        //Show the JFrame
        Frame.setVisible(true);
        
        botVocab = new Vocabulary();
        String pass = JOptionPane.showInputDialog(Frame, "Please enter the password to view vocabulary database: ");
        if(pass.equalsIgnoreCase("123123"))
        botVocab.show(true);
        else
        botVocab.show(false);
        //Adding few phrases by default
        botVocab.addKey("hello");
        botVocab.addPhrase("hello", "hello, my name is ChatBot."); //key,phrase
        botVocab.addPhrase("hello", "hello, I'm OntBot.");
        botVocab.addPhrase("hello", "hello,how are you?.");
        botVocab.addPhrase("hello", "hey there!");

        botVocab.addKey("bye");
        botVocab.addPhrase("bye", "It was nice talking to you.");
        botVocab.addPhrase("bye", "See you some other time!");
        botVocab.addPhrase("bye", "Bye!!");
        botVocab.addPhrase("bye", "Sayonara");


        botVocab.addKey("how are you");
        botVocab.addPhrase("how are you", "I'm doing fine, and you?");
        botVocab.addPhrase("how are you", "I'm doing great!");
        botVocab.addPhrase("how are you", "I'm super-fine, what about you?");

        botVocab.addKey("i'm good");
        botVocab.addPhrase("i'm good", "That's good to hear.");
        botVocab.addPhrase("i'm good", "Awesome!");
        botVocab.addPhrase("i'm good", "I'm glad to hear that.");
        
        botVocab.addKey("what is a compiler");
        botVocab.addPhrase("what is a compiler", "A compiler is a program that can read a program in source Language and translates into equivalent target language");
        botVocab.addPhrase("what is a compiler", "A compiler is a system software that reads a program in source Language and translates into a target language");

        botVocab.addKey("what is syntax analyser");
        botVocab.addPhrase("what is a syntax analyser", "Syntax Analyzer creates the syntactic structure of the given source program.");
        botVocab.addPhrase("what is a syntax analyser", "The syntax analyzer checks whether a given source program satisfies the rules implied by a context-free grammar or not");
        
       botVocab.addKey("what is context free grammar");
       botVocab.addPhrase("what is a context free grammar", "A context free grammar gives a precise syntactic specification of a programming language");
       botVocab.addPhrase("what is a context free grammar", "Inherently recursive structures of a programming language are defined by a context-free grammar");

       botVocab.addKey("what is ambiguity");
               botVocab.addPhrase("what is ambiguity", "A grammar produces more than one parse tree for a sentence is \n" +
"   called as an ambiguous grammar.");


        myBrain = new Brain(botVocab);
    }
    public void actionPerformed(ActionEvent ae) {
        //If the user clicks the send button
        if (ae.getActionCommand().equals("Send")) {
            //Set the input to the JTextField's value
             input = Input.getText();
            
            //Set the output as "Hello World"
            output = myBrain.generateOutput(input);
            
            //This is the tricky part - we want to keep the value that's currently in the JTextArea and append the values
            //from the input variable and the output variable
            Display.setText(Display.getText() + "You: " + input + "\n");
            Display.setText(Display.getText() + "OntBot: " + output + "\n");
            
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //I don't expect you to understand this, but memorize this code!
        //If you're truly a geek, this invokes the chat bot on a separate thread from the main thread.  This makes it so there
        //is less chance of a conflict.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ChatBotExample2();
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

            
        }
    

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
           //Set the input to the JTextField's value
             input = Input.getText();
            
            //Set the output as "Hello World"
             output = myBrain.generateOutput(input);
            
            //This is the tricky part - we want to keep the value that's currently in the JTextArea and append the values
            //from the input variable and the output variable
            Display.setText(Display.getText() + "You: " + input + "\n");
            Display.setText(Display.getText() + "OntBot: " + output + "\n");
    }

    }
    @Override
    public void keyReleased(KeyEvent e) {
          
    }
    
}
 