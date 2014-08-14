
import javax.sound.sampled.Line;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import junit.framework.Assert;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class FastTest {
    @Test
    public void testDrawLines() throws Exception {
        Point[] points = Fast.readPoints("test1-brute-lines.txt");
        List<List<Point>> lines = Fast.drawLines(points);
        Assert.assertEquals(0, lines.size());
        points = Fast.readPoints("test2-brute-lines.txt");
        lines = Fast.drawLines(points);
        Assert.assertEquals(0, lines.size());
        points = Fast.readPoints("test3-brute-lines.txt");
        lines = Fast.drawLines(points);
        Assert.assertEquals(0, lines.size());
        points = Fast.readPoints("test4-brute-lines.txt");
        lines = Fast.drawLines(points);
        Assert.assertEquals(1, lines.size());
        points = Fast.readPoints("test5-brute-lines.txt");
        lines = Fast.drawLines(points);
        Assert.assertEquals(1, lines.size());
        points = Fast.readPoints("test6-brute-lines.txt");
        lines = Fast.drawLines(points);
        Assert.assertEquals(1, lines.size());
        points = Fast.readPoints("test7-brute-lines.txt");
        lines = Fast.drawLines(points);
        Assert.assertEquals(2, lines.size());
        points = Fast.readPoints("test8-brute-lines.txt");
        lines = Fast.drawLines(points);
        Assert.assertEquals(2, lines.size());
    }
}
