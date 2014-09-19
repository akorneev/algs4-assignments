public class PercolationStats {
    private double[] data;

    /**
     * Performs T independent computational experiments on an N-by-N grid.
     */
    public PercolationStats(int N, int T) {
        if (N <= 0) throw new IllegalArgumentException("N <= 0");
        if (T <= 0) throw new IllegalArgumentException("T <= 0");
        data = new double[T];
        for (int t = 0; t < T; t++) {
            Percolation p = new Percolation(N);
            int opened = 0;
            while (!p.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!p.isOpen(i, j)) {
                    p.open(i, j);
                    opened++;
                }
            }
            data[t] = (double) opened / (N * N);
        }
    }

    /**
     * Test client.
     */
    public static void main(String[] args) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    /**
     * Samples mean of percolation threshold.
     */
    public double mean() {
        double sum = 0;
        for (double x : data) sum += x;
        return sum / data.length;
    }

    /**
     * Samples standard deviation of percolation threshold.
     */
    public double stddev() {
        double m = mean();
        double sum = 0;
        for (double x : data) sum += (x - m) * (x - m);
        return Math.sqrt(sum / (data.length - 1));
    }

    /**
     * Returns lower bound of the 95% confidence interval.
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(data.length));
    }

    /**
     * Returns upper bound of the 95% confidence interval.
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(data.length));
    }
}
