import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseListener;

import java.awt.event.MouseEvent;
import java.util.ArrayList;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.Image;
public class Piece{
    private double x,y;
    private boolean visible = false;
    private Image img;
    private String file;
    private double defaultX, defaultY;

    public Piece(double x, double y, String file, boolean visible){
        this.x=x;
        this.y=y;
        this.visible = visible;
        this.file = file;
        defaultX=x;
        defaultY=y;
        if(file.indexOf(".png")!=-1){
            try {
                img = ImageIO.read(new File(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void changeVisibility(){
        visible = !visible;
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public boolean getVisible(){
        return visible;
    }


    public void drawMe(Graphics g){
        if(visible){
            if(file.indexOf(".gif") == -1){
                g.drawImage(img,(int)x,(int)y,null);
            }else{
                animateMe(g);
            }
            
        }
        
    }
    public void animateMe(Graphics g){
        img = new ImageIcon(file).getImage();
        if(visible){
            g.drawImage(img,(int)x,(int)y,null);
        }
    }

    public void setVisible(boolean yn){
        visible = yn;
    }

    public void setX(double x){
        this.x =x;
    }
    public void setY(double y){
        this.y =y;
    }
    public void returnToDefault(){
        setVisible(false);
        setX(defaultX);
        setY(defaultY);
    }
    public double getDefaultX(){
        return defaultX;
    }
    public double getDefaultY(){
        return defaultY;
    }
}