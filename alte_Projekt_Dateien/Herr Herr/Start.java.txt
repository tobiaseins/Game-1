import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
 
public class Start extends JFrame implements ActionListener
{
	private Fenster f;
		
    //Konstruktor
	public Start() {
    	
    	super("PacMan");
    	
    	//JButton mit Text "Start" wird erstellt
        JButton start = new JButton("Start");
        
        //JButton zum ActionListener hinzufügen, so dass per Mausklick auch was passiert
        start.addActionListener(this);
 
        //JButton wird dem Panel hinzugefügt
        add(start);
 
        //Fenstergröße setzen    
        setSize(435,400);
        
        //Fenster sichtbar machen
        setVisible(true);
    	
    }
	
	//Hauptmethode
    public static void main(String[] args)
    {
        //JFrame starten
		new Start();
    }
    
    //Action für den Mausklick festlegen
    public void actionPerformed(ActionEvent event)
    {
    	//aktives Fenster unsichtbar machen
    	setVisible(false);
    	
    	//neues Fenster erzeugen --> siehe Fenster.java
    	f = new Fenster();
    }
}