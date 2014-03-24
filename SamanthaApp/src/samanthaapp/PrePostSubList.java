package samanthaapp;

import java.util.Vector;


public class PrePostSubList extends Vector {

    
    public void add(String src, String dest) {
        addElement(new PrePostSub(src, dest));
    }

   
    public void print(int indent) {
        for (int i = 0; i < size(); i++) {
            PrePostSub p = (PrePostSub)elementAt(i);
            p.print(indent);
        }
    }

   
    String xlate(String str) {
        for (int i = 0; i < size(); i++) {
            PrePostSub p = (PrePostSub)elementAt(i);
            if (str.equals(p.src())) {
                return p.dest();
            }
        }
        return str;
    }

    //translate a string s
     // break into words and replace src words with dest words
     
    public String translate(String s) 
    {
        String lines[] = new String[2];
        String work = StringOp.trim(s);
        s = "";
        while (StringOp.match(work, "* *", lines)) {
            System.out.println("Lines="+lines[0]);
            s += xlate(lines[0]) + " ";
            work = StringOp.trim(lines[1]);
        }
        System.out.println("Work="+work);
        s += xlate(work);
        return s;
    }
}