public class LinkedListDeque<Item> {
    private class Node {
        public Node prev;
        public Item item;
        public Node next;

        public Node(Node p, Item i, Node n) {
            this.prev = p;
            this.item = i;
            this.next = n;
        }

        public Node() {
            this.prev = null;
            this.item = null;
            this.next = null;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(Item item) {
        size = 1;
        sentinel = new Node();
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
    }

    public void addFirst(Item item) {
        size += 1;
        if (size == 0) {
            Node nodeToAdd = new Node(sentinel, item, sentinel);
        } else {
            Node reminderOfOriginal = sentinel.next;
            Node nodeToAdd = new Node(sentinel, item, reminderOfOriginal);
            sentinel.next = nodeToAdd;
            reminderOfOriginal.prev = nodeToAdd;
        }
    }

    public void addLast(Item item) {
        size += 1;
        if (size == 0) {
            Node nodeToAdd = new Node(sentinel, item, sentinel);
        } else {
            Node reminderOfOriginal = sentinel.prev;
            Node nodeToAdd = new Node(reminderOfOriginal, item, sentinel);
            sentinel.prev = nodeToAdd;
            reminderOfOriginal.next = nodeToAdd;
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> Deque = new LinkedListDeque<Integer>(2);
        Deque.addFirst(1);
        Deque.addLast(3);
    }

}
