/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logicTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author angel
 */
public class logicTest {
    
    
     @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    public logicTest() {
        
    // BLOC 1 — TESTS DE TAULER
    
    @Test
            
    void testInicialitzacioNoNula() {
        // Comprova que totes les caselles del tauler s’inicialitzen correctament
        // i que cap sigui null ni buida sense estat.
    }

    @Test
    void testDimensionsPerDefecte() {
        // Comprova que el tauler per defecte és de 6 files × 7 columnes.
    }

    @Test
    void testDimensionsPersonalitzades() {
        // Crea un tauler de 4×4 i comprova que:
        //  - getFiles() == 4
        //  - getColumnes() == 4
        //  - una casella dins dels límits no és null.
    }

    @Test
    void testColocarFitxaColumnaValida() {
        // Col·loca una fitxa a la columna 3 i comprova que:
        //  - el mètode retorna true
        //  - la fitxa apareix a la fila inferior (fila 5).
    }

    @Test
    void testColocarFitxaColumnaPlena() {
        // Omple completament una columna
        // i comprova que al intentar afegir-ne una més retorna false.
    }

    @Test
    void testColocarFitxaColumnaInvalida() {
        // Intenta col·locar una fitxa en una columna fora de rang (-1 o 10)
        // i comprova que retorna false.
    }

    @Test
    void testJugadaUsuariIA() {
        // Fes una jugada d’usuari i una d’IA.
        // Comprova que les caselles canviïn d’estat correctament:
        // USUARI (O) i IA (X).
    }

    @Test
    void testTaulerPle() {
        // Omple tot el tauler i comprova que:
        //  - estaPle() == true
        // Deixa una casella buida i comprova que:
        //  - estaPle() == false
    }

    @Test
    void testGetCasellaValida() {
        // Comprova que una casella dins de límits (per ex. 0,0)
        // retorna un objecte Casella no null.
    }

    @Test
    void testGetCasellaInvalida() {
        // Prova d’accedir a una casella fora dels límits (per ex. fila 10, col 3)
        // i comprova que retorna null.
    }

    @Test
    void testObtenirRepresentacioTextual() {
        // Comprova que la representació textual conté:
        //  - 6 files (salt de línia per cada una)
        //  - 7 columnes amb símbols "-"
        //  - No és buida ni null.
    }
    
    
   
    }
}
