
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


import java.awt.Rectangle;

public class Manager {
    double speed = 40.0;
    
    
    //
    ArrayList<SpellCard> spellDeck = new ArrayList<SpellCard>();
    ArrayList<SpellCard> p1Hand = new ArrayList<SpellCard>();
    ArrayList<SpellCard> p2Hand = new ArrayList<SpellCard>();
    
    //Lists for animating
    ArrayList<Animation> animationList = new ArrayList<Animation>();
    ArrayList<AnimateOnce> animateOnceList = new ArrayList<AnimateOnce>();
    //Lists for drawing
    ArrayList<Piece> everything = new ArrayList<Piece>();
    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<Text> texts = new ArrayList<Text>();
    ArrayList<Effect> effects = new ArrayList<Effect>();
    
    //p1 stuff
    ArrayList<Mana> p1ManaPool = new ArrayList<Mana>();
    ArrayList<Creature> p1Creatures = new ArrayList<Creature>();
    //p2 stuff
    ArrayList<Mana> p2ManaPool = new ArrayList<Mana>();
    ArrayList<Creature> p2Creatures = new ArrayList<Creature>();
    //active stuff
    ArrayList<Mana> activeManaPool = p1ManaPool;
    ArrayList<Creature> activeCreatures = p1Creatures;

    //Sounds
    Audio dragonAudio = new Audio("dragon.wav");
    Audio fireBallAudio = new Audio("fireball.wav");
    Audio lightningAudio = new Audio("lightning.wav");
    Audio healAudio = new Audio("heal.wav");
    Audio summonAudio = new Audio("summon.wav");
    Audio treasonAudio = new Audio("treason.wav");
    Audio slashAudio = new Audio("slash.wav");
    Audio silent = new Audio("silent.wav");
   
    //pieces

    Piece homeScreen = new Piece(0,0,"startScreen.png",true);
    Piece tutorialScreen = new Piece(0,0,"tutorial.png", true);

    Piece p1TurnTracker = new Piece(10, 100, "turnTracker.png", true);
    Piece p2TurnTracker = new Piece(1300+10, 100, "turnTracker.png", false);

    Piece p1HealthTracker = new Piece(200,450+30, "playerHeart.png", true);
    Piece p2HealthTracker = new Piece(1320+10-190,450+30, "playerHeart.png", true);

    Wizard w1 = new Wizard(50,300,"wizard1.gif",true);
    Wizard w2 = new Wizard(1200,300,"wizard2.gif",true);

    Button p1CastSpell = new Button(10,670,"button3.png","Cast Spell", 45,43);
    Button p1StartAttacking = new Button(10,470,"button3.png","Attack", 68, 43);
    Button p1EndTurn = new Button (10,520,"button3.png", "End Turn",50,43);
    Button p1StartSummoning = new Button(10,570,"button3.png","Summon",63,43);
    Button p1ShowCards = new Button(10, 620, "button3.png", "Show Hand",45,43);
    //Button p1DrawCard = new Button(10,670,"button3.png","Draw Card", 45,43);
    
    Button p2CastSpell = new Button(1220+10,670, "button3.png","Cast Spell",45,43);
    Button p2StartAttacking = new Button(1220+10,470,"button3.png","Attack", 68, 43 );
    Button p2EndTurn = new Button (1220+10,520,"button3.png", "End Turn",50,43);
    Button p2StartSummoning = new Button(1220+10,570,"button3.png","Summon",63,43);
    Button p2ShowCards = new Button(1220+10, 620, "button3.png", "Show Hand",45,43);
    //Button p2DrawCard = new Button(1220+10,670,"button3.png","Draw Card", 45,43);
    
    Button skipDefense = new Button(600,520,"button3.png","No Defense",45,43);

    Button spell1 = new SmallButton(500,670,"button1.png","One",17,43);
    Button spell2 = new SmallButton(675,670,"button1.png","Two",17,43);
    Button spell3 = new SmallButton(850,670,"button1.png","Three",7,43);

    Button startPlayingButton = new Button(620,70,"button3.png", "Start",70,43);
    Button playAgainButton = new Button(620,70,"button3.png", "Play Again",49,43);

