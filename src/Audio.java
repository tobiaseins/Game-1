// Erstellt am 29.01. von Nico

// Import
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class Audio {
    URL url;				// Wo der Audio Clip gespeichert ist!
    Clip clip;				// Der Clip, der erstellt wird

    // Konstruktor: Es wird ein neues Audio Objekt erstellt
    public Audio(String st) {   
        clip = null;

        // try-catch: mögliche Fehler abfangen
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(st));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch(IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    // Funktion des Objektes: Clip abspielen
    public void playClip() {
    	// stopt Clip, wenn er schon läuft...
        if(clip.isRunning()) {
            clip.stop();
        }
        
        // ... und startet den Clip von vorne
        clip.setFramePosition( 0 );
        clip.start();
    }
}