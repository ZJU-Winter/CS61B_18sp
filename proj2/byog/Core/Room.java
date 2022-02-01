package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Room extends World {
    private static final int MAX_WIDTH = 9;
    private static final int MAX_HEIGHT = 9;
    private static final int MIN_WIDTH = 3;
    private static final int MIN_HEIGHT = 3;
    private static final int LOOP_AMOUNT = 5;
    private static final int MIN_VERTICAL_AMOUNT = 2;
    private static final int MAX_VERTICAL_AMOUNT = 5;
    private static final int HORIZONTAL_AMOUNT = 8;
    private static int rightBound;
    private static Position startPoint =
            new Position(RandomUtils.uniform(RANDOM, MIN_WIDTH, MAX_WIDTH),
                    RandomUtils.uniform(RANDOM, MIN_HEIGHT, MAX_HEIGHT));
    Position leftBottom;
    int width;
    int height;

    Room(Position leftBottom, int width, int height) {
        this.leftBottom = leftBottom;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        String info = "Room Started at (" + leftBottom.x + "," + leftBottom.y
                +
                ")," + "Width is " + width + " and Height is " + height;
        return info;
    }

    private Position getLeftTop() {
        return new Position(this.leftBottom.x, this.leftBottom.y + this.height);
    }

    private Position getRightTop() {
        return new Position(this.leftBottom.x + this.width, this.leftBottom.y + this.height);
    }

    private Position getMiddleTop() {
        return new Position(this.leftBottom.x + this.width / 2, this.leftBottom.y + this.height);
    }

    protected static void addFloor(TETile[][] world, Room room) {
        int xCoord, yCoord;
        int xStart = room.leftBottom.x + 1;
        int yStart = room.leftBottom.y + 1;
        for (xCoord = xStart; xCoord < xStart + room.width - 2; xCoord += 1) {
            for (yCoord = yStart; yCoord < yStart + room.height - 2; yCoord += 1) {
                world[xCoord][yCoord] = Tileset.FLOOR;
            }
        }
    }

    protected static void addXOfRoom(TETile[][] world, Room room) {
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

    protected static void addYOfRoom(TETile[][] world, Room room) {
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

    public static void addRoom(TETile[][] world, Room room) {
        addXOfRoom(world, room);
        addYOfRoom(world, room);
        addFloor(world, room);
    }

    private static Position getVerticalPosition(Position start) {
        return new Position(start.x + RandomUtils.uniform(RANDOM, -2, 5),
                start.y + RandomUtils.uniform(RANDOM, MAX_HEIGHT / 2));
    }

    private static Position getHorizontalPosition() {
        return new Position(rightBound + RandomUtils.uniform(RANDOM, -2, 5),
                RandomUtils.uniform(RANDOM, HEIGHT / 2));
    }

    private static boolean checkLegalRoom(Room room) {
        return (room.leftBottom.x + room.width) < WIDTH
                &&
                (room.leftBottom.y + room.height) < HEIGHT;
    }

    private static Room randomSizeOfRoom(Position leftBottom) {
        return new Room(leftBottom,
                RandomUtils.uniform(RANDOM, MIN_WIDTH, MAX_WIDTH),
                RandomUtils.uniform(RANDOM, MIN_HEIGHT, MAX_HEIGHT));
    }

    private void updateRightBound() {
        int newRightBound = this.getRightTop().x;
        if (newRightBound > rightBound)
            rightBound = newRightBound;
    }

    private static void printInfo(Room room) {
        System.out.println("StartPoint is: " + startPoint);
        System.out.println(room);
        System.out.println("Right Top Position is: " + room.getRightTop());
        System.out.println("Now RightBound is: " + rightBound);
    }

    public static void addVerticalRooms(TETile[][] world) {
        int VERTICAL_AMOUNT = RandomUtils.uniform(RANDOM, MIN_VERTICAL_AMOUNT, MAX_VERTICAL_AMOUNT);
        for (int i = 0; i < VERTICAL_AMOUNT; i += 1) {
            int count = 0;
            boolean legalFlag = true;
            Room room = randomSizeOfRoom(startPoint);
            while (!checkLegalRoom(room)) {
                room = randomSizeOfRoom(startPoint);
                count += 1;
                if (count == LOOP_AMOUNT) {
                    legalFlag = false;
                    break;
                }
            }
            if (!legalFlag) {
                break;
            }
            room.updateRightBound();
            Position leftTop = room.getLeftTop();
            addRoom(world, room);
            rooms.add(room);
            //printInfo(room);
            startPoint = getVerticalPosition(leftTop);
        }
    }

    public static void addRooms(TETile[][] world) {
        addVerticalRooms(world);
        for (int i = 0; i < HORIZONTAL_AMOUNT; i += 1) {
            startPoint = getHorizontalPosition();
            addVerticalRooms(world);
        }
    }

}
