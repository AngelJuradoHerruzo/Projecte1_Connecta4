package entities;

/**
 * @author Ángel Jurado Herruzo
 */ 

public class Tauler {
    private Casella[][] caselles;   // Matriu bidimensional
    private int files;              // Nombre de files: 6
    private int columnes;           // Nombre de columnes: 7
    
    
    /**************    .GETTERS.    **************
     * Inicialitza el tauler creant totes les caselles i establint-les com a buides.
     * Aquest mètode és cridat pels constructors per preparar el tauler per al joc.
     */
    
    public int getFiles() {
        return files;
    }
    
    public int getColumnes() {
        return columnes;
    }
    
// Constructor de copia
public Tauler(Tauler otro) {
    this.files = otro.files;
    this.columnes = otro.columnes;
    this.caselles= new Casella[files][columnes];
    
    for (int i = 0; i < files; i++) {
        for (int j = 0; j < columnes; j++) {
            // Crear una nueva casilla copiando el estado de la original
            this.caselles[i][j] = new Casella(otro.caselles[i][j].getEstat());
        }
    }
}
    
    /**
     * Obté una casella específica del tauler.
     * Aquest mètode és útil per a la lògica de comprovació de victòries.
     * 
     * @param fila Fila de la casella (0-based)
     * @param columna Columna de la casella (0-based)
     * @return La casella sol·licitada, o null si les coordenades són invàlides
     */
    
    public Casella getCasella(int fila, int columna) {
        // Si les coordenades estan dins dels límits del tauler
        if (fila >= 0 && fila < files && columna >= 0 && columna < columnes) {
            return caselles[fila][columna]; // Coordenades vàlides
        }
        return null; // Coordenades invàlides
    }
    
    
    /**************    .CONSTRUCTORS.    **************
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
      
    
    /**************    .MÈTODE D'INICIALITZACIÓ.    **************
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
    
    
    /**************    .MÈTODES PER A COL·LOCAR FITXES.    **************
     * Intenta col·locar una fitxa a la columna especificada per al jugador indicat.
     * La fitxa cau a la primera posició buida des de baix de la columna.
     * 
     * @param columna Columna on es vol col·locar la fitxa (0-based)
     * @param jugador Tipus de jugador (USUARI O IA)
     * @return True: Jugada realitzada correctament. False: Columna plena o és invàlida.
     */
    
    public boolean colocarFitxa(int columna, Casella.Estat jugador) {
        if (columna < 0 || columna >= columnes) { // Verificar si la columna existeix al tauler
            return false; // Columna invàlida
        }
        
        for (int fila = files - 1; fila >= 0; fila--) { // Recórrer la columna de baix a dalt
            if (caselles[fila][columna].estaBuida()) {  // Si trobem una casella buida, hi col·loquem la fitxa
                caselles[fila][columna].setEstat(jugador);
                return true; // Jugada realitzada amb èxit
            }
        }
        return false; // Columna plena, jugada invàlida
    }
    
    public boolean jugadaJugador_1(int columna) { // Realitzar una jugada del JUGADOR_1
        return colocarFitxa(columna, Casella.Estat.JUGADOR_1);
    }
    
    public boolean jugadaJugador_2(int columna) { // Realitzar una jugada del JUGADOR_2
        return colocarFitxa(columna, Casella.Estat.JUGADOR_2);
    }
    
    
    /**************    .MÈTODES D'ESTAT DEL TAULE.    **************
     * Comprova si el tauler està completament ple (Empat).
     * El tauler està ple quan totes les caselles de la fila superior estan ocupades.
     * 
     * @return True: Tauler està ple. False: Hi ha caselles buides.
     */
    
    public boolean estaPle() {
        for (int j = 0; j < columnes; j++) { // Comprovar cada columna de la fila superior (Fila 0)
            
            if (caselles[0][j].estaBuida()) { // Si la casella buida a la fila superior
                return false; // El tauler no està ple
            }
        }
        return true; // Totes les columnes de la fila superior estan plenes
    }
    
    
// Comprueba si la columna está llena
    public boolean isColumnFull(int columna) {
    // La columna está llena si la casilla de la fila superior no está vacía
        return !caselles[0][columna].estaBuida();
    }
    
    /**************    .MÈTODES DE VISUALITZACIÓ.    **************
     * Mostra el tauler actual per la consola amb un format llegible.
     * Utilitza els símbols de cada casella per representar l'estat del joc.
     */
    
    public void mostrarTauler() {
        System.out.println("-------------------------- TAULER ACTUAL --------------------------\n");

        
        //IMPRIMIR CAPÇALERA DE COLUMNES
        System.out.print("       |");
        for (int j = 0; j < columnes; j++) {
            System.out.print(" Col " + j + " |");
        }
        System.out.println();

        
        //IMPRIMIR LINIA SEPARADORA DE CAPÇALERA
        System.out.print("-------|");
        for (int j = 0; j < columnes; j++) {
            System.out.print("-------|");
        }
        System.out.println();

        
        //IMPRIMIR CADA FILA DEL TAULER
        for (int i = 0; i < files; i++) {
            System.out.print("Fila " + i + " |"); // Nom de la fila

            for (int j = 0; j < columnes; j++) { // Contingut de cada casella de la fila
                String simbol = caselles[i][j].getSimbol();
                System.out.print("   " + simbol + "   |");
            }
            System.out.println();

            System.out.print("-------|"); // Linia separadora entre files
            for (int j = 0; j < columnes; j++) {
                System.out.print("-------|");
            }
            System.out.println();
        }
        System.out.println();
    }
}





/**  REPRESENTACIÓ DEL TAULER.

-------------------------- TAULER ACTUAL --------------------------

       | Col 0 | Col 1 | Col 2 | Col 3 | Col 4 | Col 5 | Col 6 |
-------|-------|-------|-------|-------|-------|-------|-------|
Fila 5 |   -   |   -   |   -   |   -   |   -   |   -   |   -   |
-------|-------|-------|-------|-------|-------|-------|-------|
Fila 4 |   -   |   -   |   -   |   -   |   -   |   -   |   -   |
-------|-------|-------|-------|-------|-------|-------|-------|
Fila 3 |   -   |   -   |   -   |   -   |   -   |   -   |   -   |
-------|-------|-------|-------|-------|-------|-------|-------|
Fila 2 |   -   |   -   |   -   |   -   |   -   |   -   |   -   |
-------|-------|-------|-------|-------|-------|-------|-------|
Fila 1 |   -   |   -   |   -   |   -   |   -   |   -   |   -   |
-------|-------|-------|-------|-------|-------|-------|-------|
Fila 0 |   -   |   -   |   -   |   -   |   -   |   -   |   -   |
-------|-------|-------|-------|-------|-------|-------|-------|

**/