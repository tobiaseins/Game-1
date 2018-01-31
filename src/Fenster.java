import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenster extends JFrame implements KeyListener {
	
	// Objekt vom Typ Elemente_im_Spiel erzeugen, der von JPanel erbt
	JPanel elemente = new Elemente();
	
	// Variablen
	private Point Groesse = new Point(887,340);
	
	// Konstruktor
	public Fenster() {
		setTitle("PacMan");				// Titel setzten
		addKeyListener(this);			// Füge die Tastenerkennung hinzu
		setSize(Groesse.x,Groesse.y);	// Größe festlegen
    	setVisible(true);				// sichtbar machen
    	add(elemente);					// Füge die Elemente hinzu
	}
	
	//Hauptmethode
    public static void main(String[] args) { new Fenster(); };
	
	// auf Tastendrücke reagieren
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();	// Tastencode auslesen
		
		// Tastencode vergleichen und die Bewegungsrichtung festlegen
		// Bewegung: 1 = rechts; 2 = links; 3 = oben; 4 = unten
	    switch(keyCode) {
	    	case KeyEvent.VK_RIGHT: 	// Vergleich 
	    		break; 					// Fertig - nicht mehr weitermachen
	        case KeyEvent.VK_LEFT: 		// Vergleich
	        	break; 					// Fertig - nicht mehr weitermachen
	        case KeyEvent.VK_UP: 		// Vergleich
	        	break; 					// Fertig - nicht mehr weitermachen
	        case KeyEvent.VK_DOWN: 		// Vergleich
	        	break; 					// Fertig - nicht mehr weitermachen
	    } 
	}

	// Unnötig - muss aber auftauchen - sagt der KeyListener
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}