import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Fenster extends JComponent implements ActionListener {
	
	public static int key = 0; // 0: nichts, 1: UP, 2: RIGHT, 3: DOWN, 4: LEFT
	public static Spielfeld s = new Spielfeld();
	public static PacMan p = new PacMan();
	public static Geist g1 = new Geist(new Point(14*s.raster_Groesse,9*s.raster_Groesse), 1, Color.RED);
	
	public static int fps = 24; // Bilder pro Sekunde
    public static int refresh = 1000/fps; // in ms

	
	//Hauptmethode
    public static void main(String[] args) {
    	
    	Point Groesse = new Point(s.spielfeld[0].length*s.raster_Groesse+16,s.spielfeld.length*s.raster_Groesse+38);
    	
    	JFrame w = new JFrame("PacMan");
    	
        Fenster game = new Fenster();
        
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setLocationRelativeTo(null);
        w.add(game);
		w.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch( keyCode ) { 
                    case KeyEvent.VK_UP: key = 1; p.richtungs_update(1); break;
                    case KeyEvent.VK_RIGHT: key = 2; p.richtungs_update(2); break;
                    case KeyEvent.VK_DOWN: key = 3; p.richtungs_update(3); break;
                    case KeyEvent.VK_LEFT: key = 4; p.richtungs_update(4); break;
                }
                //System.out.println(e.getKeyChar() + " pressed");
            }
            public void keyReleased(KeyEvent e) {
                //System.out.println(e.getKeyChar() + " released");
            }
            public void keyTyped(KeyEvent e) {
                //System.out.println(e.getKeyChar() + " typed");
            }
		});			// FÃ¼ge die Tastenerkennung hinzu
		
		w.setSize(Groesse.x,Groesse.y);	// GrÃ¶ÃŸe festlegen
    	w.setVisible(true);				// sichtbar machen
    	
    	// Timer für das Neuzeichnen --> ersetzt das repaint() in
    	// der Methode paintComponent(Graphics g)
    	// Hierdurch werden weniger Ressourcen verbraucht und die
    	// Animation läuft flüssiger
        Timer t = new Timer(refresh, game);
        t.start();
    }

    protected void paintComponent(Graphics g) {
    	// Hintergrund
        g.setColor(new Color(s.get_Hintergrundfarbe()[0], s.get_Hintergrundfarbe()[1], s.get_Hintergrundfarbe()[2]));
        g.fillRect(0, 0, s.spielfeld[0].length*s.raster_Groesse, s.spielfeld.length*s.raster_Groesse);
        
        // WÃƒÂ¤nde und Punkte
        for(int a = 0; a<s.spielfeld.length; a++) {
            for(int b = 0; b<s.spielfeld[0].length; b++) {
                if(s.spielfeld[a][b] == 1) {
                    // Wand
                    g.setColor(new Color(s.get_farbe_Waende()[0],s.get_farbe_Waende()[1],s.get_farbe_Waende()[2]));
                    g.fillRect(b*s.raster_Groesse, a*s.raster_Groesse, s.raster_Groesse, s.raster_Groesse);
                } else if(s.spielfeld[a][b] == 2) {
                    // Punkt
                	g.setColor(new Color(s.get_farbe_Punkte()[0],s.get_farbe_Punkte()[1],s.get_farbe_Punkte()[2]));
                    int c = s.raster_Groesse/3;
                    g.fillRect(b*s.raster_Groesse+s.raster_Groesse/2-s.raster_Groesse/c/2, a*s.raster_Groesse+s.raster_Groesse/2-s.raster_Groesse/c/2, s.raster_Groesse/c, s.raster_Groesse/c);
                } else if(s.spielfeld[a][b] == 3) {
                    // Geisterwand
                	g.setColor(new Color(s.get_farbe_Geister_Waende()[0],s.get_farbe_Geister_Waende()[1],s.get_farbe_Geister_Waende()[2]));
                    int c = s.raster_Groesse/3;
                    g.fillRect(b*s.raster_Groesse + 1, a*s.raster_Groesse+s.raster_Groesse/2-s.raster_Groesse/c/2, s.raster_Groesse, s.raster_Groesse/c);
                }
            }
        }
        
        // Score und Leben anzeigen
        g.setColor(Color.white);
        //System.out.println(g.getFont() + "");
        g.drawString("Score: " + "100", s.spielfeld[0].length*s.raster_Groesse - 100, s.spielfeld.length*s.raster_Groesse - 10);
        g.drawString("Leben: " + "2", 100, s.spielfeld.length*s.raster_Groesse - 10);
        
        //PacMan
        g.setColor(p.get_farbe());
	    g.fillArc(p.get_position().x, p.get_position().y, p.get_radius(), p.get_radius(),
	              45 + p.get_bewegungsrichtung(), 360-2*p.get_bewegungsrichtung());
	    
	    
	    //Geist
	    g.setColor(g1.get_farbe());
	    //g.drawImage(g1.animation(), g1.get_position().x, g1.get_position().y, g1.get_radius(), g1.get_radius(), null);
	    g.fillRect(g1.get_position().x, g1.get_position().y, g1.get_radius(), g1.get_radius());
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
		p.wand_vor_figur();
		g1.richtungs_update(p.get_position());
		g1.wand_vor_figur();
    }
};