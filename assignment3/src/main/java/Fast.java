import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fast {
    public static void main(String[] args) {
        Point[] points = read(args[0]);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        drawPoints(points);
        Iterable<Point[]> lines = findLines(points);
        printLines(lines);
        drawLines(lines);
    }

    private static void drawLines(Iterable<Point[]> lines) {
        for (Point[] line : lines) {
            line[0].drawTo(line[line.length - 1]);
        }
    }

    private static void drawPoints(Point[] points) {
        for (Point p : points) {
            p.draw();
        }
    }

    private static boolean isInOrder(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) > 0) return false;
        }
        return true;
    }

    private static Iterable<Point[]> findLines(Point[] points) {
        List<Point[]> lines = new ArrayList<>();
        Arrays.sort(points);
        for (Point p : points) {
            Point[] ps = points.clone();
            Arrays.sort(ps, p.SLOPE_ORDER);
            int i = 0;
            while (i < ps.length - 1) {
                for (int j = i + 1; j <= ps.length; j++) {
                    if (j == ps.length || p.SLOPE_ORDER.compare(ps[j - 1], ps[j]) != 0) {
                        int count = j - i;
                        if (count >= 3) {
                            Point[] line = new Point[count + 1];
                            line[0] = p;
                            System.arraycopy(ps, i, line, 1, count);
                            boolean ordered = isInOrder(line);
                            if (ordered) {
                                lines.add(line);
                            }
                        }
                        i = j;
                    }
                }
            }
        }
        return lines;
    }

    private static Point[] read(String file) {
        In in = new In(file);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }

    private static void printLines(Iterable<Point[]> lines) {
        for (Point[] line : lines) {
            for (int i = 0; i < line.length; i++) {
                StdOut.print(line[i].toString());
                if (i != line.length - 1) {
                    StdOut.print(" -> ");
                }
            }
            StdOut.println();
        }
    }
}