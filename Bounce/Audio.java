import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioSystem;

import java.io.File;


public class Audio {

    File file=new File("thud.wav");
    
    public void playSound(){
        try{
            
            
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            //FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            //volume.setValue(vol);
            
            clip.start();
            
            
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
