package API;

import API.Enums.TipoItem;

public class Item {

    private String divisao;
    private int pontosAdicionais;
    private TipoItem tipoItem;

    public Item(String divisao, int pontosAdicionais, TipoItem tipoItem) {
        this.divisao = divisao;
        this.pontosAdicionais = pontosAdicionais;
        this.tipoItem = tipoItem;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public int getPontosAdicionais() {
        return pontosAdicionais;
    }

    public void setPontosAdicionais(int pontosAdicionais) {
        this.pontosAdicionais = pontosAdicionais;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
    }

}
