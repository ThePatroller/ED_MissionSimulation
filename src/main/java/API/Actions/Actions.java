package API.Actions;

import API.Inimigo;
import API.Item;
import API.Missao;
import Collections.Lists.LinkedList;
import Collections.Stacks.ArrayStack;


public class Actions implements ActionI {
    private LinkedList<Inimigo> inimigos; // Maybe will need to run all enemies
    private ArrayStack<Item> itens; // WIll only work with the top one (LIFO)

    public Actions() {
        this.inimigos = new LinkedList<>();
        this.itens = new ArrayStack<>();
    }


    @Override
    public void executar(Missao missao) {

    }

    @Override
    public void removerInimigosDerrotados() {
        for (Inimigo inimigo : inimigos) {
            if (inimigo.foiDerrotado()) {
                inimigos.remove(inimigo);
                System.out.println("Inimigo removido: " + inimigo.getNome());
            }
        }
    }

    @Override
    public void removerItemDaSala() {
        if (itens.isEmpty()) {
            Item itemRemovido = itens.pop();
            System.out.println("Item removido: " + itemRemovido.getTipoItem());
        } else {
            System.out.println("Nenhum item para remover");
        }
    }

    @Override
    public void iniciarCombate() {

    }
}
