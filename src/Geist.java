import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Geist extends Figuren {
	BufferedImage imgright = null;
	BufferedImage imgleft = null;
	BufferedImage imgup = null;
	BufferedImage imgdown = null;
	BufferedImage imgright_2 = null;
	BufferedImage imgleft_2 = null;
	BufferedImage imgup_2 = null;
	BufferedImage imgdown_2 = null;
	public int geist_art; // 1 = normal; 2 = aggressiv; 3 = ...
	
	public void richtungs_update(int x) {
		this.set_soll_richtung(x);
	}

	
	public Geist(Point p, int geist_art) {											
		set_position(p.x, p.y);
		set_geist_art(geist_art);
		set_bewegungsrichtung(1);
		set_geschwindigkeit(6); // eventuell ändern
	}
	
	// Set-Methoden
	public void set_geist_art(int  geist_art) {this.geist_art = geist_art;}
	
	// Get-Methoden
	public int get_geist_art() {return this.geist_art;}

	
	public void animation() {								// geister Blickrichtung	
		if(this.get_bewegungsrichtung() == 1 && this.geist_art == 1)
			try{
				imgup = ImageIO.read(new File("oben.png"));
			} catch (IOException e) {	
			}
		if(this.get_bewegungsrichtung() == 2 && this.geist_art == 1)
			try{
				imgright = ImageIO.read(new File("rechts.png"));
			} catch (IOException e) {
			}
		if(this.get_bewegungsrichtung() == 3 && this.geist_art == 1)
			try{
				imgdown = ImageIO.read(new File("unten.png"));
			} catch (IOException e) {
			}
		if(this.get_bewegungsrichtung() == 4 && this.geist_art == 1)
			try{
				imgleft = ImageIO.read(new File("links.png"));
			} catch (IOException e) {
		}
		if(this.get_bewegungsrichtung() == 1 && this.geist_art == 2)
			try{
				imgup_2 = ImageIO.read(new File("oben.png"));
			} catch (IOException e) {	
			}
		if(this.get_bewegungsrichtung() == 2 && this.geist_art == 2)
			try{
				imgright_2 = ImageIO.read(new File("rechts.png"));
			} catch (IOException e) {
			}
		if(this.get_bewegungsrichtung() == 3 && this.geist_art == 2)
			try{
				imgdown_2 = ImageIO.read(new File("unten.png"));
			} catch (IOException e) {
			}
		if(this.get_bewegungsrichtung() == 4 && this.geist_art == 2)
			try{
				imgleft_2 = ImageIO.read(new File("links.png"));
			} catch (IOException e) {
		}
		else System.exit(0);
		
		
	
	}
	
	public void richtungs_update(Point PacPosition) { //RentomieZa Bewegungsrichtung
		Random ran = new Random();
		Point Unterschied = new Point(PacPosition.x - this.get_position().x, PacPosition.y - this.get_position().y);
		int Quadrant = 0; 										// 1: unten rechts, 2: oben rechts, 3: oben links, 4: unten links
		if(Unterschied.x >= 0 && Unterschied.y > 0) Quadrant = 1;
		if(Unterschied.x > 0 && Unterschied.y < 0) Quadrant = 2;
		if(Unterschied.x < 0 && Unterschied.y <= 0) Quadrant = 3;
		if(Unterschied.x < 0 && Unterschied.y > 0) Quadrant = 4;
		
		if(Quadrant == 1) this.set_bewegungsrichtung(ran.nextInt(2) * 3 + 1);
		if(Quadrant == 2) this.set_bewegungsrichtung(ran.nextInt(2) * 2 + 1);
		if(Quadrant == 3) this.set_bewegungsrichtung(ran.nextInt(2) + 2);
		if(Quadrant == 4) this.set_bewegungsrichtung(ran.nextInt(2) * 2 + 2);
	
	}
	
}