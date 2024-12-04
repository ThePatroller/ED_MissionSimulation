package API;

import API.Enums.TipoEntidade;

public class Inimigo extends Entidade{

    public Inimigo(String nome, int vida, int poder, String divisao, TipoEntidade tipoEntidade) {
        super(nome, vida, poder, divisao, tipoEntidade);
    }

    public boolean foiDerrotado() {
        return this.getVida() < 0;
    }

}
