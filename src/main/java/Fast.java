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
    protected static List<List<Point>> drawLines(Point[] points) {
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        List<List<Point>> result = new ArrayList<List<Point>>();
        Set<Line> lines = new HashSet<Line>();
        for (Point basicPoint : points) {
            List<Point> segment = new ArrayList<Point>();
            Arrays.sort(pointsCopy, basicPoint.SLOPE_ORDER);
            Double slope1 = basicPoint.slopeTo(pointsCopy[0]);
            segment.add(basicPoint);
            for (int j = 1; j < pointsCopy.length; j++) {
                Double slope2 = basicPoint.slopeTo(pointsCopy[j]);
                if (slope1.equals(slope2) && !slope1.equals(Double.NEGATIVE_INFINITY)) {
                    segment.add(pointsCopy[j]);
                }
                slope1 = slope2;
            }
            if (segment.size() > 3) {
                Collections.sort(segment);
                Line line = new Line(segment.get(0), segment.get(segment.size() - 1), segment);
                printLinePoints(segment);
                lines.add(line);
            }
        }
        for (Line line : lines) {
            result.add(line.allPoints);
        }
        return result;
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

            if (!start.equals(line.start)) return false;
            if (!end.equals(line.end)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = start.hashCode();
            result = 31 * result + end.hashCode();
            return result;
        }
    }
}
