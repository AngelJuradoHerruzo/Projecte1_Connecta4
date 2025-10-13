package entities;

/**
 * @author Ángel Jurado Herruzo
 */ 

public class Tauler {
   
    private Casella[][] caselles;   // Matriu bidimensional
    private int files;              // Nombre de files: 6
    private int columnes;           // Nombre de columnes: 7
    
    
    /**************    CONSTRUCTORS.    **************
     * Constructors per crear un tauler de Conecta 4.
     * Per defecte: 6 Files × 7 Columnes.
     * També es pot especificar un nombre personalitzat de files i columnes.
     *
     * @param files Nombre de files del tauler
     * @param columnes Nombre de columnes del tauler
     */
    
    public Tauler() {
        this.files = 6;
        this.columnes = 7;
        inicialitzarTauler(); // Crida el mètode
    }
    
    public Tauler(int files, int columnes) {
        this.files = files;
        this.columnes = columnes;
        inicialitzarTauler(); // Crida el mètode
    }
      
    
    /**************    MÈTODE D'INICIALITZACIÓ.    **************
     * Inicialitza el tauler creant totes les caselles i establint-les com a buides.
     * Aquest mètode és cridat pels constructors per preparar el tauler per al joc.
     */
    
    private void inicialitzarTauler() {
        caselles = new Casella[files][columnes]; // Crear la matriu amb les dimensions especificades
        
        for (int i = 0; i < files; i++) { // Omplir tot el tauler amb caselles buides
            for (int j = 0; j < columnes; j++) {
                caselles[i][j] = new Casella();
            }
        }
    }
    
    
    
     
    
    
    
    
    
    
    
    
    
}