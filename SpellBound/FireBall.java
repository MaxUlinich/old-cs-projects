
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


public class FireBall extends SpellCard{
    

    public FireBall(double x, double y, boolean visible){
        super(x,y,"FireBall.png",visible,1);
    }
    public void doAction(ArrayList<Creature> toKill,int caster,Manager m){
        m.fireBallAudio.playSound();
        if(caster == 1){
            m.increaseP2Health(-2);
            m.p1fireEffect.setX(10);
            //m.fireEffect.setY(10);
            m.p1fireEffect.setVisible(true);
            m.animationList.add(new Animation(m.p1fireEffect, 1000.0/m.speed, 1400, 1.0/1000, -5.0/5, 290));
        }else{
            m.increaseP1Health(-2);
            m.p2fireEffect.setX(1000);
            //m.fireEffect.setY(10);
            m.p2fireEffect.setVisible(true);
            m.animationList.add(new Animation(m.p2fireEffect, -1000.0/m.speed, 0, 1.0/1000, -5.0/5, 290));
        }
    }


}
