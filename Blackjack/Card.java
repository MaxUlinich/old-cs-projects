
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Card {
    private boolean visible = true;
    private int value;
    private String symbol, suit;
    private BufferedImage spadeImg;
    private BufferedImage heartImg;
    private BufferedImage clubImg;
    private BufferedImage diamondImg;
    public static BufferedImage backImg;
    public double x = 50;
    public double y = 250;
    public Card(int value, String symbol, String suit){
        this.value = value;
        this.symbol = symbol;
        this.suit = suit;

        try {
            spadeImg = ImageIO.read(new File("spade.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            diamondImg = ImageIO.read(new File("diamond.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            heartImg = ImageIO.read(new File("heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clubImg = ImageIO.read(new File("club.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            backImg = ImageIO.read(new File("cardBack2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getValue(){
        return value;
    }
    public boolean isVisible(){
        return visible;
    }
    public void setVisible(boolean tf){
        visible = tf;
    }

    public void drawMe(Graphics g){
        g.setColor(Color.white);
        g.fillRoundRect((int)x, (int)y, 120, 150, 10, 10);
        g.setColor(Color.black);
        g.drawRoundRect((int)x,(int)y,120,150,10,10);
        
        Font font = new Font("Arial", Font.PLAIN, 50);
        g.setFont(font);
        g.drawString(symbol, (int)x+50,(int)y+90);
        if(visible){
            if(suit.equals("diamond")){
                g.drawImage(diamondImg,(int)x+10,(int)y+10,null);
                g.drawImage(rotateImage(diamondImg, 180),(int)x+80,(int)y+100,null);
            }
            if(suit.equals("spade")){
                g.drawImage(spadeImg,(int)x+10,(int)y+10,null);
                g.drawImage(rotateImage(spadeImg, 180),(int)x+80,(int)y+100,null);
            }
            if(suit.equals("heart")){
                g.drawImage(heartImg,(int)x+10,(int)y+10,null);
                g.drawImage(rotateImage(heartImg, 180),(int)x+80,(int)y+100,null);
            }
            if(suit.equals("club")){
                g.drawImage(clubImg,(int)x+10,(int)y+10,null);
                g.drawImage(rotateImage(clubImg, 180),(int)x+80,(int)y+100,null);
            }
        } else{
            g.setColor(Color.RED);
            g.fillRoundRect((int)x, (int)y, 120, 150, 10, 10);
            g.drawImage(backImg,(int)x,(int)y, null);
            g.setColor(Color.black);
            g.drawRoundRect((int)x,(int)y,120,150,10,10);
        
            
        }
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
    

