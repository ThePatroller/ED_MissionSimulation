package API;

import API.Divisao;
import API.Entidade;
import API.Enums.TipoEntidade;
import API.Jogador;
import API.MapaExtension;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Lists.ArrayUnorderedList;

import java.util.Random;

public class Simulacao {

    private Jogador jogador;
    private MapaExtension mapa;

    public Simulacao(Jogador jogador, MapaExtension mapa) {
        this.jogador = jogador;
        this.mapa = mapa;
    }



    public ArrayUnorderedList<Divisao> entradasESaidas() {
        ArrayUnorderedList<Divisao> divisoes = new ArrayUnorderedList<>();
        ArrayUnorderedList<Divisao> entradasSaidas = new ArrayUnorderedList<>();

        for (Divisao divisao : divisoes) {
            if (divisao.isEntradaSaida()) {
                entradasSaidas.addToFront(divisao);
            }
        }
        return entradasSaidas;
    }

    public void mostrarEntradasESaidas() {
        try {
            for (Divisao divisao : entradasESaidas()) {
                System.out.println("Entrada/Saída: " + divisao.getNome());
            }
        } catch (EmptyCollectionException e) {
            System.out.println("Nenhuma entrada ou saída foi encontrada."); // maybe e.printStackTrace() ?
        }
    }


    public void iniciarSimulacao() {
        while (checkGameStatus()) {
            executarFaseJogador();
            executarFaseInimigos();
        }
    }

    public void executarFaseJogador() {

    }

    protected void executarFaseInimigos() {
        moverTodosInimigos(mapa);
    }

    public boolean checkGameStatus() {
        if (!jogador.estaVivo()) {
            System.out.println("O jogador não sobreviveu, missão falhada!");
            return false;
        }

        boolean todosEliminados = true;
        for (Entidade inimigo : mapa.getTodosInimigos()) {
            if (inimigo.estaVivo()) {
                todosEliminados = false;
                break;
            }
        }

        if (todosEliminados) {
            System.out.println("Todos os iinimigos foram eliminados. Missão cumprida com successo! Parabéns.");
            return true;
        }
        return false;
    }




    public void moverTodosInimigos(MapaExtension mapa) {
        String divisaoAtualJogador = jogador.getDivisao();
        Random random = new Random();

        for (Divisao divisao : mapa.getTodasDivisoes()) {
            if (divisao.getNome().equals(divisaoAtualJogador)) {
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

    public void entradaJogador(Divisao divisao){
        if(divisao != null && divisao.isEntradaSaida()) {
            jogador.setDivisao(divisao.getNome());
            divisao.addPessoa(jogador);
            if(divisao.hasItens()){
                while(divisao.hasItens()) {
                    jogador.guardarItem(divisao.removeItem());
                }
            }
            if(divisao.getNumPessoasPresentes() > 1){
                confrontoJogador(divisao);
            }
            if(divisao.hasAlvo()) {

            }
        } else {
            throw new EmptyCollectionException("Divisão " + divisao.getNome() + " não é uma entrada/saída.");
        }
    }

    public void confrontoJogador(Divisao divisao){
        int i = 0;
        while(divisao.getPessoa(i) != null) {
            if(divisao.getPessoa(i).getTipoEntidade() == TipoEntidade.INIMIGO) {
                divisao.getPessoa(i).tomarDano(jogador.getPoder());
                if (divisao.getPessoa(i).getVida() <= 0) {
                    divisao.removePessoa(i);
                    i--;
                }
            }
            i++;
        }
        int j = 0;
        if(divisao.getNumPessoasPresentes() > 1) {
            while(divisao.getPessoa(j) != null) {
                if(divisao.getPessoa(j).getTipoEntidade() == TipoEntidade.JOGADOR) {
                    jogador.tomarDano(divisao.getPessoa(j).getPoder());
                    if (jogador.getVida() <= 0) {
                        divisao.removePessoa(jogador);
                        fimDeJogo();
                        j--;
                    }
                }
                j++;
            }
        }
        System.out.println("Vida restante: " + jogador.getVida());
    }

    public void fimDeJogo() {
        if (!jogador.estaVivo()) {
            System.out.println("Missão Falhada, Tó Cruz bateu as botas!");
            System.exit(0);
        }
    }

    public void resgatarAlvo(Divisao divisao) {
        if (divisao.hasAlvo()) {
            if (divisao.getNumPessoasPresentes() == 0) {
                jogador.resgatarItem();
            } else {
                confrontoJogador(divisao);
            }
        }
    }




}
