import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Geist extends Figuren {
	BufferedImage img = null;
	BufferedImage imgleft = null;
	BufferedImage imgup = null;
	BufferedImage imgdown = null;
	public int geist_art; // 1 = normal; 2 = aggressiv; 3 = ...
	
	public void richtungs_update(int x) {
		this.set_soll_richtung(x);
	}

	
	public Geist(Point p, int geist_art, Color Farbe) {											// noch keine Verwendung
		set_radius(30);
		set_farbe(Farbe);
		set_position(p.x, p.y);
		set_geist_art(geist_art);
		set_bewegungsrichtung(1);
		set_geschwindigkeit(6); // eventuell ändern
	}
	
	// Set-Methoden
	public void set_geist_art(int  geist_art) {this.geist_art = geist_art;}
	
	// Get-Methoden
	public int get_geist_art() {return this.geist_art;}

	
	public BufferedImage animation() {								// geister Blickrichtung	
		if(this.get_bewegungsrichtung() == 1)
			try{
				img = ImageIO.read(new File("./bilder/oben.png"));
			} catch (IOException e) {	
			}
		if(this.get_bewegungsrichtung() == 2)
			try{
				img = ImageIO.read(new File("./bilder/rechts.png"));
			} catch (IOException e) {
			}
		if(this.get_bewegungsrichtung() == 3)
			try{
			img = ImageIO.read(new File("./bilder/unten.png"));
			} catch (IOException e) {
			}
		if(this.get_bewegungsrichtung() == 4)
			try{
			img = ImageIO.read(new File("./bilder/links.png"));
			} catch (IOException e) {
			}
		return img;
		
		
	
	}
	
	public void richtungs_update(Point PacPosition) { //RentomieZa Bewegungsrichtung
		Random ran = new Random();
		Point Unterschied = new Point(PacPosition.x - this.get_position().x, PacPosition.y - this.get_position().y);
		int Quadrant = 0; 										// 1: unten rechts, 2: oben rechts, 3: oben links, 4: unten links
		if(Unterschied.x >= 0 && Unterschied.y > 0) Quadrant = 3;
		if(Unterschied.x > 0 && Unterschied.y < 0) Quadrant = 4;
		if(Unterschied.x < 0 && Unterschied.y <= 0) Quadrant = 1;
		if(Unterschied.x < 0 && Unterschied.y > 0) Quadrant = 2;
		
		if(Quadrant == 1) this.set_soll_richtung(ran.nextInt(2) * 3 + 1);
		if(Quadrant == 2) this.set_soll_richtung(ran.nextInt(2) * 2 + 1);
		if(Quadrant == 3) this.set_soll_richtung(ran.nextInt(2) + 2);
		if(Quadrant == 4) this.set_soll_richtung(ran.nextInt(2) * 2 + 2);
	
	}
	
}