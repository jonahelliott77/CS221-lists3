import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Single-linked node-based implementation of IndexedUnsortedList
 *
 * @author Jonah Elliott
 */

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount;

    /**
     * Initialize a new empty list.
     */
    public IUSingleLinkedList() {
        head = tail = null;
        size = 0;
        modCount = 0;
    }
    @Override
    public void addToFront(T element) {
        Node<T> newNode = new Node<T>(element);
        newNode.setNextNode(head);
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<T>(element);
        if (tail != null) {
            tail.setNextNode(newNode);
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
        modCount++;
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {

    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> firstNode = head; //T retVal
        head = head.getNextNode();
        if (head == null) { //just removed the only node from the list
            tail = null; //so tail is also null
        }
        size--;
        modCount++;
        return firstNode.getElement(); //or return retVal
    }

    @Override
    public T removeLast() { //O(n)
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T retVal = tail.getElement();
        if (size > 0) {
            Node<T> currentNode = head;
            for (int i = 0; i < size-2; i++) { //iterates n-2 times
                currentNode = currentNode.getNextNode();
            }
            tail = currentNode;
            currentNode.setNextNode(null);
        } else {
            head = tail = null;
        }
        size--;
        modCount++;
        return retVal;
    }

    @Override
    public T remove(T element) {
        //int index = indexOf(element); //don't want to repeat walking through same nodes

        //empty?
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T retVal;
        //must check head node before general case search
        if (head.getElement().equals(element)) {
            retVal = head.getElement();
            head = head.getNextNode();
            if (head == null) {
                tail = null;
            }
        } else {
            //need the node in front of the node containing element
            Node<T> curNode = head;
            //the order of the following condition is important - don't rearrange it!
            while (curNode != tail && !curNode.getNextNode().getElement().equals(element)) {
                curNode = curNode.getNextNode();
            }

            //what if I never found it?
            if (curNode == tail) {
                throw new NoSuchElementException();
            }
            retVal = curNode.getNextNode().getElement();
            if (curNode == tail) {
                tail = curNode;
            }
            //"general case" - middle of a long list is the most common location
            curNode.setNextNode(curNode.getNextNode().getNextNode());

            //was it the last node?
            if (curNode.getNextNode() == null) {
                tail = curNode;
            }
        }

        //beginning of a list?

        //end of a list?

        //only element?

        size--;
        modCount++;
        return retVal;
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
        return new SLLIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Unimplemented method 'listIterator()'");
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        throw new UnsupportedOperationException("Unimplemented method 'listIterator()'");
    }

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

    private class SLLIterator implements Iterator<T> {
        private Node<T> nextNode;
        private int iterModCount;
        private boolean canRemove;

        SLLIterator() {
            nextNode = head;
            iterModCount = modCount;
            canRemove = false;
        }

        @Override
        public boolean hasNext() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T retVal = nextNode.getElement();
            nextNode = nextNode.getNextNode();
            canRemove = true;
            return retVal;
        }

        @Override
        public void remove() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!canRemove) {
                throw new IllegalStateException();
            }
            canRemove = false;
            if (head.getNextNode() == nextNode) { //We need to remove first element
                head = head.getNextNode();
                if (head == null) { //or size == 1 or nextNode == null
                    tail = null;
                }
            } else {
                //general case - find ""previousPreviousNode"
                Node<T> prevPrevNode = head;
                while (prevPrevNode.getNextNode().getNextNode() != nextNode) {
                    prevPrevNode = prevPrevNode.getNextNode();
                }
                prevPrevNode.setNextNode(nextNode);
//              if (prevPrevNode.getNextNode() == null) {
                if (nextNode == null) {
                    tail = prevPrevNode;
                }
            }
            size--;
            modCount++;
            iterModCount++;
        }
    }
}
