import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioSystem;

import java.io.File;


public class Audio {
    
    File file;

    public Audio(String fileName){
       file = new File(fileName);
    }
    
    public void playSound(){
        try{                   
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));        
            clip.start();        
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
