import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

import static org.junit.Assert.*;
public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     */
    private static <Item extends Comparable> Queue<Queue<Item>>
    makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> rst = new Queue<>();
        for (Item item : items) {
            Queue<Item> singleItem = new Queue<>();
            singleItem.enqueue(item);
            rst.enqueue(singleItem);
        }
        return rst;
    }


    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * <p>
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least to
     * greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> rst = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            rst.enqueue(getMin(q1, q2));
        }
        return rst;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.isEmpty()) {
            return items;
        }
        Queue<Queue<Item>> singleItemQueue = makeSingleItemQueues(items);
        Queue<Item> rst = new Queue<>();
        Queue<Item> prev = singleItemQueue.dequeue();
        while (!singleItemQueue.isEmpty()) {
            rst = mergeSortedQueues(prev, singleItemQueue.dequeue());
            prev = rst;
        }
        return rst;
    }


    @Test
    public void testSingleItem() {
        Queue<String> students = new Queue<>();
        Queue<Queue<String>> actual;
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        actual = makeSingleItemQueues(students);
        while (!students.isEmpty()) {
            assertEquals(students.dequeue(), actual.dequeue().dequeue());
        }
    }

    @Test
    public void testMergeSortedQueues() {
        Queue<Integer> q1 = new Queue<>();
        Queue<Integer> q2 = new Queue<>();
        for (int i = 0; i < 10; i += 2) {
            q1.enqueue(i);
            q2.enqueue(i + 1);
        }
        Queue<Integer> rst = new Queue<>();
        rst = mergeSortedQueues(q1, q2);
        for (int i = 0; i < 10; i += 1) {
            assertEquals((Integer) i, rst.dequeue());
        }
    }

    public static void main(String[] args) {
        Queue<Integer> nums = new Queue<>();
        Queue<Integer> rst = new Queue<>();
        for (int i = 10; i > 0; i -= 1) {
            nums.enqueue(i);
        }
        System.out.println("The original Queue Before sorted: " + nums);
        rst = mergeSort(nums);
        System.out.println("The original Queue after sorted: " + nums);
        System.out.println("The sorted Queue: " + rst);
        for (int i = 1; i <= 10; i += 1) {
            assertEquals((Integer) i, rst.dequeue());
        }
    }
}
