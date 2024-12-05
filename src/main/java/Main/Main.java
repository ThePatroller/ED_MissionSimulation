/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import API.*;
import API.Enums.TipoAlvo;
import Collections.Lists.ArrayUnorderedList;

import java.util.Iterator;

/**
 *
 * @author klotz
 */
public class Main<T> {
    public static void main(String[] args) {
        ImportExport importExport = new ImportExport();
        ArrayUnorderedList<Missao> missoes = importExport.importMissoes();

        for (Missao missao : missoes) {
            System.out.println("Missao: " + missao.getCod_missao());
            System.out.println("Versao: " + missao.getVersao());
            System.out.println("Alvo na divisão: " + missao.getAlvo().getDivisao());

            Divisao heliporto = missao.encontrarDivisaoPorNome("Heliporto");
            System.out.println(heliporto.getPessoa(0).getNome());

        }
    }
}
