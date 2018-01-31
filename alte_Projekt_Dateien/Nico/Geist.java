package pacman;

import java.util.Random;

public class Geist extends Animiert {
    
    
    public void dirUpdateGeist() {
        boolean neueDirGefunden = false;
        Random ran = new Random();
        
        while(!neueDirGefunden) {
            int r = ran.nextInt(4) + 1; // von 1 bis 4
            
            switch(r) {
                // checkt ob in die vorgeschlagene Richtung eine Wand ist und dass die Richtung nicht rückwärts ist
                case 1: if(s.feld[this.getY()/s.r - 1][this.getX()/s.r] != 1 && this.getVy() <= 0) {this.setSollRichtung(r); neueDirGefunden = true;} break; // UP
                case 2: if(s.feld[this.getY()/s.r][this.getX()/s.r + 1] != 1 && this.getVx() >= 0) {this.setSollRichtung(r); neueDirGefunden = true;} break; // RIGHT
                case 3: if(s.feld[this.getY()/s.r + 1][this.getX()/s.r] != 1 && this.getVy() >= 0) {this.setSollRichtung(r); neueDirGefunden = true;} break; // DOWN
                case 4: if(s.feld[this.getY()/s.r][this.getX()/s.r - 1] != 1 && this.getVx() <= 0) {this.setSollRichtung(r); neueDirGefunden = true;} break; // LEFT
                default: System.out.println("Key kann nur 0 - 4 sein."); System.out.println("Position und Geschwindigkeit - X/s.r: " + this.getX()/s.r + "; Y/s.r: " + this.getY()/s.r + "; X: " + this.getX() + "; Y: " + this.getY() + "; Vx: " + this.getVx() + "; Vy: " + this.getVy());
            }
        }
    }
    
    Geist() {
        this.setX(12*s.r);
        this.setY(14*s.r);
        this.setR(0); // Rotation
        this.setV(5); // Max. Geschwindigkeit in Pixeln pro Frame, muss Teiler von r sein, 5
        this.setVx(0);
        this.setVy(0);
        this.setSoundPlaying(false);
        this.setSollRichtung(key);
    }
}
