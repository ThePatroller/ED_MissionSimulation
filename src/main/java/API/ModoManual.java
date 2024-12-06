package API;

import API.Enums.TipoEntidade;
import Collections.Lists.ArrayUnorderedList;

import java.util.Random;

public class ModoManual {

    private Jogador jogador = new Jogador("Tó Cruz", 150, 25, "Porta",TipoEntidade.JOGADOR);

    //Vai ser disponibilizada uma lista com as divisões que são entrada/saida, para ser escolhida aqui.
    // Logica pra isso: rodar tds as divisoes e verificar se é entrada/saida, se sim, add em um array.
    public void entradaJogador(Divisao divisao){
        if(divisao != null){
            jogador.setDivisao(divisao.getNome());
            divisao.addPessoa(jogador);
            if(divisao.hasItens()){
                while(divisao.hasItens()){
                    jogador.guardarItem(divisao.removeItem());
                }
            }
            if(divisao.getNumPessoasPresentes() > 1){
                confrontoJogador(divisao);

            }
            if(divisao.hasAlvo()){

            }
        }
    }

    public void confrontoJogador(Divisao divisao){
        int i = 0;
        while(divisao.getPessoa(i) != null){
            if(divisao.getPessoa(i).getTipoEntidade() == TipoEntidade.INIMIGO){
                divisao.getPessoa(i).tomarDano(jogador.getPoder());
                if (divisao.getPessoa(i).getVida() <= 0){
                    divisao.removePessoa(i);
                    i--;
                }
            }
            i++;
        }
        int j = 0;
        if(divisao.getNumPessoasPresentes() > 1){
            while(divisao.getPessoa(j) != null){
                if(divisao.getPessoa(j).getTipoEntidade() == TipoEntidade.JOGADOR){
                    jogador.tomarDano(divisao.getPessoa(j).getPoder());
                    if (jogador.getVida() <= 0){
                        divisao.removePessoa(jogador);
                        fimDeJogo();
                        j--;
                    }
                }
                j++;
            }
        }
    }

    public void fimDeJogo() {
        if (!jogador.estaVivo()) {
            System.out.println("Missão Falhada, Tó Cruz bateu as botas!");
            System.exit(0);
        }
    }

    public void moverJogador(Divisao origem, Divisao destino){
        if(origem != null && destino != null){
            jogador.setDivisao(destino.getNome());
            destino.addPessoa(origem.removePessoa(jogador));
            if(destino.hasItens()){
                while(destino.hasItens()){
                    jogador.guardarItem(destino.removeItem());
                }
            }
            if(destino.getNumPessoasPresentes() > 1){
                confrontoJogador(destino);
                if(destino.getNumPessoasPresentes() > 1){
                    moverTodosInimigos();
                }
            }else{
                moverTodosInimigos();
            }
            if(destino.hasAlvo()){
                confrontoJogador(destino);
            }
        }
    }

    public void moverTodosInimigos(Divisao divisaoAtualJogador, MapaExtension mapa) {
        Random random = new Random();

        for (Divisao divisao : mapa.getTodasDivisoes()) {
            if (divisao.equals(divisaoAtualJogador)) {
                continue; // Ignore the tó room
            }
        }

        ArrayUnorderedList<Entidade> inimigos = mapa.getTodosInimigos(); // get all enemies in the build

        for (Entidade inimigo : inimigos) {
            if (inimigo.estaVivo()) {
                ArrayUnorderedList<Divisao> adjacencias = mapa.getAdj(mapa.getTodasDivisoes());

                if (!adjacencias.isEmpty()) {
                    int randomIndex = random.nextInt(adjacencias.size());
                    Divisao newDivisao = adjacencias.remove(randomIndex);

                    // update the enemies' divisions
                    mapa.getTodasDivisoes().remove(inimigo);
                    newDivisao.addPessoa(inimigo);
                    inimigo.setDivisao(newDivisao.getNome());
                }
            }
        }
    }





}