    Button tutorialButton = new Button(620,10, "button3.png","Tutorial",59,43);


    //texts
    Text p1SelectAmountOfMana = new Text(10, 225,"Select amount of Mana",false);
    Text p2SelectAmountOfMana = new Text(1120+10, 225,"Select amount of Mana",false);

    Text p1Attacking = new Text(10,225, "Select Creature to Attack With", false);
    Text p2Attacking = new Text(1110+10,225, "Select Creature to Attack With", false);
    
    Text p1Defending = new Text(10,225, "Select Creature to Defend With", false);
    Text p2Defending = new Text(1110+10,225, "Select Creature to Defend With", false);

    //Effects
    Effect p1fireEffect = new Effect(0,0,"fireball.gif",false,10,1000,true);
    Effect p2fireEffect = new Effect(0,0,"fireballreverse.gif",false,10,1000,true);

    Effect p1ChainLightningEffect = new Effect(500,400,"chainlightningeffect.gif", false,0,1400,false);
    Effect p2ChainLightningEffect = new Effect(300,200,"chainlightningeffectReverse.gif", false,0,1400,false);

    Effect p1healingCircleEffect = new Effect(145,422,"healingcircleeffect.gif", false, 0, 1400, false);
    Effect p2healingCircleEffect = new Effect(1085,422,"healingcircleeffect.gif", false, 0, 1400, false);

    //constants
    int maxHandSize = 3;
    int maxSpellsPerTurn = 3;
    //trackers
    ArrayList<Creature> toSacrifice = new ArrayList<Creature>();
    boolean showingTutorial = false;
    boolean summoning = false;
    boolean attacking = false;
    boolean defending = false;
    boolean p1CastingSpell = false;
    boolean p2CastingSpell = false;
    boolean playing = false;
    boolean firstPlay = true;
    int winner = 0;

    int spellsCast = 0;
    int spellPrice = 0;
    int spellToCast = 0;
    int activePlayer = 1;
    int p1Health = 20;
    int p2Health = 25;
    int counter = 0;
    
    //place holders
    Creature attackingCreature = null;
    


    public Manager(){

        
        silent.playSound();
        everything.add(w1);
        everything.add(w2);
        everything.add(p1TurnTracker);
        everything.add(p2TurnTracker);
        everything.add(p1HealthTracker);
        everything.add(p2HealthTracker);


        giveCard(p1Hand);
        giveCard(p1Hand);
        giveCard(p1Hand);
        activePlayer =2;
        giveCard(p2Hand);
        giveCard(p2Hand);
        giveCard(p2Hand);
        activePlayer =1;
        
        buttons.add(p1CastSpell);
        buttons.add(p1StartAttacking);
        buttons.add(p1EndTurn);
        buttons.add(p1StartSummoning);
        buttons.add(p1ShowCards);
        //buttons.add(p1DrawCard);

        buttons.add(p2CastSpell);
        buttons.add(p2StartAttacking);
        buttons.add(p2EndTurn);
        buttons.add(p2StartSummoning);
        buttons.add(p2ShowCards);
        //buttons.add(p2DrawCard);

        buttons.add(skipDefense);
        skipDefense.setVisible(false);

        buttons.add(spell1);
        buttons.add(spell2);
        buttons.add(spell3);
        spell1.setVisible(false);
        spell2.setVisible(false);
        spell3.setVisible(false);

        
        

        texts.add(p1SelectAmountOfMana);
        texts.add(p2SelectAmountOfMana);

        texts.add(p1Attacking);
        texts.add(p1Defending);
        texts.add(p2Attacking);
        texts.add(p2Defending);

        effects.add(p1fireEffect);
        effects.add(p2fireEffect);
        effects.add(p1ChainLightningEffect);
        effects.add(p2ChainLightningEffect);
        effects.add(p1healingCircleEffect);
        effects.add(p2healingCircleEffect);

        activePlayer = 2;
        activeManaPool = p2ManaPool;
        fillManaPool();
        activePlayer = 1;
        activeManaPool = p1ManaPool;
        fillManaPool();


        
    }




