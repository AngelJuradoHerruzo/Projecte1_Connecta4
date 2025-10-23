/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import entities.Casella;
import logic.Score;

/**
 *
 * @author angel
 */
public class MiniMax {
     /**
     * Aplica el algoritmo Minimax para determinar la millor jugada.
     * 
     * @param node Node arrel (tauler actual)
     * @param depth Profunditat màxima d'exploració
     * @param maximizingPlayer True si és el torn de la IA (maximitza), False si és el torn humà (minimitza)
     * @param jugadorIA Jugador de la IA (Casella.Estat)
     * @return Node que representa la millor jugada per a la IA
     */
    public Node minimax(Node node, int depth, boolean maximizingPlayer, Casella.Estat jugadorIA) {
        // Caso base: si llegamos al final de la profundidad o no hay hijos
        if (depth == 0 || node.getHijos().isEmpty()) {
            Score scoreObj = new Score(); // <-- Creamos el objeto aquí
            int valor = scoreObj.score(node.getTauler(), jugadorIA); // <-- Calculamos heurística
            node.setValor(valor);
            return node;
        }

        // Turno de la IA → maximizamos
        if (maximizingPlayer) {
            int mejorValor = Integer.MIN_VALUE;
            Node mejorNode = null;

            for (Node hijo : node.getHijos()) {
                Node resultado = minimax(hijo, depth - 1, false, jugadorIA);

                if (resultado.getValor() > mejorValor) {
                    mejorValor = resultado.getValor();
                    mejorNode = resultado; // usamos resultado para propagar valor y columna
                }
            }

            node.setValor(mejorValor);

            // Propagamos la columna de la mejor jugada
            if (mejorNode != null) {
                node.setColumnaSeleccionada(mejorNode.getColumnaSeleccionada());
            }

            return mejorNode;

        // Turno del humano → minimizamos
        } else {
            int peorValor = Integer.MAX_VALUE;
            Node peorNode = null;

            for (Node hijo : node.getHijos()) {
                Node resultado = minimax(hijo, depth - 1, true, jugadorIA);

                if (resultado.getValor() < peorValor) {
                    peorValor = resultado.getValor();
                    peorNode = resultado;
                }
            }

            node.setValor(peorValor);

            // Propagamos la columna de la peor jugada
            if (peorNode != null) {
                node.setColumnaSeleccionada(peorNode.getColumnaSeleccionada());
            }

            return peorNode;
        }
    }
}