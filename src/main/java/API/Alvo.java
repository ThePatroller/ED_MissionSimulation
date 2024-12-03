package API;

import API.Enums.TipoAlvo;

public class Alvo {

    private String divisao;
    private TipoAlvo tipoAlvo;

    public Alvo(String divisao, TipoAlvo tipoAlvo) {
        this.divisao = divisao;
        this.tipoAlvo = tipoAlvo;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public TipoAlvo getTipoAlvo() {
        return tipoAlvo;
    }

    public void setTipoAlvo(TipoAlvo tipoAlvo) {
        this.tipoAlvo = tipoAlvo;
    }

}
