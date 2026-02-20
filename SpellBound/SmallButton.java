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



public class SmallButton extends Button{
    public SmallButton(int x, int y, String file, String text,int changeX, int changeY){
        super( x,  y,  file,  text, changeX, changeY);
        
    }
    public void drawMe(Graphics g){
        super.drawMe(g);
        //g.drawRect((int)getX()+5,(int)getY()+15,60,43);

    }
    public boolean detectPress(int mouseX, int mouseY){
        if(getVisible()){
        if(mouseX > getX()+5 && mouseX < getX()+5 + 60){
            if(mouseY>getY()+15 && mouseY< getY() + 15+43){
                return true;
            }
        }
        }
        return false;
    }
    
}
