package Collections.Lists;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList() {
        super();
    }

    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void add(T element) throws IllegalArgumentException {
        if (size() == list.length) {
            expandCapacity();
        }

        if (!(element instanceof Comparable<?>)) {
            throw new IllegalArgumentException("Elemento não comparavel!");
        }
        Comparable<T> comparable = (Comparable<T>) element;

        int position = 0;
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
