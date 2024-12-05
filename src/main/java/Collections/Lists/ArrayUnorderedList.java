package Collections.Lists;

public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList() {
        super();
    }

    public ArrayUnorderedList(int capacity) {
        super(capacity);
    }

    @Override
    public void addToFront(T element) {
        if (size() == list.length) {
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
        if (size() == list.length) {
            expandCapacity();
        }

        list[rear++] = element;
        modCount++;
    }

    @Override
    public void addAfter(T element, T target){
        if (size() == list.length) {
            expandCapacity();
        }

        int targetIndex = find(target);
        if(targetIndex != -1){
            for (int i = rear; i > targetIndex + 1; --i) {
                list[i] = list[i - 1];
            }
            list[targetIndex + 1] = element;
            rear++;
            modCount++;
        }
    }

}
