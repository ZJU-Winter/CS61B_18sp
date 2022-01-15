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

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("It is an empty Deque!");
        }
        Node ptr = sentinel.next;
        while (ptr.item != null) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Item value = sentinel.next.item;
        Node reminderOfOriginal = sentinel.next.next;
        sentinel.next = reminderOfOriginal;
        reminderOfOriginal.prev = sentinel;
        return value;
    }

    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Item value = sentinel.prev.item;
        Node reminderOfOriginal = sentinel.prev.prev;
        sentinel.prev = reminderOfOriginal;
        reminderOfOriginal.next = sentinel;
        return value;
    }

    public Item get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        Node ptr = sentinel.next;
        int i = 0;
        while (i < index) {
            ptr = ptr.next;
            i += 1;
        }
        return ptr.item;
    }

    public Item getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            return getRecursiveHelper(sentinel.next, index);
        }
    }

    private Item getRecursiveHelper(Node ptr, int index) {
        if (ptr == null)
            return null;
        else if (index == 0) {
            return ptr.item;
        } else {
            return getRecursiveHelper(ptr.next, index - 1);
        }
    }
    

}
