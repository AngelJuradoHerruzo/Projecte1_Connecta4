/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Mecanica {
    private char[][] board = new char[6][7]; // 6 filas, 7 columnas mientras no tengo entities
    private int tornActual = 1;
    private int columnaNovaFicha;
    
    public void tableroPlaceHolder(){
        for(int i=0;i<6;i++)
        for(int j=0;j<7;j++)
            board[i][j] = '.';
    }
    public void incrementarTorn(){
        tornActual++;
    }
    public int getTornActual() {
    return tornActual;
}
    public void afegirFichaMecanica(){
}
    public int getColumnaNovaFicha() {
    return columnaNovaFicha;
}
    
    public void fichaJugada(Scanner scanner, GameText textos, char ficha){    
    int columna = -1;
    boolean colocada = false;

    while (!colocada) {
        textos.afegirFichaText(); // mensaje para pedir la columna

        try {                                   //si no es un int muestra mensaje y repite la peticion
            columna = scanner.nextInt();
        } catch (Exception e) {
            textos.columnaInvalidaText(this);
            scanner.nextLine(); // limpiar entrada inválida
            continue; // volver a pedir
        }

        // Validar rango de columna
        if (columna < 0 || columna > 6) {
            textos.columnaInvalidaText(this);
            continue; // volver a pedir
        }

        // Validar si la columna está llena
        if (board[5][columna] != '.') {
            textos.columnaPlenaText(this);
            continue; // volver a pedir
        }

        // Colocar ficha en la primera posición libre de abajo hacia arriba
        for (int i = 0; i < 6; i++) {
            if (board[i][columna] == '.') {
                board[i][columna] = ficha;
                columnaNovaFicha = columna;
                textos.columnaEscollidaPjText(this); // mostrar columna elegida
                colocada = true;
                break; // salir del bucle for
            }
        }
    }
    
    }
    public boolean comprobarGanador(char ficha){        //se comprueba solo a partir de la ultima ficha, IMPORTANTE se comprueba siempre despues de añadir una ficha al tablero
        int fila = -1;

    // Buscar la fila de la última ficha colocada en la columna
    for (int i = 0; i < 6; i++) {
        if (board[i][columnaNovaFicha] == ficha) {
            fila = i;
            break;
        }
    }
    if (fila == -1) return false; // no se encontró la ficha (por seguridad)

    int contador;

    // horizontal
    contador = 1;
     // contar fichas a la izquierda
            int colActual = columnaNovaFicha - 1;
            while (colActual >= 0 && board[fila][colActual] == ficha) {
                contador++;
                colActual--;
                }
     // contar fichas a la derecha
            colActual = columnaNovaFicha + 1;
            while (colActual < 7 && board[fila][colActual] == ficha) {
            contador++;
            colActual++;
            }
            if (contador >= 4) return true;

    // vertical
        // contar fichas hacia arriba
         contador = 1;
         int filaActual = fila - 1;
         while (filaActual >= 0 && board[filaActual][columnaNovaFicha] == ficha) {
             contador++;
             filaActual--;
             }
         
        // contar fichas hacia abajo
        filaActual = fila + 1;
        while (filaActual < 6 && board[filaActual][columnaNovaFicha] == ficha) {
            contador++;
            filaActual++;
            }
    if (contador >= 4) return true;

    // diagonal \
     contador = 1;

        // arriba-izquierda
         filaActual = fila - 1;
        colActual = columnaNovaFicha - 1;
        while (filaActual >= 0 && colActual >= 0 && board[filaActual][colActual] == ficha) {
            contador++;
            filaActual--;
            colActual--;
            }
        // abajo-derecha
        filaActual = fila + 1;
        colActual = columnaNovaFicha + 1;
        while (filaActual < 6 && colActual < 7 && board[filaActual][colActual] == ficha) {
            contador++;
            filaActual++;
            colActual++;
            }
    if (contador >= 4) return true;
    
    
    // diagonal secundaria / 
    
    contador = 1;

    // arriba-derecha
    filaActual = fila - 1;
    colActual = columnaNovaFicha + 1;
    while (filaActual >= 0 && colActual < 7 && board[filaActual][colActual] == ficha) {
        contador++;
        filaActual--;
        colActual++;
    }

    // abajo-izquierda
    filaActual = fila + 1;
    colActual = columnaNovaFicha - 1;
    while (filaActual < 6 && colActual >= 0 && board[filaActual][colActual] == ficha) {
        contador++;
        filaActual++;
        colActual--;
    }

    if (contador >= 4) return true;

    return false; // no hay ganador
}
    
}
    