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

    private Tauler t;

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
    }

    // BLOC 1 — TESTS DE TAULER
    @Test

    void testInicialitzacioNoNula() {
        // Comprova que totes les caselles del tauler s’inicialitzen correctament
        // i que cap sigui null ni buida sense estat.

        for (int fila = 0; fila < t.getFiles(); fila++) {
            for (int col = 0; col < t.getColumnes(); col++) {
                casella c = t.getCasella(fila, col);
                assertNotNull(c, "La casella no hauria de ser null");
                assertEquals(Estat.BUIDA, c.getEstat(), "Les caselles noves haurien d'estar buides");
            }
        }
    }

    @Test
    void testDimensionsPerDefecte() {
        assertEquals(6, t.getFiles(), "El tauler per defecte hauria de tenir 6 files");
        assertEquals(7, t.getColumnes(), "El tauler per defecte hauria de tenir 7 columnes");
    }

    @Test
    void testDimensionsPersonalitzades() {
        tauler t2 = new tauler(4, 4);
        assertEquals(4, t2.getFiles(), "El tauler personalitzat hauria de tenir 4 files");
        assertEquals(4, t2.getColumnes(), "El tauler personalitzat hauria de tenir 4 columnes");
        assertNotNull(t2.getCasella(0, 0), "Una casella dins dels límits no pot ser null");
    }

    @Test
    void testColocarFitxaColumnaValida() {
        boolean resultat = t.colocarFitxa(3, Estat.JUGADOR);
        assertTrue(resultat, "Hauria de permetre col·locar una fitxa en una columna vàlida");

        int filaInferior = t.getFiles() - 1;
        casella c = t.getCasella(filaInferior, 3);
        assertEquals(Estat.JUGADOR, c.getEstat(), "La fitxa s'hauria de col·locar a la fila inferior");
    }

    @Test
    void testColocarFitxaColumnaPlena() {
        int col = 2;
        for (int i = 0; i < t.getFiles(); i++) {
            assertTrue(t.colocarFitxa(col, Estat.MAQUINA));
        }
        assertFalse(t.colocarFitxa(col, Estat.MAQUINA), "No hauria de permetre afegir més fitxes a una columna plena");
    }

    @Test
    void testColocarFitxaColumnaInvalida() {
        assertFalse(t.colocarFitxa(-1, Estat.JUGADOR), "Columna negativa no vàlida");
        assertFalse(t.colocarFitxa(10, Estat.JUGADOR), "Columna fora de rang no vàlida");
    }

    @Test
    void testJugadaUsuariIA() {
        assertTrue(t.colocarFitxa(0, Estat.JUGADOR));
        assertTrue(t.colocarFitxa(1, Estat.MAQUINA));

        casella c1 = t.getCasella(t.getFiles() - 1, 0);
        casella c2 = t.getCasella(t.getFiles() - 1, 1);

        assertEquals(Estat.JUGADOR, c1.getEstat(), "Casella de jugador hauria de tenir 'JUGADOR'");
        assertEquals(Estat.MAQUINA, c2.getEstat(), "Casella de màquina hauria de tenir 'MAQUINA'");
    }

    @Test
    void testTaulerPle() {
        for (int c = 0; c < t.getColumnes(); c++) {
            for (int f = 0; f < t.getFiles(); f++) {
                t.colocarFitxa(c, Estat.JUGADOR);
            }
        }
        assertTrue(t.estaPle(), "El tauler hauria d'estar ple");

        // Buidem una casella manualment
        t.getCasella(0, 0).setEstat(Estat.BUIDA);
        assertFalse(t.estaPle(), "El tauler no hauria d'estar ple si hi ha una casella buida");
    }

    @Test
    void testGetCasellaValida() {
        casella c = t.getCasella(0, 0);
        assertNotNull(c, "Una casella dins dels límits no pot ser null");
    }

    @Test
    void testGetCasellaInvalida() {
        assertNull(t.getCasella(10, 3), "Fora dels límits hauria de retornar null");
        assertNull(t.getCasella(-1, 2), "Fora dels límits (negatiu) hauria de retornar null");
    }

    @Test
    void testObtenirRepresentacioTextual() {
        String representacio = t.toString();
        assertNotNull(representacio, "La representació textual no pot ser null");
        assertFalse(representacio.isEmpty(), "La representació no pot estar buida");

        String[] files = representacio.split("\n");
        assertEquals(6, files.length, "El tauler per defecte hauria de tenir 6 línies");
        assertTrue(files[0].contains("-"), "Cada fila hauria de contenir símbols '-' per caselles buides");
    }
    
    // BLOC 2 — TESTS DE CREATETREE
    
    void testGeneraHijosCorrectament() {
        // Crea un Node amb un tauler buit
        // Executa treeGeneration(node, 'X', 0, 1)
        // Comprova que es generen x fills (un per cada columna possible).
    }
    @Test
    void testNoGeneraSiColumnaPlena() {
        // Omple una columna i comprova que CreateTree
        // no genera cap fill per aquella columna plena.
    }

    @Test
    void testAlternanciaJugadors() {
        // Comprova que si el node pare és 'X',
        // els fills generats són del jugador 'O'.
    }

    @Test
    void testLimitDeProfunditat() {
        // Crida treeGeneration amb maxProfunditat = 2
        // i comprova que cap node s’ha generat amb profunditat > 2.
    }

    @Test
    void testCopiaDeTaulerIndependent() {
        // Comprova que els taulers dels fills són còpies independents:
        // si modifiques un fill, el tauler del pare no canvia.
    }

    @Test
    void testNumeroMaximDeFills() {
        // Comprova que cap node té més de 7 fills.
    }
}
