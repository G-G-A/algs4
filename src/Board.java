public class Board {
    private int N;
    private int N2;
    private int hammingPriority;
    private int manhattanPriority;
    private int emptySquarePos;
    private char[] a;
    public Board(int[][] blocks)
    {
        N = blocks.length;
        N2 = N*N;
        a = new char[N2];
        hammingPriority = 0;
        manhattanPriority = 0;
        int index = 0;
        int verticalDelta = 0;
        int horizontalDelta = 0;
        for (int i=0,j=0; i < N && j < N; i++, j++)
        {
            index = i * N + j;
            a[index] = (char) blocks[i][j];
            if (blocks[i][j] == 0) emptySquarePos = index;
            else if ((index + 1) % N2 != blocks[i][j]) 
            {
                hammingPriority += 1;
                verticalDelta = blocks[i][j] / N - i;
                horizontalDelta = blocks[i][j] % N - j;
                manhattanPriority += abs(verticalDelta) + abs(horizontalDelta);
            }
        }
    }
    
    public int dimension()
    {
        return N;
    }
    
    public int hamming()
    {
        return hammingPriority;
    }
    
    public int manhattan()
    {
        return manhattanPriority;
    }
    
    public boolean isGoal()
    {
        if (manhattanPriority == 0 && hammingPriority == 0)
            return true;
        return false;
    }
    
    public Board twin()
    {
        int[][] blocks = new int[N][N];
        for (int i=0,j=0; i < N && j < N; i++, j++)
        {
            if (i == 0 && j == N - 1)
                blocks[i][j] = (int) a [i*N + j - 1];
            else if (i == 0 && j == N - 2)
                blocks[i][j] = (int) a [i*N + j + 1];
            else 
                blocks[i][j] = (int) a [i*N + j];
        }
        return new Board(blocks);
    }
    
    public Iterable<Board> neighbors()
    {
        Queue<Board> q = new Queue<Board>();
        
        return q;
    }
    
    private int abs(int value)
    {
        if (value < 0) return  -value;
        return value;
    }
}
