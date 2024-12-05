package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class ArrayList<T> implements ListADT<T> {

    private final int DEFAULT_CAPACITY = 20;
    private final int EXPAND_NUMBER = 2;
    private final int NOT_FOUND = -1;

    protected int modCount;
    protected int rear;
    protected T[] list;

    public ArrayList() {
        modCount = 0;
        this.rear = 0;
        this.list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        modCount = 0;
        this.rear = 0;
        this.list = (T[]) (new Object[capacity]);
    }

    protected void expandCapacity() {
        T[] newList = (T[]) new Object[EXPAND_NUMBER * list.length];
        int i = 0;
        for (T t : list) {
            newList[i++] = t;
        }
        list = newList;
    }

    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia!");
        }
        T element = list[0];

        for (int i = 0; i < rear - 1; i++) {
            list[i] = list[i + 1];
        }
        list[--rear] = null;
        modCount++;
        return element;
    }

    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia!");
        }
        T element = list[rear - 1];

        list[--rear] = null;
        modCount++;
        return element;
    }

    protected int find(T target) {
        for (int i = 0; i < rear; i++) {
            if (list[i].equals(target)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia!");
        }

        int index = find(element);
        if(index != -1){
            T removedElement = list[index];

            for (int i = index; i < rear - 1; i++) {
                list[i] = list[i + 1];
            }

            list[--rear] = null;
            modCount++;
            return removedElement;
        }
        return null;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia!");
        }
        return list[0];
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia!");
        }
        return list[rear - 1];
    }

    @Override
    public boolean contains(T target) {
        if(find(target) != -1){
            return true;
        }
        return false;
    }

    private boolean indexIsValid(int index) {
        return ((index < size()) && index >= 0);
    }

    public T get(int index) throws IllegalArgumentException {
        if (!indexIsValid(index)) {
            throw new IllegalArgumentException("Index invalido!");
        }
        return list[index];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return rear;
    }

    @Override
    public String toString() {
        String str = "Lista: \n";
        for (int i = 0; i < rear; i++) {
            str += list[i] + "\n";
        }
        return str;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private boolean okToRemove;
        private int expectedModCount;
        private int current;

        public ListIterator() {
            expectedModCount = modCount;
            current = 0;
            okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            return (current != size());
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.okToRemove = true;
            return (T) list[this.current++];
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!this.okToRemove) {
                throw new IllegalStateException();
            }
            this.okToRemove = false;
            try {
                ArrayList.this.remove(list[--this.current]);
                this.expectedModCount++;
            } catch (EmptyCollectionException ex) {}
        }

    }

}
