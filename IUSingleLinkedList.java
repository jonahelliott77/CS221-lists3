import java.util.Iterator;
import java.util.ListIterator;

/**
 * Single-linked node-based implementation of IndexedUnsortedList
 * @author Jonah Elliott
 * @param <T>
 */

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Initialize a new empty list.
     */
    public IUSingleLinkedList() {
        head = tail = null;
        size = 0;
    }
    @Override
    public void addToFront(T element) {

    }

    @Override
    public void addToRear(T element) {

    }

    @Override
    public void add(T element) {

    }

    @Override
    public void addAfter(T element, T target) {

    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public void set(int index, T element) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public T first() {
        return null;
    }

    @Override
    public T last() {
        return null;
    }

    @Override
    public boolean contains(T target) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        return null;
    }
}
