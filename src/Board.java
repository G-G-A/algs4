public class Board {
    private int N;
    private int N2;
    private int hammingPriority;
    private int manhattanPriority;
    private char[] a;
    public Board(int[][] blocks)
    {
        N = blocks.length;
        N2 = N*N;
        a = new char[N2];
        hammingPriority = -1;
        manhattanPriority = -1;
        for (int i=0,j=0; i < N && j < N; i++, j++)
        {
          //  if (i+j != blocks[i][j] + 1)
            //ToDo: calculate hamming and manhattan here.
            //think about internal board representation (char array
            // or other way, indexes)
            a[i+j] = (char) blocks[i][j];
        }
        hamming();
        manhattan();
        
    }
    
    public int dimension()
    {
        return N;
    }
    
    public int hamming()
    {
        if (hammingPriority < 0) for (int i = 0; i < N2 - 1; i++)
        {
            if (a[i] != (char)(i+1)) hammingPriority++;
        }
        return hammingPriority;
    }
    
    public int manhattan()
    {
        if (manhattanPriority < 0) for (int i = 0; i < N2 - 1; i++)
        {
            if (a[i] != (char)(i+1))
            {
                int delta = (int)a[i] - (i + 1);
                if (delta < 0) delta *= -1;
                manhattanPriority += delta;
            }
        }
        return manhattanPriority;
    }
    
    public boolean isGoal()
    {
        for (int i = 0; i < N2 - 1; i++)
        {
            if (a[i] != (char)(i+1)) return false;
        }
        return true;
    }
}
