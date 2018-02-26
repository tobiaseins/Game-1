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
	BufferedImage imgright_2 = null;
	BufferedImage imgleft_2 = null;
	BufferedImage imgup_2 = null;
	BufferedImage imgdown_2 = null;
	public int geist_art; // 1 = normal; 2 = aggressiv; 3 = ...
	public int wand;
	public boolean start = true;
	
	public void richtungs_update(int x) {
		this.set_soll_richtung(x);
	}

	
	public Geist(Point p, int geist_art, Color Farbe) {											// noch keine Verwendung
		this.set_radius(30);
		this.set_farbe(Farbe);
		this.set_position(p.x, p.y);
		this.set_geist_art(geist_art);
		this.set_bewegungsrichtung(2);
		this.set_soll_richtung(2);
		this.set_geschwindigkeit(6); // eventuell ändern
		
	}
	
	// Set-Methoden
	public void set_geist_art(int  geist_art) {this.geist_art = geist_art;}
	public void set_wand(int wand) {this.wand = wand;}
	public void set_start(boolean start) {this.start = start;}
	
	// Get-Methoden
	public int get_geist_art() {return this.geist_art;}
	public int get_wand() {return this.wand;}
	public boolean get_start() {return this.start;}

	
	public BufferedImage animation() {								// geister Blickrichtung	
		if(this.get_bewegungsrichtung() == 1 && this.geist_art == 1)
			try{
				img = ImageIO.read(new File("./bilder/oben.png"));
			} catch (IOException e) {	
			}
		if(this.get_bewegungsrichtung() == 2 && this.geist_art == 1)
			try{
				img = ImageIO.read(new File("./bilder/rechts.png"));
			} catch (IOException e) {
			}
		if(this.get_bewegungsrichtung() == 3 && this.geist_art == 1)
			try{
			img = ImageIO.read(new File("./bilder/unten.png"));
			} catch (IOException e) {
			}
		if(this.get_bewegungsrichtung() == 4 && this.geist_art == 1)
			try{
			img = ImageIO.read(new File("./bilder/links.png"));
			} catch (IOException e) {
			}
		return img;}
		
	public void reset(int raster_Groesse) {
		set_position(14*raster_Groesse,9*raster_Groesse);
		set_start(true);
		set_bewegungsrichtung(2);
		set_soll_richtung(2); 
	}
		
		
	public void wand_vor_geist(int count, int[][] spielfeld, int raster_Groesse) {
		if(this.get_start()) {
			if(count <= 3*raster_Groesse/this.get_geschwindigkeit()) {
				this.set_bewegungsrichtung(2);
				this.set_soll_richtung(2);
			} else if(count <= 5*raster_Groesse/this.get_geschwindigkeit()) {
				this.set_bewegungsrichtung(1);
				this.set_soll_richtung(1);
			} else this.set_start(false);
		}	
		
		switch(this.get_bewegungsrichtung()) {
			case 1://rechts
				if (spielfeld[this.get_position().y/raster_Groesse][this.get_position().x/raster_Groesse + 1] == 1) ; 
					this.set_wand(1);
				break;
			case 3://links
		   		if (spielfeld[this.get_position().y/raster_Groesse][(this.get_position().x - 1)/raster_Groesse] == 1) ;
		   		this.set_wand(1);
		    	break;
		    case 2://oben
		    	if (spielfeld[(this.get_position().y - 1)/raster_Groesse][this.get_position().x/raster_Groesse] == 1) ;
		    	this.set_wand(1);
		    	break;
		    case 4://unten
		    	if (spielfeld[this.get_position().y/raster_Groesse + 1][this.get_position().x/raster_Groesse] == 1) ;
		    	this.set_wand(1);
		    	break;
		    default:
		    		//wand = false;
		}
		this.set_wand(0);
	}
	
	public void richtungs_update(Point PacPosition) { //RentomieZa Bewegungsrichtung
		Random ran = new Random();
		if(this.get_wand() == 1 && !this.get_start()) {
			Point Unterschied = new Point(PacPosition.x - this.get_position().x, PacPosition.y - this.get_position().y);
			int Quadrant = 0; 										// 1: unten rechts, 2: oben rechts, 3: oben links, 4: unten links
			if(Unterschied.x >= 0 && Unterschied.y >= 0) Quadrant = 1;
			if(Unterschied.x >= 0 && Unterschied.y <= 0) Quadrant = 2;
			if(Unterschied.x <= 0 && Unterschied.y <= 0) Quadrant = 3;
			if(Unterschied.x <= 0 && Unterschied.y >= 0) Quadrant = 4;
			
			if(Quadrant == 1) this.set_soll_richtung(ran.nextInt(2) * 3 + 1);
			if(Quadrant == 2) this.set_soll_richtung(ran.nextInt(2) + 1);
			if(Quadrant == 3) this.set_soll_richtung(ran.nextInt(2) + 2);
			if(Quadrant == 4) this.set_soll_richtung(ran.nextInt(2) + 3);
		}
	}
	
}