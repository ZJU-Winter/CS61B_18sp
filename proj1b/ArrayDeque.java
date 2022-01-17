public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int totalLength = 8;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public void addFirst(T t) {
        size += 1;
        items[nextFirst] = t;
        nextFirst = minusOne(nextFirst);
        if (size == items.length) {
            resizeExpand();
        }
    }

    @Override
    public void addLast(T t) {
        size += 1;
        items[nextLast] = t;
        nextLast = plusOne(nextLast);
        if (size == items.length) {
            resizeExpand();
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i = nextFirst + 1;
        for (int index = 0; index < size; index += 1, i += 1) {
            if (i == totalLength) {
                i = 0;
            }
            System.out.print(items[i] + " ");
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            int temp = nextFirst + 1 + index;
            return items[temp % totalLength];
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T value = items[nextFirst];
        size -= 1;
        if (!isEmpty() && items.length / size >= 4) {
            resizeDownsize();
        }
        return value;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T value = items[nextLast];
        size -= 1;
        if (!isEmpty() && items.length / size >= 4) {
            resizeDownsize();
        }
        return value;
    }

    private void resizeExpand() {
        T[] a = (T[]) new Object[totalLength * 2];
        copyArray(a);
        totalLength *= 2;
        nextLast = size;
        nextFirst = totalLength - 1;
    }

    private void resizeDownsize() {
        T[] a = (T[]) new Object[totalLength / 2];
        copyArray(a);
        totalLength /= 2;
        nextLast = size;
        nextFirst = totalLength - 1;
    }

    private void copyArray(T[] a) {
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
        if (temp == totalLength) {
            temp = 0;
        }
        return temp;
    }

    private int minusOne(int index) {
        int temp = index - 1;
        if (temp < 0) {
            temp = totalLength - 1;
        }
        return temp;
    }

    /*
    public static void main(String[] args) {
        ArrayDeque<Integer> Deque = new ArrayDeque();
        Deque.addFirst(0);
        Deque.addFirst(1);
        System.out.println(Deque.removeFirst());
        System.out.println(Deque.removeFirst());
    }*/

}

