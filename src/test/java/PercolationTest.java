import junit.framework.Assert;
import org.junit.Test;

public class PercolationTest {

    private Percolation percolation;

    @Test
    public void testInitiation() {
        try {
            percolation = new Percolation(0);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException iae) {
            Assert.assertTrue(true);
        }
        try {
            percolation = new Percolation(-1);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException iae) {
            Assert.assertTrue(true);
        }
        percolation = new Percolation(4722);
        try {
            percolation = new Percolation(46341);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println(Math.ceil(Math.sqrt((double) (Integer.MAX_VALUE - 2))));
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void testOpen() throws Exception {
        percolation = new Percolation(4);
        Assert.assertFalse(percolation.isOpen(1, 1));
        Assert.assertFalse(percolation.isOpen(1, 4));
        Assert.assertFalse(percolation.isOpen(2, 3));
        checkOpenning(1, 1);
        checkOpenning(1, 3);
        checkOpenning(1, 4);
        checkOpenning(2, 1);
        checkOpenning(2, 2);
        checkOpenning(2, 4);
        checkOpenning(4, 1);
        checkOpenning(4, 3);
        checkOpenning(4, 4);
        checkOpenningFail(0, 1);
        checkOpenningFail(5, 1);
        checkOpenningFail(1, 0);
        checkOpenningFail(1, 5);
    }

    @Test
    public void testIsOpen() {
        percolation = new Percolation(5);
        checkIsOpenFail(0, 2);
        checkIsOpenFail(6, 2);
        checkIsOpenFail(2, 0);
        checkIsOpenFail(2, 6);
    }

    private void checkIsOpenFail(int i, int j) {
        try {
            percolation.isOpen(i, j);
            Assert.assertTrue(false);
        } catch (IndexOutOfBoundsException ioobe) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIsFull() throws Exception {
        percolation = new Percolation(4);
        Assert.assertFalse(percolation.isFull(1, 1));
        percolation.open(1, 1);
        Assert.assertTrue(percolation.isFull(1, 1));
        percolation.open(2, 4);
        Assert.assertFalse(percolation.isFull(2, 4));
        percolation.open(2, 2);
        Assert.assertFalse(percolation.isFull(2, 2));
        percolation.open(1, 2);
        percolation.open(2, 3);
        Assert.assertTrue(percolation.isFull(2, 4));
        percolation.open(3, 4);
        percolation.open(4, 4);
        Assert.assertTrue(percolation.isFull(4, 4));
    }

    @Test
    public void testPercolates() throws Exception {
        percolation = new Percolation(1);
        Assert.assertFalse(percolation.percolates());
        percolation.open(1, 1);
        Assert.assertTrue(percolation.percolates());
        percolation = new Percolation(4);
        percolation.open(1, 1);
        Assert.assertFalse(percolation.percolates());
        percolation.open(4, 4);
        Assert.assertFalse(percolation.percolates());
        percolation.open(2, 2);
        percolation.open(3, 3);
        Assert.assertFalse(percolation.percolates());
        percolation.open(1, 2);
        percolation.open(2, 3);
        Assert.assertFalse(percolation.percolates());
        percolation.open(3, 4);
        Assert.assertTrue(percolation.percolates());
    }

    private void checkOpenningFail(int i, int j) {
        try {
            percolation.open(i, j);
            Assert.assertTrue(false);
        } catch (IndexOutOfBoundsException iobe) {
            Assert.assertTrue(true);
        }
    }

    private void checkOpenning(int i, int j) {
        percolation.open(i, j);
        Assert.assertTrue(percolation.isOpen(i, j));
    }
}
