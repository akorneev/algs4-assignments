import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPercolation {
    @Test
    public void testOpen1x1closedGrid() {
        Percolation p = new Percolation(1);
        assertFalse(p.isOpen(1, 1));
        p.open(1, 1);
        assertTrue(p.isOpen(1, 1));
    }

    @Test
    public void testPercolate1x1closedGrid() {
        Percolation p = new Percolation(1);
        assertFalse(p.percolates());
    }

    @Test
    public void testPercolate1x1openedGrid() {
        Percolation p = new Percolation(1);
        p.open(1, 1);
        assertTrue(p.percolates());
    }
}
