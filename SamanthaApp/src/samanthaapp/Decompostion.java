
package samanthaapp;

import java.lang.Math;


public class Decompostion 
{
    
    String pattern;
   
    boolean mem;
   
    ReassembleList reasemb;
 
    int currReasmb;

    
    Decompostion(String pattern, boolean mem, ReassembleList reasemb) 
    {
        this.pattern = pattern;
        this.mem = mem;
        this.reasemb = reasemb;
        this.currReasmb = 100;
    }

  
    public void print(int indent) 
    {
        String m = mem ? "true" : "false";
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println("decomp: " + pattern + " " + m);
        reasemb.print(indent + 2);
    }

    
    public String pattern() 
    {
        return pattern;
    }

    
    public boolean mem() 
    {
        return mem;
    }

    
    public String nextRule() 
    {
        if (reasemb.size() == 0) {
            System.out.println("No reassembly rule.");
            return null;
        }
        return (String)reasemb.elementAt(currReasmb);
    }

    
    public void stepRule() 
    {
        int size = reasemb.size();
        if (mem) {
            currReasmb = (int)(Math.random() * size);
        }
        //  Increment and make sure it is within range.
        currReasmb++;
        if (currReasmb >= size) currReasmb = 0;
    }


}

