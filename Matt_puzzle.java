import java.io.*;
import java.util.*;

public class Matt_puzzle {

    public class Tree<T> {
        private Node<T> root;

        public Tree(T rootData) {
            root = new Node<T>();
            root.data = rootData;
            root.children = new ArrayList<Node<T>>();
            root.isRoot = true;
            root.isLeaf = false;
        }
        
        public ArrayList<T> getRightMostPath()
        {
        	ArrayList<T> pathData = new ArrayList<T>();
        	Node<T> node = this.root;
        	pathData.add(node.getData());
        	while(!node.getChildren().isEmpty())
        	{
        		node= node.getChildren().get(node.getChildren().size()-1);
        		pathData.add(node.getData());
        	}
        	return pathData;
        }
        
        public static class Node<T> {
            public T data;
            public boolean isRoot;
            public boolean isLeaf;
            public Node<T> parent;
            public List<Node<T>> children;

            public void setData(T d)
            {
                data = d;
            }

            public Node<T>()
            {
                children = new ArrayList<Node<T>>();
            }

            public Node<T>(T d, Node<T> p)
            {
                this.parent = p;
                this.data = d;
                this.isRoot = false;
                this.isLeaf = false;
            }

            public Node<T> getParent()
            {
                return parent;
            }
            
            public boolean getIsLeaf()
            {
            	return this.isLeaf;
            }
            
            public void setLeaf(boolean isL)
            {
            	this.isLeaf = isL;
            }

            public T getData()
            {
                return data;
            }

            public List<Node<T>> getChildren()
            {
                return children;
            }
        }
    }

    public class Move 
    {
        private int size;
        private boolean isRow;
        private int index;
        private int pos;

        public Move(int s, boolean isR, int p, int i)
        {
            this.size = s;
            this.isRow = isR;
            this.pos = p;
            this.index = i;
        }

        public int getSize()
        {
            return this.size;
        }

        public boolean getIsRow()
        {
            return this.isRow;
        }

        public int getIndex()
        {
            return this.index;
        }

        public int getPos()
        {
            return this.pos;
        }
    }

    public class Rec
    {
        public boolean sat;
        public int size;
        public int pos;

        public boolean isSat()
        {
            return sat;
        }

        public int getSize()
        {
            return size;
        }

        public int getPos()
        {
            return pos;
        }

        public Rec(boolean st, int sz, int p)
        {
            this.sat = st;
            this.size = sz;
            this.pos = p;
        }
    }
    
    public class Puzzle
    {
    	private boolean[][] nonogram;
    	private ArrayList<Move> moves;
    	private int row;
        private int col;
    	public Puzzle(int r, int c)
    	{
    		row = r;
    		col = c;
    		moves = new ArrayList<Move>();
    		boolean[][] nonogram = new boolean[row][col]; 
            for(int i = 0; i < row; i++)
            {
                for(int j = 0; j < col; j++)
                {
                    nonogram[i][j] = false;
                }
            }
    	}
    	
    	public boolean isLeagal(ArrayList<Rec> rrecs,ArrayList<Rec> crecs)
    	{
    		boolean tempborad[][] = this.nonogram.clone();
    		ArrayList<Move> tempmoves = this.moves.clone();
    		for(Rec rec : rrecs)
    		{
    			for(int ii=0; ii<tempmoves.size();ii++)
    			{
    				if(tempmoves.get(ii).isRow && 
    						tempmoves.get(ii).getPos()==rec.getPos() &&
    						tempmoves.get(ii).getSize()==rec.getSize())
    				{
    					for(int i = tempmoves.get(ii).getIndex(); i<tempmoves.get(ii).getSize()
    							+tempmoves.get(ii).getIndex(); i++)
    	        		{
    						if(tempborad[m.getPos()][i])
    						{
    							tempborad[m.getPos()][i] = false;
    						}
    						else
    						{
    							return false;
    						}
    	        		}
    					rec.sat = true;
    					tempmoves.remove(ii);
    					break;
    				}
    			}
    		}
    		for(Rec rec : crecs)
    		{
    			for(int ii=0; ii<tempmoves.size();ii++)
    			{
    				if((!tempmoves.get(ii).isRow) && 
    						tempmoves.get(ii).getPos()==rec.getPos() &&
    						tempmoves.get(ii).getSize()==rec.getSize())
    				{
    					for(int i = tempmoves.get(ii).getIndex(); i<tempmoves.get(ii).getSize()
    							+tempmoves.get(ii).getIndex(); i++)
    	        		{
    						if(tempborad[i][m.getPos()])
    						{
    							tempborad[i][m.getPos()] = false;
    						}
    						else
    						{
    							return false;
    						}
    	        		}
    					rec.sat = true;
    					tempmoves.remove(ii);
    					break;
    				}
    			}

    		}
    	}
    	
