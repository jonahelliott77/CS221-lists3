/**
 * Linkable node for linear data structures
 * @author jonah elliott
 */

public class Node<T> {
    private Node<T> nextNode;
    private Node<T> prevNode;
    private T element;

    /**
     * Initialize a new Node with the given element
     * @param element
     */
    public Node(T element) {
        this.element = element;
        nextNode = null;
        prevNode = null;
    }

//    /**
//     * Initialize a Node with the given element and next Node
//     * @param element
//     * @param nextNode
//     */
//    public Node(T element, Node<T> nextNode) {
//        this.element = element;
//        this.nextNode = nextNode;
//    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<T> getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node<T> nextNode) {
        this.prevNode = nextNode;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    //TODO Add getters and setters and stop using intellij

}
