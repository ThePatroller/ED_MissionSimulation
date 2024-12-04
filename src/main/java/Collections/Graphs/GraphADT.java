package Collections.Graphs;

import java.util.Iterator;

import Collections.Exceptions.EmptyCollectionException;

public interface GraphADT<T> {

    public void addVertex(T vertex);

    public void removeVertex(T vertex) throws EmptyCollectionException;

    public void addEdge(T vertex1, T vertex2);

    public void removeEdge(T vertex1, T vertex2);

    public Iterator<T> iteratorBFS(T startVertex);

    public Iterator<T> iteratorDFS(T startVertex);

    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex);

    public boolean isEmpty();

    public boolean isConnected();

    public int size();

    public String toString();

}
