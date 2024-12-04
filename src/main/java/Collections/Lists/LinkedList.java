package Collections.Lists;

import Collections.Nodes.Node;

public class LinkedList<T> {

    private Node<T> head;

    public LinkedList() {
        head = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if(head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while(current.next != null) {
                current = current.next; // Advance till the end
            }
            current = current.next; // Add the new node at the end of the list
        }
    }


    public void remove(T data) {
        if(head == null) {
            System.out.println("The list is empty");
            return;
        }

        //If the element to be removed is on the first node
        if(head.getElement().equals(data)) {
            head = head.next; // The second node pass to be the first
            return;
        }

        Node<T> current = head;
        while(current.next != null && !current.next.getElement().equals(data)) {
            current = current.next; // Advance to the next node;
        }

        // If finds the element, remove node
        if(current.next != null) {
            current.setNext(current.next.next);
        } else {
            System.out.println("Element not found in the list");
        }
    }

    // Method to print all the elements
    public void printList() {
        if(head == null) {
            System.out.println("The list is empty");
            return;
        }

        Node<T> current = head;
        while(current != null) {
            System.out.println(current.getElement() + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public class Iterator {
        private Node actual;
        private Node previous;

        public Iterator() {
            this.actual = head;
            this.previous = null;
        }

        public boolean hasNext() {
            return actual != null;
        }

        public T next() {
            T element = (T) actual.getElement();
            previous = actual;
            actual = actual.next;
            return element;
        }

        public void remove() {
            if (previous == null) {
                head = actual.next;
            } else {
                previous.next = actual.next;
            }
            actual = actual.next;
        }

    }


}

