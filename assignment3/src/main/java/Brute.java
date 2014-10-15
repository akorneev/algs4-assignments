import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Brute {
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

    private static Iterable<Point[]> findLines(Point[] points) {
        List<Point[]> lines = new ArrayList<>();
        for (int i = 0; i < points.length - 3; i++) {
            Point p0 = points[i];
            for (int j = i + 1; j < points.length - 2; j++) {
                Point p1 = points[j];
                for (int k = j + 1; k < points.length - 1; k++) {
                    Point p2 = points[k];
                    for (int l = k + 1; l < points.length; l++) {
                        Point p3 = points[l];
                        if (p0.slopeTo(p1) == p1.slopeTo(p2) && p1.slopeTo(p2) == p2.slopeTo(p3)) {
                            Point[] line = new Point[]{p0, p1, p2, p3};
                            Arrays.sort(line);
                            lines.add(line);
                        }
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