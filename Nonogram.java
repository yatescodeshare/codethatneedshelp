import java.io.*;
import java.util.*;

public class Nonogram
{
  public static void main(String []args) throws IOException
  {
   String fileName = args[0]; 
   NonogramPuzzle2 nonogram = new NonogramPuzzle2(fileName);
   
   nonogram.solve();
  }
}
  