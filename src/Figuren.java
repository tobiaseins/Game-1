import java.awt.*;
import javax.swing.JPanel;

public abstract class Figuren extends Fenster {				// extends Fenster ???, Warum sollte Figuren von JPanel erben?
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
	public void wand_vor_figur() {
		switch (this.get_soll_richtung()) {
	    	case 2://rechts
	    		if (s.spielfeld[this.get_position().y/s.raster_Groesse][this.get_position().x/s.raster_Groesse + 1] != 1) {
	    			this.bewege();
	    		}
	    		break;
	    	case 4://links
	    		if (s.spielfeld[this.get_position().y/s.raster_Groesse][(this.get_position().x - 1)/s.raster_Groesse] != 1) {
	    			this.bewege();
	    		}
	    		break;
	    	case 1://oben
	    		if (s.spielfeld[(this.get_position().y - 1)/s.raster_Groesse][this.get_position().x/s.raster_Groesse] != 1) {
	    			this.bewege();
	    		}
	    		break;
	    	case 3://unten
	    		if (s.spielfeld[this.get_position().y/s.raster_Groesse + 1][this.get_position().x/s.raster_Groesse] != 1) {
	    			this.bewege();
	    		}
	    		break;
	    	default:
	    		break;
		}	
	}

	public void bewege() {
		switch (this.get_soll_richtung()) {
			case 2:	// rechts
				this.set_xGeschwindigkeit(this.get_geschwindigkeit());
				this.set_yGeschwindigkeit(0);
				//this.set_position(get_position().x + get_xGeschwindigkeit(), get_position().y);
				break;
			case 4: // links
				this.set_xGeschwindigkeit(0 - this.get_geschwindigkeit());
				this.set_yGeschwindigkeit(0);
				//set_position(get_position().x - get_xGeschwindigkeit(), get_position().y);
				break;
			case 1: // oben
				this.set_xGeschwindigkeit(0);
				this.set_yGeschwindigkeit(0 - this.get_geschwindigkeit());
				//set_position(get_position().x, get_position().y - get_yGeschwindigkeit());
				break;
			case 3: // unten
				this.set_xGeschwindigkeit(0);
				this.set_yGeschwindigkeit(this.get_geschwindigkeit());
				//set_position(get_position().x, get_position().y + get_yGeschwindigkeit());
				break;
			default:
				break;
		}
		
		this.set_position(this.get_position().x + this.get_xGeschwindigkeit(), this.get_position().y + this.get_yGeschwindigkeit());
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
    public void set_position(int xPo, int yPo) {this.position = new Point(xPo, yPo);}
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
