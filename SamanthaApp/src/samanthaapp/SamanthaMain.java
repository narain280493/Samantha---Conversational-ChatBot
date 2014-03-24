package samanthaapp;

import java.util.Vector;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;


 //  Samantha main class.
  // Reads the script, categorises into keys, decomposition lists and corresponding patterns
   //Does pre and post input transformations.
// finally generates reply for user after performing above steps

 public class SamanthaMain implements ActionListener, KeyListener{


    final boolean echoInput = false;   //printing the script file on command prompt
    final boolean printData = false;  // Test Variable for printing Keys, syns etc on command prompt

    final boolean printKeys = false;
    final boolean printSyns = false;
    final boolean printPrePost = false;
    final boolean printInitialFinal = false;
    
    static private String userName;
    static private JFrame Frame;
    static private JScrollPane ScrollPane;
    static private JPanel textareaPanel,textfieldPanel;
        static private JButton Send,Save;
    static private JLabel initLabel;
    static private JTextArea Display;
    static private JTextField Input;
    static private String reply;
   
   
     public SamanthaMain()
     {
        
        
        Frame = new JFrame("Samantha");
        Frame.setSize(600, 600);
        Frame.setLayout(new FlowLayout());
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLocationRelativeTo(null);
        
        textareaPanel=new JPanel();
        textfieldPanel=new JPanel();
      
       
        Display = new JTextArea(25, 50);
        
      
        Display.setEditable(false);
        
    
        Display.setLineWrap(true);
        
  
        ScrollPane = new JScrollPane(Display);
        
        textareaPanel.add(ScrollPane,BorderLayout.CENTER);
       
        Input = new JTextField(35);
        Input.addKeyListener(this);
        Input.setEditable(true);
        textfieldPanel.add(Input,BorderLayout.EAST);
        
   
        Send = new JButton("Send");
         Save  =new JButton("Save");
        
       
        Save.addActionListener(this);
       
        Send.addActionListener(this);
        textfieldPanel.add(Send,BorderLayout.WEST);
       textfieldPanel.add(Save,BorderLayout.SOUTH);
        Frame.add(textareaPanel,BorderLayout.NORTH);
        Frame.add(textfieldPanel,BorderLayout.SOUTH);        
        Frame.setVisible(true);
        
        
     }
  
    
    KeyList keys = new KeyList();
    
    SynonymList syns = new SynonymList();
    
    PrePostSubList pre = new PrePostSubList();
    
    PrePostSubList post = new PrePostSubList();
   
    String initial = "Hello.";
    
    String finl = "Goodbye.";
  
    WordList quit = new WordList();

    
    KeyStack keyStack = new KeyStack();

  
    Mem mem = new Mem();

    DecompositionList lastDecomp;
    ReassembleList lastReasemb;
    boolean finished = false;

    static final int success = 0;
    static final int failure = 1;
    static final int gotoRule = 2;

    public boolean finished() {
        return finished;
    }

    
     // Processing a line of script input.
     
    public void preProcess(String s){
   
        String lines[] = new String[4];
        boolean status;
        status=StringOp.match(s, "*reasmb: *", lines);
     
        if (status) // a line of script file, pattern, and matching statement ;
        {
        
           
            if (lastReasemb == null) {
                System.out.println("Error: no last reasemb");
                return;
            }
           System.out.println("Adding lines[1] to vector:"+lines[1]);
            lastReasemb.add(lines[1]);
         
        }
    
      else if (StringOp.match(s, "*decomp: *", lines)) {
    
             // Lines[0]--> Nothing
             //Lines[1]---> Decomposition pattern
           
            if (lastDecomp == null) {
                System.out.println("Error: no last decomp");
                return;
            }
            lastReasemb = new ReassembleList(); //create a new instance of reassembly pattern pertaining to a decompostion pattern.
            String temp = new String(lines[1]);
            if (StringOp.match(temp, "$ *", lines)) {
                lastDecomp.add(lines[0], true, lastReasemb);
            } else {
                lastDecomp.add(temp, false, lastReasemb); 
            }
        }
        else if (StringOp.match(s, "*key: * #*", lines)) {
   
            lastDecomp = new DecompositionList(); // create a new instance of decomposition patter pertaining to a key.
            lastReasemb = null; // initializing lastReasemb (reassembly pattern for the current decompostion pattern) as null
            int n = 0;
      
            //Lines[0] -> Nothing
            //Lines[1]-> Key Content
            //Lines[2]-> Rank of that key . 
            if (lines[2].length() != 0) {
                try {
                    n = Integer.parseInt(lines[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Number is wrong in key: " + lines[2]);
                }
            }
   
            keys.add(lines[1], n, lastDecomp);
        }
        else if (StringOp.match(s, "*key: *", lines)) {
      
            lastDecomp = new DecompositionList();
            lastReasemb = null;
            keys.add(lines[1], 0, lastDecomp);
        }
        if (StringOp.match(s, "*synon: * *", lines)) {
            WordList words = new WordList();
       
            words.add(lines[1]);
            s = lines[2];
         
            while (StringOp.match(s, "* *", lines)) {
             
                words.add(lines[0]);
                s = lines[1];
               
            }
          
            words.add(s);
            syns.add(words);
         
        }
        else if (StringOp.match(s, "*pre: * *", lines)) {
  
            pre.add(lines[1], lines[2]);
        }
        else if (StringOp.match(s, "*post: * *", lines)) {
        
            post.add(lines[1], lines[2]);
        }
        else if (StringOp.match(s, "*initial: *", lines)) {
            initial = lines[1];
        }
        else if (StringOp.match(s, "*final: *", lines)) {
            finl = lines[1];
        }
        else if (StringOp.match(s, "*quit: *", lines)) {
            quit.add(" " + lines[1]+ " ");
        }
        else {
            System.out.println("Unrecognized input: " + s);
        }
    

    /**
     *  Print the stored script. 
     */
    }
   /*public void print() {
        if (printKeys) keys.print(0);
        if (printSyns) syns.print(0);
        if (printPrePost) {
            pre.print(0);
            post.print(0);
        }
        if (printInitialFinal) {
            System.out.println("initial: " + initial);
            System.out.println("final:   " + finl);
            quit.print(0);
        }
    }*/

    /**
     *  Process a line of input.
     */
    public String processInput(String s) {
        System.out.println("Processing string:"+s+"\n");
        String reply;
        //  Do some input transformations first.
        s = StringOp.translate(s, "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                                 "abcdefghijklmnopqrstuvwxyz"); // Convert everything into lowercase first
        System.out.println("Level-1 Processing string:"+s+"\n");
        
        s = StringOp.translate(s, "@#$%^&*()_-+=~`{[}]|:;<>\\\"", // remove special characters in the input
                                 "                          "  );
         System.out.println("Level-2 Processing string:"+s+"\n");
        s = StringOp.translate(s, ",?!", "...");
         System.out.println("Level-3 Processing string:"+s+"\n");
        //  Compress out multiple speace.
        s = StringOp.compress(s);
         System.out.println("After compressing multiple spaces Processing string:"+s+"\n");
        String lines[] = new String[2];
        //  Break apart sentences, and do each separately.
   
        if (s.length() != 0) {
            System.out.println("String length is nt zero");
            reply = sentence(s);
            System.out.println("Reply:"+reply);
            if (reply != null) return reply;
        }
        //  Nothing matched, so try memory.
        String m = mem.get();
        if (m != null) return m;

        //  No memory, reply with xnone.
        Key key = keys.getKey("xnone");
        if (key != null) {
            Key dummy = null;
            reply = decompose(key, s, dummy);
            if (reply != null) return reply;
        }
        //  No xnone, just say anything.
        return "I am at a loss for words.";
    }

    
     //  Process a sentence and make pre transformations
    
     // Scan sentence for keys, build key stack.
     // Try decompositions for each key.
     
    String sentence(String s) {
        System.out.println("String s="+s+"\n");
        s = pre.translate(s); 
        System.out.println("After translating:"+s);
        s = StringOp.pad(s);
        if (quit.find(s)) {  //checking if user wants to stop interacting with the chatbot
            finished = true;
            return finl;
        }
        
        keys.buildKeyStack(keyStack, s); // identify keys in the string and generate a key stack based on weight
        
        for (int i = 0; i < keyStack.keyTop(); i++) {
            Key gotoKey = new Key();
            
            String reply = decompose(keyStack.key(i), s, gotoKey);
            
            if (reply != null) return reply;
            //  If decomposition returned gotoKey, try it
            while (gotoKey.key() != null) {
                reply = decompose(gotoKey, s, gotoKey);
                if (reply != null) return reply;
            }
        }
        
        return null;
    }

    
     // Decompose a string according to the given key.
     // Try each decomposition rule in order.
     //  If it matches, assemble a reply and return it.
     // If assembly fails, try another decomposition rule.
     //  If assembly is a goto rule, return null and give the key.
     //  If assembly succeeds, return the reply;
     
    String decompose(Key key, String s, Key gotoKey) {
        String reply[] = new String[10];
        for (int i = 0; i < key.decomp().size(); i++) { //key.decomp returns the decomp list associated with that key
            Decompostion d = (Decompostion)key.decomp().elementAt(i);
          //  System.out.println("Decomp:"+d);
            String pat = d.pattern();
            System.out.println("Decomp pattern:"+pat);
            if (syns.matchDecomp(s, pat, reply)) {
                System.out.println("REP:"+reply[0]);
                System.out.println("REP:"+reply[1]);
                 System.out.println("REP:"+reply[2]);
                String rep = assemble(d, reply, gotoKey);
                if (rep != null) return rep;
                if (gotoKey.key() != null) return null;
            }
        }
        return null;
    }

      //Generate a reply from a decomp rule and the input.
   
    String assemble(Decompostion d, String reply[], Key gotoKey) {
        String lines[] = new String[3];
        d.stepRule();
        String rule = d.nextRule();
        System.out.println("Rule:"+rule);
        if (StringOp.match(rule, "goto *", lines)) {
            //  goto rule -- set gotoKey and return false.
            System.out.println("Lines[0]="+lines[0]);
            gotoKey.copy(keys.getKey(lines[0]));
            if (gotoKey.key() != null) return null;
            System.out.println("Goto rule did not match key: " + lines[0]);
            return null;
        }
        String work = "";
        while (StringOp.match(rule, "* (#)*", lines)) {
             /*
              * Example:
              No one likes me 
              * decomp pattern matched: *no one*
              * reasembly pattern: Are you sure no one (2)?
              * lines[0]=Are you sure, no one
                lines[1]=2
                lines[2]= ?
              */
            //  reassembly rule with number substitution
            rule = lines[2];        
            
            int n = 0;
            try {
                n = Integer.parseInt(lines[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Number is wrong in reassembly rule " + lines[1]);
            }
            if (n < 0 || n >= reply.length) {
                System.out.println("Substitution number is bad " + lines[1]);
                return null;
            }
            System.out.println("n="+n);
            System.out.println("Reply["+n+"]="+reply[n]);
            
            reply[n] = post.translate(reply[n]); 
           System.out.println("lines[0]="+lines[0]+"+reply[n]+"+reply[n]);
            work += lines[0] + " " + reply[n];
            System.out.println("Work:"+work+"\n");
        }
        work += rule;
        if (d.mem()) {
          System.out.println("Saved in memory");
                  
            mem.save(work);
            return null;
        }
        System.out.println("Work:"+work);
        return work; //final rule
    }

    TextArea textarea;
    TextField textfield;

  


    int readScript(boolean local, String script) {
        DataInputStream in = null;
        FileInputStream fin;
        try {
            if (local) {
                fin = new FileInputStream("c:\\Users\\narainsharma\\Desktop\\script");
               
                in = new DataInputStream(fin);
                

            } 
            while (true) {
                String s;
                s = in.readLine();
      
                if (s == null) break;
            //    if (echoInput) // change to ! if you want to print.
             //   System.out.println(s); //printing the script files. Testing
             //   System.out.println("\n Processing(calling preProcess function) line:"+s+"\n");
                preProcess(s); // The processing starts here
            }
        } 
        catch (IOException e) 
        {
            System.out.println("There was a problem reading the script file.");
            System.out.println("Tried " + script);
            System.out.println("Exception:"+e);
            return 1;
        }

        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                  
                     if(e.getSource()==Send)
                    {
                        String input =  Input.getText();
                       // System.out.println("Input:"+input);
                        if(input.toLowerCase().contains("what is"))
                        {
                            reply="Get lost";
                            
                                  
                        }
                   else{
                        reply = processInput(input);
                       
                         }
                        Input.setText("");
                        
                        Display.setText(Display.getText() + "You: " + input + "\n");
                        Display.setText(Display.getText() + "Samantha: " + reply + "\n");
                       
                    }
                     else if(e.getSource()==Save){
                         try {
                     
 
                             
			String content = Display.getText();
                     
			File file = new File("C:\\Users\\narainsharma\\Desktop\\conv.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} 
                         catch (IOException io) {
			io.printStackTrace();
		}
                     }

    }
 

    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
         
            String input = Input.getText();
            if(input.toLowerCase().contains("what is"))
                        {
                            reply="Get lost";
                            
                                  
                        }
            else {
             reply = processInput(input);
            }
             Input.setText("");
            
            Display.setText(Display.getText() + "You: " + input + "\n");
            Display.setText(Display.getText() + "Samantha: " + reply + "\n");
    }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

 }
