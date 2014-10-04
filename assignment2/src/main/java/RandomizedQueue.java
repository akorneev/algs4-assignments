import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] buffer = (Item[]) new Object[1];
    private int head = -1;
    private int tail = 0;

    /**
     * Constructs an empty randomized queue.
     */
    public RandomizedQueue() {
    }

    public static void main(String[] args) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty() {
        return head == -1;
    }

    /**
     * Returns the size of the queue.
     *
     * @return a number of items on the queue
     */
    public int size() {
        if (isEmpty()) return 0;
        else if (tail >= head) return tail - head;
        else return tail + buffer.length - head;
    }

    /**
     * Adds the item.
     *
     * @param item item to add
     */
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("`item` is null.");
        if (head == -1) head = 0;
        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        if (tail == head) { // grow
            Item[] oldBuffer = buffer;
            buffer = (Item[]) new Object[oldBuffer.length * 2];
            System.arraycopy(oldBuffer, 0, buffer, 0, oldBuffer.length);
            head = 0;
            tail = oldBuffer.length;
        }
    }

    /**
     * Removes a random item.
     *
     * @return removed item
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue.");
        int rnd = StdRandom.uniform(size());
        swap(buffer, head, (head + rnd) % buffer.length);
        Item item = buffer[head];
        buffer[head] = null; // release reference
        head = (head + 1) % buffer.length;
        if (head == tail) { // empty queue
            head = -1;
            tail = 0;
        } else if (size() < buffer.length / 2) { //shrink
            Item[] newBuffer = (Item[]) new Object[size() + 1];
            copyItems(newBuffer);
            buffer = newBuffer;
            head = 0;
            tail = buffer.length - 1;
        }
        return item;
    }

    /**
     * Returns a random item.
     *
     * @return random item
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue.");
        int idx = StdRandom.uniform(size());
        return buffer[(head + idx) % buffer.length];
    }

    /**
     * Return an independent iterator over items in random order.
     *
     * @return iterator over items
     */
    public Iterator<Item> iterator() {
        return new ItemsIterator();
    }

    private void copyItems(Item[] to) {
        if (isEmpty()) {
            return;
        } else if (tail > head) {
            System.arraycopy(buffer, head, to, 0, size());
        } else {
            System.arraycopy(buffer, 0, to, 0, tail);
            System.arraycopy(buffer, head, to, tail, buffer.length - head);
        }
    }

    private void swap(Item[] arr, int i, int j) {
        Item tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private final class ItemsIterator implements Iterator<Item> {
        private Item[] items;
        private int last;

        public ItemsIterator() {
            items = (Item[]) new Object[size()];
            copyItems(items);
            last = items.length - 1;
        }

        @Override
        public boolean hasNext() {
            return last >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Empty iterator.");
            swap(items, StdRandom.uniform(last + 1), last);
            Item item = items[last];
            items[last] = null;
            --last;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }
}
