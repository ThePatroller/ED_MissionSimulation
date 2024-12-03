package API;

import API.Enums.TipoEntidade;
import Collections.Stacks.ArrayStack;

public class Jogador extends Entidade {

    private static final int CAPACIDADE_MOCHILHA = 5;

    private ArrayStack<Item> mochila;

    public Jogador(String nome, int vida, int poder, String divisao, TipoEntidade tipoEntidade) {
        super(nome, vida, poder, divisao, tipoEntidade);
        this.mochila = new ArrayStack<>(CAPACIDADE_MOCHILHA);
    }

    public void usarColete(int pontos){

    }

    public void usarKit(){

    }

    public void guardarKit(Item kit){

    }

}
