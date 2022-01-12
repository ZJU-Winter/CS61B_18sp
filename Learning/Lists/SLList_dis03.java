import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.swing.plaf.ColorUIResource;

public class SLList_dis03 {
    private class IntNode {
        int item;
        IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public SLList_dis03() {
        first = new IntNode(66, null);
    }

    public SLList_dis03(int x) {
        first = new IntNode(x, null);
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }
        IntNode currentNode = first;
        /** find the node before the position */
        for (int i = 0; i < position - 1; i += 1) {
            currentNode = currentNode.next;
        }
        IntNode newNode = new IntNode(item, currentNode.next);
        currentNode.next = newNode;
    }

    public void reverse() {
        IntNode frontofReversed = null;
        IntNode reminderofOriginal = null;
        IntNode nextNodeToAdd = first;
        while (nextNodeToAdd != null) {
            reminderofOriginal = nextNodeToAdd.next;
            nextNodeToAdd.next = frontofReversed;
            frontofReversed = nextNodeToAdd;
            nextNodeToAdd = reminderofOriginal;
        }
        first = frontofReversed;
    }

    public void reverse_recur() {
        first = reverseRecursiveHelper(first);
    }

    private IntNode reverseRecursiveHelper(IntNode front) {
        if (front == null || front.next == null) {
            return front;
        } else {
            IntNode reversed = reverseRecursiveHelper(front.next);
            front.next.next = front;
            front.next = null;
            return reversed;
        }
    }

    public static void main(String[] args) {
        SLList_dis03 L = new SLList_dis03(2);
        L.addFirst(6);
        L.addFirst(5);
        L.insert(10, 1);
        L.reverse();
    }
}