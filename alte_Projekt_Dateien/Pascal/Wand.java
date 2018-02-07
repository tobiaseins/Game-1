import java.awt.Color;
import java.awt.Graphics2D;

public class Wand {
	
	//Wand zeichnen
	public void paintWand(Graphics2D g,int x, int y, int hoehe, int breite, Color farbe) {
		g.setColor(farbe);
        g.fillRect( x, y, hoehe, breite);
	}
}
