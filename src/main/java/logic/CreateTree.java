/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import entities.Casella;
import entities.Tauler;

/**
 *
 * @author angel
 */
public class CreateTree {
  
     public static void treeGeneration(Node node, Casella.Estat jugador, int profundidad, int maxProfundidad) {
        // Caso base: si llegamos a la profundidad máxima, detenemos la recursión
        if (profundidad >= maxProfundidad) return;

        for (int col = 0; col < 7; col++) {
            Tauler copy = new Tauler(node.getTauler()); // Copiamos el tablero actual

            // Si la columna no está llena
            if (!copy.isColumnFull(col)) {
                boolean jugadaExitosa;

                // Realizamos la jugada según el jugador actual
                if (jugador == Casella.Estat.IA) {
                jugadaExitosa = copy.jugadaJugador_2(col); // IA → X
                } else {
                jugadaExitosa = copy.jugadaJugador_1(col); // HUMA → O
                }

                // Si la jugada es válida, creamos un nuevo nodo hijo
                if (jugadaExitosa) {
                    Node hijo = new Node(copy);

                    // 🔹 Asignamos la columna de la jugada al hijo
                    hijo.setColumnaSeleccionada(col);

                    // Añadimos el hijo al nodo actual
                    node.addHijo(hijo);

                    // Alternamos el jugador y generamos los hijos del siguiente turno
                    Casella.Estat siguientejugador = (jugador == Casella.Estat.IA) ? Casella.Estat.HUMA : Casella.Estat.IA;
                    treeGeneration(hijo, siguientejugador, profundidad + 1, maxProfundidad);
                }
            }
        }
    }
}