import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private final int trialCount;
    private final double[] results;
    private final static double con96 = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("input should be positive!");
        }
        int size = n;
        trialCount = trials;
        results = new double[trialCount];
        for (int i = 0; i < trialCount; i++) {
            Percolation test = new Percolation(size);
            while (!test.percolates()) {
                int row = StdRandom.uniform(1, size + 1);
                int col = StdRandom.uniform(1, size + 1);
                test.open(row, col);
            }
            int openSites = test.numberOfOpenSites();
            double result = (double) openSites / (size * size);
            results[i] = result;
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - (con96 * stddev() / Math.sqrt(trialCount));
    }

    public double confidenceHi() {
        return mean() + (con96 * stddev() / Math.sqrt(trialCount));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(n, trials);
        
        String confidence = "[" + p.confidenceLo() + ", " + p.confidenceHi() + "]";
        StdOut.println("mean                    = " + p.mean());
        StdOut.println("stddev                  = " + p.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
