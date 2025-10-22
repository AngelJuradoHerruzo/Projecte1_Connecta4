/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import entities.Casella;
import entities.Tauler;
import java.util.Random;
import java.util.Scanner;
import logic.LogicaJoc;
import logic.MiniMax;
import logic.Node;

/**
 *
 * @author angel
 */
public class Mecanica {
    private Tauler tauler;
    LogicaJoc logica = new LogicaJoc();
    
    private int tornActual = 0;
    private int columnaNovaFicha;
    private Casella.Estat jugadorActual = Casella.Estat.JUGADOR_1;
    

public Mecanica(Tauler taulerExistente) {
    this.tauler = taulerExistente;
}
    public int getTornActual() {
    return tornActual;
}
    public void afegirFichaMecanica(){
}
    public int getColumnaNovaFicha() {
    return columnaNovaFicha;
}
    
        // 1️⃣ Método para pedir y validar columna
    public void fichaJugada(Scanner scanner, Casella.Estat jugador) {
        int columna;
        boolean colocada = false;

        while (!colocada) {
            System.out.println("Introduce la columna donde quieres colocar la ficha (0-6):");

            try {
                columna = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Introduce un número entre 0 y 6.");
                scanner.nextLine(); // limpiar entrada inválida
                continue;
            }

            // Validar rango de columna
            if (columna < 0 || columna > 6) {
                System.out.println("Columna inválida: " + columna);
                continue;
            }

            // Validar si la columna está llena
            if (!tauler.getCasella(0, columna).estaBuida()) {
                System.out.println("Columna " + columna + " está llena.");
                continue;
            }

            // Si pasa todas las validaciones, guardamos la columna y salimos
            columnaNovaFicha = columna;
            colocada = true;
        }
    }

    // 2️⃣ Método para colocar la ficha en el tablero usando columnaNovaFicha
    public boolean colocarUltimaFicha(Casella.Estat jugador) {
        if (columnaNovaFicha < 0 || columnaNovaFicha > tauler.getColumnes() - 1) {
        return false; // seguridad, nunca debería pasar
        }

        boolean colocada = tauler.colocarFitxa(columnaNovaFicha, jugador);
        return colocada; // devuelve true si la ficha se colocó correctamente
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
    public void alternarTurno(){
        // Incrementar el turno
    tornActual++;

    // Cambiar el jugador actual según el turno
    if (tornActual % 2 == 1) {
        jugadorActual = Casella.Estat.JUGADOR_1;
    } else {
        jugadorActual = Casella.Estat.JUGADOR_2; // IA
    }
}   
    public void iniciarPartida(Scanner scanner , GameText textos){
         // Mostrar información inicial y pedir nombre
        textos.infoInici();
        textos.demanarNom(scanner);
        
             while (!tauler.estaPle()) {
        // Cambiar turno al jugador que toca y muestra el tablero disponible
        alternarTurno();
        textos.mostrarTorn(this);
        tauler.mostrarTauler();
            if (jugadorActual == Casella.Estat.JUGADOR_1) {
                // TURNO DEL USUARIO
                textos.mostrarTornPj();
                textos.afegirFichaText();
                fichaJugada(scanner, jugadorActual); // pide la columna y actualiza columnaNovaFicha (para separar metodos de peticion y añadir ficha
                colocarUltimaFicha(jugadorActual);   // coloca la ficha en el tablero
                textos.columnaEscollidaPjText(columnaNovaFicha);
                } else {
                    //  TURNO DE LA IA 
                    textos.mostrarTornIA();
                // Llamamos al método auxiliar
                    columnaNovaFicha = logica.calcularMejorColumnaIA(tauler, 6); // profundidad 6
    
                    colocarUltimaFicha(jugadorActual);
                    textos.columnaEscollidaIAText(columnaNovaFicha);
                }
        // Comprobar si hay ganador
        if (comprobarGanador(jugadorActual)) {
            tauler.mostrarTauler();
            textos.mostrarGanador(jugadorActual); // mensaje de usuario ganador
            return; // terminar la partida
        }

        // Comprobar empate
        if (tauler.estaPle()) {
            tauler.mostrarTauler();
            textos.mostrarGanador(Casella.Estat.BUIDA); // usa BUIDA para empate ya que en el switch esta en default
            return; //termina partida
        }
    }
    }
        
    }
    