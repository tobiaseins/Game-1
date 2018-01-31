package pacman;

public class Spieler extends Animiert {
    // Variablen nur für Pacman
    public int AngleStart = 45;
    public int AngleStop = 270;
    public int L = 2; // Leben
    public boolean offen = false;
    
    // Sounds
    Audio beginning = new Audio("pacman_beginning.wav");
    Audio chomp = new Audio("pacman_chomp.wav");
    Audio death = new Audio("pacman_death.wav");
    Audio eatfruit = new Audio("pacman_eatfruit.wav");
    Audio eatghost = new Audio("pacman_eatghost.wav");
    Audio extrapac = new Audio("pacman_extrapac.wav");
    Audio intermission = new Audio("pacman_chomp.wav");
    
    // dreht Pacman und öffnet Mund -> in zwei Funktionen aufteilen
    public void Animation() {
        // Pacman drehen
        if(this.getVx() > 0) this.setR(0);
        if(this.getVx() < 0) this.setR(180);
        if(this.getVy() > 0) this.setR(270);
        if(this.getVy() < 0) this.setR(90);
        
        // Mund offen?
        offen = count%(fps/5) == 0 && (this.getVx() != 0 || this.getVy() != 0);
        
        // Mund öffnen & schließen
        if(offen) {
            AngleStart = 45 + this.getR();
            AngleStop = 270;
        } else {
            AngleStart = 20 + this.getR();
            AngleStop = 330;
        }
    }
    
    // Punktefressen
    public void Fressen() {
        try {
            if(s.feld[this.getY()/s.r][this.getX()/s.r] == 2 && this.getX()%s.r == 0 && this.getY()%s.r == 0) {
                s.feld[this.getY()/s.r][this.getX()/s.r] = 0;
                Score += 10;
                //if(!AClips[0].isActive()) pSound(0);
                Sound(2);
            } else {
                if(this.getX()%s.r == 0 && this.getY()%s.r == 0) {chomp.clip.stop();}
            }
        } catch(ArrayIndexOutOfBoundsException exception) {
            //Fehler("pFressen", "ArrayIndexOutOfBoundsException");
        }
    }
    
    // Sounds
    public void Sound(int x) {
        switch(x) {
            case 1:
                if(!beginning.clip.isRunning()) beginning.playClip();
                break;
            case 2:
                if(!chomp.clip.isRunning()) chomp.clip.loop(100);
                break;
        }
    }
    
    // Pacman wird durch Tasten gesteuert
    public void dirUpdateSpieler() {
        this.setSollRichtung(key);
    }
    
    // Getter und Setter
    public int getAngleStart() {return AngleStart;}
    public int getAngleStop() {return AngleStop;}
    public int getL() {return L;}
    public boolean getOffen() {return offen;}
    
    public void setAngleStart(int x) {AngleStart = x;}
    public void setAngleStop(int x) {AngleStop = x;}
    public void setL(int x) {L = x;}
    public void setOffen(boolean x) {offen = x;}
    
    Spieler() {
        // Pacman Variablen
        this.setX(14*s.r);
        this.setY(23*s.r);
        this.setR(0); // Rotation
        this.setV(5); // Max. Geschwindigkeit in Pixeln pro Frame, muss Teiler von r sein, 5
        this.setVx(this.getV()); // Startrichtung
        this.setVy(0);
        this.setSoundPlaying(false);
        this.setSollRichtung(key);
    }
}
