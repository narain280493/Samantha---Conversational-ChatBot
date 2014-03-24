
package samanthaapp;
import java.util.Vector;


public class SynonymList extends Vector {

   
    public void add(WordList words) {
        addElement(words);
    }

    public void print(int indent) {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < indent; j++) System.out.print(" ");
            System.out.print("synon: ");
            WordList w = (WordList)elementAt(i);
            w.print(indent);
        }
    }

    // find a synonym word list given any word
    public WordList find(String s) {
        for (int i = 0; i < size(); i++) {
            WordList w = (WordList)elementAt(i);
            if (w.find(s)) return w;
        }
        return null;
    }
   // check if decomp has no synonyms, do a regular match.
    //  Otherwise, try all synonyms.
     
    boolean matchDecomp(String str, String pat, String lines[]) {
        if (! StringOp.match(pat, "*@* *", lines)) {
            //  no synonyms in decomp pattern
            return StringOp.match(str, pat, lines);
        }
        //  Decomp pattern has synonym  - seperate the synonym
        String first = lines[0];
        System.out.println("First="+first);
        String synWord = lines[1];
        System.out.println("synWord="+synWord);
        String theRest = " " + lines[2];
        System.out.println("theRest="+theRest);
       
        //  Look up the synonym
        WordList syn = find(synWord);
        if (syn == null) {
            System.out.println("Could not fnd syn list for " + synWord);
            return false;
        }
        //  Try each synonym individually
        for (int i = 0; i < syn.size(); i++) {
            //  Make a modified pattern
            pat = first + (String)syn.elementAt(i) + theRest;
            System.out.println("Pat="+pat);
            if (StringOp.match(str, pat, lines)) {
                System.out.println("Lines[0]"+lines[0]);
                   System.out.println("Lines[1]"+lines[1]);
                      System.out.println("Lines[2]"+lines[2]);
                int n = StringOp.count(first, '*');
                System.out.println("n="+n+"lines.length="+lines.length);
                //  Make room for the synonym in the match list.
                for (int j = lines.length-2; j >= n; j--)
                { System.out.println(lines[j+1]+"="+lines[j]);
                    lines[j+1] = lines[j];
                }
                //  The synonym goes in the match list.
                lines[n] = (String)syn.elementAt(i);
                System.out.println("Lines[n]="+lines[n]);
                return true;
            }
        }
        return false;
    }

}
