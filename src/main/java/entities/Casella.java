package entities;

/**
 * @author Ángel Jurado Herruzo
 */

public class Casella {
    
    //ESTATS POSSIBLES DE LA CASELLA
    public enum Estat {
        BUIDA("-"),
        USUARI("O"),
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
}