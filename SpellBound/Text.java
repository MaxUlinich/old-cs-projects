
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

public class Text extends Piece{

    String text;
    public Text(double x, double y, String text, boolean visible){
        super(x,y,text,visible);
        this.text = text;
    }

    public void drawMe(Graphics g){

        if(getVisible()){
        //g.setColor(Color.yellow);
        g.setColor(new Color(255, 196, 0));
        g.setFont(new Font(MakeFont.fontName, 1, 20));

        g.drawString(text,(int)getX(),(int)getY());
        }
    }









}