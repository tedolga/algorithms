import java.util.Iterator;

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

    }

    @Test
    public void testAddLast() throws Exception {

    }

    @Test
    public void testRemoveFirst() throws Exception {

    }

    @Test
    public void testRemoveLast() throws Exception {

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
    }
}
