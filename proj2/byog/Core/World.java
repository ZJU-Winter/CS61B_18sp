package byog.Core;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class World {
    static final int WIDTH = 80;
    static final int HEIGHT = 40;
    static final long SEED = 2873123;
    static final long SEED2 = 10001212;
    static final Random RANDOM = new Random(SEED);
    public static List<Room> rooms = new LinkedList<>();

}
