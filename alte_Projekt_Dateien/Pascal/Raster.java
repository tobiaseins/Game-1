import java.awt.Color;
import java.awt.Graphics2D;

public class Raster extends Figuren{

	//Linien in x-Richtung zeichnen
	public void paintXRaster(Graphics2D g, int breite, Color farbe, int anzahl)
	{
		g.setColor(farbe);
		g.drawLine(0, anzahl, breite, anzahl);
	}
	
	//Linien in y-Richtung zeichnen
	public void paintYRaster(Graphics2D g, int hoehe, Color farbe, int anzahl)
	{
		g.setColor(farbe);
		g.drawLine(anzahl, 0, anzahl, hoehe);
		
	}
}
