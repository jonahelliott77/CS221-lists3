import java.util.*;

/**
 * Array-base implementation of IndexedUnsortedList
 * @author Jonah Elliott
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
    private T[] array;
    private int rear;
    private int modCount;

    public static final int DEFAULT_CAPACITY = 10;

    /**
     * Initialize list with default capacity.
     */
    public IUArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize list with default capacity.
     @param initialCapacity size of initial array
     */
    @SuppressWarnings("unchecked")
    public IUArrayList(int initialCapacity) {
        array = (T[])(new Object[initialCapacity]);
        rear = 0;
        modCount = 0;
    }

    /**
     * Double the array capacity if there is no room to add
     */
    private void expandIfNecessary() {
        if (rear == array.length) {
            array = Arrays.copyOf(array, array.length*2);
        }
    }

    @Override
    public void addToFront(T element) {
        add(0, element);
    }

    @Override
    public void addToRear(T element) {
        add(rear, element);
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        if (!contains(target)) {
            throw new NoSuchElementException();
        }
        add(indexOf(target)+1, element);
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > rear) {
            throw new IndexOutOfBoundsException();
        }
        expandIfNecessary();
        for (int i = rear; i > index; i--) { //"shift" loop
            array[i] = array[i-1];
        }
        array[index] = element;
        rear++;
        modCount++;
    }

    @Override
    public T removeFirst() {
        if (rear == 0) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }

    @Override
    public T removeLast() {
        if (rear == 0) {
            throw new NoSuchElementException();
        }
        return remove(rear-1);
    }

    @Override
    public T remove(T element) {
        if (!contains(element)) {
            throw new NoSuchElementException();
        }
        return remove(indexOf(element));
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= rear) {
            throw new IndexOutOfBoundsException();
        }
        T retVal = array[index]; //for safe keeping
        //now we shift
        for (int i = index; i < rear-1; i++) {
            array[i] = array[i+1];
        }
        //array[rear-1] = null;
        rear--;
        array[rear] = null; //clear the duplicate
        modCount++;
        return retVal;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= (rear)) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= (rear)) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public int indexOf(T element) {
        int index = -1;
        for (int i = 0; index < 0 && i < rear; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return index;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[0];
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[rear - 1];
    }

    @Override
    public boolean contains(T target) {
        return indexOf(target) > -1;
    }

    @Override
    public boolean isEmpty() {
        return rear == 0;
    }

    @Override
    public int size() {
        return rear;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
//        for (int i = 0; i < rear; i++) {
//            str.append(array[i].toString());
//            str.append(", ");
//        }
        for (T element : this) {
            str.append(element.toString());
            str.append(", ");
        }
        if (!isEmpty()) {
            str.delete(str.length() - 2, str.length());
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public Iterator iterator() {
        return new ALIterator();
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    /* Basic Iterator for IUArrayList */
    private class ALIterator implements Iterator<T> {
        private int nextIndex;
        private boolean canRemove;
        private int iterModCount;

        /** Initialize Iterator before first element */
        public ALIterator() {
            nextIndex = 0;
            canRemove = false;
            iterModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (iterModCount != modCount) { //Something happened
                throw new ConcurrentModificationException();
            }
            return nextIndex < rear;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            canRemove = true; //Will be allowed to remove next element
            nextIndex++;
            return array[nextIndex-1];
        }

        @Override
        public void remove() {
            if (iterModCount != modCount) { //Something happened
                throw new ConcurrentModificationException();
            }
            if (!canRemove) {
                throw new IllegalStateException();
            }
            canRemove = false; //Won't be allowed to remove this twice
            for (int i = nextIndex-1; i < rear-1; i++) {
                array[i] = array[i+1];
            }
            array[rear-1] = null;
            rear--;
            nextIndex--;
            modCount++;
            iterModCount++; //ONLY THIS iterator knows
        }
    }
} //End of IUArrayList