package Collections.Stack;

import Collections.Exceptions.EmptyCollectionException;

public class ArrayStack<T> implements StackADT<T> {
    /**
     * Constant to represent the default capacity of the array
     */
    private final int DEFAULT_CAPACITY = 100;

    /**
     * Int that represents both the number of elements and the next
     * available position in the array
     */
    private int top;

    /**
     * Array of generic elements to represent the stack
     */
    private T[] stack;

    /**
     * Creates an empty stack using the default capacity.
     */
    public ArrayStack() {
        top = 0;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty stack using the specified capacity.
     *
     * @param initialCapacity represents the specified capacity
     */
    public ArrayStack(int initialCapacity) {
        top = 0;
        stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Adds the specified element to the top of this stack,
     * expanding the capacity of the stack array if necessary.
     *
     * @param element generic element to be pushed onto stack
     */
    public void push(T element) {
        if (size() == stack.length) {
            expandCapacity();
        }
        stack[top] = element;
        top++;
    }

    /**
     * Removes the element at the top of this stack and
     * returns a reference to it.
     * Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element removed from top of stack
     * @throws EmptyCollectionException if a pop is attempted on empty stack
     */
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        top--;
        T result = stack[top];
        stack[top] = null;
        return result;
    }

    /**
     * Returns a reference to the element at the top of this stack.
     * The element is not removed from the stack.
     * Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element on top of stack
     * @throws EmptyCollectionException if a peek is attempted on empty stack
     */
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        return stack[top - 1];
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return int the number of elements in the stack
     */
    public int size() {
        return top;
    }

    /**
     * Returns true if the stack is empty and false otherwise.
     *
     * @return boolean true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Expands the capacity of the stack array by doubling its current capacity.
     */
    private void expandCapacity() {
        T[] largerStack = (T[]) (new Object[stack.length * 2]);
        System.arraycopy(stack, 0, largerStack, 0, stack.length);
        stack = largerStack;
    }

    /**
     * Returns a string representation of the stack.
     *
     * @return String representation of the stack
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < top; i++) {
            result.append(stack[i].toString()).append(" ");
        }
        return result.toString().trim();
    }
}
