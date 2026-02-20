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

public class Chip {
    

    private BufferedImage chipImg;
    private BufferedImage noChipsImg;
    private double x;
    private double y;
    private boolean visible;
   
    public Chip(double x, double y){
        this.x = x;
        this.y = y;
        visible = false;
        try {
            chipImg = ImageIO.read(new File("chip.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }   
       
    }

    public void drawMe(Graphics g){
        g.drawImage(chipImg, (int)x, (int)y, null);
    }

    public void increaseX(double amount){
        x+=amount;
    }
    public void increaseY(double amount){
        y+=amount;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }
    public void setLoc(double x, double y){
        setX(x);
        setY(y);
    }
}
