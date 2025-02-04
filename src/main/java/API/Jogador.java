package API;

import API.Enums.TipoEntidade;
import API.Enums.TipoItem;
import Collections.Stacks.ArrayStack;

public class Jogador extends Entidade {

    private static final int CAPACIDADE_MOCHILHA = 5;

    private ArrayStack<Item> mochila;

    public Jogador(String nome, int vida, int poder, String divisao, TipoEntidade tipoEntidade) {
        super(nome, vida, poder, divisao, tipoEntidade);
        this.mochila = new ArrayStack<>(CAPACIDADE_MOCHILHA);
    }

    public void usarColete(int pontos){
        if (pontos > 0) {
            this.setVida(getVida() + pontos);
        }
    }

    public void usarKit(){
        if (!mochila.isEmpty()) {
            Item item = mochila.pop();
            if (item.getTipoItem() == TipoItem.KIT_VIDA) {
                int vidaFinal = this.getVida() + item.getPontosAdicionais();
                if (vidaFinal > 100) {
                    this.setVida(100);
                } else {
                    this.setVida(vidaFinal);
                }
            }
        }
    }

    public void guardarKit(Item kit){
        if (mochila.size() < CAPACIDADE_MOCHILHA) {
            mochila.push(kit);
        }
    }

}
