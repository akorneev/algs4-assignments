import java.util.Comparator;

public class Point implements Comparable<Point> {
    /**
     * Compares points by slope to this point.
     */
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        public int compare(Point o1, Point o2) {
            throw new UnsupportedOperationException("Not implemented.");
        }
    };
    private final int x;
    private final int y;


    /**
     * Constructs the point (x, y).
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draw the line segment from this point to that point.
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        if (this.y == that.y) {
            if (this.x == that.x) return 0;
            else if (this.x < that.x) return -1;
            else return 1;
        } else if (this.y < that.y) return -1;
        else return 1;
    }

    /**
     * Returns the slope between this point and that point.
     */
    public double slopeTo(Point that) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}