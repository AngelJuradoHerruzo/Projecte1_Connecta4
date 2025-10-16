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
public class GameText {
    private String pjName;
    
    public void infoInici() {
        System.out.println("""
                           L\u2019objectiu del joc \u00e9s connectar quatre fitxes seguides en l\u00ednia horitzontal, vertical o diagonal abans que el rival.
                           En cada torn, el jugador ha d\u2019introduir el n\u00famero de la columna (de l\u20191 al 6) on vol deixar caure la seva fitxa.
                           Les fitxes cauen fins ocupar la posici\u00f3 lliure m\u00e9s baixa de la columna; si la columna est\u00e0 plena, cal escollir-ne una altra.""");
    }
    
    public void demanarNom(Scanner scanner){
        System.out.println("Insereix el teu nom");
        pjName = scanner.nextLine();
    }
    
    public  void afegirFichaText(){
        System.out.println("Escull on vols col·locar la teva ficha (escriu un nombre enter entre 1-7)");
    }
    
    public void mostrarTorn(Mecanica game){
    System.out.println("Torn actual" + game.getTornActual());
    }
    public void mostrarTornPj(){
        System.out.println("És el torn de "+pjName);
    }
    public void mostrarTornCPU(){
        System.out.println("És el torn de la CPU");
    }
    public void columnaPlenaText(Mecanica game){
        System.out.println("Aquesta columna ("+game.getColumnaNovaFicha()+") esta plena, si us plau seleccioni una altra");
    }
    public void columnaInvalidaText(Mecanica game){
        System.out.println("Aquesta columna ("+game.getColumnaNovaFicha()+") no és una jugada valida, si us plau seleccioni un nombre entre 1-7");
    }
    public void columnaEscollidaPjText(Mecanica game){
        System.out.println(pjName+ " ha escollit la columna: "+game.getColumnaNovaFicha());
    }
    public void columnaEscollidaCPUText(Mecanica game){
        System.out.println("La CPU ha escollit la columna: "+game.getColumnaNovaFicha());
    }
    
    public void pjWinnerText(){
        System.out.println(pjName + " ha guanyat!");
    }
    public void cpuWinnerText(){
        System.out.println("La màquina ha guanyat, per tant " + pjName + " ha perdut");
    }
}
