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

    public int getTornActual() { return tornActual; }
    public int getColumnaNovaFicha() { return columnaNovaFicha; }
    public Casella.Estat getJugadorActual() { return jugadorActual; }

    public void alternarTurno() {
        tornActual++;
        if (tornActual % 2 == 1) jugadorActual = Casella.Estat.HUMA;
        else jugadorActual = Casella.Estat.IA;
    }

    public void fichaJugada(Scanner scanner, Casella.Estat jugador) {
        int columna;
        boolean colocada = false;
        while (!colocada) {
            System.out.println("Introdueix la columna on vols colocar la ficha (0-6):");
            try { columna = scanner.nextInt(); } 
            catch (Exception e) { System.out.println("Entrada inválida."); scanner.nextLine(); continue; }

            if (columna < 0 || columna > 6) { System.out.println("Columna inválida: " + columna); continue; }
            if (!tauler.getCasella(0, columna).estaBuida()) { System.out.println("Columna plena."); continue; }

            columnaNovaFicha = columna;
            colocada = true;
        }
    }

    public boolean colocarUltimaFicha(Casella.Estat jugador) {
        if (columnaNovaFicha < 0 || columnaNovaFicha > tauler.getColumnes() - 1) return false;
        return tauler.colocarFitxa(columnaNovaFicha, jugador);
    }

    public boolean comprobarGanador(Casella.Estat jugador) {
        Score score = new Score();
        score.numFitxesJuntes(tauler);
        return score.hayGanador() == jugador;
    }

public void iniciarPartida(Scanner scanner, GameText textos) {
    textos.infoInici();
    textos.demanarNom(scanner);

    while (!tauler.estaPle()) {
        alternarTurno();
        textos.mostrarTorn(this);
        tauler.mostrarTauler();

        if (jugadorActual == Casella.Estat.HUMA) {
            // TORN DE L'USUARI
            textos.mostrarTornPj();
            textos.afegirFichaText();
            fichaJugada(scanner, jugadorActual);
            colocarUltimaFicha(jugadorActual);
            textos.columnaEscollidaPjText(columnaNovaFicha);
        } else {
            // TORN DE LA IA
            textos.mostrarTornIA();
            columnaNovaFicha = logica.calcularMejorColumnaIA(tauler, profunditat_IA);
            colocarUltimaFicha(jugadorActual);
            textos.columnaEscollidaIAText(columnaNovaFicha);
        }

        // Comprovar guanyador
        if (comprobarGanador(jugadorActual)) {
            tauler.mostrarTauler();
            textos.mostrarGanador(jugadorActual); // missatge de guanyador
            break;
        }

        // Comprovar empat
        if (tauler.estaPle()) {
            tauler.mostrarTauler();
            textos.mostrarGanador(Casella.Estat.BUIDA); // missatge d'empat
            break;
        }
    }

    // Després del bucle, preguntar si iniciar nova partida
    boolean novaPartida = preguntarNuevaPartida(scanner);
    if (novaPartida) {
        this.tauler = new Tauler();          // Reiniciar tauler
        this.tornActual = 0;                  // Reiniciar torn
        this.jugadorActual = Casella.Estat.HUMA;
        iniciarPartida(scanner, textos);      // Tornar a iniciar partida
    } else {
        System.out.println("Gràcies per jugar!");
    }
}
    // 🔹 Método para preguntar nueva partida (private o public según uso)
    private boolean preguntarNuevaPartida(Scanner scanner) {
        int opcion = 0;
        while (true) {
            System.out.println("\n¿Qué vols fer  ara?");
            System.out.println("1. Iniciar una nova partida");
            System.out.println("2. Sortir del joc");
            System.out.print("Selecciona una opció (1 o 2): ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1: return true;
                    case 2: return false;
                    default: System.out.println("Opció no válida. Introdueix 1 o 2."); break;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Introdueix 1 o 2.");
                scanner.nextLine();
            }
        }
    }
}