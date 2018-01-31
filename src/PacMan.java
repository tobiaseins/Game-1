public class PacMan extends Figuren{
	// Variablen nur für Pacman

	private int Winkel_min;
	private int Winkel_max;
	private int leben;
	private boolean mund_offen;
	private Audio audio_punkt;
	private Audio audio_sterben;
	private Audio audio_intro;
	
	public PacMan(){
		this.Winkel_min = 270;
		this.Winkel_max = 45;
		this.leben = 3;
		this.mund_offen = false;
		this.audio_punkt = new Audio("pacman_punkt");
		this.audio_sterben = new Audio("pacman_sterben");
		this.audio_intro = new Audio("pacman_intro");
	}

	// Getter- und Settermethoden
	public int get_Winkel_min () {return Winkel_min;}
	public int get_Winkel_max () {return Winkel_max;}
	public int get_leben () {return leben;}
	public boolean get_mund_offen () {return mund_offen;}
	

	public void set_Winkel_min (int newWinkel_min) {Winkel_min = newWinkel_min;}
	public void set_Winkel_max (int newWinkel_max) {Winkel_max = newWinkel_max;}
	public void set_leben (int newLeben) {leben = newLeben;}
	public void set_mund_offen (boolean newMund_offen) {mund_offen = newMund_offen;}
}
