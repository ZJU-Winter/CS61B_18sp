package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    public Iterator<T> iterator() {
        return new bufferIterator();
    }

    private class bufferIterator implements Iterator<T> {
        int position;

        bufferIterator() {
            position = first;
        }

        @Override
        public boolean hasNext() {
            return position < last;
        }

        @Override
        public T next() {
            T value = rb[position];
            position += 1;
            return value;
        }

    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T value = rb[first];
        first = plusOne(first);
        fillCount -= 1;
        return value;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        return rb[first];
    }

    private int plusOne(int index) {
        int temp = index + 1;
        if (temp == capacity) {
            temp = 0;
        }
        return temp;
    }

    public static void main(String[] args) {
        ArrayRingBuffer<Double> rb = new ArrayRingBuffer<>(4);
        for (int i = 0; i < 4; i += 1) {
            rb.enqueue((double) i);
        }
        for (int i = 0; i < 4; i += 1) {
            System.out.println(rb.dequeue());
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
