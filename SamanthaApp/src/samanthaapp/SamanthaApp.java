package samanthaapp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SamanthaApp {

    static String scriptPathname = "script"; // Name of the script file
    static String testPathname = "test"; //Name of the test file
    static String scriptURL = "c:\\Users\\narainsharma\\Desktop\\script"; // Change this URL to the location of your script

    static final boolean useWindow = true;
    static final boolean local = true; // Script is on local machine. Need to remove this.
    
    public static void main(String args[]) {
        
        SamanthaMain sam = new SamanthaMain();
        String script = scriptPathname; 
        int res = sam.readScript(local, script);
        if (res != 0) System.exit(res);
      
    }
}
