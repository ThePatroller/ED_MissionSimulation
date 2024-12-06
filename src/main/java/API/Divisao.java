package API;

import API.Enums.TipoEntidade;
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

    public Item removeItem(Item item) { //If a specific item needs to be removed (but only the top can be) it can be useful later
        if (itensPresentes.contains(item)) {
            itensPresentes.remove(item);
            numItensPresentes--;
            return item;
        }
        return null;
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

    public String encontrarDivisaoAtual1(Graph<Divisao> mapa) {
        Iterator<Divisao> iterator = mapa.iteratorBFS(this);

        while (iterator.hasNext()) {
            Divisao divisao = iterator.next();
            for (int i = 0; i < divisao.getNumPessoasPresentes(); i++) {
                Entidade pessoa = divisao.getPessoa(i);
                if (pessoa != null && pessoa.equals(TipoEntidade.JOGADOR)) {
                    return divisao.getNome();
                }
            }
        }
        return null;
    }

    public boolean hasAlvo() {
        return getNumPessoasPresentes() > 0;
    }

}
