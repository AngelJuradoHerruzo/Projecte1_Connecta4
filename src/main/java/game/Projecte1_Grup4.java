/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package game;

import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Projecte1_Grup4 {

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in); // solo un scanner
                GameText jocText = new GameText();       // para usar metodos de otras clases
                Mecanica jocMecanica = new Mecanica();  // para usar metodos de otras clases

        jocText.infoInici();                          // mostramos instrucciones
        jocText.demanarNom(scanner);                   // pedimos el nombre usando el mismo scanner

    }
}
