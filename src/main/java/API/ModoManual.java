package API;

import API.Enums.TipoEntidade;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Lists.ArrayUnorderedList;

public class ModoManual extends Simulacao {

    private Jogador jogador;
    private Simulacao simulacao;

    public ModoManual(Jogador jogador, MapaExtension mapa) {
        super(jogador, mapa);
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
            if(destino.getNumPessoasPresentes() > 1) {
                simulacao.confrontoJogador(destino);
                if(destino.getNumPessoasPresentes() > 1) {
                    simulacao.moverTodosInimigos();
                }
            }else{
                simulacao.moverTodosInimigos();
            }
            if(destino.hasAlvo()) {
                simulacao.confrontoJogador(destino);
            }
        }
    }

    public void fazerNada() { // Improve the logic to make it finish the turn (to be defined on Simulacao)
        return;
    }


    public void escolherProximaAcao() {
        int option = 0;

        System.out.println("---------- ESCOLHA UMA AÇÃO GUERREIRO! ----------");
        System.out.println("1. Usar Kit de Vida.");
        System.out.println("2. Usar Colete.");
        System.out.println("3. Guardar Item.");
        System.out.println("4. Confrontar Inimigos na Sala.");
        System.out.println("5. Fazer nada.");

        switch (option) {
            case 1:
                jogador.usarKit();
                break;
            case 2:
                jogador.usarColete(); // Need to parse a parameter
                break;
            case 3:
                jogador.guardarItem(); // Need to parse a parameter
                break;
            case 4:
                simulacao.confrontoJogador(jogador.getDivisao());
                break;
            case 5:
                fazerNada();
                break;
            default:
                System.out.println("Opção inválida.");
        }

    }




}
