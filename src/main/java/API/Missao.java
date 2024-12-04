package API;

import Collections.Graphs.Graph;

public class Missao {

    private String cod_missao;
    private int versao;
    private MapaExtension<Divisao> edificio;
    private Alvo alvo;

    public Missao(String cod_missao, int versao, MapaExtension<Divisao> edificio, Alvo alvo) {
        this.cod_missao = cod_missao;
        this.versao = versao;
        this.edificio = edificio;
        this.alvo = alvo;
    }

    public Missao() {
    }

    public String getCod_missao() {
        return cod_missao;
    }

    public int getVersao() {
        return versao;
    }

    public Alvo getAlvo() {
        return alvo;
    }

}
