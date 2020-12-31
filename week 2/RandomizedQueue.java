import StdRandom.java;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] items;

    private void doubleStorage() {
        Item[] newItems = (Item[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    private void halfStorage() {
        Item[] newItems = (Item[]) new Object[size / 4];
        for (int i = 0; i < size / 4; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    private boolean full() {
        return items.length() == size;
    }

    private boolean oversize() {
        return items.length <= items.length / 4;
    }


    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[10];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (full()) {
            doubleStorage();
        }
        size++;
        items[size] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            if (isEmpty()) throw new java.util.NoSuchElementException();
        }

        int removeIndex = StdRandom.uniform(size);
        Item val = items[removeIndex];
        items[removeIndex] = items[size];
        items[size] = null;
        size--;
        if (oversize()) {
            halfStorage();
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int returnIndex = StdRandom.uniform(size);
        return items[returnIndex];
    }

    public Iterator<Item> iterator() {
        return new RandomIteartor();
    }

    private class RandomIterator implements Iterator<Item> {

        private Item[] Iterators;
        private int current;

        public boolean hasNext() {
            return current < Iterators.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (hasNext()) {
                current++;
                return Iterators[current];
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    // return an independent iterator over items in random order

    // unit testing (required)
    public static void main(String[] args) {
        RandomIterator<String> a = new RandomIterator<String>();

    }

}
