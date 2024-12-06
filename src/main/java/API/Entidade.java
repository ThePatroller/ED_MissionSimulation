package API;

import API.Enums.TipoEntidade;


public class Entidade {

    private String nome;
    private int vida;
    private int poder;
    private String divisao;
    private TipoEntidade tipoEntidade;

    public Entidade(String nome, int vida, int poder, String divisao, TipoEntidade tipoEntidade) {
        this.nome = nome;
        this.vida = vida;
        this.poder = poder;
        this.divisao = divisao;
        this.tipoEntidade = tipoEntidade;
    }

    public Entidade(String nome, int vida, int poder, TipoEntidade tipoEntidade) {
        this.nome = nome;
        this.vida = vida;
        this.poder = poder;
        this.tipoEntidade = tipoEntidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public TipoEntidade getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(TipoEntidade tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }

    public void tomarDano(int dano){
        if(dano > 0){
            this.vida = vida - dano;
        }
    }

    public boolean estaVivo() {
        return this.getVida() > 0;
    }

}