    public void drawEverything(Graphics g){
        //check on animations
        counter++;
        if(counter>10000){
            counter = 0;
        }

        for(int i = 0; i<animateOnceList.size(); i++){
            AnimateOnce animation = animateOnceList.get(i);
            if(animation.getEnd()<counter){
                animation.getE().setVisible(false);
                animateOnceList.remove(animation);
                i--;
            }else if(animation.needToDraw()){
                animation.getE().drawMe(g);
            }
        }
        //draw stuff
        for(Piece p : everything){
            p.drawMe(g);
        }

        for(Button b : buttons){
            b.drawMe(g);
        }


        for(Text t : texts){
            t.drawMe(g);
        }

        for(Mana m : p1ManaPool){
            m.drawMe(g);
        }
        for(Mana m : p2ManaPool){
            m.drawMe(g);
        }
        for(Creature c : p1Creatures){
            c.drawMe(g);
            
        }
        for(Creature c : p2Creatures){
            c.drawMe(g);
        }
        
        
        //g.setColor(Color.black);
        g.setColor(new Color(92, 26, 26));
        g.setFont(new Font(MakeFont.fontName, 1, 30));
        if(p1Health<10)
            g.drawString(p1Health+"",48+190,505+30);
        else
            g.drawString(p1Health+"",41+190,505+30);

        if(p2Health<10)
            g.drawString(p2Health+"",1370-190,505+30);
        else
            g.drawString(p2Health+"",1363-190,505+30);

        //g.setColor(Color.white);
        g.setColor(new Color(255, 196, 0));
        if((p1CastingSpell || p2CastingSpell) && spellPrice-toSacrifice.size()==1){
            g.drawString("Sacrifice " + (spellPrice-toSacrifice.size()) + " more Creature", 550,225);
        }else if ((p1CastingSpell || p2CastingSpell) && spellPrice-toSacrifice.size()>0){
            g.drawString("Sacrifice " + (spellPrice-toSacrifice.size()) + " more Creatures", 550,225);
        }

        for(SpellCard c : p1Hand){
            c.drawMe(g);
        }

        for(SpellCard c : p2Hand){
            c.drawMe(g);
        }

        for(Effect e : effects){
            e.drawMe(g);
            
        }

        if(!playing){

            homeScreen.drawMe(g);
            if(firstPlay){
                startPlayingButton.setVisible(true);
                startPlayingButton.drawMe(g);
            }else{
                playAgainButton.setVisible(true);
                playAgainButton.drawMe(g);
                g.setColor(new Color(92, 26, 26));
                g.setFont(new Font(MakeFont.fontName, 1, 40));
                g.drawString("Player " + winner + " won", 600,300);
            }

        }
        if(showingTutorial){
            tutorialScreen.drawMe(g);
        }
        tutorialButton.drawMe(g);

    }

    

    public void removeCreature(ArrayList<Creature> creatures, int index){
            if(index>=0){
            creatures.remove(index);
            slideCreatures(creatures);
        }
       
    }
    public void slideCreatures(ArrayList<Creature> creatures){
        for(int i = 0; i<creatures.size(); i++){
            if(creatures.equals(p1Creatures)){
                    int endX = i*200+300;
                    animationList.add(new Animation(creatures.get(i),-200.0/speed,(double)endX, (double)0,(double)0,creatures.get(i).getY()));
                    
            }
        
            else{
                int endX = 1000-(200*i);
                animationList.add(new Animation(creatures.get(i),200.0/speed,(double)endX, (double)0,(double)0,creatures.get(i).getY()));
            }
        }
    }

    public void calculateDamage(Creature attacking, Creature defending){
        dragonAudio.playSound();
        if(activePlayer == 1){
            if(defending.getHealth()-attacking.getDamage()<1){
                removeCreature(p2Creatures, p2Creatures.indexOf(defending));
            
                p2Health-=(attacking.getDamage()-defending.getHealth());
            }
        }else{
            if(defending.getHealth()-attacking.getDamage()<1){
                removeCreature(p1Creatures, p1Creatures.indexOf(defending));
            
                p1Health-=(attacking.getDamage() - defending.getHealth());
            }
        }
        defending.changeHealth(-attacking.getDamage());
        attacking.changeHealth(-defending.getDamage());
        if(attacking.getHealth()<1){
            removeCreature(activeCreatures, activeCreatures.indexOf(attacking));
        }
        attacking.setAttacked(true);
        skipDefense.setVisible(false);
    }

