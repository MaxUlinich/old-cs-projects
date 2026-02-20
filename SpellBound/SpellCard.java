
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

public class SpellCard extends Piece{
    private int price;
    public SpellCard(double x, double y, String file, boolean visible,int price){
        super(x,y,file,visible);
        this.price = price;
    }
    
    public void doAction(ArrayList<Creature> toKill,int caster,Manager m){

    }
    public int getPrice(){
        return price;
    }







}