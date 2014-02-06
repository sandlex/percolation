/**
 * author: Alexey Peskov
 */
public class Percolation {

    private int n;
    private WeightedQuickUnionUF model;
    private boolean[] state;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        n = N;
        model = new WeightedQuickUnionUF(N * 2);
        for (int i = 0; i < N * 2; i++) {
            state[i] = false;
        }


        while (!percolates()) {

            int rnd = StdRandom.uniform(N);
            open(rnd % N, rnd / N);

            //connect with sides if they opened
            //TODO consider walls
            model.union(rnd, rnd - 1);
            model.union(rnd, rnd + 1);
            model.union(rnd, rnd - n);
            model.union(rnd, rnd + n);
        }
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        if (!isOpen(i, j)) {
            state[i * n + j] = true;
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return state[i * n + j];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        int gr = model.find(i * n + j);
        for (int k = 0; k < n; k++) {
            if (gr == k && state[k]) {
                return true;
            }
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int k = n * 2 - 1; k >= n * 2 - 1 - n; k--) {
            if (isFull(k % n, k / n)) {
                return true;
            }
        }
        return false;
    }
}
