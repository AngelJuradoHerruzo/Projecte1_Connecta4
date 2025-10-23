package logic;
import entities.Tauler;
import entities.Casella;

/**
 * @author Ángel Jurado Herruzo
 */

public class Score {
    int Fitxes_2_HUMA;
    int Fitxes_3_HUMA;
    int Fitxes_4_HUMA;
    
    int Fitxes_2_IA;
    int Fitxes_3_IA;
    int Fitxes_4_IA;
    
    int ScoreHUMA = 0;
    int ScoreIA = 0;
    
    
    /**************    .GETTERS PER A PROVES.    **************/
    public int getFitxes2HUMA() { return Fitxes_2_HUMA; }
    public int getFitxes3HUMA() { return Fitxes_3_HUMA; }
    public int getFitxes4HUMA() { return Fitxes_4_HUMA; }

    public int getFitxes2IA() { return Fitxes_2_IA; }
    public int getFitxes3IA() { return Fitxes_3_IA; }
    public int getFitxes4IA() { return Fitxes_4_IA; }
    
    
    /**************    .SETTERS PER A PROVES.    **************/
    public void setFitxes2HUMA(int val) { Fitxes_2_HUMA = val; }
    public void setFitxes3HUMA(int val) { Fitxes_3_HUMA = val; }
    public void setFitxes4HUMA(int val) { Fitxes_4_HUMA = val; }

    public void setFitxes2IA(int val) { Fitxes_2_IA = val; }
    public void setFitxes3IA(int val) { Fitxes_3_IA = val; }
    public void setFitxes4IA(int val) { Fitxes_4_IA = val; }

    
    /**************    .NOMBRE DE FITXES JUNTES.    **************
     * Mètode que emmagatzema totes les variables de tots els grups de fitxes del tauler.
     * 
     * @param tauler Tauler on es busquen les fitxes
     * @param estat Estat de les caselles
     */
  
    public void numFitxesJuntes(Tauler tauler) {
        Fitxes_2_HUMA = 0;
        Fitxes_3_HUMA = 0;
        Fitxes_4_HUMA = 0;
        
        Fitxes_2_IA = 0;
        Fitxes_3_IA = 0;
        Fitxes_4_IA = 0;
        

        Fitxes_2_HUMA = comptarFitxes(tauler, 2, Casella.Estat.HUMA);
        Fitxes_3_HUMA = comptarFitxes(tauler, 3, Casella.Estat.HUMA);
        Fitxes_4_HUMA = comptarFitxes(tauler, 4, Casella.Estat.HUMA);
        
        Fitxes_2_IA = comptarFitxes(tauler, 2, Casella.Estat.IA);
        Fitxes_3_IA = comptarFitxes(tauler, 3, Casella.Estat.IA);
        Fitxes_4_IA = comptarFitxes(tauler, 4, Casella.Estat.IA);
    } 
    
    
    /**************    .COMPTAR FITXES.    **************
     * Mètode que recorre totes les caselles buscant els grups de fitxes corresponents.
     * 
     * @param tauler Tauler on es busquen les fitxes
     * @param grup La quantitat del grup que es vol
     * @param estat Estat de les caselles
     * @param f Fila
     * @param c columna
     */

    public int comptarFitxes(Tauler tauler, int grup, Casella.Estat estat) {
        int comptador = 0;

        /**************    .HORITZONTAL I VERTICAL.    **************/
        for (boolean horitzontal : new boolean[] {true, false}) {  // Primer horitzontal, després vertical
            for (int i = 0; i < (horitzontal ? 6 : 7); i++) {       // Files si Horitzontal, columnes si vertical
                int count = 0;

                for (int j = 0; j < (horitzontal ? 7 : 6); j++) {   // Columnes si Horitzontal, files si vertical
                    int f = horitzontal ? i : 5 - j;
                    int c = horitzontal ? j : i;
                    Casella casella = tauler.getCasella(f, c);  // Obtenim casella

                    if (casella.getEstat() == estat) { // Estat passat al mètode (HUMA o IA)
                        count++;
                    } 
                    else { // Quan la seqüència es trenca
                        if (count == grup) {
                            comptador++; // Només comptem si és exactament del tamany desitjat
                        }
                        count = 0;
                    }
                }

                if (count == grup) comptador++; // Comprovem al final de la seqüència
            }
        }
        
        /**************    .DIAGONAL PRINCIPAL I INVERSA.    **************/
        for (int diagonal = 0; diagonal < 2; diagonal++) {
            int df = (diagonal == 0) ? 1 : -1;  // Increment de fila: +1 per diagonal principal, -1 per inversa

            for (int f = 0; f <= 5; f++) {       // Recorrem totes les files
                for (int c = 0; c <= 6; c++) {   // Recorrem totes les columnes
                    int count = 0;

                    //RECORREM CASELLES DE LA DIAGONAL SENSE SORTIR-NOS DEL TAULER
                    for (int i = 0; i < grup; i++) {
                        int fi = f + df * i;      // Fila segons el tipus de diagonal
                        int ci = c + i;           // Columna incrementant cap a la dreta
                        
                        if (fi < 0 || fi >= 6 || ci < 0 || ci >= 7) {
                            break; // Seqüència fora del tauler
                        }
                        Casella casella = tauler.getCasella(fi, ci);
                        
                        if (casella.getEstat() == estat) {
                            count++; // Incrementem si la casella és del jugador buscat
                        }
                        else {
                            break;
                        }
                    }
                    
                    if (count == grup) comptador++; // Comprovem al final de la seqüència
                }
            }
        }
        
        return comptador; // Retorna el nombre de grups trobats
    }    
    public Casella.Estat hayGanador() {
    if (Fitxes_4_HUMA > 0) return Casella.Estat.HUMA;
    if (Fitxes_4_IA > 0) return Casella.Estat.IA;
    return Casella.Estat.BUIDA;
}
        
        
    /**************    .GET SCORE.    **************
     * Mètode que recorre totes les caselles buscant els grups de fitxes corresponents.
     *
     * @param estat Estat de les caselles
     */
    
    public int getScore(Casella.Estat estat) {
        int ScoreFinal = 0;
        ScoreHUMA = (Fitxes_2_HUMA * 40) + (Fitxes_3_HUMA * 300) + (Fitxes_4_HUMA * 100000);
        ScoreIA = (Fitxes_2_IA * 40) + (Fitxes_3_IA * 300) + (Fitxes_4_IA * 100000);
        
        switch (estat) {
            case (Casella.Estat.HUMA):  // HUMA
                ScoreFinal = ScoreHUMA - ScoreIA;
                break;
                
            case (Casella.Estat.IA):    // IA
                ScoreFinal = ScoreIA - ScoreHUMA;
                break;
            
            default:    // BUIDA
                ScoreFinal = 0; 
                break;
        }
        return ScoreFinal;
    } 
    
    
    /**************    .SCORE.    **************
     * Mètode que calcula un valor heurístic o score per a un jugador concret
     * en un tauler determinat.
     *
     * @param tauler Tauler on es busquen les fitxes
     * @param estat Estat del jugador (HUMA o IA)
     * @return Valor heurístic calculat segons les fitxes agrupades
     */
    
    public int score(Tauler tauler, Casella.Estat estat) {
        numFitxesJuntes(tauler); // Recalcula las fitxes juntas para el tablero actual
        return getScore(estat); // Devuelve el score final para el jugador pasado
    }
}