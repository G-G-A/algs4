import java.util.Comparator;

public class Solver {
    
    private class SearchNode
    {
        private Board board;
        private int moves;
        private SearchNode previous;
        public int manhattanScore = board.manhattan() + moves;
        public int hammingScore = board.hamming() + moves;
    }
    
    private MinPQ<SearchNode> pq;
    
    private static class ManhattanComparator implements Comparator<SearchNode>{
        public int compare (SearchNode n1, SearchNode n2)
        {
            if (n1.manhattanScore < n2.manhattanScore) return -1;
            if (n1.manhattanScore > n2.manhattanScore) return +1;
            return 0;
        }
    }
    
    private static class HammingComparator implements Comparator<SearchNode>{
        public int compare (SearchNode n1, SearchNode n2)
        {
            if (n1.hammingScore < n2.hammingScore) return -1;
            if (n1.hammingScore > n2.hammingScore) return +1;
            return 0;
        }
    }
    
    public Solver(Board initial)
    {
        pq = new MinPQ<SearchNode>(new HammingComparator());
        SearchNode initNode = new SearchNode();
        initNode.board = initial;
        initNode.moves = 0;
        initNode.previous = null;
        pq.insert(initNode);
    }
    
    public boolean isSolvable()
    {
        return false;
    }
    
    public int moves()
    {
        return 0;
    }
    
    public Iterable<Board> solution()
    {
        return null;
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

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
