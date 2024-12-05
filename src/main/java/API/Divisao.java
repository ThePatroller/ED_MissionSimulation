package API;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Graphs.Graph;
import Collections.Lists.ArrayUnorderedList;

import java.util.Iterator;


public class Divisao {

    private String nome;
    private boolean entradaSaida;
    private ArrayUnorderedList<Entidade> pessoasPresentes;
    private int numPessoasPresentes;
    private ArrayUnorderedList<Item> itensPresentes;
    private int numItensPresentes;

    public Divisao(String nome) {
        this.nome = nome;
        this.entradaSaida = false;
        this.pessoasPresentes = new ArrayUnorderedList<>();
        this.numPessoasPresentes = 0;
        this.itensPresentes = new ArrayUnorderedList<>();
        this.numItensPresentes = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEmpty() {
        return getNumPessoasPresentes() == 0;
    }

    public boolean hasItens() {
        return getNumItensPresentes() > 0;
    }

    public boolean isEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(boolean entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public Entidade getPessoa(int index) {
        if(pessoasPresentes.contains(pessoasPresentes.get(index))){
            return pessoasPresentes.get(index);
        }
        return null;
    }

    public void addPessoa(Entidade pessoa){
        if(pessoa != null){
            pessoasPresentes.addToRear(pessoa);
            numPessoasPresentes++;
        }
    }

    public Entidade removePessoa(Entidade removido){
        if(pessoasPresentes.contains(removido)){
            removido = pessoasPresentes.remove(removido);
            numPessoasPresentes--;
        }
        return removido;
    }

    public Item getItem(int index) {
        if(itensPresentes.contains(itensPresentes.get(index))){
            return itensPresentes.get(index);
        }
        return null;
    }

    public void addItem(Item item){
        if(item != null){
            itensPresentes.addToRear(item);
            numItensPresentes++;
        }
    }

    public Item removeItem(){
        Item removido = null;
        if(hasItens()){
            removido = itensPresentes.removeFirst();
            numItensPresentes--;
        }
        return removido;
    }

    public int getNumItensPresentes() {
        return numItensPresentes;
    }

    public int getNumPessoasPresentes() {
        return numPessoasPresentes;
    }

    public String encontrarDivisaoAtual(Graph<String> mapa) {
        Iterator iterator = mapa.iteratorBFS(this.nome);

        while (iterator.hasNext()) {
            String divisao = (String) iterator.next(); // Get next division on the graph
            return divisao;
        }
        return null;
    }

    public boolean hasAlvo() {
        return getNumPessoasPresentes() > 0;
    }

}
