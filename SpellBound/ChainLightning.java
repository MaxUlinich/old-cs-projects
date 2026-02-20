
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


public class ChainLightning extends SpellCard{
    public ChainLightning(double x, double y, boolean visible){
        super(x,y,"ChainLightning.png",visible,2);
    }
    public void doAction(ArrayList<Creature> toKill,int caster, Manager m){
        m.lightningAudio.playSound();
        for(int i = 0; i<toKill.size(); i++){
            toKill.get(i).changeHealth(-3);
            if(toKill.get(i).getHealth()<1){
                m.removeCreature(toKill, i);
                i--;
            }
        }
        if(caster == 1){
            m.playOnce(m.p1ChainLightningEffect, 33,false);
        }else{
            m.playOnce(m.p2ChainLightningEffect,33,false);
        }
    }


}
