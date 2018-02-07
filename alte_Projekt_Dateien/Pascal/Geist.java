import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Geist extends Figuren {
	
	private int bewegung;
	private int size;
	
	public Geist(Color f) {
		this.size = 30; //Größe von PacMan
		this.bewegung = 3;
		setOrientierung(0); //PacMan guckt immer nach rechts
		setFarbe(f);
		setPosition(30,30); //Startposition
	}
	
	//Geschwindigkeit vom PacMan
		public int getBewegung() {
			return this.bewegung;
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
	
	public int getGroesse() {
		return this.size;
	}
	
	public void paintGeist(Graphics2D g)
	{	
		/*BufferedImage imgdown = null;
		BufferedImage imgup = null;
		BufferedImage imgleft = null;
		BufferedImage imgright = null;
		try {
			if (getFarbe() == Color.RED) {
		    imgdown = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GROTH3.png"));
		    imgup = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GROTH4.png"));
		    imgleft = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GROTH2.png"));
		    imgright = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GROTH1.png"));
			}
			else if (getFarbe() == Color.YELLOW) {
				imgdown = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GYEL3.png"));
			    imgup = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GYEL4.png"));
			    imgleft = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GYEL2.png"));
			    imgright = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GYEL1.png"));
			}
		} catch (IOException e) {
			
		}*/
		//g.setColor(getFarbe());
		//g.fillRect(getPosition().x, getPosition().y, getGroesse(), getGroesse());
		switch (getOrientierung()) {
	    case 0:
	    	BufferedImage imgright = null;
	    	if (getFarbe() == Color.RED) {
	    		try {
	    			imgright = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GROTH1.png"));
	    		} catch (IOException e) {
	    			
	    		}
	    	}
	    	else if (getFarbe() == Color.YELLOW) {
	    		try {
	    			imgright = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GYEL1.png"));
	    		} catch (IOException e) {
	    			
	    		}
	    	}
	    		g.drawImage(imgright, getPosition().x, getPosition().y, getGroesse(), getGroesse(), null);
	    	break;
	    case 180:
	    	BufferedImage imgleft = null;
	    	if (getFarbe() == Color.RED) {
	    		try {
	    			imgleft = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GROTH2.png"));
	    		} catch (IOException e) {
	    			
	    		}
	    	}
	    	else if (getFarbe() == Color.YELLOW) {
	    		try {
	    			imgleft = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GYEL2.png"));
	    		} catch (IOException e) {
	    			
	    		}
	    	}
	    		g.drawImage(imgleft, getPosition().x, getPosition().y, getGroesse(), getGroesse(), null);
	    	break;
	    case 90:
	    	BufferedImage imgup = null;
	    	if (getFarbe() == Color.RED) {
	    		try {
	    			imgup = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GROTH4.png"));
	    		} catch (IOException e) {
	    			
	    		}
	    	}
	    	else if (getFarbe() == Color.YELLOW) {
	    		try {
	    			imgup = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GYEL4.png"));
	    		} catch (IOException e) {
	    			
	    		}
	    	}
	    		g.drawImage(imgup, getPosition().x, getPosition().y, getGroesse(), getGroesse(), null);
	    	break;
	    case 270:
	    	BufferedImage imgdown = null;
	    	if (getFarbe() == Color.RED) {
	    		try {
	    			imgdown = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GROTH3.png"));
	    		} catch (IOException e) {
	    			
	    		}
	    	}
	    	else if (getFarbe() == Color.YELLOW) {
	    		try {
	    			imgdown = ImageIO.read(new File("C:\\Users\\psoppa\\workspace\\20170329\\BILDA\\GYEL3.png"));
	    		} catch (IOException e) {
	    			
	    		}
	    	}
	    		g.drawImage(imgdown, getPosition().x, getPosition().y, getGroesse(), getGroesse(), null);
	    	break;
	    default:
	    	break;
	    	
	    }
	}
}