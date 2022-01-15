public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        Node(Node p, T i, Node n) {
            this.prev = p;
            this.item = i;
            this.next = n;
        }

        Node() {
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

    public LinkedListDeque(T item) {
        size = 1;
        sentinel = new Node();
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
    }

    public void addFirst(T item) {
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

    public void addLast(T item) {
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

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T value = sentinel.next.item;
        Node reminderOfOriginal = sentinel.next.next;
        sentinel.next = reminderOfOriginal;
        reminderOfOriginal.prev = sentinel;
        return value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T value = sentinel.prev.item;
        Node reminderOfOriginal = sentinel.prev.prev;
        sentinel.prev = reminderOfOriginal;
        reminderOfOriginal.next = sentinel;
        return value;
    }

    public T get(int index) {
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

    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            return getRecursiveHelper(sentinel.next, index);
        }
    }

    private T getRecursiveHelper(Node ptr, int index) {
        if (ptr == null) {
            return null;
        } else if (index == 0) {
            return ptr.item;
        } else {
            return getRecursiveHelper(ptr.next, index - 1);
        }
    }

    /*
    public static void main(String[] args) {
        LinkedListDeque<Integer> Deque = new LinkedListDeque<Integer>(2);
        Deque.addLast(3);
        Deque.addLast(4);
        Deque.removeLast();
    }
    */
}
