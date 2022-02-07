package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double mean;
    private double stdDev;
    private int[] results;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (T < 0 || N < 0) {
            throw new IllegalArgumentException();
        }
        int row, col;
        results = new int[T];
        for (int i = 0; i < T; i += 1) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                row = StdRandom.uniform(N);
                col = StdRandom.uniform(N);
                while (percolation.isOpen(row, col)) {
                    row = StdRandom.uniform(N);
                    col = StdRandom.uniform(N);
                }
                percolation.open(row, col);
            }
            results[i] = percolation.numberOfOpenSites();
        }
        this.mean = StdStats.mean(results);
        this.stdDev = StdStats.stddev(results);
    }

    public double mean() {
        return this.mean;
    }

    public double stddev() {
        return this.stdDev;
    }

    public double confidenceLow() {
        return mean - 1.96 * stdDev / Math.sqrt(results.length);
    }

    public double confidenceHigh() {
        return mean + 1.96 * stdDev / Math.sqrt(results.length);
    }
}
