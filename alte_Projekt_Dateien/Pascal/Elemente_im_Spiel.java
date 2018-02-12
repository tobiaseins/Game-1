import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Elemente_im_Spiel extends Figuren{
	
	private PacMan pacMan = new PacMan();
	private Geist GeistRO = new Geist(Color.RED);
	private Geist GeistGE = new Geist(Color.YELLOW);
	private Raster raster = new Raster();
	private Spielfeld spielfeld = new Spielfeld();
	private Punkte punkte = new Punkte();
	private Wand wand = new Wand();
	//private boolean wand_vor_pacMan;
	//private boolean richtungs_merker;
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
	
	//schränke die Bewegungsgeschwingkeit
	public void bewegungsGeschw() {
		try{
	    	Thread.sleep(BEWEGUNGSGESCHWINDIGKEIT); //10 millisek.
	    } catch (InterruptedException e){
	    	
	    }
	}
		
	
	//alle Komponeten zeichnen
	public void paintComponent(Graphics g)
	{
	    //Schwarzer Hintergrund
	    g.setColor(Color.BLACK);
	    g.fillRect(0, 0, getWidth(), getHeight());
		    
	    //prüfe, ob Mund offen oder geschlossen ist und setze den Wert entsprechend
	    pacMan.bewegeMund();
	    
	    //Mund schließen und öffnen
	    if(pacMan.getMundoffen() == false) {
	    	pacMan.setMundwinkel(pacMan.getMundwinkel()+1);
	    } else if(pacMan.getMundoffen() == true) {
	    	pacMan.setMundwinkel(pacMan.getMundwinkel()-1);
	    }
		    
	    //Raster zeichnen
	    int i = 0;
	    while (i <= getWidth()) {
	    	raster.paintXRaster((Graphics2D) g, getWidth(), Color.GREEN, i);
	    	raster.paintYRaster((Graphics2D) g, getHeight(), Color.GREEN, i);
	    	i += 30;
	    }
		    
	    //Wände und Punkte zeichnen - in Abhängigkeit von dem Spielfeld
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
	    
	    //merke die Richtung
	    /*if(pacMan.getPosition().x%pacMan.getRadius() == 0 && pacMan.getPosition().y%pacMan.getRadius() == 0) {
	       richtungs_merker = true; 
	    } else {
	       richtungs_merker = false;
	    }*/
	    
	    switch (pacMan.getOrientierung()) {
	    case 0:
	    	if (spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius() + 1] != 1) {
	    	//pacMan.bewegeRechts((Graphics2D) g, getWidth(), wand_vor_pacMan, richtungs_merker);
	    	pacMan.bewege(0);
	    	}
	    	break;
	    case 180:
	    	//if (Spielfeld[getPosition().y/radius][(getPosition().x - 1)/radius] != 1) {
	    	if (spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][(pacMan.getPosition().x - 1)/pacMan.getRadius()] != 1) {
	    	//pacMan.bewegeLinks((Graphics2D) g, getWidth(), wand_vor_pacMan, richtungs_merker);
	    	pacMan.bewege(180);
	    	}
	    	break;
	    case 90:
	    	//if (Spielfeld[(getPosition().y - 1)/radius][getPosition().x/radius] != 1) {
	    	if (spielfeld.playGround[(pacMan.getPosition().y - 1)/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius()] != 1) {
	    	//pacMan.bewegeOben((Graphics2D) g, getHeight(), wand_vor_pacMan, richtungs_merker);
	    	pacMan.bewege(90);
	    	}
	    	break;
	    case 270:
	    	//if (Spielfeld[getPosition().y/radius + 1][getPosition().x/radius] != 1) {
	    	if (spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius() + 1][pacMan.getPosition().x/pacMan.getRadius()] != 1) {
	    	//pacMan.bewegeUnten((Graphics2D) g, getHeight(), wand_vor_pacMan, richtungs_merker);
	    	pacMan.bewege(270);
	    	}
	    	break;
	    default:
	    	break;
	    	
	    }
	    
	    switch (GeistRO.getOrientierung()) {
	    case 0:
	    	if (spielfeld.playGround[GeistRO.getPosition().y/GeistRO.getGroesse()][GeistRO.getPosition().x/GeistRO.getGroesse() + 1] != 1) {
	    	//pacMan.bewegeRechts((Graphics2D) g, getWidth(), wand_vor_pacMan, richtungs_merker);
	    	GeistRO.bewege(0);
	    	}
	    	else  GeistRO.setOrientierung(270);
	    	break;
	    case 180:
	    	//if (Spielfeld[getPosition().y/radius][(getPosition().x - 1)/radius] != 1) {
	    	if (spielfeld.playGround[GeistRO.getPosition().y/GeistRO.getGroesse()][(GeistRO.getPosition().x - 1)/GeistRO.getGroesse()] != 1) {
	    	//GeistRO.bewegeLinks((Graphics2D) g, getWidth(), wand_vor_GeistRO, richtungs_merker);
	    	GeistRO.bewege(180);
	    	}
	    	else  GeistRO.setOrientierung(90);
	    	break;
	    case 90:
	    	//if (Spielfeld[(getPosition().y - 1)/radius][getPosition().x/radius] != 1) {
	    	if (spielfeld.playGround[(GeistRO.getPosition().y - 1)/GeistRO.getGroesse()][GeistRO.getPosition().x/GeistRO.getGroesse()] != 1) {
	    	//GeistRO.bewegeOben((Graphics2D) g, getHeight(), wand_vor_GeistRO, richtungs_merker);
	    	GeistRO.bewege(90);
	    	}
	    	else  GeistRO.setOrientierung(0);
	    	break;
	    case 270:
	    	//if (Spielfeld[getPosition().y/radius + 1][getPosition().x/radius] != 1) {
	    	if (spielfeld.playGround[GeistRO.getPosition().y/GeistRO.getGroesse() + 1][GeistRO.getPosition().x/GeistRO.getGroesse()] != 1) {
	    	//GeistRO.bewegeUnten((Graphics2D) g, getHeight(), wand_vor_GeistRO, richtungs_merker);
	    	GeistRO.bewege(270);
	    	}
	    	else  GeistRO.setOrientierung(180);
	    	break;
	    default:
	    	break;
	    	
	    }
	    
	    switch (GeistGE.getOrientierung()) {
	    case 0:
	    	if (spielfeld.playGround[GeistGE.getPosition().y/GeistGE.getGroesse()][GeistGE.getPosition().x/GeistGE.getGroesse() + 1] != 1) {
	    	//pacMan.bewegeRechts((Graphics2D) g, getWidth(), wand_vor_pacMan, richtungs_merker);
	    	GeistGE.bewege(0);
	    	}
	    	else  GeistGE.setOrientierung(90);
	    	break;
	    case 180:
	    	//if (Spielfeld[getPosition().y/radius][(getPosition().x - 1)/radius] != 1) {
	    	if (spielfeld.playGround[GeistGE.getPosition().y/GeistGE.getGroesse()][(GeistGE.getPosition().x - 1)/GeistGE.getGroesse()] != 1) {
	    	//GeistGE.bewegeLinks((Graphics2D) g, getWidth(), wand_vor_GeistGE, richtungs_merker);
	    	GeistGE.bewege(180);
	    	}
	    	else  GeistGE.setOrientierung(270);
	    	break;
	    case 90:
	    	//if (Spielfeld[(getPosition().y - 1)/radius][getPosition().x/radius] != 1) {
	    	if (spielfeld.playGround[(GeistGE.getPosition().y - 1)/GeistGE.getGroesse()][GeistGE.getPosition().x/GeistGE.getGroesse()] != 1) {
	    	//GeistGE.bewegeOben((Graphics2D) g, getHeight(), wand_vor_GeistGE, richtungs_merker);
	    	GeistGE.bewege(90);
	    	}
	    	else  GeistGE.setOrientierung(180);
	    	break;
	    case 270:
	    	//if (Spielfeld[getPosition().y/radius + 1][getPosition().x/radius] != 1) {
	    	if (spielfeld.playGround[GeistGE.getPosition().y/GeistGE.getGroesse() + 1][GeistGE.getPosition().x/GeistGE.getGroesse()] != 1) {
	    	//GeistGE.bewegeUnten((Graphics2D) g, getHeight(), wand_vor_GeistGE, richtungs_merker);
	    	GeistGE.bewege(270);
	    	}
	    	else  GeistGE.setOrientierung(0);
	    	break;
	    default:
	    	break;
	    	
	    }
	       
	  //Bewegung des PacMans
	    if(getTaste() == 1){
	    	
	    	if (pacMan.getOrientierung() == 180 || pacMan.getPosition().x%pacMan.getRadius() == 0 && pacMan.getPosition().y%pacMan.getRadius() == 0 && spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius() + 1] != 1) {
		    	pacMan.setOrientierung(0);
		    	setTaste(0);
				}
	    	
	    	/*if(spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][(pacMan.getPosition().x+pacMan.getRadius())/pacMan.getRadius()] == 1) {
	  	       wand_vor_pacMan = false; 
	  	    } else {
	  	       wand_vor_pacMan = true;
	  	    }	
	    	
	    	pacMan.bewegeRechts((Graphics2D) g, getWidth(), wand_vor_pacMan, richtungs_merker);
	    	*/
	    } else if(getTaste() == 2) {
	    	
	    	if (pacMan.getOrientierung() == 0 || pacMan.getPosition().x%pacMan.getRadius() == 0 && pacMan.getPosition().y%pacMan.getRadius() == 0 && spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius() - 1] != 1) {
		    	pacMan.setOrientierung(180);
		    	setTaste(0);
				}
	    	
	    	/*if(spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius()][(pacMan.getPosition().x-pacMan.getBewegung())/pacMan.getRadius()] == 1) {
		  	  wand_vor_pacMan = false; 
		  	} else {
		  	  wand_vor_pacMan = true;
		  	}
	    	
	    	pacMan.bewegeLinks((Graphics2D) g, getWidth(), wand_vor_pacMan, richtungs_merker);
	    	*/
	    } else if(getTaste() == 3) {
	    
	    	if (pacMan.getOrientierung() == 270 || pacMan.getPosition().x%pacMan.getRadius() == 0 && pacMan.getPosition().y%pacMan.getRadius() == 0 && spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius() - 1][pacMan.getPosition().x/pacMan.getRadius()] != 1) {
		    	pacMan.setOrientierung(90);
		    	setTaste(0);
				}
	    	
	    	/*if(spielfeld.playGround[(pacMan.getPosition().y-pacMan.getBewegung())/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius()] == 1) {
	  	       wand_vor_pacMan = false; 
	  	    } else {
	  	       wand_vor_pacMan = true;
	  	    }
	    	
	    	pacMan.bewegeOben((Graphics2D) g, getHeight(), wand_vor_pacMan, richtungs_merker);
	    	*/
	    } else if(getTaste() == 4) {
		    
	    	if (pacMan.getOrientierung() == 90 || pacMan.getPosition().x%pacMan.getRadius() == 0 && pacMan.getPosition().y%pacMan.getRadius() == 0 && spielfeld.playGround[pacMan.getPosition().y/pacMan.getRadius() + 1][pacMan.getPosition().x/pacMan.getRadius()] != 1) {
		    	pacMan.setOrientierung(270);
		    	setTaste(0);
				}
	    	
	    	/*if(spielfeld.playGround[(pacMan.getPosition().y+pacMan.getRadius())/pacMan.getRadius()][pacMan.getPosition().x/pacMan.getRadius()] == 1) {
		  	  wand_vor_pacMan = false; 
		  	} else {
		  	  wand_vor_pacMan = true;
			}
	    	
	    	pacMan.bewegeUnten((Graphics2D) g, getHeight(), wand_vor_pacMan, richtungs_merker);
	    	*/
	    } else {
	    
	    	repaint();
	    }
		    
	    //Den PacMan zeichnen
	    
		pacMan.paintPacMan((Graphics2D) g);
		
		GeistRO.paintGeist((Graphics2D) g);
		GeistGE.paintGeist((Graphics2D) g);

		//PacMan frisst die Punkte - die Werte werden im Spielfeld entsprechend angepasst
		punkteFressen();
			
		//Bewegungsgeschwindigkeit einschränken
	    bewegungsGeschw();
		    
		//alles neu zeichnen
	    repaint();
			   
	}
		
}
