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
import logic.Score;

/**
 *
 * @author angel
 */
public class Mecanica {
    private Tauler tauler;
    LogicaJoc logica = new LogicaJoc();
    
    
    private static final int profunditat_IA = 7;
    private int tornActual = 0;
    private int columnaNovaFicha;
    private Casella.Estat jugadorActual = Casella.Estat.HUMA;
    

public Mecanica(Tauler taulerExistente) {
    this.tauler = taulerExistente;
}
    public int getTornActual() {
    return tornActual;
}
    public int getColumnaNovaFicha() {
    return columnaNovaFicha;
}
    
    public Casella.Estat getJugadorActual() {
    return jugadorActual;
    }
    public void alternarTurno(){
        // Incrementar el turno
    tornActual++;

    // Cambiar el jugador actual según el turno
    if (tornActual % 2 == 1) {
        jugadorActual = Casella.Estat.HUMA;
    } else {
        jugadorActual = Casella.Estat.IA; // IA
    }
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
    
   public boolean comprobarGanador(Casella.Estat jugador) {
    Score score = new Score();
    score.numFitxesJuntes(tauler);
    return score.hayGanador() == jugador;
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
            if (jugadorActual == Casella.Estat.HUMA) {
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
                    columnaNovaFicha = logica.calcularMejorColumnaIA(tauler, profunditat_IA); // profundidad 7
    
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
    /**
 * Muestra las opciones al finalizar una partida.
 * Permite al jugador decidir si quiere iniciar una nueva o salir del juego.
 *
 * @param scanner Scanner para leer la entrada del usuario.
 * @return true si el jugador quiere jugar una nueva partida, false si quiere salir.
 */
private boolean preguntarNuevaPartida(Scanner scanner) {
    int opcion = 0;
    while (true) {
        System.out.println("\n¿Qué deseas hacer ahora?");
        System.out.println("1. Iniciar una nueva partida");
        System.out.println("2. Salir del juego");
        System.out.print("Selecciona una opción (1 o 2): ");

        try {
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("🟢 Nueva partida iniciada...\n");
                    return true;
                case 2:
                    System.out.println("🔴 Cerrando el juego. ¡Hasta pronto!");
                    return false;
                default:
                    System.out.println("Opción no válida. Introduce 1 o 2.");
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Introduce 1 o 2.");
            scanner.nextLine(); // limpiar entrada errónea
        }
    }
}
    
}  