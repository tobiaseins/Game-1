
public class PacMan extends Figuren{
	// Variablen nur für Pacman
	public int Winkel_min;
	public int Winkel_max;
	public int leben;
	public boolean mund_offen;
	public boolean kontakt_mit_geist;
	
	public void set_Winkel_min (int newWinkel_min) {Winkel_min = newWinkel_min;}
	public void set_Winkel_max (int newWinkel_max) {Winkel_max = newWinkel_max;}
	public void set_leben (int newLeben) {leben = newLeben;}
	public void set_mund_offen (boolean newMund_offen) {mund_offen = newMund_offen;}//Getter- und Settermethoden ende
	
	// Leben verlieren Funktion
	
	public void leben_verlieren () {
		
	}
}
