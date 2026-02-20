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

public class Gestures {
    private BufferedImage Img;
    private double x=350;
    private double y=650;
    private boolean visible;
    public double angle=0;
    public Gestures(String file){
        visible = false;
        try {
            Img = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }

    public void drawMe(Graphics g){
        if(visible){
            //g.drawImage(rotateImage(resize(Img,Img.getWidth(),(int)y*3-1000),(int)angle),(int) x, (int) y, null);
            g.drawImage(resize(Img,(int)y*2-500),(int) x, (int) y, null);
        }
        
    }
    public void setVisible(boolean yn){
        visible = yn;
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
    public static BufferedImage resize(BufferedImage img, int newH) { 
        int newW = img.getWidth()*newH/img.getHeight();
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    } 
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    } 
    public BufferedImage rotateImage(BufferedImage image, int degree){
        
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage newImage= new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newImage.createGraphics();
		g2d.rotate(Math.toRadians(degree),width/2,height/2);
		g2d.drawImage(image, 0, 0, null);
		return newImage;
	} 
}
