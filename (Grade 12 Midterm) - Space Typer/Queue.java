import java.util.*;

/**
 * Queue used for stimulating how a Queue works in real life
 * 
 * @author (Mr.Chan) 
 * @version (April 11, 2019)
 */
public class Queue<T> implements Iterable<T> {
    private Node first, last;
    
    private class Node {
        private T item;
        private Node next;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void enqueue(T item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
    }
    
    public T dequeue() {
        T item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
    
    // Method created for size
    public int size() {
        int amount = 0;
        for (Node n = first; n != null; n = n.next) {
            amount++;
        }
        return amount;
    }
    
    // To make this data structure work with for-each loops
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<T> {
        private Node n = first;

        public boolean hasNext() {
            return n != null;
        }

        public T next() {
            T item = n.item;
            n = n.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}