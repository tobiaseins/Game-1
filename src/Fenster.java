import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Fenster extends JComponent implements ActionListener {
	
	public static int key = 0; // 0: nichts, 1: UP, 2: RIGHT, 3: DOWN, 4: LEFT
	public static Spielfeld s = new Spielfeld();

	
	//Hauptmethode
    public static void main(String[] args) {
    	
    	//Point Groesseobr = new Point(s.playGround[0].length,s.playGround.length); // Reine Groesse ohne Pixelberechnung
    	Point Groesse = new Point(s.playGround[0].length*s.raster_Groesse+16,s.playGround.length*s.raster_Groesse+38); // Groesse mit Pixelberechnung aus Groesseobr
    	
    	JFrame w = new JFrame("PacMan");
    	
        Fenster game = new Fenster();
        
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setLocationRelativeTo(null);
        w.add(game);
		w.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch( keyCode ) { 
                    case KeyEvent.VK_UP: key = 1; break;
                    case KeyEvent.VK_RIGHT: key = 2; break;
                    case KeyEvent.VK_DOWN: key = 3; break;
                    case KeyEvent.VK_LEFT: key = 4; break;
                }
                //System.out.println(e.getKeyChar() + " pressed");
            }
            public void keyReleased(KeyEvent e) {
                //System.out.println(e.getKeyChar() + " released");
            }
            public void keyTyped(KeyEvent e) {
                //System.out.println(e.getKeyChar() + " typed");
            }
		});			// Füge die Tastenerkennung hinzu
		
		w.setSize(Groesse.x,Groesse.y);	// Größe festlegen
    	w.setVisible(true);				// sichtbar machen
    }

    protected void paintComponent(Graphics g) {
    	// Hintergrund
        g.setColor(new Color(s.get_Hintergrundfarbe()[0], s.get_Hintergrundfarbe()[1], s.get_Hintergrundfarbe()[2]));
        g.fillRect(0, 0, s.playGround[0].length*s.raster_Groesse, s.playGround.length*s.raster_Groesse);
        
        // WÃ¤nde und Punkte
        for(int a = 0; a<s.playGround.length; a++) {
            for(int b = 0; b<s.playGround[0].length; b++) {
                if(s.playGround[a][b] == 1) {
                    // Wand
                    g.setColor(new Color(s.get_farbe_Waende()[0],s.get_farbe_Waende()[1],s.get_farbe_Waende()[2]));
                    g.fillRect(b*s.raster_Groesse, a*s.raster_Groesse, s.raster_Groesse, s.raster_Groesse);
                } else if(s.playGround[a][b] == 2) {
                    // Punkt
                	g.setColor(new Color(s.get_farbe_Punkte()[0],s.get_farbe_Punkte()[1],s.get_farbe_Punkte()[2]));
                    int c = s.raster_Groesse/3;
                    g.fillRect(b*s.raster_Groesse+s.raster_Groesse/2-s.raster_Groesse/c/2, a*s.raster_Groesse+s.raster_Groesse/2-s.raster_Groesse/c/2, s.raster_Groesse/c, s.raster_Groesse/c);
                }
            }
        }
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
    }
};