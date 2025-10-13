/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package game;

import static game.Execucio.infoInici;
import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Projecte1_Grup4 {

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in); // solo un scanner
        Execucio joc = new Execucio();       // creamos el objeto del juego

        infoInici();                          // mostramos instrucciones
        joc.demanarNom(scanner);                   // pedimos el nombre usando el mismo scanner

    }
}
