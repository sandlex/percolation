/**
 * author: Alexey Peskov
 */
public class Percolation {

    private int N;
    private WeightedQuickUnionUF model;
    private boolean[][] state;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.N = N;
        model = new WeightedQuickUnionUF(N * N);
        state = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
            state[i][j] = false;
        }
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        if (!isOpen(i, j)) {
            state[i][j] = true;
            int rnd = i * N + j;
            if (j > 0) {
                int left = rnd - 1;
                if (isOpen(left / N, left % N)) {
                    model.union(rnd, left);
                }
            }

            if (j < N - 1) {
                int right = rnd + 1;
                if (isOpen(right / N, right % N)) {
                    model.union(rnd, right);
                }
            }

            if (i > 0) {
                int top = rnd - N;
                if (isOpen(top / N, top % N)) {
                    model.union(rnd, top);
                }
            }

            if (i < N - 1) {
                int bottom = rnd + N;
                if (isOpen(bottom / N, bottom % N)) {
                    model.union(rnd, bottom);
                }
            }
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return state[i][j];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        for (int k = 0; k < N; k++) {
            if (state[0][k] && model.connected(i * N + j, k)) {
                return true;
            }
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int k = N * N - 1; k >= N * N - N; k--) {
            if (isFull(k / N, k % N)) {
                return true;
            }
        }
        return false;
    }
}
