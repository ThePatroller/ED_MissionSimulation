package Collections.Stacks;

import Collections.Exceptions.EmptyCollectionException;

public interface StackADT<T> {

    public void push(T element);

    public T pop() throws EmptyCollectionException;

    public T peek() throws EmptyCollectionException;

    public boolean isEmpty();

    public int size();

    @Override
    public String toString();
}
