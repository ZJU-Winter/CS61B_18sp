import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private Picture picture;

    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
    }

    public Picture picture() {
        return new Picture(picture);
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    private double calcGradient(Color x, Color y) {
        return (x.getGreen() - y.getGreen()) * (x.getGreen() - y.getGreen())
                + (x.getBlue() - y.getBlue()) * (x.getBlue() - y.getBlue())
                + (x.getRed() - y.getRed()) * (x.getRed() - y.getRed());
    }

    private double deltaX2(int x, int y) {
        int right = Math.floorMod(x + 1, width());
        int left = Math.floorMod(x - 1, width());
        return calcGradient(picture.get(right, y), picture.get(left, y));
    }

    private double deltaY2(int x, int y) {
        int top = Math.floorMod(y + 1, height());
        int bottom = Math.floorMod(y - 1, height());
        return calcGradient(picture.get(x, top), picture.get(x, bottom));
    }

    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height()) {
            throw new IndexOutOfBoundsException();
        }
        return deltaX2(x, y) + deltaY2(x, y);
    }

    private int indexOfMin(double[] energy, int start, int end) {
        if (start > end) {
            throw new IndexOutOfBoundsException();
        }
        if (end >= energy.length) {
            end = energy.length - 1;
        }
        if (start < 0) {
            start = 0;
        }
        int index = start;
        for (int i = start; i <= end; i++) {
            if (energy[index] > energy[i]) {
                index = i;
            }
        }
        return index;
    }

    private double minNeighbor(int x, int y, double[][] energy) {
        if (y < 1) {
            throw new IndexOutOfBoundsException();
        }
        double min = energy[y - 1][x];
        if (x > 0) {
            min = Math.min(min, energy[y - 1][x - 1]);
        }
        if (x < width() - 1) {
            min = Math.min(min, energy[y - 1][x + 1]);
        }
        return min;
    }

    public int[] findVerticalSeam() {
        double[][] energy = new double[height()][width()];
        for (int j = 0; j < width(); j++) {
            energy[0][j] = energy(j, 0);
        }
        for (int i = 1; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                energy[i][j] = energy(j, i) + minNeighbor(j, i, energy);
            }
        }
        int[] indices = new int[height()];
        for (int i = height() - 1; i >= 0; i--) {
            if (i == height() - 1) {
                indices[i] = indexOfMin(energy[i], 0, width() - 1);
            } else {
                indices[i] = indexOfMin(energy[i], indices[i + 1] - 1, indices[i + 1] + 1);
            }
        }
        return indices;
    }

    public int[] findHorizontalSeam() {
        Picture transposed = new Picture(height(), width());
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                transposed.set(i, j, picture.get(j, i));
            }
        }
        SeamCarver seamCarver = new SeamCarver(transposed);
        return seamCarver.findVerticalSeam();
    }

    private boolean isValid(int[] seam) {
        int prev = seam[0];
        for (int num : seam) {
            if (Math.abs(prev - num) > 1) {
                return false;
            }
            prev = num;
        }
        return true;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width() || !isValid(seam)) {
            throw new IllegalArgumentException();
        }
        picture = SeamRemover.removeHorizontalSeam(picture, seam);
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height() || !isValid(seam)) {
            throw new IllegalArgumentException();
        }
        picture = SeamRemover.removeVerticalSeam(picture, seam);
    }

}