
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

public class Effect extends Piece{
    
    private int startVisibleX;
    private int endVisibleX;
    private boolean positionallyDependent;
    public Effect(double x, double y, String file, boolean visible,int startVisibleX, int endVisibleX,boolean positionallyDependent){
        super(x,y,file,visible);
        this.startVisibleX = startVisibleX;
        this.endVisibleX = endVisibleX;
        this.positionallyDependent = positionallyDependent;
    }
    public void drawMe(Graphics g){
        if(positionallyDependent){
        if(this.getX()<startVisibleX || this.getX() > endVisibleX){
            this.setVisible(false);
        }else{
            setVisible(true);
        }
        }
        super.drawMe(g);
    }

    

    



}