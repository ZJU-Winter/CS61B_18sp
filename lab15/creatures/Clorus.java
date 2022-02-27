package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        b = 231;
        g = 0;
        return color(r, g, b);
    }

    public void move() {
        energy -= 0.03;
    }

    public void stay() {
        energy -= 0.01;
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public Clorus replicate() {
        energy = energy / 2;
        return new Clorus(energy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> emptyDirections = getNeighborsOfType(neighbors, "empty");
        List<Direction> plipsDirections = getNeighborsOfType(neighbors, "plip");
        if (emptyDirections.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (!plipsDirections.isEmpty()) {
            int plipsRandom = HugLifeUtils.randomInt(plipsDirections.size() - 1);
            return new Action(Action.ActionType.ATTACK, plipsDirections.get(plipsRandom));
        } else if (energy >= 1) {
            int emptyRandom = HugLifeUtils.randomInt(emptyDirections.size() - 1);
            return new Action(Action.ActionType.REPLICATE, emptyDirections.get(emptyRandom));
        } else {
            int emptyRandom = HugLifeUtils.randomInt(emptyDirections.size() - 1);
            return new Action(Action.ActionType.MOVE, emptyDirections.get(emptyRandom));
        }
    }
}
