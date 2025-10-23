package logicTest;

import entities.Casella;
import entities.Tauler;
import logic.Score;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ángel Jurado Herruzo
 */

public class ScoreTest {

    private Tauler t;
    private Score score;

    public ScoreTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        t = new Tauler();
        score = new Score();
    }

    @AfterEach
    public void tearDown() {
    }

    // --- TESTS DE COMPTAR FITXES ---
    @Test
    public void testComptarFitxesHoritzontals() {
        int fila = t.getFiles() - 1;
        for (int c = 0; c < 3; c++) {
            t.getCasella(fila, c).setEstat(Casella.Estat.HUMA);
        }
        int resultat = score.comptarFitxes(t, 3, Casella.Estat.HUMA);
        assertEquals(1, resultat, "Hauria de detectar una línia horitzontal de 3 fitxes HUMA");
    }

    @Test
    public void testComptarFitxesVerticals() {
        int col = 2;
        for (int f = t.getFiles() - 1; f >= t.getFiles() - 4; f--) {
            t.getCasella(f, col).setEstat(Casella.Estat.IA);
        }
        int resultat = score.comptarFitxes(t, 4, Casella.Estat.IA);
        assertEquals(1, resultat, "Hauria de detectar una seqüència vertical de 4 fitxes IA");
    }

    @Test
    public void testComptarFitxesDiagonalPrincipal() {
        t.getCasella(2, 0).setEstat(Casella.Estat.HUMA);
        t.getCasella(3, 1).setEstat(Casella.Estat.HUMA);
        t.getCasella(4, 2).setEstat(Casella.Estat.HUMA);
        t.getCasella(5, 3).setEstat(Casella.Estat.HUMA);
        int resultat = score.comptarFitxes(t, 4, Casella.Estat.HUMA);
        assertEquals(1, resultat, "Hauria de detectar una diagonal \\ de 4 fitxes HUMA");
    }

    @Test
    public void testComptarFitxesDiagonalInversa() {
        t.getCasella(5, 0).setEstat(Casella.Estat.IA);
        t.getCasella(4, 1).setEstat(Casella.Estat.IA);
        t.getCasella(3, 2).setEstat(Casella.Estat.IA);
        t.getCasella(2, 3).setEstat(Casella.Estat.IA);
        int resultat = score.comptarFitxes(t, 4, Casella.Estat.IA);
        assertEquals(1, resultat, "Hauria de detectar una diagonal / de 4 fitxes IA");
    }

    @Test
    public void testComptarFitxesSenseSeqüències() {
        int resultat = score.comptarFitxes(t, 2, Casella.Estat.HUMA);
        assertEquals(0, resultat, "No hauria de detectar seqüències en un tauler buit");
    }

    @Test
    public void testNoDetectaSeqüènciesTrencades() {
        int fila = t.getFiles() - 1;
        t.getCasella(fila, 0).setEstat(Casella.Estat.HUMA);
        t.getCasella(fila, 1).setEstat(Casella.Estat.HUMA);
        t.getCasella(fila, 3).setEstat(Casella.Estat.HUMA);
        int resultat = score.comptarFitxes(t, 3, Casella.Estat.HUMA);
        assertEquals(0, resultat, "No hauria de detectar seqüències discontínues");
    }

    @Test
    public void testComptarFitxesAlsLímits() {
        t.getCasella(5, 6).setEstat(Casella.Estat.HUMA);
        t.getCasella(4, 5).setEstat(Casella.Estat.HUMA);
        int resultat = score.comptarFitxes(t, 2, Casella.Estat.HUMA);
        assertTrue(resultat >= 0, "No hauria de donar error als límits del tauler");
    }

    // --- TESTS DE NUMFITXESJUNTES() ---
    @Test
    public void testNumFitxesJuntesInicialitzaCorrectament() {
        int fila = t.getFiles() - 1;
        t.getCasella(fila, 0).setEstat(Casella.Estat.HUMA);
        t.getCasella(fila, 1).setEstat(Casella.Estat.HUMA);
        t.getCasella(fila, 2).setEstat(Casella.Estat.IA);
        score.numFitxesJuntes(t);
        assertTrue(score.getFitxes2HUMA() >= 0);
        assertTrue(score.getFitxes2IA() >= 0);
    }

    // --- TESTS DE GETSCORE() ---
    @Test
    public void testGetScorePerHUMA() {
        score.setFitxes2HUMA(1);
        score.setFitxes3HUMA(1);
        score.setFitxes4HUMA(1);
        score.setFitxes2IA(0);
        score.setFitxes3IA(0);
        score.setFitxes4IA(0);

        int esperat = (1 * 2 + 1 * 3 + 1 * 100000);
        int resultat = score.getScore(Casella.Estat.HUMA);
        assertEquals(esperat, resultat, "El càlcul del score HUMA hauria de ser correcte");
    }

    @Test
    public void testGetScorePerIA() {
        score.setFitxes2HUMA(1);
        score.setFitxes3HUMA(2);
        score.setFitxes4HUMA(1);
        score.setFitxes2IA(1);
        score.setFitxes3IA(1);
        score.setFitxes4IA(0);
        int esperat = (1*2 + 1*3) - (1*2 + 2*3 + 1*100000); // ScoreIA - ScoreHUMA
        int resultat = score.getScore(Casella.Estat.IA);
        assertEquals(esperat, resultat, "El càlcul del score IA hauria de ser correcte");
    }

    @Test
    public void testGetScorePerBuit() {
        int resultat = score.getScore(Casella.Estat.BUIDA);
        assertEquals(0, resultat, "Quan l'estat és buit, el score hauria de ser 0");
    }

    @Test
    public void testScoreEquilibratEntreJugadors() {
        score.setFitxes2HUMA(1);
        score.setFitxes3HUMA(1);
        score.setFitxes4HUMA(0);
        score.setFitxes2IA(1);
        score.setFitxes3IA(1);
        score.setFitxes4IA(0);
        
        int resultat = score.getScore(Casella.Estat.HUMA);
        assertEquals(0, resultat, "Quan hi ha igualtat, el resultat hauria de ser 0");
    }
}
