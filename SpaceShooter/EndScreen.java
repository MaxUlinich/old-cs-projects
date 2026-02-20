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
public class EndScreen {
    private BufferedImage gameOverImg;

    public EndScreen(){
        try {
            gameOverImg = ImageIO.read(new File("GameOver.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void drawMe(Graphics g, int i){
        double step = 600.0/1200.0;
        g.setColor(Color.black);
        g.fillRect(0,0,1200,600); 
        g.drawImage(rotateImage(resize(gameOverImg,1200-2*i+1,(int)(600-2*i*step)+1),i%360), i,(int)(i*step), null);

        
        

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
