/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import API.Alvo;
import API.Enums.TipoAlvo;
import API.ImportExport;
import API.Missao;
import Collections.Lists.LinkedUnorderedList;

/**
 *
 * @author klotz
 */
public class Main {
    public static void main(String[] args) {
        ImportExport importExport = new ImportExport();
        LinkedUnorderedList<Missao> missoes = importExport.importMissoes();

        for (Missao missao : missoes) {
            System.out.println("Missao: " + missao.getCod_missao());
            System.out.println("Versao: " + missao.getVersao());
            System.out.println("" + missao.getAlvo().getDivisao());
        }
    }

}
