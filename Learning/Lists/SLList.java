public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /* the first item (if it exsits) is at sentinel.next */
    private IntNode sentinel;
    private int size;

    public SLList(int x) {
        sentinel = new IntNode(-1, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public SLList() {
        sentinel = new IntNode(-1, null);
        size = 0;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int getFirst() {
        return sentinel.next.item;
    }

    public void addLast(int x) {
        size += 1;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    /** return the size of list from IntNode p */
    private static int size(IntNode p) {
        if (p.next == null)
            return 0;
        return 1 + size(p.next);
    }

    public int size_recur() {
        return size(sentinel);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SLList L = new SLList();
        System.out.println(L.size_recur());
    }
}