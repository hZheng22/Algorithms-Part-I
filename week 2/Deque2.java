import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item val;
        private Node previous;
        private Node next;
    }

    private Node first, last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        // Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.val = item;
        // System.out.println(oldFirst.val);
        // set new first
        first.previous = null;
        first.next = oldFirst;

        //if the deque is empty
        if (isEmpty()) {
            last = first;
            System.out.println("null");
        } else {
            oldFirst.previous = first;
            System.out.println("old first val  " + oldFirst.val);
            System.out.println("new first val  " + first.val);
        }
        size++;

    }

    public void addLast(Item item) {
        // Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
        if (item == null) throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.val = item;
        // set new last
        last.next = null;
        last.previous = oldLast;

        // set oldLast
        if (isEmpty()) {
            first = last;
            System.out.println("Last null");
        } else {
            oldLast.next = last;
            System.out.println("old last " + oldLast.val);
            System.out.println("new last " + last.val);
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
//        System.out.println("test");
//        System.out.println(first.val);
        Item returnVal = first.val;
        first = first.next;
        if (first != null) {
            first.previous = null;
        }
        size--;
        return returnVal;
    }

    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item returnVal = last.val;
        last = last.previous;
        if (last != null) {
            last.next = null;
        }
        size--;
        return returnVal;
    }

    public Iterator<Item> iterator() {
        return new ListIteartor();
    }

    private class ListIteartor implements Iterator<Item> {
        private Node current = first;


        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            Item val = current.val;
            current = current.next;
            return val;
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        System.out.println(deque.size());
        deque.addFirst("1");
//        System.out.print("Size: ");
//        System.out.println(deque.size());
        deque.addFirst("2");
        deque.addLast("4");
        deque.addLast("6");
        deque.addLast("7");
        System.out.println("remove first  " + deque.removeFirst());
        System.out.println("remove last  " + deque.removeLast());

        for (String i : deque) {
            System.out.println("i = " + i);
        }

    }


}
