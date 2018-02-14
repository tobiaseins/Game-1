// Main Klasse
package pacman;
// Problem 1: 2 Soundtracks am Anfang -> Lösung: kein Fressen auf Startkoordinate -> gelöst
// Problem 2: Pacman soll gleich loslaufen am Anfang -> soll er das wirklich? nur die Geister sollen gleich loslaufen
// Problem 2.5: Soll Pacman stehen bleiben wenn gegen Wand gesteuert wird?

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pacman extends JComponent implements ActionListener {
    // Objekte erstellen
    public static Spieler p = new Spieler();
    public static Geist g1 = new Geist();
    //public static Geist g2 = new Geist();
    ////public static Geist g4 = new Geist();
    public static Spielfeld s = new Spielfeld();
    
    // Variablen
    public static int key = 0; // 0: nichts, 1: UP, 2: RIGHT, 3: DOWN, 4: LEFT
    public static int fps = 24; // Bilder pro Sekunde
    public static int refresh = 1000/fps; // in ms
    
    public static int count = 0; // Framezähler
    public static int Score = 0;
    public static int t = 0;
    
    //@SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        // Window setup
        JFrame w = new JFrame("Nico's Pacman");
        Pacman game = new Pacman();
        w.add(game);
        w.pack();
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        
        // KeyListener
        w.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch( keyCode ) { 
                    case KeyEvent.VK_UP: key = 1; p.dirUpdateSpieler(); break;
                    case KeyEvent.VK_DOWN: key = 3; p.dirUpdateSpieler(); break;
                    case KeyEvent.VK_LEFT: key = 4; p.dirUpdateSpieler(); break;
                    case KeyEvent.VK_RIGHT: key = 2; p.dirUpdateSpieler(); break;
                }
                //System.out.println(e.getKeyChar() + " pressed");
            }
            public void keyReleased(KeyEvent e) {
                //System.out.println(e.getKeyChar() + " released");
            }
            public void keyTyped(KeyEvent e) {
                //System.out.println(e.getKeyChar() + " typed");
            }
        });
        
        // Timer
        Timer t = new Timer(refresh, game);
        t.start();
        
        game.add(p); // neu
    }
    
    // Fehlermeldung
    public void Fehler(String Fkt, String Art, String Anmerkung) {
        System.err.println("\"" + Art + "\"-Fehler in Funktion \"" + Fkt + "\".");
        System.out.println("Position und Geschwindigkeit - pX/s.r: " + p.getX()/s.r + "; pY/s.r: " + p.getY()/s.r + "; pX: " + p.getX() + "; pY: " + p.getY() + "; pVx: " + p.getVx() + "; pVy: " + p.getVy());
        System.out.println("Anmerkungen: \"" + Anmerkung + "\".");
    }
    
    // Fehlermeldung
    public void Fehler(String Fkt, String Art) {
        System.err.println("\"" + Art + "\"-Fehler in Funktion \"" + Fkt + "\".");
        System.out.println("Position und Geschwindigkeit - pX/s.r: " + p.getX()/s.r + "; pY/s.r: " + p.getY()/s.r + "; pX: " + p.getX() + "; pY: " + p.getY() + "; pVx: " + p.getVx() + "; pVy: " + p.getVy());
    }
    
    // Fenstergröße
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(s.feld[0].length*s.r, s.feld.length*s.r);
    }
    
    // Figuren zeichnen -> for Schleifen und Berechnungen reduzieren
    @Override
    protected void paintComponent(Graphics g) {
        // Hintergrund
        g.setColor(new Color(s.bgcolor[0], s.bgcolor[1], s.bgcolor[2]));
        g.fillRect(0, 0, s.feld[0].length*s.r, s.feld.length*s.r);
        
        // Wände und Punkte
        for(int a = 0; a<s.feld.length; a++) {
            for(int b = 0; b<s.feld[0].length; b++) {
                try {
                    if(s.feld[a][b] == 1) {
                        // Wand
                        g.setColor(Color.BLUE);
                        g.fillRect(b*s.r, a*s.r, s.r, s.r);
                    } else if(s.feld[a][b] == 2) {
                        // Punkt
                        g.setColor(Color.WHITE);
                        int c = 10;
                        g.fillRect(b*s.r+s.r/2-s.r/c/2, a*s.r+s.r/2-s.r/c/2, s.r/c, s.r/c);
                    }
                } catch(ArrayIndexOutOfBoundsException exception) {
                    Fehler("paintComponent", "ArrayIndexOutOfBoundsException", "Mögliche Fehlerquelle: s.feld ist keine rechteckige Array.");
                }
            }
        }
        
        // Pacman
        g.setColor(Color.YELLOW);
        g.fillArc(p.getX()+s.r/6, p.getY()+s.r/6, s.r/3*2, s.r/3*2, p.getAngleStart(), p.getAngleStop());
        
        // Geister
        g.setColor(Color.red);
        g.fillArc(g1.getX()+s.r/6, g1.getY()+s.r/6, s.r/3*2, s.r/3*2, 0, 360);
        //g.fillArc(g2.getX()+s.r/6, g1.getY()+s.r/6, s.r/3*2, s.r/3*2, 0, 360);
        //g.fillArc(g3.getX()+s.r/6, g1.getY()+s.r/6, s.r/3*2, s.r/3*2, 0, 360);
        //g.fillArc(g4.getX()+s.r/6, g1.getY()+s.r/6, s.r/3*2, s.r/3*2, 0, 360);
        
        // Augen Geist 1
        g.setColor(Color.black);
        g.fillArc(g1.getX()+s.r/6+4, g1.getY()+s.r/6+2, 3, 3, 0, 360);
        g.fillArc(g1.getX()+s.r/6+10, g1.getY()+s.r/6+2, 3, 3, 0, 360);
        
        // Score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + Integer.toString(Score), s.feld[0].length*s.r - 100, s.feld.length*s.r - 5);
    }
    
    // Refresh
    @Override
    public void actionPerformed(ActionEvent e) {
        // Pacman
        boolean Pause = false;
        
        if(!p.beginning.clip.isActive() && !Pause) {
            p.Move();
            p.Animation();
            p.Fressen();
            
            g1.Move();
        }
        
        if(count == 0) {
            p.Sound(1);
        }
        
        if(count%5 == 0) {
            g1.dirUpdateGeist();
        }
        
        // Zähler & Frame zeichnen
        count++;
        repaint();
    }
}
