import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] xValues;
    private final int trials;
    private static final double confidence95 = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.trials = trials;
        xValues = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                while (p.isOpen(row, col)) {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                }
                p.open(row, col);
            }
            this.xValues[i] = ((double) p.numberOfOpenSites()) / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(this.xValues);
    }

    public double stddev() {
        return StdStats.stddev(this.xValues);
    }

    public double confidenceLo() {
        return mean() - ((confidence95 * stddev()) / Math.sqrt(this.trials));
    }

    public double confidenceHi() {
        return mean() + ((confidence95 * stddev()) / Math.sqrt(this.trials));
    }


    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean\t\t\t\t\t= " + ps.mean());
        System.out.println("stddev\t\t\t\t\t= " + ps.stddev());
        System.out.println("95% confidence interval\t= [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}