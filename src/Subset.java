
public class Subset {
    
    public static void main(final String[] args)
    {
        int k = Integer.parseInt(args[0]);
        if (k == 0) return;
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int counter = 0;
        String s;
        String tmp;
        int p = 0;
        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            counter++;
            if (counter > k)
            {
                p = StdRandom.uniform(1, counter + 1);
                tmp = q.dequeue();
                if (p <= k)
                {
                    q.enqueue(s);
                }
                else
                {
                    q.enqueue(tmp);
                }
            }
            else
            {
                q.enqueue(s);
            }
        }
        while (k > 0) {
            StdOut.println(q.dequeue());
            k--;
        }
    }
}
