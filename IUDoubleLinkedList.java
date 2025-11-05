import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/** Double-lined node-based implementation of IndexedUnsortedList
 * Supports a fully-functional ListIterator in addition to basic Iterator
 * @author Jonah Elliott
*/
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount;

    /**
     * Initialize a new empty list
     */
    public IUDoubleLinkedList() {
        head = tail = null;
        size = 0;
        modCount = 0;
    }

    @Override
    public void addToFront(T element) {
        Node<T> newNode = new Node<T>(element);
        newNode.setNextNode(head);
        if (tail == null) {
            tail = newNode;
        } else {
            head.setPrevNode(newNode);
        }
        head = newNode;
        size++;
        modCount++;
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<T>(element);
//        if (index == 0) {
////            addToFront(element);
//            newNode.setNextNode(head);
//            if (head != null) {
//                head.setPrevNode(newNode);
//            } else {
//                tail = newNode;
//            }
//            head = newNode;
//        }
        if (isEmpty()) {
            head = tail = newNode;
        } else if (index == 0) {
            newNode.setNextNode(head);
            head.setPrevNode(newNode);
            head = newNode;
        } else {
            Node<T> curNode = head;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.getNextNode();
            }
            newNode.setPrevNode(curNode);
            newNode.setNextNode(curNode.getNextNode());
            curNode.setNextNode(newNode);
            if (newNode.getNextNode() != null) { //if index != size or curNode != tail
                newNode.getNextNode().setPrevNode(newNode);
            } else {
                tail = newNode;
            }
//        else if (index == size) {
//            addToRear(element);
//        } else {
//            Node<T> curNode = head;
//            for (int i = 0; i < index; i++) {
//                curNode = curNode.getNextNode();
//            }
//            Node<T> newNode = new Node<T>(element); //TODO this is not working
//            curNode.getNextNode().setPrevNode(newNode);
//            newNode.setNextNode(curNode.getNextNode());
//            newNode.setPrevNode(curNode);
//            curNode.setNextNode(newNode);
            size++;
            modCount++;
        }
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
    public T get(int index) { //Single-linked impl. can be improved from n/2 to n/4 avg
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> curNode = head;
        for (int i = 0; i < index; i++) {
            curNode = curNode.getNextNode();
        }
        return curNode.getNextNode().getElement();
    }

    @Override
    public int indexOf(T element) {
//        if (head == null) {
//            return -1;
//        }
        Node<T> curNode = head;
        int index = 0;
//        while (!curNode.getElement().equals(element) && tail != null) {
//            curNode = curNode.getNextNode();
//            index++;
//        }
        while (curNode != null && !curNode.getElement().equals(element)) {
            curNode = curNode.getNextNode();
            index++;
        }
        if (curNode == null) {
            return -1;
        }
        return index;
    }

    @Override
    public T first() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) {
//        Node<T> curNode = head;
//        while (!curNode.getElement().equals(target) && tail != null) {
//            curNode = curNode.getNextNode();
//        }
//        if (tail == null) {
//            return false;
//        }
//        return true;
        return indexOf(target) > -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        //return new IUSingleLinkedList.SLLIterator();
        return null;
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
}
