import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class PacMan extends Figuren {

	private static int MAX_WINKEL = 30; //30 Grad
	private static int MIN_WINKEL = 0;  //0 Grad
	private int bewegung;
	private int radius;
	private int mundwinkel;
	private boolean mundoffen;
	
	//Standardwerte setzen
	public PacMan() {
		this.radius = 30; //Größe von PacMan
		this.mundwinkel = 0; //Öffnungswinkel vom Mund
		this.bewegung = 2;
		setOrientierung(0); //PacMan guckt immer nach rechts
		setFarbe(Color.YELLOW);
		setPosition(30,30); //Startposition
	}
	
	//viele Getter und Setter-Methoden
	//Mundwinkel
	public void setMundwinkel(int mw) {
		this.mundwinkel = mw;
	}
		
	public int getMundwinkel() {
		return this.mundwinkel;
	}
	
	//Mund offen?
	public void setMundoffen(boolean mo) {
		this.mundoffen = mo;
	}
	
	public boolean getMundoffen() {
		return this.mundoffen;
	}
	
	//Größe des PacMans
	public void setRadius(int r) {
		this.radius = r;
	}
		
	public int getRadius() {
		return this.radius;
	}
	
	//Geschwindigkeit vom PacMan
	public int getBewegung() {
		return this.bewegung;
	}
	
	//PacMan zeichnen
	public void paintPacMan(Graphics2D g)
	{
		g.setColor(getFarbe());
	    g.fillArc(getPosition().x, getPosition().y, getRadius(), getRadius(),
	              getMundwinkel() + getOrientierung(), 360-2*getMundwinkel());
	}
	
	
	//prüfe, ob Mund offen oder geschlossen ist
	public void bewegeMund() {
		
		if(getMundwinkel() == MAX_WINKEL) {
			setMundoffen(true);
		} else if(getMundwinkel() == MIN_WINKEL){
			setMundoffen(false);
		}
	}
	
	public void bewege(int richtung) {
		switch (richtung) {
		case 0:
			setPosition(getPosition().x + getBewegung() ,getPosition().y);
			break;
		case 180:
			setPosition(getPosition().x - getBewegung(), getPosition().y);
			break;
		case 90:
			setPosition(getPosition().x, getPosition().y - getBewegung());
			break;
		case 270:
			setPosition(getPosition().x, getPosition().y + getBewegung());
			break;
		}
	}
	
	//Bewegung nach rechts
	/*public void bewegeRechts(Graphics2D g, int xFenstergroesse, boolean wand, boolean merker) {
		
		//if(xFenstergroesse - getPosition().x - getRadius() - getBewegung() >= 0 && getPosition().y%30 == 0  && wand == true) {
			setPosition(getPosition().x + getBewegung() ,getPosition().y);
		}
		if(getOrientierung() == 270 || getOrientierung() == 180 || getOrientierung() == 90) {
			setOrientierung(0);
		}
		
	}
	
	//Bewegung nach links - Information liefert der KeyListener	
	public void bewegeLinks(Graphics2D g, int xFenstergroesse, boolean wand, boolean merker) {
		
		//if(xFenstergroesse - getPosition().x < xFenstergroesse && getPosition().y%30 == 0  && wand == true) {
			setPosition(getPosition().x - getBewegung(), getPosition().y);
		/*}
		if(getOrientierung() == 270 || getOrientierung() == 90 || getOrientierung() == 0) {
			setOrientierung(180);
		}
	}

	//Bewegung nach oben - Information liefert der KeyListener	
	public void bewegeOben(Graphics2D g, int yFenstergroesse, boolean wand, boolean merker) {
		
		//if(yFenstergroesse - getPosition().y < yFenstergroesse && getPosition().x%30 == 0 && wand == true) {
			setPosition(getPosition().x, getPosition().y - getBewegung());
		/*}
		if(getOrientierung() == 270 || getOrientierung() == 180 || getOrientierung() == 0){
			setOrientierung(90);
		}
	}

	//Bewegung nach unten - Information liefert der KeyListener	
	public void bewegeUnten(Graphics2D g, int yFenstergroesse, boolean wand, boolean merker) {
		
		//if(yFenstergroesse - getPosition().y - getRadius() - getBewegung() >= 0 && getPosition().x%30 == 0 && wand == true) {
			setPosition(getPosition().x, getPosition().y + getBewegung());
		/*}
		if(getOrientierung() == 180 || getOrientierung() == 90 || getOrientierung() == 0) {
			setOrientierung(270);
		}
	}*/
	
}	