package API;

import API.Enums.TipoEntidade;
import com.sun.org.apache.xpath.internal.operations.Div;

public class ModoManual {

    private Jogador jogador = new Jogador("Tó Cruz", 150, 25, TipoEntidade.jogador);

    //Vai ser disponibilizada uma lista com as divisões que são entrada/saida, para ser escolhida aqui.
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
                confrontoJogador(divisao, jogador);

            }
        }
    }

    public void confrontoJogador(Divisao divisao, Entidade primeiroAtacante){
        int i = 0;
        while(divisao.getPessoa(i) != null){
            if(divisao.getPessoa(i).getTipoEntidade() == TipoEntidade.inimigo){
                divisao.getPessoa(i).tomarDano(primeiroAtacante.getPoder());
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
                if(divisao.getPessoa(j).getTipoEntidade() == TipoEntidade.inimigo){
                    primeiroAtacante.tomarDano(divisao.getPessoa(j).getPoder());
                    if (primeiroAtacante.getVida() <= 0){
                        divisao.removePessoa(j);
                        fimDeJogo();
                        j--;
                    }
                }
                j++;
            }
        }
    }

}
