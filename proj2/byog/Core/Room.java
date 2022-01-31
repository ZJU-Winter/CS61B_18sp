package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Room extends World {
    private static final int MAX_WIDTH = 10;
    private static final int MAX_HEIGHT = 10;
    private static final int MIN_WIDTH = 3;
    private static final int MIN_HEIGHT = 3;
    private static final int MAX_MOUNT = 5;
    private static int rightBound;
    private static Position startPoint =
            new Position(RandomUtils.uniform(RANDOM, MIN_WIDTH, MAX_WIDTH),
                    RandomUtils.uniform(RANDOM, MIN_HEIGHT, MAX_HEIGHT));

    Position leftBottom;
    int height;
    int width;

    Room(Position p, int height, int width) {
        this.leftBottom = p;
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString() {
        String info = "Room Started at (" + leftBottom.x + "," + leftBottom.y
                +
                ")," + "Width is " + width + " and Height is " + height;
        return info;
    }

    public Position getLeftTop() {
        return new Position(this.leftBottom.x, this.leftBottom.y + this.height);
    }

    private static void addFloor(TETile[][] world, Room room) {
        int xCoord, yCoord;
        int xStart = room.leftBottom.x + 1;
        int yStart = room.leftBottom.y + 1;
        for (xCoord = xStart; xCoord < xStart + room.width - 2; xCoord += 1) {
            for (yCoord = yStart; yCoord < yStart + room.height - 2; yCoord += 1) {
                world[xCoord][yCoord] = Tileset.FLOOR;
            }
        }
    }

    private static void addXOfRoom(TETile[][] world, Room room) {
        int xCoord;
        int yCoord = room.leftBottom.y;
        int xStart = room.leftBottom.x;
        for (xCoord = xStart; xCoord < xStart + room.width; xCoord += 1) {
            world[xCoord][yCoord] = TETile.colorVariant(Tileset.WALL, 32, 32, 32, RANDOM);
        }
        yCoord += room.height - 1;
        for (xCoord = xStart; xCoord < xStart + room.width; xCoord += 1) {
            world[xCoord][yCoord] = TETile.colorVariant(Tileset.WALL, 32, 32, 32, RANDOM);
        }
    }

    private static void addYOfRoom(TETile[][] world, Room room) {
        int xCoord = room.leftBottom.x;
        int yCoord;
        int yStart = room.leftBottom.y + 1;
        for (yCoord = yStart; yCoord < yStart + room.height - 1; yCoord += 1) {
            world[xCoord][yCoord] = TETile.colorVariant(Tileset.WALL, 32, 32, 32, RANDOM);
        }
        xCoord += room.width - 1;
        for (yCoord = yStart; yCoord < yStart + room.height - 1; yCoord += 1) {
            world[xCoord][yCoord] = TETile.colorVariant(Tileset.WALL, 32, 32, 32, RANDOM);
        }
    }

    private static void addRoom(TETile[][] world, Room room) {
        addXOfRoom(world, room);
        addYOfRoom(world, room);
        addFloor(world, room);
    }

    private static Position getVerticalPosition(Position start) {
        Position p = new Position(start.x + RandomUtils.uniform(RANDOM, -MAX_WIDTH / 2, MAX_WIDTH),
                start.y + RandomUtils.uniform(RANDOM, MAX_HEIGHT / 2));
        while (p.x < 0) {
            p = new Position(start.x + RandomUtils.uniform(RANDOM, -MAX_WIDTH / 2, MAX_WIDTH),
                    start.y + RandomUtils.uniform(RANDOM, MAX_HEIGHT / 2));
        }
        return p;
    }

    private static boolean checkLegalRoom(Room room) {
        return (room.leftBottom.x + room.width) < WIDTH
                &&
                (room.leftBottom.y + room.height) < HEIGHT;
    }

    private static Room randomSizeOfRooms(Position leftBottom) {
        return new Room(leftBottom,
                RandomUtils.uniform(RANDOM, MIN_WIDTH, MAX_WIDTH),
                RandomUtils.uniform(RANDOM, MIN_HEIGHT, MAX_HEIGHT));
    }

    private static void updateRightBound() {
        
    }

    public static void addVerticalRooms(TETile[][] world) {
        for (int i = 0; i < MAX_MOUNT; i += 1) {
            int count = 0;
            boolean legalFlag = true;
            Room room = randomSizeOfRooms(startPoint);
            while (!checkLegalRoom(room)) {
                room = randomSizeOfRooms(startPoint);
                count += 1;
                if (count == MAX_MOUNT) {
                    legalFlag = false;
                    break;
                }
            }
            if (!legalFlag) {
                break;
            }
            System.out.println(room);
            Position leftTop = room.getLeftTop();
            addRoom(world, room);
            startPoint = getVerticalPosition(leftTop);
        }
    }

}
