package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

import java.util.Iterator;

public interface ListADT<T> extends Iterable<T> {

    public T removeFirst() throws EmptyCollectionException;

    public T removeLast() throws EmptyCollectionException;

    public T remove(T element) throws EmptyCollectionException, NoSuchElementException;

    public T first() throws EmptyCollectionException;

    public T last() throws EmptyCollectionException;

    public boolean contains(T target) throws EmptyCollectionException;

    public boolean isEmpty();

    public int size();

    public Iterator<T> iterator();

    @Override
    public String toString();
}

