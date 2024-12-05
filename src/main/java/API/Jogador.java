package API;

import API.Enums.TipoEntidade;
import API.Enums.TipoItem;
import Collections.Stack.LinkedStack;

public class Jogador extends Entidade {

    private LinkedStack<Item> mochila;
    private int vidaMaxima;

    public Jogador(String nome, int vida, int poder, TipoEntidade tipoEntidade) {
        super(nome, vida, poder, tipoEntidade);
        this.vidaMaxima = vida;
        this.mochila = new LinkedStack<>();
    }

    public void usarColete(int pontos){
        if (pontos > 0) {
            vidaMaxima = vidaMaxima + pontos;
        }
    }

    public void usarKit(){
        if (!mochila.isEmpty()) {
            Item item = mochila.pop();
            int vidaFinal = this.getVida() + item.getPontosAdicionais();
            if (vidaFinal > vidaMaxima) {
                this.setVida(vidaMaxima);
            } else {
                this.setVida(vidaFinal);
            }

        }
    }

    public void guardarKit(Item kit){
        if(kit != null) {
            mochila.push(kit);
        }
    }

    public void guardarItem(Item item){
        if(item != null){
            if(item.getTipoItem() == TipoItem.colete){
                usarColete(item.getPontosAdicionais());
            }
            else{
                guardarKit(item);
            }
        }
    }

}
