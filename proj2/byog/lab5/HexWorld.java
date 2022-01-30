package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param size is the size of hexagon
     * @param i    is the row number where i = 0 is the bottom
     * @return the width of ith row of a hexagon whose size is size
     */

    public static int hexRowWidth(int size, int i) {
        int rowWidth = size + 2 * i;
        if (i >= size) {
            rowWidth = 5 * size - 2 * i - 2;
        }
        return rowWidth;
    }

    /**
     * @param size is the size of hexagon
     * @param i    is the row number where i = 0 is the bottom
     * @return the offset started from the left bottom point
     */
    public static int hexRowOffset(int size, int i) {
        int offset = i;
        if (i >= size) {
            offset = 2 * size - 1 - i;
        }
        return -offset;
    }

    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;
            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);

        }
    }

    public static void addFourHexagon(TETile[][] world, Position p, int s, TETile t) {
        addHexagon(world, p, s, t);
        Position topRight = topRightNeighbor(p, s);
        Position top = topNeighbor(p, s);
        Position topLeft = topLeftNeighbor(p, s);
        addHexagon(world, topRight, s, Tileset.MOUNTAIN);
        addHexagon(world, top, s, Tileset.MOUNTAIN);
        addHexagon(world, topLeft, s, Tileset.MOUNTAIN);
    }

    private static Position topRightNeighbor(Position p, int s) {
        Position topRightNeighbor = new Position(p.x, p.y);
        topRightNeighbor.y += s;
        topRightNeighbor.x += 2 * s - 1;
        return topRightNeighbor;
    }

    private static Position topNeighbor(Position p, int s) {
        Position topNeighbor = new Position(p.x, p.y);
        topNeighbor.y += 2 * s;
        return topNeighbor;
    }

    private static Position topLeftNeighbor(Position p, int s) {
        Position topLeftNeighbor = new Position(p.x, p.y);
        topLeftNeighbor.y += s;
        topLeftNeighbor.x -= (2 * s - 1);
        return topLeftNeighbor;
    }

    private static void worldInitializer(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    @Test
    public void hexRowWidthTest() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void hexRowOffsetTest() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        worldInitializer(world);

        Position p = new Position(WIDTH / 2, 20);
        addFourHexagon(world, p, 3, Tileset.GRASS);

        ter.renderFrame(world);
    }
}
