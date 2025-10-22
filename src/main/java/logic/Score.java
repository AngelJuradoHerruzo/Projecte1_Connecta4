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
    
    
    /**************    .NOMBRE DE FITXES JUNTES.    **************
     * Mètode que emmagatzema totes les variables de tots els grups de fitxes del tauler.
     * 
     * @param tauler Tauler on es busquen les fitxes
     * @param conjuntDeFitxes La quantitat del grup que es vol
     * @param estat Estat de les caselles
     */
  
    public void numFitxesJuntes(Tauler tauler, int conjuntDeFitxes, Casella estat) {
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
        
        /**************    .HORITZONTAL.    **************/
        for (int f = 5; f >= 0; f--) {  // Recórrer totes les files
            int count = 0;              // Comptador temporal per la seqüència

            for (int c = 0; c <= 6; c++) {                  // Recórrer totes les columnes
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
        
        
        /**************    .VERTICAL.    **************/
        for (int c = 0; c <= 6; c++) {  // Recórrer totes les columnes
            int count = 0;              // Comptador temporal per la seqüència

            for (int f = 5; f >= 0; f--) {                  // Recórrer totes les files
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
        
        
        /**************    .DIAGONALS.    **************/
        //DIAGONAL PRINCIPAL (DE DALT-ESQUERRA A BAIX-DRETA) \
        for (int f = 0; f <= 5; f++) {          // Recorrem totes les files
            for (int c = 0; c <= 6; c++) {      // Recorrem totes les columnes
                int count = 0;                  // Comptador temporal per la seqüència

                // Només començar si hi ha espai suficient cap a baix-dreta
                if (f + grup - 1 < 6 && c + grup - 1 < 7) {
                    
                    // Recorrem les caselles de la diagonal sense sortir-nos de les files i columnes
                    for (int i = 0; i < grup && f + i < 6 && c + i < 7; i++) {
                        Casella casella = tauler.getCasella(f + i, c + i);

                        if (casella.getEstat() == estat) {
                            count++;    // Incrementem si la fitxa és del jugador que busquem
                        } 
                        else {
                            break;      // Seqüència trencada, sortim del bucle
                        }
                    }

                    if (count == grup) comptador++;  // Comprovem al final de la seqüència
                }
            }
        }

        //DIAGONAL INVERSA (DE DALT-DRETA A BAIX-ESQUERRA) /
        for (int f = 0; f <= 5; f++) {          // Recorrem totes les files
            for (int c = 0; c <= 6; c++) {      // Recorrem totes les columnes
                int count = 0;                  // Comptador temporal per la seqüència

                // Només començar si hi ha espai suficient cap a baix-esquerra
                if (f - grup + 1 >= 0 && c + grup - 1 < 7) {
                    
                    // Recorrem les caselles de la diagonal sense sortir-nos de les files i columnes
                    for (int i = 0; i < grup && f - i >= 0 && c + i < 7; i++) {
                        Casella casella = tauler.getCasella(f - i, c + i);

                        if (casella.getEstat() == estat)
                            count++;           // Incrementem si la fitxa és del jugador que busquem
                        else
                            break;             // Seqüència trencada, sortim del bucle
                    }

                    if (count == grup) comptador++;  // Comprovem al final de la seqüència
                }
            }
        }
        
        return comptador; // Retorna el nombre de grups trobats
    }
    
    
    /**************    .GET SCORE.    **************
     * Mètode que recorre totes les caselles buscant els grups de fitxes corresponents.
     *
     * @param estat Estat de les caselles
     */
    
    public int getScore(Casella.Estat estat) {
        int ScoreFinal = 0;
        ScoreHUMA = (Fitxes_2_HUMA * 2) + (Fitxes_3_HUMA * 3) + (Fitxes_4_HUMA * 100000);
        ScoreIA = (Fitxes_2_IA * 2) + (Fitxes_3_IA * 3) + (Fitxes_4_IA * 100000);
        
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
}