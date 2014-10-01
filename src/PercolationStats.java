
public class PercolationStats {

    private double [] results; //results for fractions of open sites
    private int times;
    
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        results = new double[T];
        double x = 0;
        int sitesCount = N * N;
        times = T;
        Percolation percolation;
        for (int i = 0; i < T; i++) {
            x = 0;
            percolation = new Percolation(N);
            while (!percolation.percolates()) {
                int k = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!percolation.isOpen(k, j)) {
                    percolation.open(k, j);
                    x += 1;
                    }
            }
            results[i] = x / sitesCount;
        }
    }
    public double mean() {
        return StdStats.mean(results);
    }
    
    public double stddev() {
        return StdStats.stddev(results);
    }
    
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }
    
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }
    
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats percStats = new PercolationStats(N, T);
        StdOut.println("mean =" + percStats.mean());
        StdOut.println("stddev =" + percStats.stddev());
        StdOut.println("95% confidence interval ="
            + percStats.confidenceLo()
            + ", "
            + percStats.confidenceHi());
    }
}
