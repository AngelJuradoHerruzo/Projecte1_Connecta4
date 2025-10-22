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
                    millorNode = resultat;
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
                    pitjorNode = resultat;
                }
            }

            node.setValor(pitjorValor);
            return pitjorNode;
        }
    }
    
    /*
    public Node minimaxPrueba(Node node, int depth, boolean maximizingPlayer) {
    // Caso base: si llegamos a la profundidad 0 o no hay hijos
    if (depth == 0 || node.getHijos().isEmpty()) {
        // Ya tenemos el valor en node.getValor(), no hace falta score
        return node;
    }

    if (maximizingPlayer) { // Turno de la IA -> maximizamos
        int mejorValor = Integer.MIN_VALUE;
        Node mejorNode = null;
        for (Node hijo : node.getHijos()) {
            Node resultado = minimaxPrueba(hijo, depth - 1, false);
            if (resultado.getValor() > mejorValor) {
                mejorValor = resultado.getValor();
                mejorNode = resultado; // IMPORTANTE: usar resultado, no hijo
            }
        }
        node.setValor(mejorValor);
        return mejorNode;
    } else { // Turno del humano -> minimizamos
        int peorValor = Integer.MAX_VALUE;
        Node peorNode = null;
        for (Node hijo : node.getHijos()) {
            Node resultado = minimaxPrueba(hijo, depth - 1, true);
            if (resultado.getValor() < peorValor) {
                peorValor = resultado.getValor();
                peorNode = resultado; // usar resultado
            }
        }
        node.setValor(peorValor);
        return peorNode;
    }
}
    */
}
