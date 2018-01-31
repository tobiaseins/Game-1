import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenster extends JFrame implements KeyListener {
	// Variablen
	private Point Groesse = new Point(887,340);
	
	// Konstruktor
	public Fenster() {
		setTitle("PacMan");				// Titel setzten
		addKeyListener(this);			// Füge die Tastenerkennung hinzu
		setSize(Groesse.x,Groesse.y);	// Größe festlegen
    	setVisible(true);				// sichtbar machen
	}
	
	// zeichnen
	protected void paintComponent(Graphics g) {
		
	}
	
	// auf Tastendrücke reagieren
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();	// Tastencode auslesen
		
		// Tastencode vergleichen und die Bewegungsrichtung festlegen
		// Bewegung: 1 = rechts; 2 = links; 3 = oben; 4 = unten
	    switch(keyCode) {
	    	case KeyEvent.VK_RIGHT: 	// Vergleich 
	    		break; 					// Fertig - nicht mehr weitermachen
	        case KeyEvent.VK_LEFT: 		// Vergleich
	        	break; 					// Ferig - nicht mehr weitermachen
	        case KeyEvent.VK_UP: 		// Verlgeich
	        	break; 					// Fertig - nicht mehr weitermachen
	        case KeyEvent.VK_DOWN: 		// Verlgeich
	        	break; 					// Ferig - nicht mehr weitermachen
	    } 
	}

	// Unnötig - muss aber auftauchen - sagt der KeyListener
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}