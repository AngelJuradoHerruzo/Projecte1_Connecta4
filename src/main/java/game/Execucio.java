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
public class Execucio {
    private int tornActual = 1;
    private String pjName;
    
    public static void infoInici() {
        System.out.println("""
                           L\u2019objectiu del joc \u00e9s connectar quatre fitxes seguides en l\u00ednia horitzontal, vertical o diagonal abans que el rival.
                           En cada torn, el jugador ha d\u2019introduir el n\u00famero de la columna (de l\u20191 al 6) on vol deixar caure la seva fitxa.
                           Les fitxes cauen fins ocupar la posici\u00f3 lliure m\u00e9s baixa de la columna; si la columna est\u00e0 plena, cal escollir-ne una altra.""");
    }
    
    public void demanarNom(Scanner scanner){
        System.out.println("Insereix el teu nom");
        pjName = scanner.nextLine();
    }
    
    public static void afegirFicha(){
        
    }
    
    public void mostrarTorn(){
    System.out.println("Torn actual" + tornActual);
    }
    
    public void incrementarTorn(){
        tornActual++;
    }
}
