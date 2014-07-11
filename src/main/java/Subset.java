import java.util.Iterator;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class Subset {
    public static void main(String[] args) {
        while (!Thread.currentThread().isInterrupted()) {
            int k = Integer.parseInt(args[0]);
            System.out.print("Enter strings separated with whitespaces: ");
            RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
            String[] strings = StdIn.readString().split(" ");
            for (String string : strings) {
                randomizedQueue.enqueue(string);
            }
            Iterator<String> iterator = randomizedQueue.iterator();
            for (int i = 0; i < k; i++) {
                System.out.println(iterator.next());
            }
        }
    }
}
