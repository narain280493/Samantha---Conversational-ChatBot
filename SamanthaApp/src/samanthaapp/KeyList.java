package samanthaapp;

import java.util.Vector;


public class KeyList extends Vector {

    
    public void add(String key, int rank, DecompositionList decomp) {
        addElement(new Key(key, rank, decomp));
    }

    
    public void print(int indent) {
        for (int i = 0; i < size(); i++) {
            Key k = (Key)elementAt(i);
            k.print(indent);
        }
    }

    Key getKey(String s) {
        for (int i = 0; i < size(); i++) {
            Key key = (Key)elementAt(i);
            if (s.equals(key.key())) return key; //key has been found in the vect
        }
        return null;
    }

    public void buildKeyStack(KeyStack stack, String s) {
        stack.reset();
        s = StringOp.trim(s);
        String lines[] = new String[2];
        Key k;
        while (StringOp.match(s, "* *", lines)) {
            k = getKey(lines[0]);
            if (k != null) stack.pushKey(k);
            s = lines[1];
        }
        k = getKey(s);
        if (k != null) stack.pushKey(k);
        
    }
}