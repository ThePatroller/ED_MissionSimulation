package Collections.Lists;

import Collections.Lists.OrderedListADT;
import Collections.Lists.ArrayList;
import Collections.Exceptions.NonComparableElementException;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void add(T element) throws NonComparableElementException {

        if (super.size() == list.length) {
            expandCapacity();
        }

        int position = 0;
        if (!(element instanceof Comparable<?>)) {
            throw new NonComparableElementException("Not comparable");
        }
        Comparable<T> comparable = (Comparable<T>) element;

        while (position < rear && comparable.compareTo(list[position]) > 0) {
            ++position;
        }

        for (int i = rear; i > position; --i) {
            list[i] = list[i - 1];
        }

        list[position] = element;
        rear++;
        modCount++;
    }

}

