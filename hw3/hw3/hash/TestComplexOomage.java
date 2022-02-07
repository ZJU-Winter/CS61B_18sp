package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();
        int N = StdRandom.uniform(100);
        List<Integer> deadlyParameter = deadlyParameter();
        for (int i = 0; i < N; i += 1) {
            ComplexOomage co = new ComplexOomage(deadlyParameter);
            deadlyList.add(co);
        }
        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    private List deadlyParameter() {
        int N = StdRandom.uniform(4, 10);
        List<Integer> deadlyList = new ArrayList<>(12);
        for(int i = 0; i < 4; i += 1) {
            deadlyList.add(250 + i);
        }
        for (int i = 4; i < N; i += 1) {
            deadlyList.add(StdRandom.uniform(255));
        }
        return deadlyList;
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
