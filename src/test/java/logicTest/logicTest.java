/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logicTest;

import entities.Casella;
import entities.Casella.Estat;
import entities.Tauler;
import game.Execucio;
import logic.CreateTree;
import logic.Node;
import logic.Score;
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
    private Node node;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        t = new Tauler();
        node = new Node(t);

    }

    @AfterEach
    public void tearDown() {
    }

    public logicTest() {
    }

    // BLOC 1 — TESTS DE TAULER
    @Test

    void testInicialitzacioNoNula() {
        /**
         * Verifica que totes les caselles s'inicialitzen correctament.
         */

        for (int fila = 0; fila < t.getFiles(); fila++) {
            for (int col = 0; col < t.getColumnes(); col++) {
                Casella c = t.getCasella(fila, col);
                assertNotNull(c, "La casella no hauria de ser null");
                assertEquals(Estat.BUIDA, c.getEstat(), "Les caselles noves haurien d'estar buides");
            }
        }
    }

    /**
     * Comprova les dimensions per defecte del tauler.
     */
    @Test
    void testDimensionsPerDefecte() {
        assertEquals(6, t.getFiles(), "El tauler per defecte hauria de tenir 6 files");
        assertEquals(7, t.getColumnes(), "El tauler per defecte hauria de tenir 7 columnes");
    }

    /**
     * Verifica la creació de taulers amb dimensions personalitzades.
     */
    @Test
    void testDimensionsPersonalitzades() {
        Tauler t2 = new Tauler(4, 4);
        assertEquals(4, t2.getFiles(), "El tauler personalitzat hauria de tenir 4 files");
        assertEquals(4, t2.getColumnes(), "El tauler personalitzat hauria de tenir 4 columnes");
        assertNotNull(t2.getCasella(0, 0), "Una casella dins dels límits no pot ser null");
    }

    /**
     * Test per col·locar fitxa en columna vàlida.
     */
    @Test
    void testColocarFitxaColumnaValida() {
        boolean resultat = t.colocarFitxa(3, Casella.Estat.HUMA);
        assertTrue(resultat, "Hauria de permetre col·locar una fitxa en una columna vàlida");

        int filaInferior = t.getFiles() - 1;
        Casella c = t.getCasella(filaInferior, 3);
        assertEquals(Casella.Estat.HUMA, c.getEstat(), "La fitxa s'hauria de col·locar a la fila inferior");
    }

    /**
     * Verifica que no es pugui col·locar fitxa en columna plena.
     */
    @Test
    void testColocarFitxaColumnaPlena() {
        int col = 2;
        for (int i = 0; i < t.getFiles(); i++) {
            assertTrue(t.colocarFitxa(col, Casella.Estat.IA));
        }
        assertFalse(t.colocarFitxa(col, Casella.Estat.IA), "No hauria de permetre afegir més fitxes a una columna plena");
    }

    /**
     * Test per columnes invàlides (fora de rang).
     */
    @Test
    void testColocarFitxaColumnaInvalida() {
        assertFalse(t.colocarFitxa(-1, Casella.Estat.HUMA), "Columna negativa no vàlida");
        assertFalse(t.colocarFitxa(10, Casella.Estat.HUMA), "Columna fora de rang no vàlida");
    }

    /**
     * Verifica jugades d'usuari i IA.
     */
    @Test
    void testJugadaUsuariIA() {
        assertTrue(t.colocarFitxa(0, Casella.Estat.HUMA));
        assertTrue(t.colocarFitxa(1, Casella.Estat.IA));

        Casella c1 = t.getCasella(t.getFiles() - 1, 0);
        Casella c2 = t.getCasella(t.getFiles() - 1, 1);

        assertEquals(Casella.Estat.HUMA, c1.getEstat(), "Casella de jugador hauria de tenir 'JUGADOR'");
        assertEquals(Casella.Estat.IA, c2.getEstat(), "Casella de màquina hauria de tenir 'MAQUINA'");
    }

    /**
     * Comprova la detecció de tauler ple.
     */
    @Test
    void testTaulerPle() {
        for (int c = 0; c < t.getColumnes(); c++) {
            for (int f = 0; f < t.getFiles(); f++) {
                t.colocarFitxa(c, Casella.Estat.HUMA);
            }
        }
        assertTrue(t.estaPle(), "El tauler hauria d'estar ple");

        // Buidem una casella manualment
        t.getCasella(0, 0).setEstat(Estat.BUIDA);
        assertFalse(t.estaPle(), "El tauler no hauria d'estar ple si hi ha una casella buida");
    }

    /**
     * Test per obtenir casella vàlida.
     */
    @Test
    void testGetCasellaValida() {
        Casella c = t.getCasella(0, 0);
        assertNotNull(c, "Una casella dins dels límits no pot ser null");
    }

    /**
     * Test per obtenir casella invàlida.
     */
    @Test
    void testGetCasellaInvalida() {
        assertNull(t.getCasella(10, 3), "Fora dels límits hauria de retornar null");
        assertNull(t.getCasella(-1, 2), "Fora dels límits (negatiu) hauria de retornar null");
    }

    /**
     * Verifica la representació textual del tauler.
     */
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
    /**
     * Verifica la generació correcta de nodes fills.
     */
    @Test
    void testGeneraFillsCorrectament() {
        // Crea un Node amb un tauler buit
        // Executa treeGeneration(node, 'X', 0, 1)
        // Comprova que es generen x fills (un per cada columna possible).
        CreateTree.treeGeneration(node, Casella.Estat.IA, 0, 1);
        assertEquals(t.getColumnes(), node.getHijos().size(), "S'han de generar 6 fills per un tauler buit");
    }

    /**
     * Comprova l'alternança de jugadors en la generació de l'arbre.
     */
    @Test
    void testAlternanciaJugadors() {
        // Comprova que si el node pare és 'X',
        // els fills generats són del jugador 'O'.
        CreateTree.treeGeneration(node, Casella.Estat.IA, 0, 1);

        for (Node f : node.getHijos()) {
            String jugadorSeguent = f.getTauler().getCasella(
                    t.getFiles() - 1, node.getHijos().indexOf(f)
            ).getSimbol();
            assertEquals("O", jugadorSeguent, "El jugador del fill hauria de ser 'O'");
        }
    }

    /**
     * Verifica el límit de profunditat en la generació de l'arbre.
     */
    @Test
    void testLimitDeProfunditat() {
        // Crida treeGeneration amb maxProfunditat = 2
        // i comprova que cap node s’ha generat amb profunditat > 2.
        CreateTree.treeGeneration(node, Casella.Estat.IA, 0, 2);

        boolean profunditatMajor2 = false;
        for (Node f : node.getHijos()) {
            for (Node ff : f.getHijos()) {
                for (Node fff : ff.getHijos()) {
                    profunditatMajor2 = true;
                }
            }
        }
        assertFalse(profunditatMajor2, "No s'ha de generar cap node amb profunditat > 2");
    }

    /**
     * Comprova la independència de les còpies del tauler.
     */
    @Test
    void testCopiaDeTaulerIndependent() {
        // Comprova que els taulers dels fills són còpies independents:
        // si modifiques un fill, el tauler del pare no canvia.
        CreateTree.treeGeneration(node, Casella.Estat.IA, 0, 1);

        Node primerFill = node.getHijos().get(0);
        primerFill.getTauler().colocarFitxa(0, Casella.Estat.HUMA); // Modifiquem el fill

        assertEquals(Estat.BUIDA, node.getTauler().getCasella(t.getFiles() - 1, 0).getEstat(),
                "El tauler del pare no hauria de canviar si modifiquem un fill");
    }

    /**
     * Verifica el nombre màxim de fills per node.
     */
    @Test
    void testNumeroMaximDeFills() {
        // Comprova que cap node té més de 6 fills.
        CreateTree.treeGeneration(node, Casella.Estat.IA, 0, 1);

        for (Node f : node.getHijos()) {
            assertTrue(f.getHijos().size() <= 7, "Cap node fill hauria de tenir més de 7 fills");
        }
    }

    // BLOC 3 — TESTS FUTURS DE IA (ponderacions)
    /**
     * Test per ponderació de tres en ratlla.
     */
    @Test
    void testPonderacioTresEnRatlla() {
        // Comprovar que una línia de 3 fitxes rep una ponderació alta.
        // Crear un tauler buit
        Tauler tauler = new Tauler();

        // Col·locar 3 fitxes consecutives de la IA a la fila inferior
        tauler.colocarFitxa(0, Casella.Estat.IA);
        tauler.colocarFitxa(1, Casella.Estat.IA);
        tauler.colocarFitxa(2, Casella.Estat.IA);

        // Calcular el score
        Score score = new Score();
        int resultat = score.score(tauler, Casella.Estat.IA);

        // Esperem una puntuació alta (≥ 3)
        assertTrue(resultat >= 3,
                "La puntuació d'una línia de 3 fitxes de la IA hauria de ser alta, però és " + resultat);
    }

    @Test
    void testPonderacioUnaFitxa() {
        // Comprovar que una sola fitxa té una ponderació baixa.

        // Crear un tauler buit
        Tauler tauler = new Tauler();

        // Col·locar només una fitxa de la IA
        tauler.colocarFitxa(0, Casella.Estat.IA);

        // Calcular el score
        Score score = new Score();
        int resultat = score.score(tauler, Casella.Estat.IA);

        // Esperem una puntuació baixa o nul·la
        assertTrue(resultat < 3,
                "Una sola fitxa no hauria de donar una puntuació alta, però és " + resultat);
    }

    @Test
    void testPonderacioQuatreEnRatllaGuanyadora() {
        // Crear un tauler buit
        Tauler tauler = new Tauler();

        // Col·locar 4 fitxes consecutives de la IA a la fila inferior (simula victòria)
        tauler.colocarFitxa(0, Casella.Estat.IA);
        tauler.colocarFitxa(1, Casella.Estat.IA);
        tauler.colocarFitxa(2, Casella.Estat.IA);
        tauler.colocarFitxa(3, Casella.Estat.IA);

        // Calcular el score
        Score score = new Score();
        int resultat = score.score(tauler, Casella.Estat.IA);

        // Esperem una puntuació molt alta (≥ 100000 segons el pes de Score)
        assertTrue(resultat >= 100000,
                "Una línia de 4 fitxes de la IA hauria de tenir una puntuació molt alta (≥100000), però és " + resultat);
    }

    @Test
    void testPonderacioTresEnRatllaHumanaPenalitzaIA() {
        // Crear un tauler buit
        Tauler tauler = new Tauler();

        // Col·locar 3 fitxes consecutives del jugador humà
        tauler.colocarFitxa(0, Casella.Estat.HUMA);
        tauler.colocarFitxa(1, Casella.Estat.HUMA);
        tauler.colocarFitxa(2, Casella.Estat.HUMA);

        // Calcular el score per a la IA
        Score score = new Score();
        int resultat = score.score(tauler, Casella.Estat.IA);

        // El resultat hauria de ser negatiu (perquè el rival té avantatge)
        assertTrue(resultat < 0,
                "Si el jugador humà té 3 en ratlla, la puntuació per la IA hauria de ser negativa, però és " + resultat);
    }

    /**
     * Test per situació d'empat.
     */
    @Test
    void testEmpatIA() {

    }

    // BLOC 4 — TESTS DE EXECUCIO
    /**
     * Verifica l'increment correcte del torn.
     */
    @Test
    void testIncrementarTorn() {
        // Crea una instància d’Execucio
        Execucio exec = new Execucio();

        // Guarda el torn inicial
        int tornInicial = exec.getTorn();

        // Crida incrementarTorn()
        exec.incrementarTorn();

        // Comprova que el torn augmenta en +1
        assertEquals(tornInicial + 1, exec.getTorn(), "El torn hauria d'augmentar en +1 després de cridar incrementarTorn().");
    }

}
