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
	public int score;
	
	public void set_Winkel_min(int newWinkel_min) {this.Winkel_min = newWinkel_min;}
	public void set_Winkel_max(int newWinkel_max) {this.Winkel_max = newWinkel_max;}
	public void set_leben(int newLeben) {this.leben = newLeben;}
	public void set_mund_offen(boolean newMund_offen) {this.mund_offen = newMund_offen;}
	public void set_kontakt_mit_geist(boolean newKontakt_mit_geist) {this.kontakt_mit_geist = newKontakt_mit_geist;}
	public void set_score(int newScore) {this.score = newScore;}
	
	public int get_Winkel_min() {return Winkel_min;}
	public int get_Winkel_max() {return Winkel_max;}
	public int get_leben() {return leben;}
	public boolean get_mund_offen() {return mund_offen;}
	public boolean get_kontakt_mit_geist() {return kontakt_mit_geist;}
	public int get_score() {return score;}

	
	// Leben verlieren Funktion
	public boolean leben_verlieren (int[][] spielfeld, Point geist_position, int raster_Groesse) {
		boolean lebenVerloren = false;
		try {
			if((int) (this.get_position().x + raster_Groesse/2)/raster_Groesse == (int) (geist_position.x + raster_Groesse/2)/raster_Groesse && 
					(int) (this.get_position().y + raster_Groesse/2)/raster_Groesse == (int) (geist_position.y + raster_Groesse/2)/raster_Groesse) {
				lebenVerloren = true;
			}
		} catch(ArrayIndexOutOfBoundsException exception) {
			//Fehler("punkteFressen", "ArrayIndexOutOfBoundsException");
		}
		return lebenVerloren;
		
	}
	
	public boolean tot() {
		boolean sehrTot = false;
		if(this.get_leben() == -1) {
			sehrTot = true;
		}
		return sehrTot;
	}
	
	public void richtungs_update(int x) {
		this.set_soll_richtung(x);
	}
	
	public void reset(int raster_Groesse) {
		set_leben(this.get_leben()-1);
		set_position(19*raster_Groesse,19*raster_Groesse);
		set_bewegungsrichtung(2);
		set_soll_richtung(1);
	}
	
	public void punkte_fressen(int [][] spielfeld, int raster_Groesse) {
		int punkt = spielfeld[get_position().y/raster_Groesse][get_position().x/raster_Groesse]; 
		try {
			if(punkt == 2 && get_position().x%raster_Groesse == 0 && get_position().y%raster_Groesse == 0) {
				spielfeld[get_position().y/raster_Groesse][get_position().x/raster_Groesse] = 0;
				this.set_score(this.get_score()+100);
			}
			if(punkt == 5  && get_position().x%raster_Groesse == 0 && get_position().y%raster_Groesse == 0) {
				spielfeld[get_position().y/raster_Groesse][get_position().x/raster_Groesse] = 0;
				this.set_score(this.get_score()+500);
			}
			
		} catch(ArrayIndexOutOfBoundsException exception) {
			//Fehler("punkteFressen", "ArrayIndexOutOfBoundsException");
		}
	}
	
	public PacMan(int raster_Groesse) {
		this.set_position(19*raster_Groesse,19*raster_Groesse);
	    this.set_geschwindigkeit(5);
	    this.set_bewegungsrichtung(2);
	    this.set_farbe(Color.yellow);
	    this.set_radius(raster_Groesse *5/6);
	    this.set_soll_richtung(1);
	    this.set_score(0);
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
