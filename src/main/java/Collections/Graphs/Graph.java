package Collections.Graphs;

import java.util.Iterator;

import Collections.Lists.ArrayUnorderedList;
import Collections.Queues.LinkedQueue;
import Collections.Stacks.LinkedStack;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Queues.QueueADT;
import Collections.Stacks.StackADT;
import Collections.Graphs.GraphADT;
import Collections.Lists.UnorderedListADT;

public class Graph<T> implements GraphADT<T> {

    private final int DEFAULT_CAPACITY = 10;
    protected int numVertices;
    protected int[][] adjMatrix;
    protected T[] vertices;

    public Graph() {
        numVertices = 0;
        this.adjMatrix = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public Graph(int size) {
        numVertices = 0;
        this.adjMatrix = new int[size][size];
        this.vertices = (T[]) (new Object[size]);
    }

    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = 1;
            adjMatrix[index2][index1] = 1;
        }
    }

    public void addVertex(T vertex) {
        if (numVertices == vertices.length)
            expandCapacity();
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = 0;
            adjMatrix[i][numVertices] = 0;
        }
        numVertices++;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = 0;
            adjMatrix[index2][index1] = 0;
        }
    }

    @Override
    public void removeVertex(T vertex) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Graph is empty.");

        int index = getIndex(vertex);

        if (index == -1)
            throw new IllegalArgumentException("Vertex not found in the graph.");

        for (int i = index; i < numVertices - 1; i++)
            adjMatrix[i] = adjMatrix[i + 1];

        adjMatrix[numVertices - 1] = new int[numVertices];

        for (int i = 0; i < numVertices - 1; i++)
            for (int j = index; j < numVertices - 1; j++)
                adjMatrix[i][j] = adjMatrix[i][j + 1];

        for (int i = 0; i < numVertices; i++)
            adjMatrix[i][numVertices - 1] = 0;

        for (int i = index; i < numVertices - 1; i++)
            vertices[i] = vertices[i + 1];

        vertices[numVertices - 1] = null;
        numVertices--;
    }

    @Override
    public boolean isConnected() {
        if (isEmpty())
            return true;

        boolean[] visited = new boolean[numVertices];
        int visitedCount = performDFS(0, visited);
        return visitedCount == numVertices;
    }

    private int performDFS(int vertexIndex, boolean[] visited) {
        visited[vertexIndex] = true;
        int count = 1;

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertexIndex][i] == 1 && !visited[i]) {
                count += performDFS(i, visited);
            }
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return numVertices;
    }

    private int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++)
            if (vertices[i].equals(vertex))
                return i;
        return -1;
    }

    private boolean indexIsValid(int index) {
        return index >= 0;
    }

    private void expandCapacity() {
        int newSize = vertices.length * 2;
        T[] newArr = (T[]) (new Object[newSize]);
        int[][] newMat = new int[newSize][newSize];

        for (int i = 0; i < vertices.length; i++) {
            newArr[i] = vertices[i];
        }

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                newMat[i][j] = adjMatrix[i][j];
            }
        }

        this.vertices = newArr;
        this.adjMatrix = newMat;
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        QueueADT<T> queue = new LinkedQueue<T>();
        UnorderedListADT<T> list = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        T vertex;
        if (!indexIsValid(getIndex(startVertex)))
            return list.iterator();

        queue.enqueue(startVertex);
        visited[getIndex(startVertex)] = true;

        while (!queue.isEmpty()) {
            try {
                vertex = queue.dequeue();
                list.addToRear(vertices[getIndex(vertex)]);
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[getIndex(vertex)][i] == 1 && !visited[i]) {
                        queue.enqueue(vertices[i]);
                        visited[i] = true;
                    }
                }
            } catch (EmptyCollectionException e) {
                e.printStackTrace();
            }
        }
        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        UnorderedListADT<T> list = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(getIndex(startVertex))) {
            return list.iterator();
        }

        dfsRecursiveHelper(startVertex, visited, list);
        return list.iterator();
    }

    private void dfsRecursiveHelper(T vertex, boolean[] visited, UnorderedListADT<T> list) {
        int vertexIndex = getIndex(vertex);
        visited[vertexIndex] = true;
        list.addToRear(vertex);

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertexIndex][i] == 1 && !visited[i]) {
                dfsRecursiveHelper(vertices[i], visited, list);
            }
        }
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return new ArrayUnorderedList<T>().iterator();
        }

        QueueADT<Integer> queue = new LinkedQueue<>();
        boolean[] visited = new boolean[numVertices];
        int[] predecessors = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            predecessors[i] = -1;
        }

        queue.enqueue(startIndex);
        visited[startIndex] = true;

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            try {
                int currentIndex = queue.dequeue();
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[currentIndex][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        predecessors[i] = currentIndex;
                        queue.enqueue(i);

                        if (i == targetIndex) {
                            found = true;
                            break;
                        }
                    }
                }
            } catch (EmptyCollectionException e) {
                e.printStackTrace();
            }
        }

        if (!found) {
            return new ArrayUnorderedList<T>().iterator();
        }

        UnorderedListADT<T> path = new ArrayUnorderedList<>();
        int current = targetIndex;
        while (current != -1) {
            path.addToFront(vertices[current]);
            current = predecessors[current];
        }

        return path.iterator();
    }
}
