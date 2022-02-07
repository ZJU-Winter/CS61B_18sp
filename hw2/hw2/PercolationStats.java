package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] results;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (T <= 0 || N <= 0) {
            throw new IllegalArgumentException();
        }
        int row, col;
        results = new double[T];
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
            results[i] = percolation.numberOfOpenSites() / Math.pow(N, 2);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(results.length);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(results.length);
    }
}
