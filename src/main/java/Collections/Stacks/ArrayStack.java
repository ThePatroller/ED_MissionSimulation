package Collections.Stacks;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Stacks.StackADT;

public class ArrayStack<T> implements StackADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    private int top;
    private T[] stack;

    public ArrayStack() {
        top = 0;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public ArrayStack(int initialCapacity) {
        top = 0;
        stack = (T[]) (new Object[initialCapacity]);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Empty Stack");
        return stack[top - 1];
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Empty Stack");
        return stack[--top - 1];
    }

    @Override
    public void push(T element) {
        if (size() == stack.length)
            expand();
        stack[top++] = element;
    }

    public void expand() {
        T[] newStack = (T[]) (new Object[stack.length * 2]);
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public String toString() {
        String result = "Stack Contents (Top to Bottom):\n";
        for (int i = 0; i < top; i++) {
            result += stack[i] + " ";
        }
        return result;
    }


}
