package entities;

/**
 * @author Ángel Jurado Herruzo
 */

public class Casella {
    
    //ESTATS POSSIBLES DE LA CASELLA
    public enum Estat {
        BUIDA("-"),
        HUMA("O"),
        IA("X");
   
        private final String simbol;
        
        Estat(String simbol) {
            this.simbol = simbol;
        }
          
        public String getSimbol() {
            return simbol;
        }
    }
    
    private Estat estat;
    
    public Casella(Estat estat) {
    this.estat = estat;
}

    //CONSTRUCTOR
    public Casella() {
        this.estat = Estat.BUIDA; // La casella està buida per defecte
    }
    
    //GETTER
    public Estat getEstat() {
        return estat;
    }
    
    //SETTER
    public void setEstat(Estat estat) {
        this.estat = estat;
    }
    
    
    //MÈTODE PER OBTENIR EL SÍMBOL DE LA CASELLA
    public String getSimbol() { // Torna l'estat actual de la casella
        return estat.getSimbol();
    }
    
    //MÈTODE PER VERIFICAR SI LA CASELLA ESTÀ BUIDA
    public boolean estaBuida() {
        return estat == Estat.BUIDA;
    }
    
    @Override
    public String toString() {
        return getSimbol(); // Sobreescriu toString() i torna el símbol
    }
}
