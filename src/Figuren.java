import java.awt.*;

import javax.swing.JPanel;


public abstract class Figuren {		// extends Fenster ???
	private Point position;
	private int geschwindigkeit;
	private int bewegungsrichtung;
	private Color farbe;
	private int radius;
	private int soll_richtung;
	private boolean soundplay;
	protected int modus;
	private int xGeschwindigkeit;
	private int yGeschwindigkeit;
	
	//Konstruktor
	public Figuren() {
		
	}

	// Wand vor Figur?
	public boolean wand_vor_figur() {
		return false;						// später verbessern
	}

	public void bewegen() {
		if(this.get_bewegungsrichtung() == 1) {
			
		}
	}

	// Getter
    public Point get_position() {return this.position;}    
    public int get_geschwindigkeit() {return this.geschwindigkeit;}
    public int get_bewegungsrichtung() {return this.bewegungsrichtung;}
    public Color get_farbe() {return this.farbe;}
    public int get_radius() {return this.radius;}
    public int get_soll_richtung() {return this.soll_richtung;}
    public boolean get_soundplay() {return this.soundplay;}
    public int get_modus() {return this.modus;}
    public int get_xGeschwindigkeit() {return this.xGeschwindigkeit;}
    public int get_yGeschwindigkeit() {return this.yGeschwindigkeit;}

    // Setter
    public void set_Position(int xPo, int yPo) {this.position = new Point(xPo, yPo);}
    public void set_geschwindigkeit(int gesch) {this.geschwindigkeit = gesch;}
    public void set_bewegungsrichtung(int beri) {this.bewegungsrichtung = beri;}
    public void set_farbe(Color f) {this.farbe = f;}
    public void set_radius(int rad) {this.radius = rad;}
    public void set_soll_richtung(int soll) {this.soll_richtung = soll;}
    public void set_soundplay(boolean tof) {this.soundplay = tof;}
    public void set_modus(int mod) {this.modus = mod;}
    public void set_xGeschwindigkeit(int xgesch) {this.xGeschwindigkeit = xgesch;}
    public void set_yGeschwindigkeit(int ygesch) {this.yGeschwindigkeit = ygesch;}
}

