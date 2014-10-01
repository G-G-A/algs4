import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] q;         // array of items
    private int N = 0;           // number of elements on queue
    private int first = 0;       // index of first element of queue
    private int last  = 0;       // index of next available slot
    
    public RandomizedQueue()
    {
        q = (Item[]) new Object[2];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        // double size of array if necessary and recopy to front of array
        if (N == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        N++;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int rndIndex = rndIndex();
        exch(first, rndIndex);
        Item item = q[first];
        q[first] = null;                            // to avoid loitering
        N--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == q.length/4) resize(q.length/2); 
        return item;
    }
    
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return q[rndIndex()];
    }
    
    // resize the underlying array
    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = N;
    }
    
    private int rndIndex() {
        return (first + StdRandom.uniform(N)) % q.length;
    }
    
    private void exch(int i, int j) {
        Item swap = q[i];
        q[i] = q[j];
        q[j] = swap;
    }
    
    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private Item[] temp;
        
        public ArrayIterator()
        {
            i = 0;
            temp = (Item[]) new Object[N];
            for (int j = 0; j < N; j++)
            {
                temp[j] = q[(first + j) % q.length];
            }
            StdRandom.shuffle(temp);
        }
        
        public boolean hasNext()  { return i < N;                               }
        public void remove()      { throw new UnsupportedOperationException();  }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return temp[i++];
        }
    }
    
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        double p1 =  0.9f;
        int N = 50;
        double p = 0;
        for (int i = 0; i < N; i++)
        {
            q.enqueue(i);
        }
        for (int val : q)
        {
            StdOut.print(val + "; ");
        }
        StdOut.println();
        for (int val : q)
        {
            StdOut.print(val + "; ");
        }
/*        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                q.enqueue(item);
                StdOut.print("enque: "+item+ " \n");
                StdOut.print("curr q:\n");
                for (String s : q) StdOut.print(s + "; ");
            }
            else if (!q.isEmpty()) {
                StdOut.print("deque: "+q.dequeue() + " \n");
                StdOut.print("curr q:\n");
                for (String s : q) StdOut.print(s +"; ");
            }
        }
        StdOut.println("(" + q.size() + " left on queue)");*/
    }
 }
