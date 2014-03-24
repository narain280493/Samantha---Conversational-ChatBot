package samanthaapp;

import java.util.Vector;


public class DecompositionList extends Vector 
{

    
    public void add(String word, boolean mem, ReassembleList reasmb) 
    {
        addElement(new Decompostion(word, mem, reasmb));
    }

    
    public void print(int indent) 
    {
        for (int i = 0; i < size(); i++) 
        {
            Decompostion d = (Decompostion)elementAt(i);
            d.print(indent);
        }
    }
}
