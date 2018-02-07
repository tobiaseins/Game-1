import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

public abstract class Figuren extends JPanel {
	
	private Point position;
	private Color farbe;
	private int orientierung;
	
	//Konstruktor (keine Funktion)
	public Figuren() {
	}
	
	//getter- und setter-Methoden für den Zugriff auf klasseninterne Variablen
	//Position einer Figur
	public void setPosition(int xPo, int yPo) {
		
		this.position = new Point(xPo,yPo);
	}
	
	public Point getPosition() {
		
		return this.position;
	}
	
	//Farbe der Figur
	public void setFarbe(Color f) {
		
		this.farbe =f;
	}
	
	public Color getFarbe() {
		
		return farbe;
	}
	
	//Orientierung der Figur
	public void setOrientierung(int o){
		
		this.orientierung = o;
	}
	
	public int getOrientierung(){
		
		return this.orientierung;
	}
}
