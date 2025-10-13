/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Achraf
 */
public class Node {
    private Tauler tauler;           // estat del tauler en aquest node
    private List<Node> hijos;        // llista de fills

    // Constructor
    public Node(Tauler tauler) {
        this.tauler = tauler;
        this.hijos = new ArrayList<>();
    }

    // Getters i setters
    public Tauler getTauler() {
        return tauler;
    }

    public void setTauler(Tauler tauler) {
        this.tauler = tauler;
    }

    public List<Node> getHijos() {
        return hijos;
    }

    // Afegir un fill
    public void addhijo(Node hijo) {
        this.hijos.add(hijo);
    }
}
