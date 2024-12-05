package API;

import java.util.Iterator;

public class Missao<T> {

    private String cod_missao;
    private int versao;
    private MapaExtension<Divisao> mapa;
    private Alvo alvo;

    public Missao(String cod_missao, int versao, MapaExtension<Divisao> edificio, Alvo alvo) {
        this.cod_missao = cod_missao;
        this.versao = versao;
        this.mapa = edificio;
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

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public void setCod_missao(String cod_missao) {
        this.cod_missao = cod_missao;
    }

    public void setMapa(MapaExtension<Divisao> mapa) {
        this.mapa = mapa;
    }

    public void setAlvo(Alvo alvo) {
        this.alvo = alvo;
    }

    public Divisao encontrarDivisaoPorNome(String name) {
        if (mapa.isEmpty()) {
            return null;
        }

        Iterator<T> bfsIterator = mapa.iteratorBFS(mapa.firstVertex());
        while (bfsIterator.hasNext()) {
            T current = bfsIterator.next();
            if (current instanceof Divisao) {
                Divisao divisao = (Divisao) current;
                if (divisao.getNome().equals(name)) {
                    return divisao;
                }
            }
        }

        return null;
    }

}