    public void showCards(ArrayList<SpellCard> hand){
        if(p1CastingSpell || p2CastingSpell){
            stopCastingSpell();
        }
        for(int i = 0; i<hand.size(); i++){
            
                    hand.get(i).changeVisibility();
        }
            
        
    }


    private void shuffle(){
        //write code to shuffle your deck
        for(int i = 0; i < spellDeck.size(); i++){
             int loc = (int)(Math.random()*spellDeck.size());
             SpellCard temp = spellDeck.get(i);
             spellDeck.set(i, spellDeck.get(loc));
             spellDeck.set(loc,temp);
        }
    }
    private void createSpellDeck(){
        spellDeck = new ArrayList<SpellCard>();
    }
    public SpellCard chooseCard(double x, double y, boolean visible){
        int random = (int)(Math.random()*4);
        if(random == 0){
            return new ChainLightning(x, y, visible);
        }
        else if(random == 1){
            return new FireBall(x,y,visible);
        }
        else if(random ==2){
            return new HealersHands(x,y,visible);
        }else{
            return new Treason(x, y, visible);
        }

    }
    public void giveCard(ArrayList<SpellCard> hand){
        if(hand.size()<maxHandSize){
            if(activePlayer==1){
                if(hand.size()>0){
                    SpellCard previousCard = hand.get(hand.size()-1);
                    hand.add(chooseCard(previousCard.getX()+245, previousCard.getY(), previousCard.getVisible()));
                    //hand.add(new SpellCard(previousCard.getX()+245,previousCard.getY(),"ChainLightning.png",previousCard.getVisible(),2));
                }else{
                    hand.add(chooseCard(250,175,false));
                    //hand.add(new SpellCard(250,175,"ChainLightning.png",false,2));
                }
            }else{
                
                if(hand.size()>0){
                    
                    SpellCard previousCard = hand.get(hand.size()-1);
                    hand.add(chooseCard(previousCard.getX()-245, previousCard.getY(), previousCard.getVisible()));
                    //hand.add(new SpellCard(previousCard.getX()-245,previousCard.getY(),"ChainLightning.png",previousCard.getVisible(),2));
                }else{
                    hand.add(chooseCard(700+250,175,false));
                    //hand.add(new SpellCard(700+250,175,"ChainLightning.png",false,2));
                }
            }
            
        
        }
    }
    
    public void animate(Animation a){
        Piece p = a.getPiece();
        double Xspeed = a.getXspeed();
        
        
        double Xend = a.getEndX();
        p.setX(p.getX()+Xspeed);

        a.setY();

        if(Xspeed>0){
            if(p.getX() >= Xend){
                p.setX(Xend);
                a.setY();
                animationList.remove(a);
            }
        }else{
            if(p.getX() <= Xend){
                p.setX(Xend);
                a.setY();
                animationList.remove(a);
            }
        }
    }

