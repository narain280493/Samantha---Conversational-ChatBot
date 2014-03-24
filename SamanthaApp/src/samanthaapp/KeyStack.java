
package samanthaapp;

public class KeyStack 
{

   
    final int stackSize = 20;
   
    Key keyStack[] = new Key[stackSize];
   
    int keyTop = 0;

    
    public void print() 
    {
        System.out.println("Key stack " + keyTop);
        for (int i = 0; i < keyTop; i++) 
        {
            keyStack[i].printKey(0);
        }
    }

    /**
     *  Get the stack size.
     */
    public int keyTop() 
    {
        return keyTop;
    }

    
    public void reset() 
    {
        keyTop = 0;
    }

    
    public Key key(int n) 
    {
        if (n < 0 || n >= keyTop) return null;
        return keyStack[n];
    }

    
     //  Push a key in the stack.
     //  Keep the highest rank keys at the bottom.
     
    public void pushKey(Key key) 
    {
        if (key == null) {
            System.out.println("push null key");
            return;
        }
        int i;
        for (i = keyTop; i > 0; i--) {
            if (key.rank > keyStack[i-1].rank) keyStack[i] = keyStack[i-1];
            else break;
        }
        keyStack[i] = key;
        keyTop++;
    }


}
