/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import entities.Casella;
import entities.Tauler;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class LogicaJoc {

    /**
     * @param tauler Tauler actual del joc.
     * @param columnaEscollida Columna on es vol inserir la fitxa (0–6).
     * @param jugaHuma Array booleà on [0] indica si és torn humà (true) o IA (false).
     */
    public boolean insertaFitxa(Tauler tauler, int columna, Casella.Estat jugador) {
        // Recorrem la columna des de baix cap a dalt per trobar la primera casella buida
        for (int fila = tauler.getFiles() - 1; fila >= 0; fila--) {
            if (tauler.getCasella(fila, columna).getEstat() == Casella.Estat.BUIDA) {

                //Inserim la fitxa del jugador a la posició trobada
                tauler.getCasella(fila, columna).setEstat(jugador);

                // Retorna true = fitxa Inserida correctament
                return true;
            }
        }
        // Columna plena:
        System.out.println("Columna completa. Elige otra columna.");
        return false;
    }
    
    public static void imprimirTauler(Tauler tauler) {
        for (int fila = 0; fila < tauler.getFiles(); fila++) {
            for (int columna = 0; columna < tauler.getColumnes(); columna++) {
                Casella.Estat estat = tauler.getCasella(fila, columna).getEstat();

                if (estat == Casella.Estat.BUIDA) {
                    System.out.print("|   ");
                } else if (estat == Casella.Estat.JUGADOR_1) {
                    System.out.print("| X ");
                } else if (estat == Casella.Estat.JUGADOR_2) {
                    System.out.print("| O ");
                }
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
    }
    
    /*
       // Insertamos tres fichas de prueba
    tauler.insertaFitxa(tauler, 3, jugador);
    tauler.insertaFitxa(tauler, 3, jugador);
    tauler.insertaFitxa(tauler, 2, Casella.Estat.JUGADOR_2);

    // Mostramos el resultado
    tauler.imprimirTauler();
}
    */

    /**
     * Còpia profunda del tauler per garantir que els canvis no afectin l’original.
     */
    private static Tauler clonarTauler(Tauler original) {
        Tauler copia = new Tauler(original.getFiles(), original.getColumnes());
        for (int i = 0; i < original.getFiles(); i++) {
            for (int j = 0; j < original.getColumnes(); j++) {
                copia.getCasella(i, j).setEstat(original.getCasella(i, j).getEstat());
            }
        }
        return copia;
    }
    public int calcularMejorColumnaIA(Tauler taulerActual, int profundidad) {
       MiniMax miniMax = new MiniMax();

    // Usamos una copia del tablero para no modificar el original
    Node raiz = new Node(clonarTauler(taulerActual));

    // Llamamos a Minimax (maximizador = IA)
    Node mejor = miniMax.minimax(raiz, profundidad, true, Casella.Estat.JUGADOR_2);

    // Devolvemos la columna seleccionada
    return mejor.getColumnaSeleccionada();
}
}
