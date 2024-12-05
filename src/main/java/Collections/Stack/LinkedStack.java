package Collections.Stack;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Nodes.LinearNode;

public class LinkedStack<T> implements StackADT<T> {

    private int counter;
    private LinearNode<T> top;

    public LinkedStack() {
        this.counter = 0;
        this.top = null;
    }

    public LinkedStack(int counter) {
        this.counter = counter;
        this.top = null;
    }

    @Override
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);

        newNode.setNext(this.top);
        this.top = newNode;
        this.counter++;
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack vazia!");
        }

        T t = this.top.getElement();
        LinearNode<T> prev = this.top;
        this.top = this.top.getNext();
        prev.setNext(null);
        counter--;
        return t;
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack vazia!");
        }
        return this.top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public String toString() {
        LinearNode<T> current = top;
        String str = "Stack: \n";

        while (current != null) {
            str += current.getElement() + "\n";
            current = current.getNext();
        }
        return str;
    }

}
