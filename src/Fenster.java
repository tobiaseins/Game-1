import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenster extends JFrame implements KeyListener{
	
	private Point Groesse = new Point(887,340);
	
	//Konstruktor
	public Fenster()
	{
		//Titel setzten
		setTitle("PacMan");
		
		//Füge die Tastenerkennung hinzu
		addKeyListener(this);
		
		//Größe festlegen
		setSize(887,340);
		
		//sichtbar machen
    	setVisible(true);
	}
	
	//auf Tastendrücke reagieren
	public void keyPressed(KeyEvent e) {
		
		//Tastencode auslesen
		int keyCode = e.getKeyCode();
		
		//Tastencode vergleichen und die Bewegungsrichtung festlegen
		//Bewegung: 1 = rechts; 2 = links; 3 = oben; 4 = unten
	    switch( keyCode ) {
	    	case KeyEvent.VK_RIGHT: //Vergleich 
	    		break; //Fertig - nicht mehr weitermachen
	        case KeyEvent.VK_LEFT: //Vergleich
	        	break; //Ferig - nicht mehr weitermachen
	        case KeyEvent.VK_UP: //Verlgeich
	        	break; //Fertig - nicht mehr weitermachen
	        case KeyEvent.VK_DOWN: 
	        	break; //Ferig - nicht mehr weitermachen
	    }
		        
	}

	//Unnötig - muss aber auftauchen - sagt der KeyListener
	public void keyTyped(KeyEvent e)
	{
	}
			 
	//Unnötig - muss aber auftauchen - sagt der KeyListener
	public void keyReleased(KeyEvent e)
	{
	}
}