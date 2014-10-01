/****************************************************************************
 *  Compilation:  javac WeightedQuickUnionUF.java
 *  Execution:  java WeightedQuickUnionUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Weighted quick-union (without path compression).
 *
 ****************************************************************************/
/**
 *  The <tt>Percolation</tt> class represents an base 
 *  operations with N*N grid, using union-find data 
 *  structure.
 *  It supports the <em>open</em>, <em>isOpen</em>, 
 *  <em>percolates</em> operations. 
 *  <p>
 *  This implementation uses weighted quick union 
 *  by size (without path compression).
 *  Initializing with <em>N</em> objects
 *   takes quadratic time.
 *  Afterwards, <em>open</em>, <em>isFull</em>,
 *   and <em>percolates</em> take
 *  logarithmic time (in the worst case) 
 *  and <em>isOpen</em> takes constant
 *  time.
 *  <p>   
 *  @author Gennadii Gorelikov
 */
public class Percolation {    
    private boolean[] openSites; //1D array for sites
    private boolean[] toTop; 
    private boolean[] toBottom;
    private WeightedQuickUnionUF uf;
    private int sitesCount;
    private int gridSize;
    private boolean isPercolates = false;
    
    public Percolation(int N) { //create N-by-N grid, with all sites blocked
        if (N <= 0) throw new java.lang.IllegalArgumentException();
        sitesCount = N*N;
        gridSize = N;
        openSites = new boolean[sitesCount];
        toTop = new boolean[sitesCount];
        toBottom = new boolean[sitesCount];
        uf = new WeightedQuickUnionUF(sitesCount);
        for (int i = 0; i < sitesCount; i++)
        {
            openSites[i] = false;
            toTop[i] = false;
            toBottom[i] = false;
        }
    }
    private int xyTo1D(int x, int y)
    {
        checkArguments(x, y);
        return (x - 1) * gridSize + y - 1;
    }
    
    
    public boolean isOpen(int i, int j)
    {
        int index = xyTo1D(i, j);
        return openSites[index];
    }
    
    private void checkArguments(int i, int j)
    {
        if (i > gridSize || i < 1 || j > gridSize || j < 1)
            throw new java.lang.IndexOutOfBoundsException();
    }
    
    public void open(int i, int j)
    {
        int index = xyTo1D(i, j);
        if (openSites[index]) return;
        openSites[index] = true;
        int oldRoot = uf.find(index);
        if (i == 1) {
            toTop[oldRoot] = true;
        }
        if (i == gridSize) {
            toBottom[oldRoot] = true;
        }
        boolean resultTop = toTop[oldRoot];
        boolean resultBottom = toBottom[oldRoot];
        if (j > 1 && openSites[index - 1]) {
            int rightRoot = uf.find(index - 1);
            resultTop = resultTop || toTop[rightRoot];
            resultBottom = resultBottom || toBottom[rightRoot];
            checkAndConnect(oldRoot, rightRoot);
        }
        if (j < gridSize && openSites[index + 1]) {
            int leftRoot = uf.find(index + 1);
            resultTop = resultTop || toTop[leftRoot];
            resultBottom = resultBottom || toBottom[leftRoot];
            checkAndConnect(oldRoot, leftRoot);
        }
        if (i > 1 && openSites[index - gridSize]) {
            int highRoot = uf.find(index - gridSize);
            resultTop = resultTop || toTop[highRoot];
            resultBottom = resultBottom || toBottom[highRoot];
            checkAndConnect(oldRoot, highRoot);
        }
        if (i < gridSize && openSites[index + gridSize]) {
            int lowRoot = uf.find(index + gridSize);
            resultTop = resultTop || toTop[lowRoot];
            resultBottom = resultBottom || toBottom[lowRoot];
            checkAndConnect(oldRoot, lowRoot);
        }
        int newRoot = uf.find(index);
        toTop[newRoot] = resultTop;
        toBottom[newRoot] = resultBottom;
        if (resultTop && resultBottom) isPercolates = true;
    }
    
    private void checkAndConnect(int first, int second)
    {
        if (!uf.connected(first, second))
        {
            uf.union(first, second);
        }
    }
    
    public boolean isFull(int i, int j)
    {
        int index = xyTo1D(i, j);
        return toTop[uf.find(index)];
    }
    
    public boolean percolates()
    {
        return isPercolates;
    }
}
