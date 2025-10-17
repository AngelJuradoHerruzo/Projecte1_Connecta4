/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import entities.Tauler;

/**
 *
 * @author angel
 */
public class CreateTree {
   
    public static void treeGeneration(Node node, char jugador, int profundidad, int maxProfundidad){
        
        if(profundidad >= maxProfundidad)return; //para detenerlo si llegamos al final de le arbol
        for (int col = 0; col < 7; col++) {
            
            Tauler copy = new Tauler(node.getTauler()); // utilizamos una copia para no modificar el tablero original
            if (!copy.isColumnFull(col)) {
                // hacemos la  jugada según el jugador actual
                boolean jugadaExitosa;
                if (jugador == 'X') {
                    jugadaExitosa = copy.jugadaJugador_1(col); // 'X' -> JUGADOR_1
                } else {
                    jugadaExitosa = copy.jugadaJugador_2(col); // 'O' -> JUGADOR_2
                }

                // si la jugada esta bien  añadimos el nodo hijo y seguimos generando
                if (jugadaExitosa) {
                    Node hijo = new Node(copy);
                    node.addhijo(hijo);

                    // Alternamos jugador
                    char siguientejugador = (jugador == 'X') ? 'O' : 'X';
                    treeGeneration(hijo, siguientejugador, profundidad + 1, maxProfundidad);
                }
            }
        }
    }
}