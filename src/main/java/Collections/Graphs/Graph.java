package Collections.Graphs;

import Collections.Lists.ArrayUnorderedList;
import Collections.Queues.LinkedQueue;
import Collections.Stack.LinkedStack;

import java.util.Iterator;

public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;

    protected int numVertices;
    protected int[][] adjMatrix;
    protected T[] vertices;

    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    private void expandCapacity() {
        T[] newVertices = (T[]) (new Object[vertices.length * 2]);
        int[][] newAdjMatrix = new int[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            newVertices[i] = vertices[i];
        }

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                newAdjMatrix[i][j] = adjMatrix[i][j];
            }
        }

        vertices = newVertices;
        adjMatrix = newAdjMatrix;
    }

    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;

        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = 0;
            adjMatrix[i][numVertices] = 0;
        }

        numVertices++;
    }

    public int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    private boolean indexIsValid(int index) {
        return index >= 0 && index < numVertices;
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

    public void removeVertex(T vertex) {
        int posicao = getIndex(vertex);
        if (posicao != -1) {
            if (numVertices > 1) {
                vertices[posicao] = vertices[numVertices-1];
                for (int i=0; i < numVertices; i++){
                    adjMatrix[i][posicao] = adjMatrix[i][numVertices-1];
                    adjMatrix[posicao][i] = adjMatrix[numVertices-1][i];
                }
                numVertices--;
                vertices[numVertices] = null;
                for (int i = 0; i < numVertices; i++) {
                    adjMatrix[numVertices][i] = 0;
                    adjMatrix[i][numVertices] = 0;
                }
            }else{
                numVertices = 0;
                vertices[0] = null;
                adjMatrix[0][0] = 0;
            }
        }
    }

    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public void removeEdge(int index1, int index2){
        if(indexIsValid(index1) && indexIsValid(index2)){
            adjMatrix[index1][index2] = 0;
            adjMatrix[index2][index1] = 0;
        }
    }

    public boolean isEmpty() {
        return numVertices == 0;
    }

    public int size() {
        return numVertices;
    }

    public Iterator iteratorBFS(T startVertex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

        int startIndex = getIndex(startVertex);
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++)
            visited[i] = false;

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x]);
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x][i] == 1 && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    public Iterator iteratorDFS(T startVertex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];

        int startIndex = getIndex(startVertex);
        if (!indexIsValid(startIndex)){
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x][i] == 1 && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty())
                traversalStack.pop();
        }

        return resultList.iterator();
    }

    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        return null;
    }

    public boolean isConnected() {
        return false;
    }
}
