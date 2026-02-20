import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Laser {
    public double x,y;
    private int speed = 40;
    public boolean active = true;
    public int width = 20;
    public int height = 4;
    public Laser(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void drawMe(Graphics g){
        if(active){
            g.setColor(Color.CYAN);
            g.fillRect((int) x,(int) y,width,height);
        }
    }
    public void move(){
        x+=speed;
    }
    public void disable(){
        active = false;
    }
    
}
