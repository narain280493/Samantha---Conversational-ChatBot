
package samanthaapp;


public class Mem {

   
    final int memMax = 20;
    
    String memory[] = new String[memMax];
    
    int memTop = 0;

    public void save(String str) {
        if (memTop < memMax) {
            memory[memTop++] = new String(str);
        }
    }

    public String get() {
        if (memTop == 0) return null;
        String m = memory[0];
        for (int i = 0; i < memTop-1; i++)
            memory[i] = memory[i+1];
        memTop--;
        return m;
    }
}
