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
import java.awt.Font;

public class Tutorial {
    private int stage = 1;
    private Image titleImage;
    private Image robotImage;
    private Image speechImage;
    public Tutorial(){
        try {
            titleImage = ImageIO.read(new File("titleScreen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            robotImage = ImageIO.read(new File("robot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            speechImage = ImageIO.read(new File("speechbubble.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawMe(Graphics g){
        g.drawImage(titleImage,0,0,null);
        g.drawImage(robotImage,100,100,null);
        drawSpeechBubble(g,320,0);
    }

    public void drawSpeechBubble(Graphics g,int x,int y){
        g.drawImage(speechImage,x,y,null);
        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        if(stage == 1){
            g.drawString("Use Arrow Keys to move in all directions.",x+100,y+110);
            g.drawString("Press SPACE to Shoot.",x+100,y+150);
            g.setFont(new Font("Serif", Font.BOLD, 10));
            g.drawString("Enter to Continue...",x+400,y+175);
        }
        if(stage == 2){
            g.setFont(new Font("Serif", Font.BOLD, 15));
            g.drawString("You can see your health in the top left.",x+100,y+100);
            g.drawString("Your EXP is in the top right.",x+100,y+125);
            g.drawString("The image next to the EXP tells you the next upgrade.",x+100,y+150);
            g.setFont(new Font("Serif", Font.BOLD, 10));
            g.drawString("Enter to Continue...",x+400,y+175);

        }
        
        if(stage == 3){
            g.drawString("The bar at the top shows your Ammo.",x+100,y+110);
            g.drawString("It recharges while not shooting.",x+100,y+150);
            g.setFont(new Font("Serif", Font.BOLD, 10));
            g.drawString("Enter to Continue...",x+400,y+175);
        }
        if(stage == 4){
            g.setFont(new Font("Serif", Font.BOLD, 15));
            g.drawString("WATCH OUT FOR ENEMIES AND ASTEROIDS!",x+100,y+110);
            g.drawString("Asteroids will stun you, but ENEMIES WILL HURT YOU!",x+100,y+150);
            g.setFont(new Font("Serif", Font.BOLD, 10));
            g.drawString("Enter to Continue...",x+400,y+175);
        }
        if(stage == 5){
            g.drawString("Ready?",x+100,y+120);
            g.drawString("Press Enter to Begin!",x+100,y+150);
            
        }


    }

    public void increaseStage(){
        stage++;
    }
    public int getStage(){
        return stage;
    }
    public void resetStage(){
        stage = 1;
    }
}
