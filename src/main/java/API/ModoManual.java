package API;

import API.Enums.TipoEntidade;
import Collections.Graphs.Graph;
import Collections.Lists.ArrayUnorderedList;
import com.sun.org.apache.xpath.internal.operations.Div;

import java.util.Iterator;

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

            }
        }
    }

    public void moverTodosInimigos() {

        MapaExtension mapa = null;
        Iterator iterator = mapa.iteratorBFS("Porta"); //SUpondo que a entrada vai ser pela porta (igual ao obj do jogador em cima)

        while (iterator.hasNext()) {
            String nomeDivisaoAtual = (String) iterator.next();
            Divisao divisaoAtual = mapa.getDivisao(nomeDivisaoAtual);

            // Itera sobre as pessoas na divisao
            if (divisaoAtual.hasAlvo()) {
                for (int i = 0; i < divisaoAtual.getNumPessoasPresentes(); i++) {
                    Entidade pessoa = divisaoAtual.getPessoa(i);

                    if (pessoa.getTipoEntidade() == TipoEntidade.INIMIGO && !jogador) {
                        ArrayUnorderedList<String> adj = mapa.getAdj(nomeDivisaoAtual);
                        if (!adj.isEmpty()) {
                            int randomIndex = (int) (Math.random() * adj.size());
                            String nomeDivisaoDestino = adj.remove(randomIndex);

                            Divisao destino = mapa.getDivisao(nomeDivisaoDestino);
                            destino.addPessoa(divisaoAtual.removePessoa(pessoa));
                        }
                    }
                }
            }

        }

    }



}
