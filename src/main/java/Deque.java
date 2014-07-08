import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;

    public Deque() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkNewItem(item);
        Node<Item> newFirst = new Node<Item>();
        newFirst.value = item;
        newFirst.next = first;
        if (size == 0) {
            last = newFirst;
        } else {
            first.previous = newFirst;
        }
        first = newFirst;
        size++;
    }

    public void addLast(Item item) {
        checkNewItem(item);
        Node<Item> newLast = new Node<Item>();
        newLast.value = item;
        if (size != 0) {
            last.next = newLast;
            newLast.previous = last;
        } else {
            first = newLast;
        }
        last = newLast;
        size++;
    }

    public Item removeFirst() {
        checkNotEmpty();
        Item itemToRemove = first.value;
        first = first.next;
        size--;
        if (size <= 1) {
            last = first;
        }
        return itemToRemove;
    }

    public Item removeLast() {
        checkNotEmpty();
        Item itemToRemove = last.value;
        last = last.previous;
        size--;
        if (size <= 1) {
            first = last;
        }
        return itemToRemove;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
    }

    private static class Node<Item> {

        private Item value;

        private Node<Item> next;

        private Node<Item> previous;

    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("No element to return.");
            }
            Item valueToReturn = current.value;
            current = current.next;
            return valueToReturn;
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

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty, cannot remove an element");
        }
    }
}
