import java.io.*;
import java.util.*;

public class NonogramPuzzle2 {

    private int[][] rows; // Store the number of constraints for each row
    private int[][] cols; // Store the number of constraints for each col
    private boolean[][] nonogram; // Store a 2-D representation of the board
    private boolean found = false; // Analogous to the 8-Queens found
    private int row;
    private int col;

    public NonogramPuzzle2(String fileName) throws IOException
    {
      Scanner scan = new Scanner(new File(fileName));
    
      String s = scan.next();
      String[] dimensions = s.split(",");
      row = Integer.parseInt(dimensions[0]);
      col = Integer.parseInt(dimensions[1]);
    
      //Initate nonogram to be all false
      nonogram = new boolean[row][col];    
      for(int i = 0; i < row; i++)
      {
        for(int j = 0; j < col; j++)
        {
          nonogram[i][j] = false;
        }
      }
      
      rows = new int[row][];
      cols = new int[col][];

    int index = 0;
    while(scan.hasNextLine() && index < row)
    {
      String[] constraints = scan.next().split(","); 
      rows[index] = new int[constraints.length];
      
      for(int j = 0; j < constraints.length; j++)
      {
        rows[index][j] = Integer.parseInt(constraints[j]);
      }
      index++;
    }
    
    index = 0;
    while(scan.hasNextLine() && index < col)
    {
      String[] constraintsCol = scan.next().split(","); 
      cols[index] = new int[constraintsCol.length];
      
      for(int i = 0; i < constraintsCol.length; i++)
      {
        cols[index][i] = Integer.parseInt(constraintsCol[i]);
      }
      index++;
    }
    }
 
 public void solve() 
 {
   solve1(0, 0, 0);
   if(found)
   {
     for(int i = 0; i < row; i++)
     {
       for(int j = 0; j< col; j++)
       {
         if(nonogram[i][j] == true)
           System.out.print('@');
         else if(nonogram[i][j] == false)
           System.out.print('*');
       }
       System.out.println();
     }
   }
 }

      
 public void solve1(int column, int rowIndex, int constraint) {
  
   if(column == col)
   {
     if(isComplete()) 
     {
       found = true;
     }
     return;
   }
   
  if(rowIndex > row)
    return;
  
  int columnBlockSize = cols[column][constraint];
  if(isLegal(columnBlockSize, rowIndex, column))
  {
    insert(columnBlockSize, rowIndex, column);
    if(constraint == cols[column].length-1)
      solve1(column+1, 0, 0);
    else
      {
      solve1(column, rowIndex+1+columnBlockSize, constraint+1);
    }
    if(found == false)
      remove(columnBlockSize, rowIndex, column);
  }
  if(found == false)
  {
    solve1(column, rowIndex+1, constraint);
  }
 }
 
 public void insert(int chuckSize, int rowIndex, int column)
 {
   for(int r = rowIndex; r < (rowIndex + chuckSize); r++)
   {
     nonogram[r][column] = true;
   }
 }
 
 public void remove(int chuckSize, int rowIndex, int column)
 {
   for(int r = rowIndex; r < (rowIndex + chuckSize); r++)
   {
     nonogram[r][column] = false;
   }
 }
 
 
 public boolean isLegal(int size, int rowIndex, int column) 
 {
   if((size + (rowIndex-1)) >= row)
     return false;  
   
   if(rowIndex > 0 && nonogram[rowIndex-1][column] == true)
     return false;
   
   int counter = 0;
   int r = 0;
   for(int c = 0; c <= column; c++)
   {
     if(nonogram[rowIndex][c] == true)
       counter++;
     else
     {
       if(r < rows[rowIndex].length && counter > rows[rowIndex][r])
         return false;
       else
       { 
         r++;
         counter = 0;
         continue;
       }
     }
   }
  return true;
 }
 
 public boolean isComplete() 
 {
   for(int i = 0; i < row; i++)
   {
     String rowStr = "";
     for(int j = 0; j < col; j++)
     {
       if(nonogram[i][j] == true)
         rowStr += "x";
       if(nonogram[i][j] == false)
         rowStr += " ";
     }
     String [] blockArray = rowStr.trim().split("((?<=x)[ ]+)");
     int[] temp = rows[i];
     if(blockArray.length != temp.length)
       return false;
     for(int j = 0; j < temp.length; j++)
     {
       if(temp[j] != blockArray[j].length())
         return false;
     }      
   }
   return true;
 } 
}

