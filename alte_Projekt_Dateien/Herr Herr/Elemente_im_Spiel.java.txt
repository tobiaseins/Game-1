import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Elemente_im_Spiel extends Figuren{
	
	private PacMan pacMan = new PacMan();
	private Raster raster = new Raster();
	private Spielfeld spielfeld = new Spielfeld();
	private Punkte punkte = new Punkte();
	private Wand wand = new Wand();
	private boolean wand_vor_pacMan;
	private boolean richtungs_merker;
	private static int BEWEGUNGSGESCHWINDIGKEIT = 10; //10 millisek.
	private int taste = 0;
	
	//Getter- und Setter-Methode für den Tastendruck
	public void setTaste(int t) {
		this.taste = t;
	}
	
	public int getTaste() {
		return this.taste;
	}
	
	//Spielfeld verändern, so dass die Punkte mit dem Wert 2 auf 0 geändert werden
	public void punkteFressen() {
	       
	    if(spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius()] == 2 //finde den Punkt 
	       && pacMan.getPosition().x%pacMan.getRadius() == 0 && pacMan.getPosition().y%pacMan.getRadius() == 0) //gleiche die Position von PacMan ab
	    																										//befindet sich der PacMan direkt im Raster
        {																								//wird der Punkt gelöscht
	   
       	   spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius()] = 0; //Punkt im Spielfeld löschen
        }
	}
	
	//schränke die Bewegungsgeschwingkeit ein
	public void bewegungsGeschw() {
		try{
	    	Thread.sleep(BEWEGUNGSGESCHWINDIGKEIT); //10 millisek.
	    } catch (InterruptedException e){
	    	
	    }
	}
		
    //Raster zeichnen
	public void rasterZeichnen(Graphics g)
	{
		int i = 0;
		while (i <= getWidth()) {
			raster.paintXRaster((Graphics2D) g, getWidth(), Color.GREEN, i);
			raster.paintYRaster((Graphics2D) g, getHeight(), Color.GREEN, i);
			i += 30;
		}
	}
	
	//Welt zeichnen - Wände und Punkte zeichnen - in Abhängigkeit von dem Spielfeld
	public void zeichneWelt(Graphics g)
	{
	    for(int a = 0; a<spielfeld.playGround.length; a++) {
	           
	    	for(int b = 0; b<spielfeld.playGround[0].length; b++) {
	               
	           	   if(spielfeld.playGround[a][b] == 1) {
	         
	              	   // Wand
	                   wand.paintWand((Graphics2D) g, b*pacMan.getRadius(), a*pacMan.getRadius(), 
	                  		   pacMan.getRadius(), pacMan.getRadius(), Color.BLUE);
	         
	               } else if(spielfeld.playGround[a][b] == 2) {
	        
	              	   // Punkt
	              	   int c = 10; //ein Drittel der PacMan-Größe 
	                       
	              	   punkte.paintPunkte((Graphics2D) g, b*pacMan.getRadius()+pacMan.getRadius()/2-pacMan.getRadius()/c/2, 
	                  		   a*pacMan.getRadius()+pacMan.getRadius()/2-pacMan.getRadius()/c/2, 
	                   		   pacMan.getRadius()/10, pacMan.getRadius()/10, Color.WHITE);
	                	   
	                       
	               }
	            	   
	           };
	       };
	}
	
	public void pacManBewegen(Graphics g)
	{
		//Bewegung des PacMans
	    if(getTaste() == 1){
	    	
	    	if(spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][(pacMan.getPosition().x+pacMan.getRadius())/pacMan.getRadius()] == 1) {
	  	       wand_vor_pacMan = false; 
	  	    } else {
	  	       wand_vor_pacMan = true;
	  	    }	
	    	
	    	pacMan.bewegeRechts((Graphics2D) g, getWidth());
	    	setTaste(0);
	    	
	    } else if(getTaste() == 2) {
	    
	    	if(spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][(pacMan.getPosition().x-pacMan.getBewegung())/pacMan.getRadius()] == 1) {
		  	  wand_vor_pacMan = false; 
		  	} else {
		  	  wand_vor_pacMan = true;
		  	}
	    	
	    	pacMan.bewegeLinks((Graphics2D) g, getWidth(), wand_vor_pacMan, richtungs_merker);
	    	setTaste(0);
	    } else if(getTaste() == 3) {
	    
	    	if(spielfeld.playGround[(pacMan.getPosition().y-pacMan.getBewegung())/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius()] == 1) {
	  	       wand_vor_pacMan = false; 
	  	    } else {
	  	       wand_vor_pacMan = true;
	  	    }
	    	
	    	pacMan.bewegeOben((Graphics2D) g, getHeight(), wand_vor_pacMan, richtungs_merker);
	    	setTaste(0);
	    } else if(getTaste() == 4) {
		    
	    	if(spielfeld.playGround[(pacMan.getPosition().y+pacMan.getRadius())/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius()] == 1) {
		  	  wand_vor_pacMan = false; 
		  	} else {
		  	  wand_vor_pacMan = true;
			}
	    	
	    	pacMan.bewegeUnten((Graphics2D) g, getHeight(), wand_vor_pacMan, richtungs_merker);
	    	setTaste(0);
	    } else {
	    
	    	repaint();
	    }
	}
	
	//alle Komponeten zeichnen
	public void paintComponent(Graphics g)
	{
	    //Schwarzer Hintergrund
	    g.setColor(Color.BLACK);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    
	    //Raster zeichnen
	    rasterZeichnen(g);
		    
	    //prüfe, ob Mund offen oder geschlossen ist und setze den Wert entsprechend
	    pacMan.mundStellung();
	    
	    //Mund vom PacMan bewegen
	    pacMan.mundBewegen();
		    
		//Welt zeichnen
	    zeichneWelt(g);
	    
	    //merke die Richtung
	    if(pacMan.getPosition().x%pacMan.getRadius() == 0 && pacMan.getPosition().y%pacMan.getRadius() == 0) {
	       richtungs_merker = true; 
	    } else {
	       richtungs_merker = false;
	    }
	    
	    //Bewegung von PacMan
	    pacManBewegen(g);
	      
	    //Den PacMan zeichnen
		pacMan.paintPacMan((Graphics2D) g);

		//PacMan frisst die Punkte - die Werte werden im Spielfeld entsprechend angepasst
		punkteFressen();
			
		//Bewegungsgeschwindigkeit einschränken
	    bewegungsGeschw();
		    
		//alles neu zeichnen
	    repaint();
			   
	}
		
}
