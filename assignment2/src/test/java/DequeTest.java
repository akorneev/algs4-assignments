import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class DequeTest {
    public static class EmptyDequeTest {
        @Test
        public void itIsEmpty() {
            Deque<String> deque = new Deque<>();
            assertTrue(deque.isEmpty());
        }

        @Test
        public void itHas0Elements() {
            Deque<String> deque = new Deque<>();
            assertEquals(0, deque.size());
        }

        @Test(expected = NoSuchElementException.class)
        public void removeFirstThrowsException() {
            Deque<String> deque = new Deque<>();
            deque.removeFirst();
        }

        @Test(expected = NoSuchElementException.class)
        public void removeLastThrowsException() {
            Deque<String> deque = new Deque<>();
            deque.removeLast();
        }
    }

    public static class EmptyDequeIteratorTest {
        @Test
        public void itHasNoNextElement() {
            Deque<String> deque = new Deque<>();
            Iterator<String> it = deque.iterator();
            assertFalse(it.hasNext());
        }

        @Test(expected = NoSuchElementException.class)
        public void nextThrowsException() {
            Deque<String> deque = new Deque<>();
            Iterator<String> it = deque.iterator();
            it.next();
        }

        @Test(expected = UnsupportedOperationException.class)
        public void removeThrowsException() {
            Deque<String> deque = new Deque<>();
            Iterator<String> it = deque.iterator();
            it.remove();
        }
    }
}
