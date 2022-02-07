package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashSet;
import java.util.Set;

public class Percolation {


    private WeightedQuickUnionUF union;
    private WeightedQuickUnionUF union2;
    private boolean[][] grids;
    private int openSites;
    private int N;
    private Set<Integer> openInLast;

    //Create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        grids = new boolean[N][N];
        openSites = 0;
        union = new WeightedQuickUnionUF(N * N + 2);
        union2 = new WeightedQuickUnionUF(N * N + 2);
        openInLast = new HashSet<>();
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                grids[i][j] = false;
            }
        }
        for (int i = 0; i < N; i += 1) {
            union.union(toInt(0, i), N * N);
            union2.union(toInt(0, i), N * N);
        }
        for (int i = 0; i < N; i += 1) {
            union2.union(toInt(N - 1, i), N * N + 1);
        }
    }

    //open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!checkLegal(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grids[row][col] = true;
        openSites += 1;
        if (row == N - 1) {
            openInLast.add(col);
        }
        //right left down up
        int[] directions = {0, 1, 0, -1, 1, 0, -1, 0};
        for (int i = 0; i < 8; i += 2) {
            int newRow = row + directions[i];
            int newCol = col + directions[i + 1];
            if (checkLegal(newRow, newCol)) {
                if (isOpen(newRow, newCol)) {
                    union.union(toInt(row, col), toInt(newRow, newCol));
                    union2.union(toInt(row, col), toInt(newRow, newCol));
                    updateLastRow();
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (!checkLegal(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grids[row][col];
    }

    public boolean isFull(int row, int col) {
        if (!checkLegal(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return isOpen(row, col) && union.connected(toInt(row, col), N * N);
    }


    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return union.connected(N * N, N * N + 1);
    }

    private int toInt(int row, int col) {
        return row * grids.length + col;
    }

    private boolean checkLegal(int row, int col) {
        return (row < N) && (row > -1)
                && (col < N) && (col > -1);
    }

    private void updateLastRow() {
        if (!percolates() && union2.connected(N * N, N * N + 1)) {
            for (int colInLast : openInLast) {
                if (isFull(N - 1, colInLast)) {
                    union.union(toInt(N - 1, colInLast), N * N + 1);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}
