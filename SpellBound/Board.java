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

public class Board extends JPanel implements MouseListener{
    


    private Image boardImg;

    private Manager manager = new Manager();





   public Board(){
        setBackground(new Color(46,99,0));
        setLayout(null);
        setFocusable(true);
        addMouseListener(this);
        boardImg = new ImageIcon("board.gif").getImage();



    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        if(manager.tutorialButton.detectPress(e.getX(), e.getY())){
            manager.showingTutorial=!manager.showingTutorial;
        }
        if(!manager.showingTutorial){
            if(manager.playing){
                if(manager.p1CastingSpell || manager.p2CastingSpell){
                    if(manager.spell1.detectPress(e.getX(),e.getY()) ){
                        manager.spellToCast=1;
                        manager.revealSpell(manager.spellToCast);
                    }
                    if(manager.spell2.detectPress(e.getX(),e.getY()) ){
                        manager.spellToCast=2;
                        manager.revealSpell(manager.spellToCast);
                    }
                    if(manager.spell3.detectPress(e.getX(),e.getY()) ){
                        manager.spellToCast=3;
                        manager.revealSpell(manager.spellToCast);
                    }
                }

                if(manager.p1CastingSpell && manager.spellToCast>0){
                    for(int i = 0; i<manager.p1Creatures.size(); i++){
                        if(manager.toSacrifice.indexOf(manager.p1Creatures.get(i))==-1 && manager.p1Creatures.get(i).detectPress(e.getX(), e.getY()) && !manager.p1Creatures.get(i).getAttacked()){
                            manager.toSacrifice.add(manager.p1Creatures.get(i));
                            manager.castSpell();
                        }
                    }
                }
                if(manager.p2CastingSpell && manager.spellToCast>0){
                    for(int i = 0; i<manager.p2Creatures.size(); i++){
                        if(manager.toSacrifice.indexOf(manager.p2Creatures.get(i))==-1 && manager.p2Creatures.get(i).detectPress(e.getX(), e.getY()) && !manager.p2Creatures.get(i).getAttacked()){
                            manager.toSacrifice.add(manager.p2Creatures.get(i));
                            manager.castSpell();
                        }
                    }
                }

                if(manager.p1CastSpell.detectPress(e.getX(),e.getY()) && !manager.p2CastingSpell){
                    if(manager.p1CastingSpell){
                        manager.stopCastingSpell();
                    }
                    else{
                    manager.startCasting(1);
                    }
                }
                if(manager.p2CastSpell.detectPress(e.getX(),e.getY())&& !manager.p1CastingSpell){
                    if(manager.p2CastingSpell){
                        manager.stopCastingSpell();
                    }else{
                    manager.startCasting(2);
                    }
                }

                if(manager.activePlayer == 1){
                    
                    
                    if(manager.p1StartAttacking.detectPress(e.getX(), e.getY())){
                        manager.startAttacking();
                    }

                    if(manager.attacking){
                        for(int i = 0; i<manager.p1Creatures.size(); i++){
                            Creature c = manager.p1Creatures.get(i);
                            if(c.detectPress(e.getX(),e.getY()) && !c.getAttacked()){
                                manager.attackingCreature = c;
                                manager.attacking = false;
                                manager.defending = true;
                                manager.skipDefense.setVisible(true);
                                manager.p1Attacking.changeVisibility();
                                manager.p2Defending.changeVisibility();
                            }
                        }
                    }
                    

                    if(manager.defending){
                        boolean attackAgain = false;
                        for(int i = 0; i<manager.p2Creatures.size(); i++){
                            
                            Creature c = manager.p2Creatures.get(i);
                            if(c.detectPress(e.getX(),e.getY()) && !c.getAttacked()){
                                manager.calculateDamage(manager.attackingCreature, c);
                                manager.defending = false;
                                manager.p2Defending.changeVisibility();
                                for(Creature creature : manager.activeCreatures){
                                    if(!creature.getAttacked()){
                                        attackAgain = true;
                                    }
                                }
                            }
                        }
                        if(attackAgain){
                            manager.startAttacking();
                        }
                    }

                    
                    if(manager.p1EndTurn.detectPress(e.getX(), e.getY())){
                        manager.endTurn();
                    }
                    if(manager.p1ShowCards.detectPress(e.getX(), e.getY())){
                        manager.showCards(manager.p1Hand);
                    }
                    // if(manager.p1DrawCard.detectPress(e.getX(),e.getY())){
                    //     manager.giveCard(manager.p1Hand);
                    // }
                    if(manager.p1StartSummoning.detectPress(e.getX(), e.getY())){
                        manager.startSummoning();
                    }
                    if(manager.summoning){

                        for(int i = 0; i<manager.activeManaPool.size(); i++){
                            
                            Mana mana = manager.activeManaPool.get(i);
                            if(mana.detectPress(e.getX(),e.getY())){
                                
                                manager.summonCreature(mana.getNumber());
                                if(manager.activeManaPool.size()>0)
                                    manager.startSummoning();
                            }
                        }
                    }
                }else{

                    if(manager.p2StartAttacking.detectPress(e.getX(), e.getY())){
                        manager.startAttacking();
                    }

                    if(manager.attacking){
                        for(int i = 0; i<manager.p2Creatures.size(); i++){
                            Creature c = manager.p2Creatures.get(i);
                            if(c.detectPress(e.getX(),e.getY())&&!c.getAttacked()){
                                manager.attackingCreature = c;
                                manager.attacking = false;
                                manager.defending = true;
                                manager.skipDefense.setVisible(true);
                                manager.p2Attacking.changeVisibility();
                                manager.p1Defending.changeVisibility();
                            }
                        }
                    }
                    if(manager.defending){
                        boolean attackAgain = false;
                        for(int i = 0; i<manager.p1Creatures.size(); i++){
                            Creature c = manager.p1Creatures.get(i);
                            if(c.detectPress(e.getX(),e.getY())&& !c.getAttacked()){
                                manager.calculateDamage(manager.attackingCreature, c);
                                manager.defending = false;
                                manager.p1Defending.changeVisibility();
                                for(Creature creature : manager.activeCreatures){
                                    if(!creature.getAttacked()){
                                        attackAgain = true;
                                    }
                                }
                            }
                        }
                        if(attackAgain){
                            manager.startAttacking();
                        }
                    }
                    if(manager.p2EndTurn.detectPress(e.getX(), e.getY())){
                        manager.endTurn();
                    }
                    if(manager.p2ShowCards.detectPress(e.getX(), e.getY())){
                        manager.showCards(manager.p2Hand);
                    }
                    // if(manager.p2DrawCard.detectPress(e.getX(),e.getY())){
                    //     manager.giveCard(manager.p2Hand);
                    // }
                    if(manager.p2StartSummoning.detectPress(e.getX(), e.getY())){
                        manager.startSummoning();
                    }
                    if(manager.summoning){
                        for(int i = 0; i<manager.activeManaPool.size(); i++){
                            
                            Mana mana = manager.activeManaPool.get(i);
                            if(mana.detectPress(e.getX(),e.getY())){
                                
                                manager.summonCreature(mana.getNumber());
                                if(manager.activeManaPool.size()>0)
                                    manager.startSummoning();
                            }
                        }
                    }
                }
                if(manager.defending && manager.skipDefense.detectPress(e.getX(), e.getY())){
                    manager.calculateDamage(manager.attackingCreature, new Creature(0,0,"monster.gif",false,0,0));
                    manager.defending = false;
                    manager.p1Defending.setVisible(false);
                    manager.p2Defending.setVisible(false);
                    boolean attackAgain = false;
                    for(Creature creature : manager.activeCreatures){
                        if(!creature.getAttacked()){
                            attackAgain = true;
                        }
                    }
                    if(attackAgain){
                        manager.startAttacking();
                    }
                }
            }
            else{
                if(manager.firstPlay && manager.startPlayingButton.detectPress(e.getX(), e.getY())){
                    manager.startPlaying();
                }
                if(!manager.firstPlay && manager.playAgainButton.detectPress(e.getX(), e.getY())){
                    manager.startPlaying();
                }
            }
        }
    }




   public Dimension getPreferredSize() {
       //Sets the size of the panel
       return new Dimension(1425, 749);
   }


   @Override
   public void paintComponent(Graphics g){	
       super.paintComponent(g);

        //draw the background
       g.drawImage(boardImg, 0, 0, null);
       

       //draw everything else
        manager.drawEverything(g);

        //gives g to manager
        
   }
   



   
   
        

   public void animate() {

    while (true) {
        //Wait 
        try {
            Thread.sleep(33); //milliseconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        for(int i = 0; i<manager.animationList.size();i++){
            manager.animate(manager.animationList.get(i));
        }
        
        if(manager.p1Health <=0){
            manager.p1Health = 0;
            manager.endGame(2);
        }
        if(manager.p2Health<=0){
            manager.p2Health = 0;
            manager.endGame(1);
        }
        repaint();
    }

}
  
   

}