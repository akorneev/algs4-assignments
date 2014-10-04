/**
 * The client program.
 * <p/>
 * Takes a command-line integer k; reads in a sequence of N strings from standard input
 * and prints out exactly k of them, uniformly at random.
 */
public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java Subset <k>");
        } else {
            int k = Integer.parseInt(args[0]);
            RandomizedQueue<String> queue = new RandomizedQueue<>();
            String[] allStrings = StdIn.readAllStrings();
            for (String s : allStrings) {
                queue.enqueue(s);
            }
            for (int i = 0; i < k; ++i) {
                StdOut.println(queue.dequeue());
            }
        }
    }
}
