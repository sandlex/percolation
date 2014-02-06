/**
 * author: Alexey Peskov
 */
public class PercolationStats {
    private int experiments;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        experiments = T;
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {

        return 0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0;
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return 0;
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return 0;
    }

    // test client, described below
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.println("mean" + stats.mean());
        StdOut.println("stddev" + stats.stddev());
        StdOut.println("95% confidence interval" + stats.confidenceLo() + ", " + stats.confidenceHi());
    }

}
