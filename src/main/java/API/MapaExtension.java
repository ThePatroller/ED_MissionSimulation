package API;

import Collections.Graphs.Network;
import Collections.Lists.ArrayUnorderedList;

import java.util.Iterator;

public class MapaExtension<T> extends Network {

    public MapaExtension() {
        super();
    }

    private int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayUnorderedList<T> getAdj(T vertice) {
        ArrayUnorderedList<T> adjacencias = new ArrayUnorderedList<>();

        int index = getIndex(vertice);
        if (index == -1) {
            return adjacencias;
        }

        for (int i = 0; i < size(); i++) {
            if (adjMatrix[index][i] > 0) {
                adjacencias.addToRear((T) vertices[i]);
            }
        }

        return adjacencias;
    }

    protected T firstVertex(){
        if(!isEmpty()){
            return (T) vertices[0];
        }
        return null;
    }

    public Divisao getDivisao(String nomeDivisao) {
        int index = getIndex((T) nomeDivisao);
        if (index == -1) {
            return null;
        }
        return (Divisao) vertices[index];
    }

}
