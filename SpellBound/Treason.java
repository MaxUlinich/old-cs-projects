 
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


public class Treason extends SpellCard{
    

    public Treason(double x, double y, boolean visible){
        super(x,y,"ThoughtsOfTreason.png",visible,3);
    }
    public void doAction(ArrayList<Creature> toKill,int caster,Manager m){
        //m.fireBallAudio.playSound();

        
        if(toKill.size()>0){
            
            Creature weakest = toKill.get(0);
            for(int i = 0; i<toKill.size(); i++){
                if(toKill.get(i).getDamage()+toKill.get(i).getHealth()<weakest.getHealth()+weakest.getDamage()){
                    weakest = toKill.get(i);
                }
            }

            m.addCreature(caster, weakest.getHealth(), weakest.getDamage());
            m.removeCreature(toKill, toKill.indexOf(weakest));
            m.slideCreatures(m.activeCreatures);
            m.playOnce(new Effect(weakest.getX(),weakest.getY(),"smoke.gif",true,0,0,false),10,true);
            m.treasonAudio.playSound();
        } 
          
 }
    
}


 
    