    	public boolean isComplete(ArrayList<Rec> rrecs,ArrayList<Rec> crecs)
    	{
    		boolean tempborad[][] = this.nonogram.clone();
    		ArrayList<Move> tempmoves = this.moves.clone();
    		for(Rec rec : rrecs)
    		{
    			for(int ii=0; ii<tempmoves.size();ii++)
    			{
    				if(tempmoves.get(ii).isRow && 
    						tempmoves.get(ii).getPos()==rec.getPos() &&
    						tempmoves.get(ii).getSize()==rec.getSize())
    				{
    					for(int i = tempmoves.get(ii).getIndex(); i<tempmoves.get(ii).getSize()
    							+tempmoves.get(ii).getIndex(); i++)
    	        		{
    						if(tempborad[m.getPos()][i])
    						{
    							tempborad[m.getPos()][i] = false;
    						}
    						else
    						{
    							return false;
    						}
    	        		}
    					rec.sat = true;
    					tempmoves.remove(ii);
    					break;
    				}
    			}
    			if(!rec.sat)
    			{
    				return false;
    			}
    		}
    		for(Rec rec : crecs)
    		{
    			for(int ii=0; ii<tempmoves.size();ii++)
    			{
    				if((!tempmoves.get(ii).isRow) && 
    						tempmoves.get(ii).getPos()==rec.getPos() &&
    						tempmoves.get(ii).getSize()==rec.getSize())
    				{
    					for(int i = tempmoves.get(ii).getIndex(); i<tempmoves.get(ii).getSize()
    							+tempmoves.get(ii).getIndex(); i++)
    	        		{
    						if(tempborad[i][m.getPos()])
    						{
    							tempborad[i][m.getPos()] = false;
    						}
    						else
    						{
    							return false;
    						}
    	        		}
    					rec.sat = true;
    					tempmoves.remove(ii);
    					break;
    				}
    			}
    			if(!rec.sat)
    			{
    				return false;
    			}
    		}
    	}
    	
    	public boolean placeMove(Move m)
    	{
    		
    		
    		if(m.getIsRow())
        	{
    			if(m.getIndex()+m.getSize()>=this.col)
    			{
    				return false;
    			}
        		for(int i = m.getIndex(); i<m.getSize()+m.getIndex(); i++)
        		{
        			moves.add(m);
        			nonogram[m.getPos()][i] = true;
        		}
        	}
        	else
        	{
        		if(m.getIndex()+m.getSize()>=this.row)
    			{
    				return false;
    			}
        		for(int i = m.getIndex(); i<m.getSize()+m.getIndex(); i++)
        		{
        			moves.add(m);
        			nonogram[i][m.getPos()] = true;
        		}
        	}
    		return true;
    	}
    	
    	public isSpaceSet(int r, int c)
    	{
    		return nonogram[r][c];
    	}
    	
    	public void printPuzzle()
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

    public class Board {
        private int row;
        private int col;
        private Puzzle puzzle;
        private ArrayList<Rec> row_recs;
        private ArrayList<Rec> col_recs;
        private Tree<Move> moves;

        public void printBoard()
        {
        	puzzle.printPuzzle();
        }

        public Board (String filename)
        {
            Scanner scan = new Scanner(new File(fileName));

            String s = scan.next();
            String[] dimensions = s.split(",");
            row = Integer.parseInt(dimensions[0]);
            col = Integer.parseInt(dimensions[1]);
            row_recs = new ArrayList<Rec>();
            col_recs = new ArrayList<Rec>();
            moves = new Tree<Move>();


            int index = 0;
            while(scan.hasNextLine() && index < row)
            {
                String[] constraints = scan.next().split(","); 
                for(int j = 0; j < constraints.length; j++)
                {
                    row_recs.add(new Rec(false, Integer.parseInt(constraints[j]), index));
                }
                index++;
            }

            index = 0;
            while(scan.hasNextLine() && index < col)
            {
                String[] constraintsCol = scan.next().split(","); 
                for(int i = 0; i < constraintsCol.length; i++)
                {

                    col_recs.add(new Rec(false,Integer.parseInt(constraints[i]),index));
                }
                index++;
            }
            puzzle = new Puzzle(row,col);

        }

    }

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

