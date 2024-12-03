package API;

import Collections.Lists.LinkedUnorderedList;


public class Divisao {

    private String nome;
    private boolean entradaSaida;
    private LinkedUnorderedList<Entidade> pessoasPresentes;
    private int numPessoasPresentes;
    private LinkedUnorderedList<Item> itensPresentes;
    private int numItensPresentes;

    public Divisao(String nome) {
        this.nome = nome;
        this.entradaSaida = false;
        this.pessoasPresentes = new LinkedUnorderedList<>();
        this.numPessoasPresentes = 0;
        this.itensPresentes = new LinkedUnorderedList<>();
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
        return getNumItensPresentes() == 0;
    }

    public boolean isEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(boolean entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public LinkedUnorderedList<Entidade> getPessoasPresentes() {
        return pessoasPresentes;
    }

    public void setPessoasPresentes(LinkedUnorderedList<Entidade> pessoasPresentes) {
        this.pessoasPresentes = pessoasPresentes;
    }

    public int getNumPessoasPresentes() {
        return numPessoasPresentes;
    }

    public void setNumPessoasPresentes(int numPessoasPresentes) {
        this.numPessoasPresentes = numPessoasPresentes;
    }

    public LinkedUnorderedList<Item> getItensPresentes() {
        return itensPresentes;
    }

    public void setItensPresentes(LinkedUnorderedList<Item> itensPresentes) {
        this.itensPresentes = itensPresentes;
    }

    public int getNumItensPresentes() {
        return numItensPresentes;
    }

    public void setNumItensPresentes(int numItensPresentes) {
        this.numItensPresentes = numItensPresentes;
    }

}
