
package samanthaapp;

import java.util.Vector;


public class ReassembleList extends Vector 
{

   
    public void add(String reasmb) 
    {
        addElement(reasmb);
    }

    
    public void print(int indent) 
    {
        for (int i = 0; i < size(); i++) 
        {
            for (int j = 0; j < indent; j++) System.out.print(" ");
            String s = (String)elementAt(i);
            System.out.println("reasemb: " + s);
        }
    }
}