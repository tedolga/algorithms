import java.util.Comparator;

import org.junit.Test;

import junit.framework.Assert;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class PointTest {
    @Test
    public void testSlopeTo() throws Exception {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 0);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, point1.slopeTo(point2));
        Point point3 = new Point(0, 0);
        Point point4 = new Point(1, 0);
        Assert.assertEquals(0.0, point3.slopeTo(point4));
        Point point5 = new Point(1, 0);
        Point point6 = new Point(0, 0);
        Assert.assertEquals(0.0, point5.slopeTo(point6));
        Point point7 = new Point(0, 1);
        Point point8 = new Point(0, 0);
        Assert.assertEquals(Double.POSITIVE_INFINITY, point7.slopeTo(point8));
        Point point9 = new Point(0, 0);
        Point point10 = new Point(0, 1);
        Assert.assertEquals(Double.POSITIVE_INFINITY, point9.slopeTo(point10));

        Point point11 = new Point(0, 0);
        Point point12 = new Point(1, 1);
        Assert.assertEquals(1.0, point11.slopeTo(point12));

        Point point13 = new Point(1, 1);
        Point point14 = new Point(2, 0);
        Assert.assertEquals(-1.0, point13.slopeTo(point14));
    }

    @Test
    public void testCompareTo() throws Exception {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 0);
        Assert.assertTrue(point1.compareTo(point2) == 0);
        Point point3 = new Point(0, 0);
        Point point4 = new Point(0, 1);
        Assert.assertTrue(point3.compareTo(point4) < 0);
        Point point5 = new Point(6, 0);
        Point point6 = new Point(0, 1);
        Assert.assertTrue(point5.compareTo(point6) < 0);
        Point point7 = new Point(0, 1);
        Point point8 = new Point(1, 1);
        Assert.assertTrue(point7.compareTo(point8) < 0);
        Point point9 = new Point(1, 1);
        Point point10 = new Point(0, 1);
        Assert.assertTrue(point9.compareTo(point10) == 0);
        Point point11 = new Point(0, 2);
        Point point12 = new Point(1, 1);
        Assert.assertTrue(point11.compareTo(point12) > 0);
        Point point13 = new Point(1, 2);
        Point point14 = new Point(0, 1);
        Assert.assertTrue(point13.compareTo(point14) > 0);
        Point point15 = new Point(0, 2);
        Point point16 = new Point(0, 1);
        Assert.assertTrue(point15.compareTo(point16) > 0);
    }

    @Test
    public void testSlopeComparator() throws Exception {
        Point point1 = new Point(0, 0);
        Comparator<Point> comparator = point1.SLOPE_ORDER;
        Point point2 = new Point(2, 0);
        Point point3 = new Point(3, 0);
        Assert.assertEquals(0, comparator.compare(point2, point3));
        Point point4 = new Point(3, -1);
        Assert.assertEquals(1, comparator.compare(point2, point4));
        Assert.assertEquals(-1, comparator.compare(point4, point2));
    }
}
