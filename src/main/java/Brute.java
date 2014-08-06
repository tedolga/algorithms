import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class Brute {
    public static void main(String[] args) {

    }

    protected static void drawLines(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Double slope1 = points[i].slopeTo(points[j]);
                        Double slope2 = points[i].slopeTo(points[k]);
                        Double slope3 = points[i].slopeTo(points[l]);
                        if (slope1.equals(slope2)&&slope2.equals(slope3)) {
                            List<Point> linePoints = new ArrayList<Point>();
                            linePoints.add(points[i]);
                            linePoints.add(points[j]);
                            linePoints.add(points[k]);
                            linePoints.add(points[l]);
                            Collections.sort(linePoints);
                            System.out.println(printLinePoints(linePoints));
                        }
                    }
                }
            }
        }

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
