import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;


public class Elemente extends Figuren {
	
	public Elemente() {
		
	}
	
	
	// zeichnen
	public void paintComponent(Graphics g)
	{
	    g.setColor(Color.BLACK); //Schwarzer Hintergrund
	    g.fillRect(0, 0, getWidth(), getHeight());
		
	    repaint();	//alles neu zeichnen
		}		
}
