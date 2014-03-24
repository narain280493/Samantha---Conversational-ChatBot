package samanthaapp;



public class Key 
{
    
    String key;
    
    int rank;
    
    DecompositionList decomp;

    Key(String key, int rank, DecompositionList decomp) {
        this.key = key;
        this.rank = rank;
        this.decomp = decomp;
    }

   
    Key() {
        key = null;
        rank = 0;
        decomp = null;
    }

    public void copy(Key k) {
        key = k.key();
        rank = k.rank();
        decomp = k.decomp();
    }

    
    public void print(int indent) {
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println("key: " + key + " " + rank);
        decomp.print(indent+2);
    }

    
    public void printKey(int indent) {
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println("key: " + key + " " + rank);
    }

    
    public String key() {
        return key;
    }

    
    public int rank() {
        return rank;
    }

    
    public DecompositionList decomp() {
        return decomp;
    }
}

