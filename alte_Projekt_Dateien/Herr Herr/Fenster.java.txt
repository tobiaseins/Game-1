import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenster extends JFrame implements KeyListener{
    
	//Objekt vom Typ Elemente_im_Spiel erzeugen, der von JPanel erbt
	JPanel e_im_spiel = new Elemente_im_Spiel();
	
	//Konstruktor
	public Fenster()
	{
		//Titel setzten
		setTitle("PacMan");
		
		//Füge PacMan dem Fenster hinzu
		add(e_im_spiel);
		
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
	    		((Elemente_im_Spiel) e_im_spiel).setTaste(1); //Bewegung nach rechts
	    		break; //Fertig - nicht mehr weitermachen
	        case KeyEvent.VK_LEFT: //Vergleich
	        	((Elemente_im_Spiel) e_im_spiel).setTaste(2); //Bewegung nach links
	        	break; //Ferig - nicht mehr weitermachen
	        case KeyEvent.VK_UP: //Verlgeich
	        	((Elemente_im_Spiel) e_im_spiel).setTaste(3); //Bewegung nach oben
	        	break; //Fertig - nicht mehr weitermachen
	        case KeyEvent.VK_DOWN: 
	        	((Elemente_im_Spiel) e_im_spiel).setTaste(4); //Bewegung nach unten
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