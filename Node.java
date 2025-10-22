/**
 * Linkable node for linear data structures
 * @author jonah elliott
 * @param <T>
 */

public class Node<T> {
    private Node<T> nextNode;
    private T element;

    /**
     * Initialize a new Node with the given element
     * @param element
     */
    public Node(T element) {
        this.element = element;
        nextNode = null;
    }

    /**
     * Initialize a Node with the given element and next Node.
     * @param element
     * @param nextNode
     */
    public Node(T element, Node<T> nextNode) {
        this.element = element;
        this.nextNode = nextNode;
    }

    //TODO Add getters and setters and stop using intellij

}
