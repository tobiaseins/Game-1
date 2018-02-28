import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Fenster extends JComponent implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static int key = 0; // 0: nichts, 1: UP, 2: RIGHT, 3: DOWN, 4: LEFT
	public static Spielfeld s = new Spielfeld();
	public static PacMan p = new PacMan(s.raster_Groesse);
	public static Geist g1 = new Geist(new Point(14*s.raster_Groesse,9*s.raster_Groesse), 1, Color.RED);
	public static Geist g2 = new Geist(new Point(14*s.raster_Groesse,9*s.raster_Groesse), 1, Color.GREEN);
	public static Geist g3 = new Geist(new Point(14*s.raster_Groesse,9*s.raster_Groesse), 1, Color.YELLOW);
	public static Geist g4 = new Geist(new Point(14*s.raster_Groesse,9*s.raster_Groesse), 1, Color.BLUE);
	
	public static int fps = 24; // Bilder pro Sekunde
    public static int refresh = 1000/fps; // in ms
    public static int count = 0;

	
	//Hauptmethode
    public static void main(String[] args) {
    	
    	Point Groesse = new Point(s.spielfeld[0].length*s.raster_Groesse+16,s.spielfeld.length*s.raster_Groesse+38);
    	
    	JFrame w = new JFrame("PacMan");
    	
        Fenster game = new Fenster();
        
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setLocationRelativeTo(null);
        w.setLocationByPlatform(true);
        w.add(game);
		w.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch( keyCode ) { 
                    case KeyEvent.VK_UP: key = 2; p.richtungs_update(key); break;
                    case KeyEvent.VK_RIGHT: key = 1; p.richtungs_update(key); break;
                    case KeyEvent.VK_DOWN: key = 4; p.richtungs_update(key); break;
                    case KeyEvent.VK_LEFT: key = 3; p.richtungs_update(key); break;
                    case KeyEvent.VK_SPACE: if (p.tot()) game_reset(); break;
                }
                //System.out.println(e.getKeyChar() + " pressed");
            }
            public void keyReleased(KeyEvent e) {
                //System.out.println(e.getKeyChar() + " released");
            }
            public void keyTyped(KeyEvent e) {
                //System.out.println(e.getKeyChar() + " typed");
            }
		});			// FÃƒÆ’Ã‚Â¼ge die Tastenerkennung hinzu
		
		w.setSize(Groesse.x,Groesse.y);	// GrÃƒÆ’Ã‚Â¶ÃƒÆ’Ã…Â¸e festlegen
    	w.setVisible(true);				// sichtbar machen
    	
    	// Timer fÃƒÂ¼r das Neuzeichnen --> ersetzt das repaint() in
    	// der Methode paintComponent(Graphics g)
    	// Hierdurch werden weniger Ressourcen verbraucht und die
    	// Animation lÃƒÂ¤uft flÃƒÂ¼ssiger
        Timer t = new Timer(refresh, game);
        t.start();
    }

    public static void game_reset() {
    	s = new Spielfeld();
    	p.gameReset(s.raster_Groesse);
    	g1.reset(s.raster_Groesse);
    	g2.reset(s.raster_Groesse);
    	g3.reset(s.raster_Groesse);
    	g4.reset(s.raster_Groesse);
    	
    }
    
    protected void paintComponent(Graphics g) {
    	// Hintergrund
        g.setColor(new Color(s.get_Hintergrundfarbe()[0], s.get_Hintergrundfarbe()[1], s.get_Hintergrundfarbe()[2]));
        g.fillRect(0, 0, s.spielfeld[0].length*s.raster_Groesse, s.spielfeld.length*s.raster_Groesse);
        
        // WÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¤nde und Punkte
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
                } else if(s.spielfeld[a][b] == 5) {
                    // Kirsche
                	g.setColor(new Color(s.get_farbe_Kirsche()[0],s.get_farbe_Kirsche()[1],s.get_farbe_Kirsche()[2]));
                	BufferedImage kirschenimg = null;
                	try { //Lade das Bild
    	    			kirschenimg = ImageIO.read(new File("../PacMan/src/Bilder/kirschen.png"));
    	    			g.drawImage(kirschenimg, b*s.raster_Groesse, a*s.raster_Groesse, s.raster_Groesse, s.raster_Groesse, null);
    	    		} catch (IOException e) {
				//Wenn Bild nicht funktioniert mache einfaches Rechteck als Kirsche
    	    			g.fillRect(b*s.raster_Groesse, a*s.raster_Groesse, s.raster_Groesse, s.raster_Groesse);
    	    		}
    	    	}
            }
        }
        
        // Score und Leben anzeigen
        g.setColor(Color.white);
        //System.out.println(g.getFont() + "");
        g.drawString("Score: " + p.get_score(), s.spielfeld[0].length*s.raster_Groesse - 100, s.spielfeld.length*s.raster_Groesse - 10);
        g.drawString("Leben: " + p.get_leben(), 100, s.spielfeld.length*s.raster_Groesse - 10);
        
        // Pacman Animation
        int speed = s.raster_Groesse/2;        
        int count2 = count*speed;
        int offenWinkel = count2%90;
        if(offenWinkel>45) offenWinkel = 45-count2%45;
        
        //PacMan
        g.setColor(p.get_farbe());
	    g.fillArc(p.get_position().x + (s.raster_Groesse-p.get_radius())/2, 
	    		p.get_position().y + (s.raster_Groesse-p.get_radius())/2, p.get_radius(), p.get_radius(),
	              45 + 90*(p.get_bewegungsrichtung()-1)-offenWinkel, 275+offenWinkel*2);
	    
	    
	    //Geist
	    g.setColor(g1.get_farbe());
	    BufferedImage g1img = null;
    	try { //Lade das Bild
			g1img = ImageIO.read(new File("../PacMan/src/Bilder/crimsonjaeger.png"));
			g.drawImage(g1img, g1.get_position().x-g1.get_radius()/2, g1.get_position().y-g1.get_radius()/2, g1.get_radius()*2, g1.get_radius()*2, null);
		} catch (IOException e) {
		//Wenn Bild nicht funktioniert mache einfaches Rechteck als Geist
			g.fillRect(g1.get_position().x, g1.get_position().y, g1.get_radius(), g1.get_radius());
		}
	    
	    //Geist
	    g.setColor(g2.get_farbe());
	    BufferedImage g2img = null;
	    try { //Lade das Bild
			g2img = ImageIO.read(new File("../PacMan/src/Bilder/cyanschrecken.png"));
			g.drawImage(g2img, g2.get_position().x-g2.get_radius()/2, g2.get_position().y-g2.get_radius()/2, g2.get_radius()*2, g2.get_radius()*2, null);
		} catch (IOException e) {
		//Wenn Bild nicht funktioniert mache einfaches Rechteck als Geist
			g.fillRect(g2.get_position().x, g2.get_position().y, g2.get_radius(), g2.get_radius());
		}
	    
	    //Geist
	    g.setColor(g3.get_farbe());
	    BufferedImage g3img = null;
	    try { //Lade das Bild
			g3img = ImageIO.read(new File("../PacMan/src/Bilder/rosaschleicher.png"));
			g.drawImage(g3img, g3.get_position().x-g3.get_radius()/2, g3.get_position().y-g3.get_radius()/2, g3.get_radius()*2, g3.get_radius()*2, null);
		} catch (IOException e) {
		//Wenn Bild nicht funktioniert mache einfaches Rechteck als Geist
			g.fillRect(g3.get_position().x, g3.get_position().y, g3.get_radius(), g3.get_radius());
		}
	    
	    //Geist
	    g.setColor(g4.get_farbe());
	    BufferedImage g4img = null;
	    try { //Lade das Bild
			g4img = ImageIO.read(new File("../PacMan/src/Bilder/scharlachwaechter.png"));
			g.drawImage(g4img, g4.get_position().x-g4.get_radius()/2, g4.get_position().y-g4.get_radius()/2, g4.get_radius()*2, g4.get_radius()*2, null);
		} catch (IOException e) {
		//Wenn Bild nicht funktioniert mache einfaches Rechteck als Geist
			g.fillRect(g4.get_position().x, g4.get_position().y, g4.get_radius(), g4.get_radius());
		}
	    
	    if(p.tot()) {
    		g.setColor(Color.RED);
    		g.setFont(new Font("TimesRoman", Font.PLAIN, 150)); 
    		g.drawString("GAME OVER", 25, getHeight()/2);
    		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
    		g.drawString("DrÃ¼cke Leertaste zum Neustarten", getWidth()/2-300, getHeight()/2+50);
    	}
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if(!p.tot()) {
		count++;
		
		p.wand_vor_figur(s.spielfeld, s.raster_Groesse);
		p.punkte_fressen(s.spielfeld, s.raster_Groesse);
		if (p.leben_verlieren(s.spielfeld, g1.get_position(), s.raster_Groesse) ||
		p.leben_verlieren(s.spielfeld, g2.get_position(), s.raster_Groesse) ||
		p.leben_verlieren(s.spielfeld, g3.get_position(), s.raster_Groesse) ||
		p.leben_verlieren(s.spielfeld, g4.get_position(), s.raster_Groesse)) {
			count = 0;
			p.reset(s.raster_Groesse);
			g1.reset(s.raster_Groesse);
			g2.reset(s.raster_Groesse);
			g3.reset(s.raster_Groesse);
			g4.reset(s.raster_Groesse);
		}
		if(p.tot()){
			//System.exit(0);
		};
		g1.richtungs_update(p.get_position());
		g1.wand_vor_figur(s.spielfeld, s.raster_Groesse);
		g1.wand_vor_geist(count, s.spielfeld, s.raster_Groesse);
		g2.richtungs_update(p.get_position());
		g2.wand_vor_figur(s.spielfeld, s.raster_Groesse);
		g2.wand_vor_geist(count, s.spielfeld, s.raster_Groesse);
		g3.richtungs_update(p.get_position());
		g3.wand_vor_figur(s.spielfeld, s.raster_Groesse);
		g3.wand_vor_geist(count, s.spielfeld, s.raster_Groesse);
		g4.richtungs_update(p.get_position());
		g4.wand_vor_figur(s.spielfeld, s.raster_Groesse);
		g4.wand_vor_geist(count, s.spielfeld, s.raster_Groesse);
		g1.quadrant(p.get_position());
		g2.quadrant(p.get_position());
		g3.quadrant(p.get_position());
		g4.quadrant(p.get_position());
		}
    }
};
