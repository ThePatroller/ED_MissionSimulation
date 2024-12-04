package Collections.Nodes;

public class Node<T> {

    public Node<T> next; //Changed to public to be able to use on Iterator class on LinkedList
    public T element; // Same

    public Node() {
        next = null;
        element = null;
    }

    public Node(T elem) {
        next = null;
        element = elem;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> node) {
        next = node;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T elem) {
        element = elem;
    }
}
