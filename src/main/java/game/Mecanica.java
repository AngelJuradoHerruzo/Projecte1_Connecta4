/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import entities.Casella;
import entities.Tauler;
import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Mecanica {
    private Tauler tauler = new Tauler();
    private int tornActual = 1;
    private int columnaNovaFicha;
    

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
    
    public void fichaJugada(Scanner scanner, GameText textos, Casella.Estat jugador){    
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
        if (tauler.getCasella(0, columna).estaBuida() == false) {
            textos.columnaPlenaText(this);
            continue; // volver a pedir
        }

        
            // intentar colocar la ficha
            if (tauler.colocarFitxa(columna, jugador)) {
                columnaNovaFicha = columna;
                textos.columnaEscollidaPjText(this);
                colocada = true;
            } else {
                textos.columnaPlenaText(this);
            }
        }
    
    }
    public boolean comprobarGanador(Casella.Estat jugador){        //se comprueba solo a partir de la ultima ficha, IMPORTANTE se comprueba siempre despues de añadir una ficha al tablero
        int fila = -1;
        int files = tauler.getFiles();
        int columnes = tauler.getColumnes();

    // Buscar la fila de la última ficha colocada en la columna
    for (int i = 0; i < files; i++) {
        if (tauler.getCasella(i, columnaNovaFicha).getEstat() == jugador) {
            fila = i;
            break;
        }
    }
    if (fila == -1) return false; // no se encontró la ficha (por seguridad) en teoria siempre se pude encontarr por el control de errrores del metodo colocar ficha

    int contador;

    // horizontal
    contador = 1;
     // contar fichas a la izquierda
            int colActual = columnaNovaFicha - 1;
            while (colActual >= 0 && tauler.getCasella(fila, colActual).getEstat() == jugador) {
                contador++;
                colActual--;
                }
     // contar fichas a la derecha
            colActual = columnaNovaFicha + 1;
            while (colActual < columnes && tauler.getCasella(fila, colActual).getEstat() == jugador) {
            contador++;
            colActual++;
            }
            if (contador >= 4) return true;

    // vertical
        // contar fichas hacia arriba
         contador = 1;
         int filaActual = fila - 1;
         while (filaActual >= 0 && tauler.getCasella(filaActual, columnaNovaFicha).getEstat() == jugador) {
             contador++;
             filaActual--;
             }
         
        // contar fichas hacia abajo
        filaActual = fila + 1;
        while (filaActual < files && tauler.getCasella(filaActual, columnaNovaFicha).getEstat() == jugador) {
            contador++;
            filaActual++;
            }
    if (contador >= 4) return true;

    // diagonal \
     contador = 1;

        // arriba-izquierda
         filaActual = fila - 1;
        colActual = columnaNovaFicha - 1;
        while (filaActual >= 0 && colActual >= 0 && tauler.getCasella(filaActual, colActual).getEstat() == jugador) {
            contador++;
            filaActual--;
            colActual--;
            }
        // abajo-derecha
        filaActual = fila + 1;
        colActual = columnaNovaFicha + 1;
        while (filaActual < files && colActual < columnes && tauler.getCasella(filaActual, colActual).getEstat() == jugador) {
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
    while (filaActual >= 0 && colActual < columnes && tauler.getCasella(filaActual, colActual).getEstat() == jugador) {
        contador++;
        filaActual--;
        colActual++;
    }

    // abajo-izquierda
    filaActual = fila + 1;
    colActual = columnaNovaFicha - 1;
    while (filaActual < files && colActual >= 0 && tauler.getCasella(filaActual, colActual).getEstat() == jugador ) {
        contador++;
        filaActual++;
        colActual--;
    }

    if (contador >= 4) return true;

    return false; // no hay ganador
    }
    public void repetirJugada(){
        
    }

    
}
    