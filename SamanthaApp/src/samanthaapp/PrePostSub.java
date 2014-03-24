
package samanthaapp;

public class PrePostSub 
{
   
    String src;
    String dest;

   
    PrePostSub(String src, String dest) 
    {
        this.src = src;
        this.dest = dest;
    }

    
    public void print(int indent) {
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println("pre-post: " + src + "  " + dest);
    }

    
    public String src() {
        return src;
    }

   
    public String dest() {
        return dest;
    }
}

