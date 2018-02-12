import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;


public class PacMan extends Figuren{
	// Variablen nur für Pacman
	public int Winkel_min;
	public int Winkel_max;
	public int leben;
	public boolean mund_offen;
	public boolean kontakt_mit_geist;
	
	public void set_Winkel_min(int newWinkel_min) {this.Winkel_min = newWinkel_min;}
	public void set_Winkel_max(int newWinkel_max) {this.Winkel_max = newWinkel_max;}
	public void set_leben(int newLeben) {this.leben = newLeben;}
	public void set_mund_offen(boolean newMund_offen) {this.mund_offen = newMund_offen;}
	public void set_kontakt_mit_geist(boolean newKontakt_mit_geist) {this.kontakt_mit_geist = newKontakt_mit_geist;}
	
	public int get_Winkel_min() {return Winkel_min;}
	public int get_Winkel_max() {return Winkel_max;}
	public int get_leben() {return leben;}
	public boolean get_mund_offen() {return mund_offen;}
	public boolean get_kontakt_mit_geist() {return kontakt_mit_geist;}

	
	// Leben verlieren Funktion
	public void leben_verlieren () {
		
	}
	
	public void punkte_fressen() {
		try {
			if(Spielfeld.spielfeld[get_position().y/get_radius()][get_position().x/get_radius()] == 2 && get_position().x%get_radius() == 0 && get_position().y%get_radius() == 0) {
				Spielfeld.spielfeld[get_position().y/get_radius()][get_position().x/get_radius()] = 0;
			}
		} catch(ArrayIndexOutOfBoundsException exception) {
			//Fehler("punkteFressen", "ArrayIndexOutOfBoundsException");
		}
	}
	
	public PacMan() {
		this.set_position(19*s.raster_Groesse,19*s.raster_Groesse);
	    this.set_geschwindigkeit(5);
	    this.set_bewegungsrichtung(2);
	    this.set_farbe(Color.yellow);
	    this.set_radius(s.raster_Groesse *5/6);
	    this.set_soll_richtung(1);
	    //set_soundplay(boolean tof);		-> wird woanders festgelegt
	    //set_modus(1);						-> wird nicht benutzt, ist nicht definiert
	    //geschwindigkeit wird in Figuren, bewege() festgelegt
	    
	    this.set_Winkel_min(45);
	    this.set_Winkel_max(275);
	    this.set_leben(2);
	    this.set_mund_offen(true);
	    this.set_kontakt_mit_geist(false);
	}
}
