import java.awt.Point;
import java.util.Random;

public class Geist extends Figuren {
	
	public Geist() {											// noch keine Verwendung
		
	}
	
	public void animation() {									// später bearbeiten
		
	}
	
	public void richtungs_update(Point PacPosition) {
		Random ran = new Random();
		Point Unterschied = PacPosition - this.get_position(); 	// auch nicht definiert
		int Quadrant = 0; 										// 1: unten rechts, 2: oben rechts, 3: oben links, 4: unten links
		if(Unterschied.x > 0 && Unterschied.y > 0) Quadrant = 1;
		if(Unterschied.x > 0 && Unterschied.y < 0) Quadrant = 2;
		if(Unterschied.x < 0 && Unterschied.y < 0) Quadrant = 3;
		if(Unterschied.x < 0 && Unterschied.y > 0) Quadrant = 4;
		
		if(Quadrant == 1) this.set_bewegungsrichtung(ran.nextInt(2) * 3 + 1);
		if(Quadrant == 2) this.set_bewegungsrichtung(ran.nextInt(2) * 2 + 1);
		if(Quadrant == 3) this.set_bewegungsrichtung(ran.nextInt(2) + 2);
		if(Quadrant == 4) this.set_bewegungsrichtung(ran.nextInt(2) * 2 + 2);
	}
}// BLA