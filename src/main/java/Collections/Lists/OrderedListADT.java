package Collections.Lists;

import Collections.Exceptions.NonComparableElementException;

public interface OrderedListADT<T> extends ListADT<T> {

    public void add(T element) throws NonComparableElementException;

}