import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fast {
    public static void main(String[] args) throws IOException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        Point[] points = Fast.readPoints(args[0]);
        for (Point point : points) {
            point.draw();
        }
        drawLines(points);
    }

    private static List<List<Point>> drawLines(Point[] points) {
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        List<List<Point>> result = new ArrayList<List<Point>>();
        Set<Line> lines = new HashSet<Line>();
        if (points.length < 4) {
            return result;
        }
        for (Point basicPoint : points) {
            List<Point> segment = new ArrayList<Point>();
            segment.add(basicPoint);
            Arrays.sort(pointsCopy, basicPoint.SLOPE_ORDER);
            Double slope1 = basicPoint.slopeTo(pointsCopy[1]);
            for (int i = 2; i < pointsCopy.length; i++) {
                Double slope2 = basicPoint.slopeTo(pointsCopy[i]);
                segment.add(pointsCopy[i - 1]);
                if (!slope1.equals(slope2) || slope1.equals(Double.NEGATIVE_INFINITY) || points[i - 1].compareTo(points[i]) == 0) {
                    if (segment.size() > 3) {
                        lines.add(createLine(segment));
                    }
                    segment = new ArrayList<Point>();
                    segment.add(basicPoint);

                }
                if (i == pointsCopy.length - 1) {
                    segment.add(pointsCopy[i]);
                    if (segment.size() > 3) {
                        lines.add(createLine(segment));
                    }
                }
                slope1 = slope2;
            }

        }
        for (Line line : lines) {
            List<Point> pointList = line.allPoints;
            result.add(pointList);
            System.out.println(printLinePoints(pointList));
            pointList.get(0).drawTo(pointList.get(pointList.size() - 1));
        }
        return result;
    }

    private static Line createLine(List<Point> segment) {
        Collections.sort(segment);
        return new Line(segment.get(0), segment.get(segment.size() - 1), segment);
    }

    private static Point[] readPoints(String fileName) throws IOException {
        InputStream data = Brute.class.getResourceAsStream(fileName);
        BufferedReader in = null;
        Point[] points;
        try {
            in = new BufferedReader(new InputStreamReader(data));
            int pointsCount = Integer.valueOf(in.readLine().trim());
            points = new Point[pointsCount];
            int rowCounter = 0;
            while (rowCounter < pointsCount) {
                String line = in.readLine().trim();
                if (line.length() > 0) {
                    String[] coordinatesStrings = line.split(" +");
                    Point point = new Point(Integer.valueOf(coordinatesStrings[0]), Integer.valueOf(coordinatesStrings[1]));
                    points[rowCounter] = point;
                    rowCounter++;
                }
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

    private static class Line {
        private Point start;
        private Point end;

        private Line(Point start, Point end, List<Point> allPoints) {
            this.start = start;
            this.end = end;
            this.allPoints = allPoints;
        }

        private List<Point> allPoints;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Line)) return false;

            Line line = (Line) o;

            return start.compareTo(line.start) == 0 && end.compareTo(line.end) == 0;

        }

        @Override
        public int hashCode() {
            int result = start.hashCode();
            result = 31 * result + end.hashCode();
            return result;
        }
    }
}
