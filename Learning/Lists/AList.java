public class AList<Item> {
    private Item[] items;
    private int size;
    private int RFACTOR = 2;

    /** create an empty Array List */
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    private void resizeList(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** insert x into the back of the List */
    public void addLast(Item x) {
        if (size == items.length) {
            resizeList(size * RFACTOR);// unusably bad
        }
        items[size] = x;
        size++;
    }

    /** return the item from the back of the list */
    public Item getLast() {
        return items[size - 1];
    }

    /** get the ith item from the list */
    public Item get(int i) {
        return items[i];
    }

    /** return the number of items in the list */
    public int size() {
        return size;
    }

    /**
     * delete the last item of the list
     * return the value of the deleted item
     */

    public Item removeLast() {
        Item value = getLast();
        size = size - 1;
        return value;
    }
}