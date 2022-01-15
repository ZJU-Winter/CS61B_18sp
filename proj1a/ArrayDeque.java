public class ArrayDeque<Item> {
    private int size;
    private Item[] items;
    public int nextFirst;
    public int nextLast;
    private int totalLength = 8;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public void addFirst(Item item) {
        size += 1;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        if (size == items.length) {
            resizeExpand();
        }
    }

    public void addLast(Item item) {
        size += 1;
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        if (size == items.length) {
            resizeExpand();
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = nextFirst + 1;
        for (int index = 0; index < size; index += 1, i += 1) {
            if (i == totalLength) {
                i = 0;
            }
            System.out.print(items[i] + " ");
        }
    }

    public Item get(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            int temp = nextFirst + 1 + index;
            return items[temp % totalLength];
        }
    }

    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        Item value = items[nextFirst];
        size -= 1;
        if (items.length / size >= 4) {
            resizeDownsize();
        }
        return value;
    }

    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        Item value = items[nextLast];
        size -= 1;
        if (items.length / size >= 4) {
            resizeDownsize();
        }
        return value;
    }

    private void resizeExpand() {
        Item[] a = (Item[]) new Object[totalLength * 2];
        copyArray(a);
        totalLength *= 2;
        nextLast = size;
        nextFirst = totalLength - 1;
    }

    private void resizeDownsize() {
        Item[] a = (Item[]) new Object[totalLength / 2];
        copyArray(a);
        totalLength /= 2;
        nextLast = size;
        nextFirst = totalLength - 1;
    }

    private void copyArray(Item[] a) {
        int i = nextFirst + 1;
        for (int index = 0; index < size; index += 1, i += 1) {
            if (i == totalLength) {
                i = 0;
            }
            a[index] = items[i];
        }
        items = a;
    }

    private int plusOne(int index) {
        int temp = index + 1;
        if (temp == totalLength)
            temp = 0;
        return temp;
    }

    private int minusOne(int index) {
        int temp = index - 1;
        if (temp < 0)
            temp = totalLength - 1;
        return temp;
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> Deque = new ArrayDeque();
        for (int i = 0; i < 24; i++) {
            Deque.addFirst(i);
        }
        for (int i = 0; i < 20; i++) {
            Deque.removeLast();
        }
        Deque.addFirst(1);
        Deque.addLast(2);
        System.out.println(Deque.get(1));
    }
}
