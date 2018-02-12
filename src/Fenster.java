import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Fenster extends JComponent implements ActionListener {
	
	public static int key = 0; // 0: nichts, 1: UP, 2: RIGHT, 3: DOWN, 4: LEFT
	public static Spielfeld s = new Spielfeld();
	public static PacMan p = new PacMan();

	
	//Hauptmethode
    public static void main(String[] args) {
    	
    	Point Groesse = new Point(s.playGround[0].length*s.raster_Groesse+16,s.playGround.length*s.raster_Groesse+38);
    	
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
    
    public void bewegungsGeschw() {
		try{
	    	Thread.sleep(10); //10 millisek.
	    } catch (InterruptedException e){
	    	
	    }
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
                } else if(s.playGround[a][b] == 3) {
                    // Geisterwand
                	g.setColor(new Color(s.get_farbe_Geister_Waende()[0],s.get_farbe_Geister_Waende()[1],s.get_farbe_Geister_Waende()[2]));
                    int c = s.raster_Groesse/3;
                    g.fillRect(b*s.raster_Groesse + 1, a*s.raster_Groesse+s.raster_Groesse/2-s.raster_Groesse/c/2, s.raster_Groesse, s.raster_Groesse/c);
                }
            }
        }
        
        // Score und Leben anzeigen
        g.setColor(Color.white);
        System.out.println(g.getFont() + "");
        g.drawString("Score: " + "100", s.playGround[0].length*s.raster_Groesse - 100, s.playGround.length*s.raster_Groesse - 10);
        g.drawString("Leben: " + "2", 100, s.playGround.length*s.raster_Groesse - 10);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
    }
};