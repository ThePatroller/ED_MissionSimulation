package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Queues.QueueADT;

public class CircularArrayQueue<T> implements QueueADT<T> {

    private static final int DEFAULT_CAPACITY = 100;
    private T[] queue;
    private int front;
    private int rear;
    private int count;

    public CircularArrayQueue() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.front = this.rear = this.count = 0;
    }

    public CircularArrayQueue(int size) {
        this.queue = (T[]) (new Object[size]);
        this.front = this.rear = this.count = 0;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        T removed = queue[front];
        front = loop(front);
        count--;
        return removed;
    }

    @Override
    public void enqueue(T element) {
        if (size() == queue.length)
            expand();
        queue[rear] = element;
        rear = loop(rear);
        count++;
    }

    private void expand() {
        T[] newQueue = (T[]) (new Object[queue.length * 2]);
        int i = front;
        for (int j = 0; j < count; j++) {
            newQueue[j] = queue[i];
            i = loop(i);
        }
        queue = newQueue;
        front = 0;
        rear = count;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        return queue[front];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

    private int loop(int index) {
        return (index + 1) % queue.length;
    }

    @Override
    public String toString() {
        String result = "Queue Contents (Front to Rear):\n";
        int j = front;
        for (int i = 0; i < count; i++) {
            result += queue[j] + " ";
            j = loop(j);
        }
        return result.trim();
    }

}
