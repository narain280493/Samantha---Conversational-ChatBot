package samanthaapp;

// String manipulation 
public class StringOp {

    
    static final String num = "0123456789";

    
     //  Look for a match between the string and the pattern and return count of maching characters before * or #.
    
     
    public static int amatch(String str, String pat) {
        int count = 0;
        int i = 0;  // march through str
        int j = 0;  // march through pat
      
        while (i < str.length() && j < pat.length()) {
            char p = pat.charAt(j);
         //   System.out.println("P="+p+"\n");
            // stop if pattern is * or #
         
            if (p == '*' || p == '#')
            {
            //    System.out.println("P is "+p);
                return count;
            }
            if (str.charAt(i) != p) 
            {
         
                //  System.out.println("Returning -1");
                return -1;
            }
            // they are still equal
            //System.out.println("They are still equal!\n");
            i++; j++; count++;
        }
        return count;
    }

   
       //Search in successive positions of the string,looking for a match to the pattern and return position of match
    
     
    public static int findPat(String str, String pat) {
        int count = 0;
   
        for (int i = 0; i < str.length(); i++) {
      //      System.out.println("str.substring("+i+")= "+str.substring(i));
          int status=amatch(str.substring(i), pat);
        //  System.out.println("Status="+status);
            if (status >= 0)
                return count;
            count++;
        //     System.out.println("FindPat-Count="+count);
        }
   
        return -1;
    }

    // Look for a number in the string and return the number of digits at the beginning.
     
    public static int findNum(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
  //          System.out.println("Findnum:"+num.indexOf(str.charAt(i)));
            if (num.indexOf(str.charAt(i)) == -1)
                return count;
            count++;
        }
        return count;
    }

    
     // Match the string against a pattern and fills in matches array with the pieces that matched * and #
     
    static boolean matchStr(String str, String pat, String matches[]) {
        int i = 0;      //  move through str
        int j = 0;      //  move through matches
        int pos = 0;    //  move through pat
   //   System.out.println("Inside matchStr :\n");
   //   System.out.print("Strlen of str,pat,match="+str.length()+pat.length()+matches.length+"\n");
   
        while (pos < pat.length() && j < matches.length) {
            char p = pat.charAt(pos);
           // System.out.println("p="+p+"\n");
            if (p == '*') 
            {
           //    System.out.println("p==*");
                int n;
                if (pos+1 == pat.length()) {
                    //  * is the last thing in pat
                    //  n is remaining string length
           
                    n = str.length() - i;
               //     System.out.println("n="+n+"\n");
                } else {
                    //  * is not last in pat
                    //  find using remaining pat
                  //    System.out.println("* is NOT the last thing in pat");
             //      System.out.println("Str.substring="+str.substring(i)+" "+pat.substring(pos+1));
             //       System.out.println("Before executing pat\n");
                    n = findPat(str.substring(i), pat.substring(pos+1)); // Number of characters common in str and pattern
       //            
        //            System.out.println("After executing findPat function n=:"+n+"\n");
                }
                if (n < 0) return false; //if -1 is returned
        
                String temp=str.substring(i, i+n); 
        
           
                matches[j++] = temp; //eg reasmb:is stored in matches
        
                i += n; pos++;  //
            } 
            else if (p == '#') 
            {
                int n = findNum(str.substring(i));
                matches[j++] = str.substring(i, i+n);
                i += n; pos++; 
            } 
            else {
             
                int n = amatch(str.substring(i), pat.substring(pos));
           
                if (n <= 0) return false;
                i += n; pos += n;
              
            }
        }
        if (i >= str.length() && pos >= pat.length()) return true;
        return false;
    }

   

    public static boolean match(String str, String pat, String matches[]) 
    {
        boolean stat=matchStr(str, pat, matches);
        return stat;
    }

   //converts source str into appropriate dest string
    public static String translate(String str, String src, String dest) 
    {
        if (src.length() != dest.length()) {
            // impossible error
        }
          
        for (int i = 0; i < src.length(); i++) {
            str = str.replace(src.charAt(i), dest.charAt(i));  //replace src char with dest char
        }
        return str;
    }

    
     // Compresses its input by:
     //   dropping space before space, comma, and period;
     //   adding space before question and
     //    copying all others as such
     
    public static String compress(String s) 
    {
        String dest = "";
        if (s.length() == 0) return s;
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (c == ' ' &&
                 ((s.charAt(i) == ' ') ||
                 (s.charAt(i) == ',') ||
                 (s.charAt(i) == '.'))) {
                    // nothing
            } else if (c != ' ' && s.charAt(i) == '?') {
                dest += c + " ";
            } else {
                dest += c;
            }
            c = s.charAt(i);
        }
        dest += c;
        return dest;
    }

   //trim leading spaces
    public static String trim(String s) 
    {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') return s.substring(i);
        }
        return "";
    }

   
    // Padding sentences with spaces at the starteing and ending
    
    public static String pad(String s) 
    {
        if (s.length() == 0) return " ";
        char first = s.charAt(0);
        char last = s.charAt(s.length()-1);
        if (first == ' ' && last == ' ') return s;
        if (first == ' ' && last != ' ') return s + " ";
        if (first != ' ' && last == ' ') return " " + s;
        if (first != ' ' && last != ' ') return " " + s + " ";
       
        return s;
    }
   
    public static int count(String s, char c) 
    {
        int count = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == c) count++;
        return count;
    }
}