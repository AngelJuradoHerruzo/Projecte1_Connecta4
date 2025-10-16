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
    public boolean primeraJugada(Scanner scanner, GameText textos, char ficha){    
        textos.afegirFichaText();
        columnaNovaFicha = scanner.nextInt();  //pide donde quiere jugar el usuario
        int columna = -1;
    try {
        columna = scanner.nextInt();          // lee la columna
        columnaNovaFicha(columna);         // guardar para los textos
    } catch (Exception e) {
        textos.columnaInvalidaText(this);
        scanner.nextLine(); // limpiar entrada inválida
        return false;
    }

    // Validar rango de columna
    if(columna < 0 || columna > 6){
        textos.columnaInvalidaText(this);
        return false;
    }

    // Validar si la columna está llena
    if(board[5][columna] != '.'){
        textos.columnaPlenaText(this);
        return false;
    }

    // Colocar ficha en la primera posición libre
    for(int i = 0; i < 6; i++){
        if(board[i][columna] == '.'){
            board[i][columna] = ficha;
            columnaNovaFicha = columna;
            textos.columnaEscollidaPjText(this); // mostrar columna elegida
            return true; // ficha colocada correctamente
        }
    }

    return false; // por si acaso, no debería llegar aquí
    }
    
}
