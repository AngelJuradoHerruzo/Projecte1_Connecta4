/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import entities.Casella;
import java.util.Scanner;

/**
 *
 * @author angel
 */
public class GameText {
       private String pjName;

       
    public void infoInici() {
        System.out.println("""
            L’objectiu del joc és connectar quatre fitxes seguides en línia horitzontal, vertical o diagonal abans que el rival.
            En cada torn, el jugador ha d’introduir el número de la columna (de l’0 al 6) on vol deixar caure la seva fitxa.
            Les fitxes cauen fins ocupar la posició lliure més baixa de la columna; si la columna està plena, cal escollir-ne una altra.
        """);
    }

    public void demanarNom(Scanner scanner) {
        System.out.print("Introdueix el teu nom: ");
        pjName = scanner.nextLine().trim();
        if (pjName.isEmpty()) pjName = "Jugador 1";
        System.out.println("Benvingut/da, " + pjName + "!\n");
    }

    // Mensajes de turnos
    public void mostrarTorn(Mecanica game) {
        System.out.println("Torn actual: " + game.getTornActual());
    }

    public void mostrarTornPj() {
        System.out.println("És el torn de " + pjName + ".");
    }

    public void mostrarTornIA() {
        System.out.println("És el torn de la IA. La màquina està pensant la seva jugada...");
    }

    //Mensajes de columnas 
    public void afegirFichaText() {
        System.out.println("Escull on vols col·locar la teva fitxa (escriu un nombre enter entre 0 i 6):");
    }

    public void columnaPlenaText(int columna) {
        System.out.println("⚠️  La columna " + columna + " està plena. Si us plau, tria’n una altra.");
    }

    public void columnaInvalidaText(int columna) {
        System.out.println("⚠️  La columna " + columna + " no és vàlida. Escriu un nombre entre 0 i 6.");
    }
    public void columnaInvalida2Text(){
        System.out.println("⚠️  La columna no és vàlida. Escriu un nombre entre 0 i 6.");
    }
    public void columnaEscollidaPjText(int columna) {
        System.out.println(pjName + " ha escollit la columna: " + columna);
    }

    public void columnaEscollidaIAText(int columna) {
        System.out.println("La IA ha escollit la columna: " + columna);
    }

    //Resultado final
    public void mostrarGanador(Casella.Estat jugador) {
        System.out.println();
        switch (jugador) {
            case JUGADOR_1 -> System.out.println("🏆 " + pjName + " ha guanyat la partida!");
            case JUGADOR_2 -> System.out.println("💻 La IA ha guanyat. Serà la propera vegada, " + pjName + "!");
            default -> System.out.println("🤝 Empat! Bona partida!");
        }
        System.out.println();
    }

    // Getter del nombre del jugador 
    public String getNomJugador() {
        return pjName;
    }
}