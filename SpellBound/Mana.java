
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

public class Mana extends Piece{
    private int number;
    private Manager m;
    private int player;
    public Mana(double x, double y, String file, boolean visible, int number,Manager m,int player){
        super(x,y,file,visible);
        this.number = number;
        this.m=m;
        this.player = player;
    }

    public void drawMe(Graphics g){
        super.drawMe(g);
        if(getVisible() && m.summoning && player == m.activePlayer){
            g.setColor(Color.black);
            //g.setColor(new Color(204, 139, 0));
            g.setFont(new Font(MakeFont.fontName, 1, 35));
            g.drawString(number+"",(int)getX()+15,(int)getY()+40);
            g.setColor(Color.white);
            //g.setColor(new Color(204, 139, 0));
            g.setFont(new Font(MakeFont.fontName, 1, 28));
            g.drawString(number+"",(int)getX()+18,(int)getY()+37);
            
        }
    }
    public int getNumber(){
        return number;
    }

    public boolean detectPress(int mouseX, int mouseY){
        if(getVisible()){
            if(mouseX>getX() && mouseX<getX()+50){
                if(mouseY>getY()&& mouseY<getY()+50){
                    return true;
                }
            }
        }
        
        return false;
    }



}