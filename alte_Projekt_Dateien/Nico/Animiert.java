package pacman;

public class Animiert extends Pacman {
    // Objekt Variablen
    private int X;
    private int Y;
    private int R; // Rotation
    private int V; // Max. Geschwindigkeit in Pixeln pro Frame, muss Teiler von r sein, 5
    private int Vx = 0;
    private int Vy = 0;
    private int sollRichtung = 0;
    private boolean SoundPlaying = false;
    
    // Objekt bewegen
    public void Move() {
        // wenn nicht auf Raster: nur Rückwärts fahren
        if((this.getVx() == 0 && this.getSollRichtung() == 1) || 
           (this.getVy() == 0 && this.getSollRichtung() == 2) || 
           (this.getVx() == 0 && this.getSollRichtung() == 3) || 
           (this.getVy() == 0 && this.getSollRichtung() == 4)) 
        {this.Dir(this.getSollRichtung());}
        
        // Abbiegen? nur wenn auf Raster
        if(this.getX()%s.r == 0 && this.getY()%s.r == 0) this.Dir(this.getSollRichtung()); // wenn auf Raster: alle Richtungen erlaubt
        
        // auf Displayrand checken und auf andere Seite wechseln
        // s.feld[0].length: Breite
        if(this.getX() < 0) this.setX(s.feld[0].length*s.r);
        if(this.getY() < 0) this.setY(s.feld.length*s.r);
        if(this.getX() > s.feld[0].length*s.r) this.setX(0);
        if(this.getY() > s.feld.length*s.r) this.setY(0);
        
        // auf Wände checken
        try {
            if(this.getX()%s.r == 0 && this.getY()%s.r == 0) { // checke nur auf Raster für Wände
                if(this.getVy() < 1 && s.feld[this.getY()/s.r - 1][this.getX()/s.r] == 1) {this.setVy(0);}
                if(this.getVx() > 1 && s.feld[this.getY()/s.r][this.getX()/s.r + 1] == 1) {this.setVx(0);}
                if(this.getVy() > 1 && s.feld[this.getY()/s.r + 1][this.getX()/s.r] == 1) {this.setVy(0);}
                if(this.getVx() < 1 && s.feld[this.getY()/s.r][this.getX()/s.r - 1] == 1) {this.setVx(0);}
            }
        } catch(ArrayIndexOutOfBoundsException exception) {
            Fehler("pMove", "ArrayIndexOutOfBoundsException", "Objekt hat Spielrand verlassen ODER Leveldesignfehler am Displayrand.");
        }
        
        // Position ändern 
        int altX = this.getX();
        int altY = this.getY();
        int tempVx = this.getVx();
        int tempVy = this.getVy();
        
        this.setX(altX + tempVx); // Warum funktioniert das nur mit Zwischenschritt?
        this.setY(altY + tempVy);
    }
    
     // ändert Richtung von Objekt durch sollRichtung ints, checkt auf Wände
    public void Dir(int dir) {
        try {
            switch(dir) {
                case 0: break;
                case 1: if(s.feld[this.getY()/s.r - 1][this.getX()/s.r] != 1) {this.setSollRichtung(0); this.setVy(-this.getV()); this.setVx(0);} break; // UP
                case 2: if(s.feld[this.getY()/s.r][this.getX()/s.r + 1] != 1) {this.setSollRichtung(0); this.setVx(this.getV()); this.setVy(0);} break; // RIGHT
                case 3: if(s.feld[this.getY()/s.r + 1][this.getX()/s.r] != 1) {this.setSollRichtung(0); this.setVy(this.getV()); this.setVx(0);} break; // DOWN
                case 4: if(s.feld[this.getY()/s.r][this.getX()/s.r - 1] != 1) {this.setSollRichtung(0); this.setVx(-this.getV()); this.setVy(0);} break; // LEFT
                default: System.out.println("Key kann nur 0 - 4 sein."); System.out.println("Position und Geschwindigkeit - X/s.r: " + this.getX()/s.r + "; Y/s.r: " + this.getY()/s.r + "; X: " + this.getX() + "; Y: " + this.getY() + "; Vx: " + this.getVx() + "; Vy: " + this.getVy());
            }
        } catch(ArrayIndexOutOfBoundsException exception) {
            Fehler("pDir", "ArrayIndexOutOfBoundsException", "Pacman hat Spielrand verlassen ODER Leveldesignfehler am Displayrand.");
        }
    }
    
    // Getter und Setter
    public int getX() {return X;} // oder muss ich auch noch return this.X schreiben?
    public int getY() {return Y;}
    public int getR() {return R;}
    public int getV() {return V;}
    public int getVx() {return Vx;}
    public int getVy() {return Vy;}
    public int getSollRichtung() {return sollRichtung;}
    public boolean getSoundPlaying() {return SoundPlaying;}
    
    public void setX(int x) {X = x;}
    public void setY(int x) {Y = x;}
    public void setR(int x) {R = x;}
    public void setV(int x) {V = x;}
    public void setVx(int x) {Vx = x;}
    public void setVy(int x) {Vy = x;}
    public void setSollRichtung(int x) {sollRichtung = x;}
    public void setSoundPlaying(boolean x) {SoundPlaying = x;}
    
    
    
    /*
    // Polymorphie
    public void Sound() {
        
    }
    
    public void Animation() {
        
    }
    
    public void dirUpdate() {
        
    }
    */
    
    // Konstruktor
    Animiert() {
        
    }
}
