package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Nodes.LinearNode;

public class LinkedQueue<T> implements QueueADT<T> {

    private int counter;
    private LinearNode<T> front;
    private LinearNode<T> rear;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.counter = 0;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);

        if (isEmpty()) {
            this.front = newNode;
        } else {
            this.rear.setNext(newNode);
        }

        this.rear = newNode;
        this.counter++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Fila vazia!");
        }
        T element = this.front.getElement();

        this.front = this.front.getNext();
        this.counter--;
        if (isEmpty()) {
            this.rear = null;
        }

        return element;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Fila vazia!");
        }
        return front.getElement();
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
        LinearNode<T> current = front;
        String str = "Queue: \n";
        while (current != null) {
            str += current.getElement() + "\n";
            current = current.getNext();
        }
        return str;
    }

}

