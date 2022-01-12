package Learning.Lists;

public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        this.first = f;
        this.rest = r;
    }

    public int size() {
        if (this.rest == null)
            return 1;
        else
            return 1 + this.rest.size();
    }

    public int size_iter() {
        IntList p = this;
        int size = 0;
        while (p != null) {
            size++;
            p = p.rest;
        }
        return size;
    }

    public int get(int i) {
        if (i == 0)
            return first;
        else
            return rest.get(i - 1);
    }

    public int get_iterative(int i) {
        if (i > this.size())
            return -1;
        IntList p = this;
        for (int index = 0; index < i; index++) {
            p = p.rest;
        }
        return p.first;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L = new IntList(10, L);
        L = new IntList(15, L);
        System.out.println("size of the List is:" + L.size());
        System.out.println("size of the List is using itera:" + L.size_iter());
        System.out.println("the third element of the list is " + L.get(2));
    }
}
