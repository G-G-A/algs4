import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N;               // number of elements on queue
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }
    
    //construct an empty dequeue
    public Deque() {
        first = null;
        last  = null;
        N = 0;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;     
    }
    
    //insert the item at the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        if (oldfirst != null) oldfirst.previous = first;
        N++;
    }
    
    public void  addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else {
            if (oldlast != null) oldlast.next = last;
            last.previous = oldlast;
        }
        N++;
    }
    
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first != null) first.previous = null;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if (last != null) last.next = null;
        N--;
        if (isEmpty()) first = null;   // to avoid loitering
        return item;
    }
    
    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }
    
    /**
     * Unit tests the <tt>Queue</tt> data type.
     */
    public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.startsWith("+h:")) q.addFirst(item);
            if (item.startsWith("+t:")) q.addLast(item);
            if (item.startsWith("-h:")) 
                StdOut.print("remove first: " + q.removeFirst() + "\n");
            if (item.startsWith("-t:")) 
                StdOut.print("remove last: " + q.removeLast() + "\n");
            else for (String s : q)
                StdOut.println(s);
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }

}
