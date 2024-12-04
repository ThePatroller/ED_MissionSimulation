package API.Actions;

import API.Missao;

public interface ActionI {

    void executar(Missao missao);
    //boolean foiDerrotado();
    void removerInimigosDerrotados();
    void removerItemDaSala();
    void iniciarCombate();

    //void mudarDeSala();
    //void movimentarInimigos();
    //boolean jogadorNaSala();
    //boolean refemNaSala();
    //boolean inimigosNaSala();
    //void resgatarAlvo();
    //void naoMexer();
    //void melhorCaminhoAlvo(); Modo manual
    //void melhorCaminhoKitVida(); Modo manual
    //void mostrarMapa();
    //void mostrarRelatorios(); Mostra simulações manuais feitas anteriormente

}
