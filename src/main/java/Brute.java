import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class Brute {
    public static void main(String[] args) throws IOException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        Point[] points = Brute.readPoints(args[0]);
        for (Point point : points) {
            point.draw();
        }
        drawLines(points);
    }

    protected static List<List<Point>> drawLines(Point[] points) {
        List<List<Point>> lines = new ArrayList<List<Point>>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Double slope1 = points[i].slopeTo(points[j]);
                        Double slope2 = points[j].slopeTo(points[k]);
                        Double slope3 = points[i].slopeTo(points[k]);
                        Double slope4 = points[k].slopeTo(points[l]);
                        if (slope1.equals(slope2) && slope2.equals(slope3) && slope3.equals(slope4)
                                && !slope1.equals(Double.NEGATIVE_INFINITY)) {
                            List<Point> linePoints = new ArrayList<Point>();
                            linePoints.add(points[i]);
                            linePoints.add(points[j]);
                            linePoints.add(points[k]);
                            linePoints.add(points[l]);
                            Collections.sort(linePoints);
                            System.out.println(printLinePoints(linePoints));
                            linePoints.get(0).drawTo(linePoints.get(3));
                            lines.add(linePoints);
                        }
                    }
                }
            }
        }
        return lines;
    }

    protected static Point[] readPoints(String fileName) throws IOException {
        InputStream data = Brute.class.getResourceAsStream(fileName);
        BufferedReader in = null;
        Point[] points;
        try {
            in = new BufferedReader(new InputStreamReader(data));
            int pointsCount = Integer.valueOf(in.readLine());
            points = new Point[pointsCount];
            int rowCounter = 0;
            while (rowCounter < pointsCount) {
                String[] coordinatesStrings = in.readLine().split(" +");
                Point point = new Point(Integer.valueOf(coordinatesStrings[0]), Integer.valueOf(coordinatesStrings[1]));
                points[rowCounter] = point;
                rowCounter++;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return points;
    }

    private static String printLinePoints(List<Point> linePoints) {
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (Point linePoint : linePoints) {
            counter++;
            stringBuilder.append(linePoint.toString());
            if (counter < linePoints.size()) {
                stringBuilder.append(" -> ");
            }
        }
        return stringBuilder.toString();
    }
}
