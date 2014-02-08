/**
 * author: Alexey Peskov
 */
public class PercolationStats {
    private double[] thresholds;
    private int T;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {

        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Positive values > 0 expected");
        }

        this.T = T;
        thresholds = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            int iterations = 0;
            while (!percolation.percolates()) {
                int rnd = StdRandom.uniform(N * N);
                int row = rnd / N;
                int column = rnd % N;
                if (percolation.isOpen(row, column)) continue;
                percolation.open(row, column);
                iterations++;
            }
            thresholds[i] = iterations / (double) (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    // test client, described below
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo()
                + ", " + stats.confidenceHi());
    }

}
