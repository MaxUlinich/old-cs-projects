
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
import java.awt.Image;
import javax.swing.ImageIcon;

public class Creature extends Piece{
    private int damage;
    private int health;
    private BufferedImage damageIcon;
    private BufferedImage healthIcon;
    private BufferedImage attackedIcon;
    private boolean attacked;

    public Creature(double x, double y, String file, boolean visible,int damage, int health){
        super(x,y,file,visible);
        this.damage = damage;
        this.health = health;

        try {
            damageIcon = ImageIO.read(new File("swordIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            healthIcon = ImageIO.read(new File("heartIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            attackedIcon = ImageIO.read(new File("attackedIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void drawMe(Graphics g){
        if(getVisible()){
            super.drawMe(g);
            g.setColor(Color.black);
            g.setFont(new Font(MakeFont.fontName, 1, 20));

            g.drawString(health+"",(int)getX()+20,(int)getY());
            g.drawString(damage+"",(int)getX()+20,(int)getY()+20);
            g.drawImage(healthIcon,(int)getX()-10,(int)getY()-25, null);
            g.drawImage(damageIcon,(int)getX()-10,(int)getY(), null);
            if(attacked){
                g.drawImage(attackedIcon, (int)getX()+50, (int)getY()-10,null);
            }
            //g.drawRect((int)getX(),(int)getY(),198,130);
        }
    }

    public int getHealth(){
        return health;
    }

    public int getDamage(){
        return damage;
    }

    public void changeHealth(int amount){
        health+=amount;
    }

    public boolean detectPress(int mouseX, int mouseY){
        if(getVisible()){
            if(mouseX>getX() && mouseX<getX()+198){
                if(mouseY>getY()&& mouseY<getY()+130){
                    return true;
                }
            }
        }
        
        return false;
    }

    public boolean getAttacked(){
        return attacked;
    }
    public void setAttacked(boolean yn){
        attacked = yn;
    }

}