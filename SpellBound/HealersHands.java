
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


public class HealersHands extends SpellCard{
    public HealersHands(double x, double y, boolean visible){
        super(x,y,"HealersHands.png",visible,1);
    }
    public void doAction(ArrayList<Creature> toKill,int caster,Manager m){
        m.healAudio.playSound();
        if(caster == 1){
            m.increaseP1Health(2);
            m.playOnce(m.p1healingCircleEffect, 75,false);
        }else{
            m.increaseP2Health(2);
            m.playOnce(m.p2healingCircleEffect, 75,false);
        }
    }


}
