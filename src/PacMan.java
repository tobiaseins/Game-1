public class PacMan extends Figuren{
	// Variablen nur für Pacman
	public int Winkel_min;
	public int Winkel_max;
	public int leben;
	public boolean mund_offen;
	
	// Getter- und Settermethoden
	public int get_Winkel_min () {return Winkel_min;}
	public int get_Winkel_max () {return Winkel_max;}
	public int get_leben () {return leben;}
	public boolean get_mund_offen () {return mund_offen;}
	
	public void set_Winkel_min (int newWinkel_min) {Winkel_min = newWinkel_min;}
	public void set_Winkel_max (int newWinkel_max) {Winkel_max = newWinkel_max;}
	public void set_leben (int newLeben) {leben = newLeben;}
	public void set_mund_offen (boolean newMund_offen) {mund_offen = newMund_offen;}
	
	// Leben verlieren Funktion
}
