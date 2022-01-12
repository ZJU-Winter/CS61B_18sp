public class ArrayList {
    private int[] items;
    private int size;

    /** create an empty Array List */
    public ArrayList() {
        items = new int[100];
        size = 0;
    }

    /** insert x into the back of the List */
    public void addLast(int x) {
        items[size] = x;
        size++;
    }

    /** return the item from the back of the list */
    public int getLast() {
        return items[size - 1];
    }

    /** get the ith item from the list */
    public int get(int i) {
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

    public int removeLast() {
        int value = getLast();
        size = size - 1;
        return value;
    }

    public static void main(String[] args) {
        ArrayList L = new ArrayList();
    }
}
