/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package game;

import entities.Tauler;
import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Projecte1_Grup4 {

    public static void main(String[] args) {  
        // Crear objetos necesarios
        Scanner scanner = new Scanner(System.in);
        Tauler tauler = new Tauler(); 
        Mecanica game = new Mecanica(tauler);
        GameText textos = new GameText();

        // Iniciar la partida
        game.iniciarPartida(scanner, textos);

        // Cerrar el scanner al final
        scanner.close();
    }
}
