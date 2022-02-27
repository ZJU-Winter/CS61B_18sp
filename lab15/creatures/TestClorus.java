package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus clorus = new Clorus(1);
        Plip p = new Plip(2);
        assertEquals(1, clorus.energy(), 0.01);
        assertEquals(new Color(34, 0 , 231), clorus.color());

        clorus.attack(p);
        assertEquals(3, clorus.energy(), 0.01);

        clorus.stay();
        assertEquals(2.99, clorus.energy(), 0.001);

        clorus.move();
        assertEquals(2.96, clorus.energy(), 0.001);

        Clorus offspring = clorus.replicate();
        assertEquals(1.48, offspring.energy(), 0.01);
        assertNotSame(offspring, clorus);
    }

    @Test
    public void testStay() {
        Clorus clorus = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = clorus.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);
    }

    @Test
    public void testAttack() {
        Clorus clorus = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Plip());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = clorus.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);

        assertEquals(expected, actual);
    }

    @Test
    public void testReplicate() {
        Clorus clorus = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = clorus.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.REPLICATE, Direction.LEFT);

        assertEquals(expected, actual);
    }

    @Test
    public void testMove() {
        Clorus clorus = new Clorus(0.5);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = clorus.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.MOVE, Direction.LEFT);

        assertEquals(expected, actual);
    }

}
