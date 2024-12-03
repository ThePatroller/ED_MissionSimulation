package Collections.Queues;

import Collections.Nodes.Node;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Queues.QueueADT;

public class LinkedQueue<T> implements QueueADT<T> {

    private Node<T> front;
    private Node<T> rear;
    private int count;

    public LinkedQueue() {
        this.front = this.rear = null;
        this.count = 0;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        T removed = front.getElement();
        front = (front == rear) ? (rear = null) : (front.getNext());
        count--;
        return removed;
    }

    @Override
    public void enqueue(T element) {
        Node<T> newNode = new Node<T>(element);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        count++;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        return front.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        String result = "Queue Contents (Front to Rear):\n";
        Node<T> current = front;
        while (current != null) {
            result += current.getElement() + " ";
            current = current.getNext();
        }
        return result.trim();
    }
}

