import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestPercolationStats {
    @Test
    public void testMean() {
        PercolationStats stats = new PercolationStats(100, 50);
        assertTrue(stats.mean() >= 0.55);
        assertTrue(stats.mean() <= 0.65);
    }

    @Test
    public void testStddev() {
        PercolationStats stats = new PercolationStats(100, 50);
        assertTrue(stats.stddev() > 0);
        assertTrue(stats.stddev() <= 0.05);
    }
}