    public void startAttacking(){
        stopCastingSpell();
        stopSummoning();
        attacking = ! attacking;
        defending = false;
        attackingCreature = null;
        
        if(activePlayer == 1){
            p1Attacking.changeVisibility();
        }else{
            p2Attacking.changeVisibility();
        }
        p1Defending.setVisible(false);
        p2Defending.setVisible(false);
        skipDefense.setVisible(false);
    }
    public void stopAttacking(){
        attacking = false;
        defending = false;
        attackingCreature = null;
        p1Defending.setVisible(false);
        p2Defending.setVisible(false);
        skipDefense.setVisible(false);
        p1Attacking.setVisible(false);
        p2Attacking.setVisible(false);

    }
    public void stopSummoning(){
        summoning = false;
        p1SelectAmountOfMana.setVisible(false);
        p2SelectAmountOfMana.setVisible(false);
    }
    public void startSummoning(){

        stopAttacking();
        stopCastingSpell();
        if(activeCreatures.size()<4){
            summoning = !summoning;
            if(activePlayer==1)
                p1SelectAmountOfMana.changeVisibility();
            else
                p2SelectAmountOfMana.changeVisibility();
        }
    }
    public void startCasting(int player){
        if(spellsCast<maxSpellsPerTurn){
        stopAttacking();
        stopSummoning();
        stopCastingSpell();
        
        if(player == 1){
            p1CastingSpell = !p1CastingSpell;
        }if(player == 2){
            p2CastingSpell = !p2CastingSpell;
        }
        if(p1CastingSpell || p2CastingSpell){
            spell1.setVisible(true);
            spell2.setVisible(true);
            spell3.setVisible(true);
        }else{
            spell1.setVisible(false);
            spell2.setVisible(false);
            spell3.setVisible(false);
        }
        }

    }
    public void revealSpell(int spell){
        for(int i = 0; i<p1Hand.size(); i++){
            p1Hand.get(i).returnToDefault();
            p2Hand.get(i).returnToDefault();
        }
        if(p1CastingSpell){
            p1Hand.get(spell-1).setVisible(true);
            p1Hand.get(spell-1).setX(10);
            p1Hand.get(spell-1).setY(175);
            spellPrice = p1Hand.get(spell-1).getPrice();
        }else{
            p2Hand.get(p2Hand.size()-spell).setX(1140);
            p2Hand.get(p2Hand.size()-spell).setY(175);
            p2Hand.get(p2Hand.size()-spell).setVisible(true);
            spellPrice = p2Hand.get(p2Hand.size()-spell).getPrice();
        }

    }

    public void stopCastingSpell(){
        for(SpellCard card : p1Hand){
            card.returnToDefault();
        }
        for(SpellCard card : p2Hand){
            card.returnToDefault();
        }
        spellToCast = 0;
        spellPrice = -1;
        toSacrifice.clear();
        p1CastingSpell=false;
        p2CastingSpell=false;
        spell1.setVisible(false);
        spell2.setVisible(false);
        spell3.setVisible(false);
    }


    public void castSpell(){
        if(toSacrifice.size()==spellPrice){
            for(int i = 0; i<toSacrifice.size(); i++){
                Creature c = toSacrifice.get(i);
                
                playOnce(new Effect(c.getX(),c.getY(),"sacrifice.gif", true, 0,0,false),8,true);
                slashAudio.playSound();

                if(p1CastingSpell){
    
                    removeCreature(p1Creatures,p1Creatures.indexOf(toSacrifice.get(i)) );
                    
                }else{
                    removeCreature(p2Creatures,p2Creatures.indexOf(toSacrifice.get(i)) );
                    
                }
                
            }
            if(p1CastingSpell){

                p1Hand.get(spellToCast-1).doAction(p2Creatures,1,this);
                SpellCard oldCard = p1Hand.get(spellToCast-1);
                p1Hand.set(spellToCast-1,chooseCard(oldCard.getDefaultX(), oldCard.getDefaultY(), false));
            }else{
                
                p2Hand.get(p2Hand.size()-spellToCast).doAction(p1Creatures,2,this);
                SpellCard oldCard = p2Hand.get(p2Hand.size()-spellToCast);

                p2Hand.set(p2Hand.size()-spellToCast,chooseCard(oldCard.getDefaultX(), oldCard.getDefaultY(), false));
            }
            spellsCast++;
            stopCastingSpell();
        }
    }

    public void summonCreature(int amountOfMana){
        //remove from mana pool
        if(summoning){

        
            for(int i = 0; i<amountOfMana; i++){
                activeManaPool.remove(activeManaPool.size()-1);
            }
            //create a new creature
            int extraMana = amountOfMana-1;
            int bonusHealth = (int)(Math.random()*(2*extraMana+1));
            int bonusAttack = 2*extraMana-bonusHealth;
            
            addCreature(activePlayer,1+bonusHealth,1+bonusAttack);




            summoning = false;
            
            if(activePlayer==1)
                p1SelectAmountOfMana.changeVisibility();
            else
                p2SelectAmountOfMana.changeVisibility();
            summonAudio.playSound();
    }
    }

