import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import java.awt.Image;

public class victoryAnimation{

    private BufferedImage chipsImg;
    private BufferedImage noChipsImg;
    private double x=100;
    private double y=0;
    private boolean visible;
    public double angle=0;
    public boolean hasChips;
    public victoryAnimation(String file1,String file2){
        visible = false;
        try {
            chipsImg = ImageIO.read(new File(file1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            noChipsImg = ImageIO.read(new File(file2));
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    public void drawMe(Graphics g){
        if(visible){
            if(hasChips){
                g.drawImage(chipsImg,(int) x, (int) y, null);
            }else{
                g.drawImage(noChipsImg,(int) x, (int) y, null);
            }
            
            
        }
        
    }
    public void setChips(boolean yn){
        hasChips = yn;
    }
    public void setVisible(boolean tf){
        visible = tf;
    }

    public void increaseX(double amount){
        x+=amount;
    }
    public void increaseY(double amount){
        y+=amount;
    }
}
