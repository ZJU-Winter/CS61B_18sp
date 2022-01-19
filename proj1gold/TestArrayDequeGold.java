import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> studentDequeForTest = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> dequeSolutionForTest = new ArrayDequeSolution<>();
    Integer expectedRemovedLast, expectedRemovedFirst;
    Integer actualRemovedLast, actualRemovedFirst;
    String msg = "";
    @Test
    public void test () {
        for(int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                studentDequeForTest.addFirst(i);
                dequeSolutionForTest.addFirst(i);
                msg += "addFirst"+'(' + i + ')' + '\n';
            } else if (numberBetweenZeroAndOne < 0.5) {
                studentDequeForTest.addLast(i);
                dequeSolutionForTest.addLast(i);
                msg += "addLast"+'(' + i + ')' + '\n';
            } else if (numberBetweenZeroAndOne < 0.75) {
                if(!studentDequeForTest.isEmpty() && !dequeSolutionForTest.isEmpty()) {
                    actualRemovedFirst = studentDequeForTest.removeFirst();
                    expectedRemovedFirst = dequeSolutionForTest.removeFirst();
                    msg += "removeFirst()\n";
                    assertEquals(msg ,expectedRemovedFirst,actualRemovedFirst);
                }
            } else {
                    if(!studentDequeForTest.isEmpty() && !dequeSolutionForTest.isEmpty()) {
                        actualRemovedLast = studentDequeForTest.removeLast();
                        expectedRemovedLast = dequeSolutionForTest.removeLast();
                        msg += "removeLast()\n";
                        assertEquals(msg ,expectedRemovedLast,actualRemovedLast);
                }
            }
        }
    }
}