    public void addCreature(int player, int health, int damage){
        if(player==1){
            if(p1Creatures.size()>0){
                Creature previous = p1Creatures.get(p1Creatures.size()-1);
                p1Creatures.add(new Creature(previous.getX()+200,previous.getY(),"monster.gif", true, damage, health));
            }else{
                p1Creatures.add(new Creature(300,250,"monster.gif",true,damage, health));
            }
        }else{
            if(p2Creatures.size()>0){
                Creature previous = p2Creatures.get(p2Creatures.size()-1);
                p2Creatures.add(new Creature(previous.getX()-200,previous.getY(),"monsterReverse.gif", true, damage, health));
            }else{
                p2Creatures.add(new Creature(700+300,400,"monsterReverse.gif",true,damage, health));
            }
        }
    }

    public void fillManaPool(){
        activeManaPool.clear();
        if(activePlayer==1){
            activeManaPool.add(new Mana(10,10,"mana.png",true,1,this,1));
            activeManaPool.add(new Mana(60,10,"mana.png",true,2,this,1));
            activeManaPool.add(new Mana(110,10,"mana.png",true,3,this,1));
            activeManaPool.add(new Mana(10,60,"mana.png",true,4,this,1));
            activeManaPool.add(new Mana(60,60,"mana.png",true,5,this,1));
            activeManaPool.add(new Mana(110,60,"mana.png",true,6,this,1));
        }
        else{
            activeManaPool.add(new Mana(1220+10,10,"mana.png",true,1,this,2));
            activeManaPool.add(new Mana(1220+60,10,"mana.png",true,2,this,2));
            activeManaPool.add(new Mana(1220+110,10,"mana.png",true,3,this,2));
            activeManaPool.add(new Mana(1220+10,60,"mana.png",true,4,this,2));
            activeManaPool.add(new Mana(1220+60,60,"mana.png",true,5,this,2));
            activeManaPool.add(new Mana(1220+110,60,"mana.png",true,6,this,2));
        }
    }
    
    public void endTurn(){
        if(activePlayer == 1){
            activePlayer = 2;
            activeManaPool = p2ManaPool;
            activeCreatures = p2Creatures;
        }
        else if(activePlayer == 2){
            activePlayer = 1;
            activeManaPool = p1ManaPool;
            activeCreatures = p1Creatures;
        }
        fillManaPool();
        stopAttacking();
        stopSummoning();
        defending = false;
        skipDefense.setVisible(false);

        for(Text t : texts){
            t.setVisible(false);
        }

        p1TurnTracker.changeVisibility();
        p2TurnTracker.changeVisibility();

        for(Creature c : activeCreatures){
            c.setAttacked(false);
        }
        
        stopCastingSpell();
        spellsCast = 0;
    }

    public void increaseP1Health(int amount){
        p1Health+=amount;
    }
    public void increaseP2Health(int amount){
        p2Health+=amount;
    }

    public void playOnce(Effect e,int length,boolean needToDraw){
        e.setVisible(true);
        if(counter+length<10000){
            animateOnceList.add(new AnimateOnce(e, counter+length,needToDraw));
        }else{
            animateOnceList.add(new AnimateOnce(e, length,needToDraw));
        }

    }

    public void endGame(int player){
        playing = false;
        winner = player;
        firstPlay = false;
    }
    public void startPlaying(){
        activePlayer = 1;
        p1TurnTracker.setVisible(true);
        p2TurnTracker.setVisible(false);
        winner = 0;
        playing = true;
        stopCastingSpell();
        stopSummoning();
        stopAttacking();
        p1Health = 20;
        p2Health = 25;
        p1Creatures.clear();
        p2Creatures.clear();
        p1Hand.clear();
        p2Hand.clear();
        
        giveCard(p1Hand);
        giveCard(p1Hand);
        giveCard(p1Hand);
        activePlayer =2;
        fillManaPool();
        giveCard(p2Hand);
        giveCard(p2Hand);
        giveCard(p2Hand);
        activePlayer =1;
        fillManaPool();

    }

}
