package pacman;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

public class Audio 
{
    URL url;
    Clip clip;
    AudioInputStream ais;

    public Audio(String st)
    {   
        clip = null;

        try{
            AudioInputStream audioIn = AudioSystem.getAudioInputStream( getClass().getResource(st));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
            e.printStackTrace();
        }
    }

    public void playClip()
    {
        if( clip.isRunning() )
        {
            clip.stop();
        }
        clip.setFramePosition( 0 );
        clip.start();
    }
}