import java.awt.Color;
import java.awt.Graphics2D;

public class Punkte extends Figuren {
	
	//Punkte zeichnen
	public void paintPunkte(Graphics2D g,int x, int y, int hoehe, int breite, Color farbe){
		g.setColor(farbe);
        g.fillOval( x, y, hoehe, breite);
	}
}
