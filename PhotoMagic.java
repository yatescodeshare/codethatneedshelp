/*
 * Megan Yates
 * mey29
 * Assignment Two
 * January 28, 2013
 */

import java.awt.Color;

public class PhotoMagic {

    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        StringBuffer bits = new StringBuffer(args[1]);
        int tapbit = Integer.parseInt(args[2]);

        Picture encryptedPicture = transform(picture, bits, tapbit);
        encryptedPicture.show();
        encryptedPicture.save("encrypted_" + args[0]);

    }

    public static Picture transform(Picture picture, StringBuffer bits, int tapbit) {

        for (int x = 0; x < picture.width(); x++) {
            for (int y = 0; y < picture.height(); y++) {
                Color c = picture.get(x, y);
                int red = c.getRed();
                int blue = c.getBlue();
                int green = c.getGreen();
				int  rbyte;
                int  bbyte;
   			   int 	gbyte;
                        
                String tempRed = RandomBitGenerator.generate(8, bits, tapbit);
                if (tempRed.charAt(0) == '0'){
                    rbyte = Byte.valueOf(tempRed,2);
                }
                else{
                    tempRed = ("-" + twoscomp(RandomBitGenerator.generate(8, bits, tapbit)));
                    rbyte = Byte.valueOf(tempRed,2);
                }
                
                String tempBlue = RandomBitGenerator.generate(8, bits, tapbit);
                if (tempBlue.charAt(0) == '0'){
                    bbyte = Byte.valueOf(tempBlue,2);
                }
                else{
                    tempBlue = ("-" + twoscomp(RandomBitGenerator.generate(8, bits, tapbit)));
                    bbyte = Byte.valueOf(tempBlue,2); 
               }
                
                String tempGreen = RandomBitGenerator.generate(8, bits, tapbit);
                if (tempGreen.charAt(0) == '0'){
                    gbyte = Byte.valueOf(tempGreen,2);
                }
                else{
                    tempGreen = ("-" + twoscomp(RandomBitGenerator.generate(8, bits, tapbit)));
                    gbyte = Byte.valueOf(tempGreen,2);
                }
                           
                 int  newRed =  (red ^ rbyte);
                 int newBlue =  (blue ^ bbyte);
                 int newGreen =  (green ^ gbyte);
                                            
                if(newRed < 0){
                    newRed+= 256; 
                }    
                  if(newBlue < 0){
                    newBlue+= 256; 
                }         
                    if(newGreen < 0){
                   newGreen+= 256; 
                }
/*
				 newRed+= 256;
				 newRed %= 256;
				 newBlue+= 256;
				 newBlue %= 256;
				 newGreen+= 256;
				 newGreen %= 256;
  */                  
                Color newColor = new Color(newRed, newBlue, newGreen);
                picture.set(x, y, newColor);
               
            }
        }
        
        return picture;
        
    }

    public static String twoscomp(String s) {
        StringBuilder temp = new StringBuilder(s);
        for (int x = 0; x < temp.length(); x++) {
            if (temp.charAt(x) == '1') {
                temp.setCharAt(x, '0');
            }
            if (temp.charAt(x) == '0') {
                temp.setCharAt(x, '1');
            }
        }
        int bitByte = Integer.parseInt(temp.toString(),2);
		//int bitByte = bitStringToInt(temp.toString());
        bitByte++;
        return Integer.toBinaryString((byte) bitByte);

    }

	public static int bitStringToInt(String s)
	{
		int base =1;
		int sum =0;
		for(int ii=0; ii<s.length(); ii++)
		{
			if(s.toCharArray()[ii]=='1')
				sum += base;
			base*= 2;
		}
		return sum;
	}
}



