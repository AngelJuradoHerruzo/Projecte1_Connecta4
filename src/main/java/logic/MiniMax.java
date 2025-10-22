/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import entities.Casella;

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

        // Cas base: si hem arribat al final de la profunditat o no hi ha més fills
        if (depth == 0 || node.getHijos().isEmpty()) {
            int valor = Score.score(node.getTauler(), jugadorIA); // heurística
            node.setValor(valor);
            return node;
        }

        // Si és el torn de la IA -> maximitza el valor
        if (maximizingPlayer) {
            int millorValor = Integer.MIN_VALUE;
            Node millorNode = null;

            for (Node fill : node.getHijos()) {
                Node resultat = minimax(fill, depth - 1, false, jugadorIA);

                if (resultat.getValor() > millorValor) {
                    millorValor = resultat.getValor();
                    millorNode = fill;
                }
            }

            node.setValor(millorValor);
            return millorNode;

        // Si és el torn del jugador -> minimitza el valor
        } else {
            int pitjorValor = Integer.MAX_VALUE;
            Node pitjorNode = null;

            for (Node fill : node.getHijos()) {
                Node resultat = minimax(fill, depth - 1, true, jugadorIA);

                if (resultat.getValor() < pitjorValor) {
                    pitjorValor = resultat.getValor();
                    pitjorNode = fill;
                }
            }

            node.setValor(pitjorValor);
            return pitjorNode;
        }
    }
}
