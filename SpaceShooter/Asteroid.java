
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
public class Asteroid{
	public int x, y, width,height;
    private double angle = 0;
    private double rotateSpeed = .5;
    public int w = 1200;
    public int h = 600;
    public static int typeOfAsteroid = 1;
    private BufferedImage a1;
    private BufferedImage a2;
    private BufferedImage a3;
    private BufferedImage a4;
    private int speed = 0;
    public boolean active = true;
    public Asteroid(){
        x = (int)(Math.random()*w-2)+w;
        y = (int)(Math.random()*h-2);
        angle = (int)(Math.random()*360);
        rotateSpeed = Math.random()*3+1;
        width = (int)(Math.random()*75+25);
        height = width;
        speed = width/10;
        try {
            a1 = ImageIO.read(new File("a1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            a2 = ImageIO.read(new File("a2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            a3 = ImageIO.read(new File("a3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            a4 = ImageIO.read(new File("a4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	public void drawMe(Graphics g){
        if(active){
            if(typeOfAsteroid == 1){
                g.drawImage(rotateImage(resize(a1,width,height),(int) angle),x,y,null);
            }else if(typeOfAsteroid == 2){
                g.drawImage(rotateImage(resize(a2,width,height),(int) angle),x,y,null);
            }else if(typeOfAsteroid == 3){
                g.drawImage(rotateImage(resize(a3,width,height),(int) angle),x,y,null);
            }else if(typeOfAsteroid == 4){
                g.drawImage(rotateImage(resize(a4,width,height),(int) angle),x,y,null);
            }
        }    

    }

    public void move(){
        x-=speed;
        if(x<-200){
            x=w+(int)(Math.random()*w);
            y = (int)(Math.random()*h-100);
            rotateSpeed = Math.random()*3+1;
            width = (int)(Math.random()*75+25);
            height = width;
            speed = width/10;
            active = true;
        }
        this.angle+=rotateSpeed;
    }

    public static void newAsteroidType(){
        int oldTypeOfAsteroid = typeOfAsteroid;
        while(oldTypeOfAsteroid == typeOfAsteroid){
            typeOfAsteroid = (int)(Math.random()*4+1);
        }
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

    public void disable(){
        active = false;
    }
}
