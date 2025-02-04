package Collections.Lists;

import Collections.Lists.UnorderedListADT;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList() {
        super();
    }

    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void addToFront(T element) {
        if (super.size() == list.length) {
            expandCapacity();
        }

        for (int i = rear; i > 0; --i) {
            list[i] = list[i - 1];
        }

        list[0] = element;
        rear++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        if (super.size() == list.length) {
            expandCapacity();
        }

        list[rear++] = element;
        modCount++;
    }

    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, NoSuchElementException {
        if (super.size() == list.length) {
            expandCapacity();
        }
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty list");
        }

        int targetIndex = find(target);

        for (int i = rear; i > targetIndex + 1; --i) {
            list[i] = list[i - 1];
        }

        list[targetIndex + 1] = element;
        rear++;
        modCount++;
    }



}

