import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tedolga
 * Date: 10.08.14
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class Fast {
    protected static List<List<Point>> drawLines(Point[] points) {
        List<List<Point>> lines = new ArrayList<List<Point>>();
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        for (int i = 0; i < points.length; i++) {
            List<Point> line=new ArrayList<Point>();
            Point basicPoint = points[i];
            Arrays.sort(pointsCopy, basicPoint.SLOPE_ORDER);
            double slope1 = basicPoint.slopeTo(pointsCopy[0]);
            for (int j = 1; j < pointsCopy.length; j++) {
                double slope2 = basicPoint.slopeTo(pointsCopy[j]);
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

    private class Line {
        private Point point1;
        private Point point2;

        @Override
        public boolean equals(Object o) {

        }

        @Override
        public int hashCode() {

        }
    }
}
