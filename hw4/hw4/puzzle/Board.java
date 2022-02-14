package hw4.puzzle;

import java.util.LinkedList;
import java.util.List;

public class Board implements WorldState {
    private int[][] tiles;
    private int size;

    public Board(int[][] tiles) {
        this.size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return size;
    }

    /**
     * Ideas from Josh Hug
     */
    public Iterable<WorldState> neighbors() {
        List<WorldState> neighbors = new LinkedList<>();
        int blankX = -1;
        int blankY = -1;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tileAt(i, j) == 0) {
                    blankX = i;
                    blankY = j;
                }
            }
        }

        int[][] neighbor = new int[size][size];
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                neighbor[i][j] = tileAt(i, j);
            }
        }
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (Math.abs(i - blankX) + Math.abs(j - blankY) == 1) {
                    neighbor[blankX][blankY] = neighbor[i][j];
                    neighbor[i][j] = 0;
                    neighbors.add(new Board(neighbor));
                    neighbor[i][j] = neighbor[blankX][blankY];
                    neighbor[blankX][blankY] = 0;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int sum = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tiles[i][j] != 0 && tiles[i][j] != goalTile(i, j)) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                int tile = tileAt(i, j);
                if (tile != 0 && tile != goalTile(i, j)) {
                    sum += Math.abs(getX(tile) - i);
                    sum += Math.abs(getY(tile) - j);
                }
            }
        }
        return sum;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        } else if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board other = (Board) y;
        if (this.size != other.size) {
            return false;
        } else {
            for (int i = 0; i < size; i += 1) {
                for (int j = 0; j < size; j += 1) {
                    if (this.tileAt(i, j) != other.tileAt(i, j)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                hashcode = hashcode * 7 + tiles[i][j];
            }
        }
        return hashcode;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    private int goalTile(int x, int y) {
        return x * size + y + 1;
    }

    private int getX(int i) {
        return (i - 1) / size;
    }

    private int getY(int i) {
        return (i - 1) % size;
    }
}
