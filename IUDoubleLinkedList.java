import java.util.ConcurrentModificationException;
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
//        Node<T> newNode = new Node<T>(element);
//        newNode.setNextNode(head);
//        if (tail == null) { // adding to empty list
//            tail = newNode;
//        } else {
//            head.setPrevNode(newNode);
//        }
//        head = newNode;
//        size++;
//        modCount++;
        ListIterator<T> lit = listIterator();
        lit.add(element);
    }

    @Override
    public void addToRear(T element) {
//        Node<T> newNode = new Node<T>(element);
//        newNode.setPrevNode(tail);
//        if (head == null) {
//            head = newNode;
//        } else {
//            head.setNextNode(newNode);
//        }
//        head = newNode;
//        size++;
//        modCount++;
        //CRITICAL that indexed listIterator starts at the best end
        ListIterator<T> lit = listIterator(size);
        lit.add(element);
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        ListIterator<T> lit = listIterator();
        boolean foundIt = false;
        while (lit.hasNext()) {
            if (lit.next().equals(target)) {
                lit.add(element);
                foundIt = true;
            } //else, nothing - just needed to keep moving
        }
        if (!foundIt) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void add(int index, T element) {
//        if (index < 0 || index > size) {
//            throw new IndexOutOfBoundsException();
//        }
//        Node<T> newNode = new Node<T>(element);
////        if (index == 0) {
//////            addToFront(element);
////            newNode.setNextNode(head);
////            if (head != null) {
////                head.setPrevNode(newNode);
////            } else {
////                tail = newNode;
////            }
////            head = newNode;
////        }
//        if (isEmpty()) {
//            head = tail = newNode;
//        } else if (index == 0) {
//            newNode.setNextNode(head);
//            head.setPrevNode(newNode);
//            head = newNode;
//        } else {
//            Node<T> curNode = head;
//            for (int i = 0; i < index - 1; i++) {
//                curNode = curNode.getNextNode();
//            }
//            newNode.setPrevNode(curNode);
//            newNode.setNextNode(curNode.getNextNode());
//            curNode.setNextNode(newNode);
//            if (newNode.getNextNode() != null) { //if index != size or curNode != tail
//                newNode.getNextNode().setPrevNode(newNode);
//            } else {
//                tail = newNode;
//            }
////        else if (index == size) {
////            addToRear(element);
////        } else {
////            Node<T> curNode = head;
////            for (int i = 0; i < index; i++) {
////                curNode = curNode.getNextNode();
////            }
////            Node<T> newNode = new Node<T>(element);
////            curNode.getNextNode().setPrevNode(newNode);
////            newNode.setNextNode(curNode.getNextNode());
////            newNode.setPrevNode(curNode);
////            curNode.setNextNode(newNode);
//            size++;
//            modCount++;
//        }
        ListIterator<T> lit = listIterator(index);
        lit.add(element);
    }

    @Override
    public T removeFirst() {
//        if (isEmpty()) {
//            throw new NoSuchElementException();
//        }
//        Node<T> firstNode = head;
//        head = head.getNextNode();
//        head.setPrevNode(null);
//        firstNode.setNextNode(null);
//        if (head == null) {
//            tail = null;
//        }
//        size--;
//        modCount++;
//        return firstNode.getElement();
        ListIterator<T> lit = listIterator();
        if (!lit.hasNext()) {
            throw new NoSuchElementException();
        }
        T retVal = lit.next();
        lit.remove();
        return retVal;
    }

    @Override
    public T removeLast() {
//        if (isEmpty()) {
//            throw new NoSuchElementException();
//        }
//        T retVal = tail.getElement();
//        tail = tail.getPrevNode();
//        if (size == 1) { //or if tail == null
//            head = null; //removed only element
//        } else {
//            tail.setNextNode(null);
//        }
//        size--;
//        modCount++;
//        return retVal;
        ListIterator<T> lit = listIterator(size);
        if (!lit.hasPrevious()) {
            throw new NoSuchElementException();
        }
        T retVal = lit.previous();
        lit.remove();
        return retVal;
    }

    @Override
    public T remove(T element) {
//        ListIterator<T> lit = listIterator(); //Use method similar to addAfter
//        while (lit.hasNext()) {
//
//        }
        ListIterator<T> lit = listIterator();
        T retVal;
        boolean foundIt = false;
        if (head != null) {
            retVal = head.getElement();
        } else {
            throw new NoSuchElementException();
        }
        while (lit.hasNext()) {
            retVal = lit.next();
            if (retVal.equals(element)) {
                lit.remove();
                foundIt = true;
            } //else, nothing - just needed to keep moving
        }
        if (!foundIt) {
            throw new NoSuchElementException();
        }
        return retVal;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        ListIterator<T> lit = listIterator(index);
        if (!lit.hasNext()) {
            throw new IndexOutOfBoundsException();
        }
        T retVal = lit.next();
        lit.remove();
        return retVal;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            ListIterator<T> lit = listIterator(index);
            lit.next();
            lit.set(element);
        }
    }

    @Override
    public T get(int index) { //Single-linked impl. can be improved from n/2 to n/4 avg
//        if (index < 0 || index > size) {
//            throw new IndexOutOfBoundsException();
//        }
//        Node<T> curNode = head;
//        for (int i = 0; i < index; i++) {
//            curNode = curNode.getNextNode();
//        }
//        return curNode.getNextNode().getElement();
        if (index == size) { //only index not caught by ListIterator's constructor
            throw new IndexOutOfBoundsException();
        }
        ListIterator<T> lit = listIterator(index);
        return lit.next();
    }

    @Override
    public int indexOf(T element) {
//        if (head == null) {
//            return -1;
//        }
//        Node<T> curNode = head;
//        int index = 0;
////        while (!curNode.getElement().equals(element) && tail != null) {
////            curNode = curNode.getNextNode();
////            index++;
////        }
//        while (curNode != null && !curNode.getElement().equals(element)) {
//            curNode = curNode.getNextNode();
//            index++;
//        }
//        if (curNode == null) {
//            return -1;
//        }
        int index = -1;
        int currentIndex = 0;
        ListIterator<T> lit = listIterator();
        while (index < 0 && lit.hasNext()) {
            if (lit.next().equals(element)) {
                index = currentIndex;
            }
            currentIndex++;
        }
        return index;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (isEmpty()) {
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
        return new DLLIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DLLIterator();
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        return new DLLIterator(startingIndex);
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


    /* ListIterator for IUDoubleLinkedList */
    private class DLLIterator implements ListIterator<T> {
        private Node<T> nextNode;
        private int nextIndex;
        private Node<T> lastReturnedNode;
        private int iterModCount;

        /** Initialize iterator before first element */
        public DLLIterator() {
            nextNode = head;
            nextIndex = 0;
            iterModCount = modCount;
            lastReturnedNode = null;
        }

        /** Initialize iterator before the specified index
         * @param startingIndex index that would be next after constructor
         * */
        public DLLIterator(int startingIndex) {
            if (startingIndex < 0 || startingIndex > size) {
                throw new IndexOutOfBoundsException();
            }
            if (startingIndex > size/2) {
                if (startingIndex == size) {
                    nextNode = null;
                    //nextIndex = size;
                } else {
                    nextNode = tail;
                    //nextIndex = size;
                    for (int i = size-1; i > startingIndex; i--) {
                        nextNode = nextNode.getPrevNode();
                        //nextIndex--;
                    }
                }
            } else {
                nextNode = head;
                nextIndex = 0;
                for (int i = 0; i < startingIndex; i++) {
                    nextNode = nextNode.getNextNode();
                    //nextIndex++;
                }
            }

            nextIndex = startingIndex;
            iterModCount = modCount;
            lastReturnedNode = null;
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
            lastReturnedNode = nextNode;
            nextNode = nextNode.getNextNode();
            nextIndex++;
            return retVal;
        }

        @Override
        public boolean hasPrevious() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            //nextNode.getPrevious() != null or nextIndex != 0 or nextNode != head
            return nextNode != head;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextNode == null) { //We must be past tail
                nextNode = tail;
            } else { //A node is in front of us
                nextNode = nextNode.getPrevNode();
            }
            lastReturnedNode = nextNode;
            nextIndex--;
            return nextNode.getElement();
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            }
            //now I can remove lastReturnedNode
            if (lastReturnedNode == tail) {
                tail = lastReturnedNode.getPrevNode();
            } else {
                lastReturnedNode.getNextNode().setPrevNode(lastReturnedNode.getPrevNode());
            }
            if (lastReturnedNode == head) {
                head = lastReturnedNode.getNextNode();
            } else {
                lastReturnedNode.getPrevNode().setNextNode(lastReturnedNode.getNextNode());
            }
            if (lastReturnedNode == nextNode) { //last move was previous and just removed nextNode
                nextNode = nextNode.getNextNode();
            } else { //last move was next
                nextIndex--;
            }
            lastReturnedNode = null;
            size--;
            modCount++;
            iterModCount++;
        }

        @Override
        public void set(T t) {
            //You can call set as many times as you want, do not clear lastReturnedNode
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            } else {
                lastReturnedNode.setElement(t);
            }
            modCount++;
            iterModCount++;
        }

        @Override
        public void add(T t) {
            //As long as no ConcurrentModificationException, you can call add at any time
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            Node<T> newNode = new Node<T>(t);

//            //Check whether iterator is at the beginning or end of the list
//            if (nextIndex > 0 && nextNode != null) {
//                newNode.setPrevNode(nextNode.getPrevNode());
//                nextNode.getPrevNode().setNextNode(newNode);
//            } else if (nextNode == null) {
//                tail.setNextNode(newNode);
//                newNode.setPrevNode(tail);
//                tail = newNode;
//            } else {
//                head = newNode;
//            }
//            //Check if the right of the iterator is null
//            if (nextNode != null) {
//                newNode.setNextNode(nextNode);
//                nextNode.setPrevNode(newNode);
//            } else {
//                tail = newNode;
//            }
            if (nextNode == null) { //There is nothing in front of us
                if (nextIndex == 0) { //The list is empty
                    head = tail = newNode;
                } else { //We must be past tail
                    newNode.setPrevNode(tail);
                    tail.setNextNode(newNode);
                    tail = newNode;
                }
            }
            else { //There must be an element in front of us
                if (nextIndex == 0) { //We are at the beginning
                    newNode.setNextNode(nextNode);
                    nextNode.setPrevNode(newNode);
                    head = newNode;
                }
                else { //We are in between two nodes
                    newNode.setPrevNode(nextNode.getPrevNode());
                    newNode.getPrevNode().setNextNode(newNode);
                    newNode.setNextNode(nextNode);
                    nextNode.setPrevNode(newNode);
                }
            }
            lastReturnedNode = null;
            nextIndex++;
            size++;
            modCount++;
            iterModCount++;
        }
    }
}
