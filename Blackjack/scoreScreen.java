import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class scoreScreen {
    
    private BufferedImage borderImg;
    public scoreScreen(){
    
        try {
            borderImg = ImageIO.read(new File("border.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMe(Graphics g, int x, int y, int total, int pointsWon){

        g.setColor(Color.black);
        g.fillRoundRect(x-75,y,650,300,10,10);
        g.setColor(Color.white);
        g.setFont(new Font(MakeFont.fontName, 1, 40));
        g.drawString("Total: " + total, x+100, y+150);
        String message = "Loser";
        if(pointsWon>0){
            message = "Winner";
        }
        
        g.drawString(message, x+100,y+50);
        g.drawString("You get " + pointsWon + " points" , x+100,y+250);
        g.drawImage(borderImg,x-100,y-100,null);
    }
}
