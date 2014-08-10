import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tedolga
 * Date: 09.08.14
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class BruteTest {
    @Test
    public void testDrawLines() throws Exception {
        Point[] points = Brute.readPoints("test1-brute-lines.txt");
        List<List<Point>> lines = Brute.drawLines(points);
        Assert.assertEquals(0, lines.size());
        points = Brute.readPoints("test2-brute-lines.txt");
        lines = Brute.drawLines(points);
        Assert.assertEquals(0, lines.size());
        points = Brute.readPoints("test3-brute-lines.txt");
        lines = Brute.drawLines(points);
        Assert.assertEquals(0, lines.size());
        points = Brute.readPoints("test4-brute-lines.txt");
        lines = Brute.drawLines(points);
        Assert.assertEquals(1, lines.size());
        points = Brute.readPoints("test5-brute-lines.txt");
        lines = Brute.drawLines(points);
        Assert.assertEquals(1, lines.size());
        points = Brute.readPoints("test6-brute-lines.txt");
        lines = Brute.drawLines(points);
        Assert.assertEquals(5, lines.size());
        points = Brute.readPoints("test7-brute-lines.txt");
        lines = Brute.drawLines(points);
        Assert.assertEquals(2, lines.size());
        points = Brute.readPoints("test8-brute-lines.txt");
        lines = Brute.drawLines(points);
        Assert.assertEquals(6, lines.size());
    }
}
