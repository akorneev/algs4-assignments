import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head = null;
    private Node tail = null;
    /**
     * Constructs an empty deque.
     */
    public Deque() {
    }

    public static void main(String[] args) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    /**
     * Returns whether the deque is empty.
     *
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the size of the deque.
     *
     * @return a number of items on the deque
     */
    public int size() {
        int sz = 0;
        for (Node curr = head; curr != null; curr = curr.next) {
            sz++;
        }
        return sz;
    }

    /**
     * Inserts the item at the front.
     *
     * @param item item to insert
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("`item` is null.");
        }
        head = new Node(item, null, head);
        if (head.next != null) {
            head.next.prev = head;
        }
        if (tail == null) {
            tail = head;
        }
    }

    /**
     * Inserts the item at the end.
     *
     * @param item item to insert
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("`item` is null.");
        }
        tail = new Node(item, tail, null);
        if (tail.prev != null) tail.prev.next = tail;
        if (head == null) {
            head = tail;
        }
    }

    /**
     * Removes the item at the front.
     *
     * @return removed item
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty deque.");
        }
        Node removed = head;
        head = removed.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        return removed.item;
    }

    /**
     * Removes the item at the end.
     *
     * @return removed item
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty deque.");
        }
        Node removed = tail;
        tail = removed.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        return removed.item;
    }

    /**
     * Returns an iterator over items in order from front to end.
     *
     * @return iterator over items
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (current == null) {
                    throw new NoSuchElementException("Empty deque.");
                } else {
                    Item item = current.item;
                    current = current.next;
                    return item;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not implemented.");
            }
        };
    }

    private final class Node {
        private Item item;
        private Node prev;
        private Node next;

        public Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            String p = (prev == null) ? "null" : prev.item.toString();
            String n = (next == null) ? "null" : next.item.toString();
            return p + " <- " + item.toString() + " -> " + n;
        }
    }
}
