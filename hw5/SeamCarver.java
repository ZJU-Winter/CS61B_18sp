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

    public double energy(int x, int y) {
        int deltaX, deltaY;
        Color left = getLeft(x, y);
        Color right = getRight(x, y);
        Color up = getUp(x, y);
        Color down = getDown(x, y);
        int Rx = right.getRed() - left.getRed();
        int Gx = right.getGreen() - left.getGreen();
        int Bx = right.getBlue() - left.getBlue();
        deltaX = Rx * Rx + Gx * Gx + Bx * Bx;

        int Ry = up.getRed() - down.getRed();
        int Gy = up.getGreen() - down.getGreen();
        int By = up.getBlue() - down.getBlue();
        deltaY = Ry * Ry + Gy * Gy + By * By;
        return deltaX + deltaY;
    }

    private Color getLeft(int x, int y) {
        if (x == 0) {
            x = width();
        }
        return picture.get(x - 1, y);
    }

    private Color getRight(int x, int y) {
        if (x == width() - 1) {
            x = -1;
        }
        return picture.get(x + 1, y);
    }

    private Color getUp(int x, int y) {
        if (y == 0) {
            y = height();
        }
        return picture.get(x, y - 1);
    }

    private Color getDown(int x, int y) {
        if (y == height() - 1) {
            y = -1;
        }
        return picture.get(x, y + 1);
    }

    private int indexOfMin(double[] energy, int left, int right) {
        if (left > right) {
            throw new IndexOutOfBoundsException();
        }
        if (right >= energy.length) {
            right = energy.length - 1;
        }
        if (left < 0) {
            left = 0;
        }
        int index = left;
        for (int i = left; i <= right; i++) {
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