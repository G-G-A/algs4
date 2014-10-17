import java.util.Arrays;

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
        int curr = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j ++)
            {
                index = i * N + j;
                curr = blocks[i][j];
               // a[index] = Character.forDigit(curr, 100);
                a[index] = (char) (blocks[i][j]);
                if (blocks[i][j] == 0) 
                {
                    emptySquarePos = index;
                }
                else if ((index + 1) % N2 != blocks[i][j]) 
                {
                    hammingPriority += 1;
                    verticalDelta = blocks[i][j] / N - i;
                    horizontalDelta = blocks[i][j] % N - j;
                    manhattanPriority += abs(verticalDelta) + abs(horizontalDelta);
                }
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
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j ++)
            {
                if (i == 0 && j == N - 1)
                    blocks[i][j] = (int) a [i*N + j - 1];
                else if (i == 0 && j == N - 2)
                    blocks[i][j] = (int) a [i*N + j + 1];
                else 
                    blocks[i][j] = (int) a [i*N + j];
            }
        }
        return new Board(blocks);
    }
    
    public Iterable<Board> neighbors()
    {
        Queue<Board> q = new Queue<Board>();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j ++)
            {
                blocks[i][j] = (int) a [i*N + j];
            }
        }
        int i0 = emptySquarePos / N;
        int j0 = emptySquarePos % N;
        if (i0 > 0)
        {
            exch(i0, j0, i0 - 1, j0, blocks);
            q.enqueue(new Board(blocks));
            exch(i0 - 1, j0, i0, j0, blocks);
        }
        if (i0 < N - 1)
        {
            exch(i0, j0, i0 + 1, j0, blocks);
            q.enqueue(new Board(blocks));
            exch(i0 + 1, j0, i0, j0, blocks);
        }
        if (j0 > 0)
        { 
            exch(i0, j0, i0, j0 - 1, blocks);
            q.enqueue(new Board(blocks));
            exch(i0, j0 - 1, i0, j0, blocks);
        }
        if (j0 < N - 1)
        {
            exch(i0, j0, i0, j0 + 1, blocks);
            q.enqueue(new Board(blocks));
            exch(i0, j0 + 1, i0, j0, blocks);
        }
        return q;
    }
    
    public boolean equals(Object y)
    {
        if (y == this) return true;
        
        if (y == null) return false;
        
        if (y.getClass() != this.getClass())
            return false;
        Board that = (Board) y;
        if (Arrays.equals(this.a, that.a)) return true;
        return false;
    }
    
    public String toString()
    {
        String result = "";
        for (int i = 0; i < N2; i++)
        {
            result += (int)a[i];
            if ((i + 1) % N == 0) result += "\n";
            else result += " ";
        }
        return result;
    }
    
    private int abs(int value)
    {
        if (value < 0) return  -value;
        return value;
    }
    
    private void exch(int i1, int j1, int i2, int j2, int[][] blocks)
    {
        int tmp = blocks[i1][j1];
        blocks[i1][j1] = blocks[i2][j2];
        blocks[i2][j2] = tmp;
    }
    
    public static void main(String[] args)
    {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        StdOut.println("board of dim : "+ initial.dimension() + "\n" + initial.toString());
        StdOut.println("hamming : "+ initial.hamming());
        StdOut.println("manhatten : "+ initial.manhattan());
        StdOut.print(initial.twin().toString());
        StdOut.println("Neighbors");
        for(Board neighbor : initial.neighbors())
        {
            StdOut.println(neighbor.toString());
        }
    }
}
