package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * Generating hallways that include turns, or equivalently, straight hallways that intersect.
 * The locations of the hallways should be random.
 * There should not be gaps in the floor between adjacent rooms or hallways
 */
public class Hallway extends Room {
    Position leftBottom;
    int width;
    int height;

    Hallway(Position leftBottom, int width, int height) {
        super(leftBottom, width, height);
    }

    @Override
    public String toString() {
        String info = "";
        if (height == 3) {
            info = "Horizontal Hallway Started at (" + leftBottom.x + "," + leftBottom.y
                    +
                    ")," + "Length is " + width;
        } else {
            info = "Vertical Hallway Started at (" + leftBottom.x + "," + leftBottom.y
                    +
                    ")," + "Length is " + height;
        }
        return info;
    }

    private static void addHallway(TETile[][] world, Hallway hallway) {
        Room.addXOfRoom(world, hallway);
        Room.addYOfRoom(world, hallway);
        Room.addFloor(world, hallway);
    }

    private static void addVerticalHallway(TETile[][] world, Position leftBottom) {
        Hallway hallway = new Hallway(leftBottom, 3, RandomUtils.uniform(RANDOM, 5, 10));
        addHallway(world, hallway);
    }

    private static void addHorizontalHallway(TETile[][] world, Position leftBottom) {
        Hallway hallway = new Hallway(leftBottom, RandomUtils.uniform(RANDOM, 5, 10), 3);
        addHallway(world, hallway);
    }

    public static void test(TETile[][] world) {
        for (Room room : rooms) {
            addVerticalHallway(world, room.leftBottom);
        }
    }

}
