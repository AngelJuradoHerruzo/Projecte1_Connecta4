/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

/**
 *
 * @author angel
 */
public class CreateTree {
   
    public static void treeGeneration(Node node, char jugador, int profundidad, int maxProfundidad){
        
        if(profundidad >= maxProfundidad)return; //para detenerlo si llegamos al final de le arbol
        for (int col = 0; col < 7; col++) {
            
            Tauler copy = node.getTauler().copy(); // utilizamos una copia para no modificar el tablero original
            if (!copy.isColumnFull(col)) {
                copy.jugada(col,jugador); // jugada de el jugador en el tablero copy
                Node hijo = new Node(copy); // nuevo node  con este tablero
                
                node.addhijo(hijo);
                
                char siguientejugador = (jugador == 'X')?'O' : 'X';
                createTree(hijo, siguientejugador, profundidad + 1, maxProfundidad);
                
                
            }
            
            
        }
    }
    
}

