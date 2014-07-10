import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class RandomizedQueueTest {
    private RandomizedQueue<Integer> randomizedQueue;

    @Before
    public void setUp() {
        randomizedQueue = new RandomizedQueue<Integer>();
    }

    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertTrue(randomizedQueue.isEmpty());
        randomizedQueue.enqueue(0);
        Assert.assertFalse(randomizedQueue.isEmpty());
        randomizedQueue.dequeue();
        Assert.assertTrue(randomizedQueue.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(0, randomizedQueue.size());
        randomizedQueue.enqueue(0);
        randomizedQueue.enqueue(2);
        Assert.assertEquals(2, randomizedQueue.size());
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        Assert.assertEquals(0, randomizedQueue.size());
    }

    @Test
    public void testEnqueue() throws Exception {
        try {
            randomizedQueue.enqueue(null);
            Assert.assertTrue(false);
        } catch (NullPointerException npe) {
            Assert.assertEquals("Null value cannot be inserted.", npe.getMessage());
        }
        List<Integer> checkList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i);
        }
        Assert.assertEquals(10, randomizedQueue.size());
        for (int i = 0; i < 10; i++) {
            checkList.add(randomizedQueue.dequeue());
        }
        for (int i = 0; i < 10; i++) {
            Assert.assertTrue("List doesn't contain " + i, checkList.contains(i));
        }
        Assert.assertEquals(0, randomizedQueue.size());
    }

    @Test
    public void testDequeue() throws Exception {
        try {
            randomizedQueue.dequeue();
            Assert.assertTrue(false);
        } catch (NoSuchElementException nsee) {
            Assert.assertEquals("Deque is empty, cannot return any element.", nsee.getMessage());
        }
    }

    @Test
    public void testSample() throws Exception {
        try {
            randomizedQueue.sample();
            Assert.assertTrue(false);
        } catch (NoSuchElementException nsee) {
            Assert.assertEquals("Deque is empty, cannot return any element.", nsee.getMessage());
        }
        List<Integer> checkList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i);
            checkList.add(i);
        }
        for (int i = 0; i < 10; i++) {
            Integer sample = randomizedQueue.sample();
            Assert.assertTrue("Check list should not contain " + sample, checkList.contains(sample));
        }
        Assert.assertEquals(10, randomizedQueue.size());
    }

    @Test
    public void testIterator() throws Exception {
        Iterator<Integer> iterator1 = randomizedQueue.iterator();
        Assert.assertFalse(iterator1.hasNext());
        try {
            iterator1.next();
            Assert.assertFalse(true);
        } catch (NoSuchElementException nsee) {
            Assert.assertEquals("No element to return.", nsee.getMessage());
        }
        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i);
        }
        iterator1 = randomizedQueue.iterator();
        List<Integer> checkList = new ArrayList<Integer>();
        while (iterator1.hasNext()) {
            Integer next = iterator1.next();
            checkList.add(next);
            System.out.println(next);
        }
        Assert.assertEquals(10, randomizedQueue.size());
        Assert.assertEquals(10, checkList.size());
        for (int i = 0; i < 10; i++) {
            Assert.assertTrue("List doesn't contain " + i, checkList.contains(i));
        }
        try {
            iterator1.next();
            Assert.assertFalse(true);
        } catch (NoSuchElementException nsee) {
            Assert.assertEquals("No element to return.", nsee.getMessage());
        }
        Iterator<Integer> iterator2 = randomizedQueue.iterator();
        try {
            iterator2.remove();
            Assert.assertTrue(false);
        } catch (UnsupportedOperationException unsoe) {
            Assert.assertEquals("Operation is not supported.", unsoe.getMessage());
        }
        Assert.assertFalse(iterator1.hasNext());
        checkList = new ArrayList<Integer>();
        System.out.println("------------------------------------------------------------------");
        while (iterator2.hasNext()) {
            Integer next = iterator2.next();
            checkList.add(next);
            System.out.println(next);
        }
        Assert.assertEquals(10, checkList.size());
    }
}
