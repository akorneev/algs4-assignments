public class Percolation {
    private int N;
    private boolean[] grid;
    private WeightedQuickUnionUF percolationUF;
    private int percolationTop;
    private int percolationBottom;
    private WeightedQuickUnionUF fullnessUF;
    private int fullnessTop;

    /**
     * Creates N-by-N grid, with all sites blocked.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Size of a grid is negative or equal to 0.");
        }
        this.N = N;
        grid = new boolean[N * N];
        percolationUF = new WeightedQuickUnionUF(N * N + 2);
        percolationTop = N * N;
        percolationBottom = N * N + 1;
        fullnessUF = new WeightedQuickUnionUF(N * N + 1);
        fullnessTop = N * N;
    }

    /**
     * Test client, optional.
     */
    public static void main(String[] args) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    /**
     * Opens site (row i, column j) if it is not already.
     */
    public void open(int i, int j) {
        checkIndices(i, j);
        if (!isOpen(i, j)) {
            int idx = (i - 1) * N + (j - 1);
            grid[idx] = true;
            if (i > 1 && isOpen(i - 1, j)) {
                percolationUF.union(idx, idx - N);
                fullnessUF.union(idx, idx - N);
            }
            if (i < N && isOpen(i + 1, j)) {
                percolationUF.union(idx, idx + N);
                fullnessUF.union(idx, idx + N);
            }
            if (j > 1 && isOpen(i, j - 1)) {
                percolationUF.union(idx, idx - 1);
                fullnessUF.union(idx, idx - 1);
            }
            if (j < N && isOpen(i, j + 1)) {
                percolationUF.union(idx, idx + 1);
                fullnessUF.union(idx, idx + 1);
            }
            if (i == 1) {
                percolationUF.union(idx, percolationTop);
                fullnessUF.union(idx, fullnessTop);
            }
            if (i == N) {
                percolationUF.union(idx, percolationBottom);
            }
        }
    }

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        checkIndices(i, j);
        return grid[(i - 1) * N + (j - 1)];
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        checkIndices(i, j);
        return fullnessUF.connected(percolationTop, (i - 1) * N + (j - 1));
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return percolationUF.connected(percolationTop, percolationBottom);
    }

    /**
     * Checks whether indices is out of bounds.
     *
     * @param i row
     * @param j column
     * @throws IndexOutOfBoundsException is index out of bounds
     */
    private void checkIndices(int i, int j) {
        if (i < 1) throw new IndexOutOfBoundsException("Index i is less than 1.");
        if (i > N) throw new IndexOutOfBoundsException("Index i is grater than N.");
        if (j < 1) throw new IndexOutOfBoundsException("Index j is less than 1.");
        if (j > N) throw new IndexOutOfBoundsException("Index j is grater than N.");
    }
}
