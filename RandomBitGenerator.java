/*
 * Megan Yates
 * mey29
 * Assignment Two
 * Febuary 1, 2013
 */

public class RandomBitGenerator {
    public static void main(String[] args) {
        StringBuffer bits = new StringBuffer("    01101000010");
        int tapbit = 8;
        System.out.println(generate(100, bits, tapbit));
    }

    public static int step(StringBuffer bits, int tapbit) {
        while(bits.charAt(0) == ' '){
            bits.deleteCharAt(0);
        }
        char bitToAdd;
        if(bits.charAt(0)==bits.charAt(bits.length() - tapbit - 1))
            bitToAdd='0';
        else
            bitToAdd='1';
        
        bits.insert(bits.length(), bitToAdd);
        bits = bits.deleteCharAt(0);
        return bitToAdd;
    }

    public static String generate(int k, StringBuffer bits, int tapbit) {
        StringBuffer temp=new StringBuffer();
        for (int i = 0; i < k; i++) {
            temp.append( (char)step(bits, tapbit) );
        }
        return temp.toString();            
    }
}
