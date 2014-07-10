import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        checkNewItem(item);
        if (size + 1 > queue.length) {
            queue = Arrays.copyOf(queue, size * 2);
        }
        queue[size] = item;
        size++;
    }

    public Item dequeue() {
        checkNotEmpty();
        int randomIndex = StdRandom.uniform(size);
        Item itemToReturn = queue[randomIndex];
        queue[randomIndex] = queue[--size];
        queue[size] = null;
        if (size < queue.length / 3) {
            queue = Arrays.copyOf(queue, queue.length / 2);
        }
        return itemToReturn;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty, cannot return any element.");
        }
    }

    public Item sample() {
        checkNotEmpty();
        int randomIndex = StdRandom.uniform(size);
        return queue[randomIndex];
    }


    public static void main(String[] args) {
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int counter = 0;
        private List<Integer> indexes = new ArrayList<Integer>();

        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                indexes.add(i);
            }
            Collections.shuffle(indexes);
        }

        public boolean hasNext() {
            return size != 0 && counter < size;
        }

        public Item next() {
            if (size == 0 || counter >= size) {
                throw new NoSuchElementException("No element to return.");
            }
            return queue[indexes.get(counter++)];
        }

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported.");
        }
    }

    private void checkNewItem(Item item) {
        if (item == null) {
            throw new NullPointerException("Null value cannot be inserted.");
        }
    }
}
