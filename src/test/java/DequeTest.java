import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class DequeTest {
    private Deque<Integer> deque;

    @Before
    public void setUp() {
        deque = new Deque<Integer>();
    }

    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertTrue("Deque is not empty as expected.", deque.isEmpty());
        deque.addFirst(0);
        Assert.assertFalse("Deque is empty, but not empty expected", deque.isEmpty());
        deque.removeLast();
        Assert.assertTrue("Deque is not empty as expected.", deque.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(0, deque.size());
        deque.addFirst(0);
        deque.addLast(1);
        Assert.assertEquals(2, deque.size());
        deque.removeFirst();
        Assert.assertEquals(1, deque.size());
        deque.removeFirst();
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void testAddFirst() throws Exception {
        try {
            deque.addFirst(null);
            Assert.assertTrue(false);
        } catch (NullPointerException npe) {
            Assert.assertEquals("Null value cannot be inserted.", npe.getMessage());
        }
        for (int i = 0; i < 5; i++) {
            deque.addFirst(i);
        }
        for (int i = 4; i >= 0; i--) {
            Assert.assertEquals(i, deque.removeFirst().intValue());
        }
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        Assert.assertEquals(3, deque.removeFirst().intValue());
        Assert.assertEquals(0, deque.removeLast().intValue());
        Assert.assertEquals(2, deque.removeFirst().intValue());
        Assert.assertEquals(1, deque.removeLast().intValue());
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testAddLast() throws Exception {
        try {
            deque.addLast(null);
            Assert.assertTrue(false);
        } catch (NullPointerException npe) {
            Assert.assertEquals("Null value cannot be inserted.", npe.getMessage());
        }
        for (int i = 0; i < 5; i++) {
            deque.addLast(i);
        }
        for (int i = 4; i >= 0; i--) {
            Assert.assertEquals(i, deque.removeLast().intValue());
        }
        deque.addLast(0);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        Assert.assertEquals(3, deque.removeLast().intValue());
        Assert.assertEquals(0, deque.removeFirst().intValue());
        Assert.assertEquals(2, deque.removeLast().intValue());
        Assert.assertEquals(1, deque.removeFirst().intValue());
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testRemoveFirst() throws Exception {
        try {
            deque.removeFirst();
            Assert.assertTrue(false);
        } catch (NoSuchElementException nsee) {
            Assert.assertEquals("Deque is empty, cannot remove an element", nsee.getMessage());
        }
        deque.addFirst(0);
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        Assert.assertEquals(2, deque.removeFirst().intValue());
        Assert.assertEquals(0, deque.removeFirst().intValue());
        Assert.assertEquals(1, deque.removeFirst().intValue());
        Assert.assertEquals(3, deque.removeFirst().intValue());
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void testRemoveLast() throws Exception {
        try {
            deque.removeLast();
            Assert.assertTrue(false);
        } catch (NoSuchElementException nsee) {
            Assert.assertEquals("Deque is empty, cannot remove an element", nsee.getMessage());
        }
        deque.addFirst(0);
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        Assert.assertEquals(3, deque.removeLast().intValue());
        Assert.assertEquals(1, deque.removeLast().intValue());
        Assert.assertEquals(0, deque.removeLast().intValue());
        Assert.assertEquals(2, deque.removeLast().intValue());
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void testIterator() throws Exception {
        Iterator<Integer> iterator = deque.iterator();
        Assert.assertFalse(iterator.hasNext());
        for (int i = 0; i < 4; i++) {
            deque.addFirst(i);
        }
        iterator = deque.iterator();
        int counter = 3;
        while (iterator.hasNext()) {
            Assert.assertEquals(counter, iterator.next().intValue());
            counter--;
        }
        Assert.assertEquals(-1, counter);
        try {
            iterator.next();
            Assert.assertTrue(false);
        } catch (NoSuchElementException nsee) {
            Assert.assertEquals("No element to return.", nsee.getMessage());
        }

        try {
            iterator.remove();
            Assert.assertTrue(false);
        } catch (UnsupportedOperationException uoe) {
            Assert.assertEquals("Operation is not supported.", uoe.getMessage());
        }
    }
}
