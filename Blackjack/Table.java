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

public class Table extends JPanel implements MouseListener{
    double smoothness = 40.0;
    ArrayList<Card> deck = new ArrayList<Card>();
    ArrayList<Card> playerHand = new ArrayList<Card>();
    ArrayList<Card> dealerHand = new ArrayList<Card>();
    int playerHandSize = 2;
    int playerWins = 0;
    int dealerWins = 0;

    int deckX = 50;
    int deckY = 250;
    int playerHandX=200;
    int playerHandY=490;
    int dealerHandX=200;
    int dealerHandY=50;

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;

    Chip chip1;
    Chip chip2;

    boolean playingHand;
    boolean firstHand = true;
    boolean endingHand = false;

    scoreScreen ss = new scoreScreen();

    BufferedImage backgroundImg;

    Audio win1Audio;
    Audio win2Audio;
    Audio lose1Audio;
    Audio lose2Audio;
    Audio hit1Audio;
    Audio hit2Audio;
    Audio stand1Audio;
    Audio stand2Audio;
    Audio chipAudio;
    Audio cardAudio;
    Audio blackjackAudio;
    Audio tieAudio;
    Audio yesYouShouldHitAudio;
    Audio noYouShouldHitAudio;
    Audio noYouShouldStandAudio;
    Audio yesYouShouldStandAudio;

    Gestures stand = new Gestures("stand.png");
    Gestures hit = new Gestures("hit.png");
    victoryAnimation win = new victoryAnimation("winChips.png","winNoChips.png");
    //create a singular card and delete it just to set the static variable back img
    Card tempCard = new Card(1,"heart","heart" );
    //new Card(1,"heart","heart" );
    
   public Table(){
        tempCard = null;
        setBackground(new Color(46,99,0));
        setLayout(null);
        setFocusable(true);
        addMouseListener(this);

        b1 = new Button(800,50, "button1.png","Hit");
        b2 = new Button(800,125, "button2.png","Stand");
        b3 = new Button(800,200, "button3.png","Deal");
        b4 = new Button(800,525,"button3.png", "Hint");
        b5 = new Button(800,600,"button3.png", "Cheat");

        chip1 = new Chip(400,-100);
        chip2 = new Chip(400,1000);

        win1Audio = new Audio("win1.wav");
        win2Audio = new Audio("win2.wav");
        hit1Audio = new Audio("hit1.wav");
        hit2Audio = new Audio("hit2.wav");
        lose1Audio = new Audio("lose1.wav");
        lose2Audio = new Audio("lose2.wav");
        stand1Audio = new Audio("stand1.wav");
        stand2Audio = new Audio("stand2.wav");
        chipAudio = new Audio("chip.wav");
        cardAudio = new Audio("card.wav");
        blackjackAudio = new Audio("blackjack.wav");
        tieAudio = new Audio("tie.wav");
        yesYouShouldHitAudio = new Audio("yesyoushouldhit.wav");
        yesYouShouldStandAudio = new Audio("yesyoushouldstand.wav");
        noYouShouldHitAudio = new Audio("noyoushouldhit.wav");
        noYouShouldStandAudio = new Audio("noyoushouldstand.wav");

        playingHand = false;

        //startGame();
    
       
        try {
            backgroundImg = ImageIO.read(new File("background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

   }
   public void mousePressed(MouseEvent e){
        
       if(b1.detectPress(e.getX(), e.getY()) && playingHand){
        playRandomAudio(hit1Audio, hit2Audio);
        hitAnimation(350,450);
        giveCard(playerHand,playerHandX,playerHandY);
        
       }
       if(b2.detectPress(e.getX(), e.getY()) && playingHand){
            playRandomAudio(stand1Audio,stand2Audio);
            standAnimation(150,350,450);
           endHand();
       }
       if(! playingHand &&  b3.detectPress(e.getX(), e.getY())){
           
        startGame();
       }
       if(playingHand && b4.detectPress(e.getX(),e.getY())){
        giveNormalHint();
       }
       if(playingHand && b5.detectPress(e.getX(),e.getY())){
        giveCheatHint();
       }

    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}




   public Dimension getPreferredSize() {
       //Sets the size of the panel
       return new Dimension(1050,690);
   }
   @Override
   public void paintComponent(Graphics g){	
       super.paintComponent(g);
       
       g.setColor(new Color(46,99,0));
       g.fillRect(0,0,1000,800);
       g.drawImage( backgroundImg,0, 0, null);
       if(playingHand){
        b1.drawMe(g);
        b2.drawMe(g);
        b4.drawMe(g);
        b5.drawMe(g);
       }
       else{
        b3.drawMe(g);
       }
        
        //draw player hand and dealer hand
        for(Card card : playerHand){
            card.drawMe(g);
        }
        for(Card card : dealerHand){
            card.drawMe(g);
        }
       //draw deck
       
        g.setColor(Color.RED);
        g.fillRoundRect(deckX, deckY, 120, 150, 10, 10);
        g.drawImage(Card.backImg, deckX, deckY, null);
        g.setColor(Color.black);
        g.drawRoundRect(deckX,deckY,120,150,10,10);
    
        //draw totals
        g.setFont(new Font(MakeFont.fontName, 1, 30));
        
    
        //draw wins
        g.setFont(new Font(MakeFont.fontName, 1, 20));
        g.drawString("Dealer Wins: " + dealerWins, deckX,deckY-100);
        g.drawString("Player Wins: " + playerWins, deckX,deckY+290);
       //b1.listFonts(g);
        if(playingHand){
            g.drawString("Dealer total: " + getTotal(dealerHand),400,300);
            g.drawString("Player total: " + getTotal(playerHand),400,400);
        }else if(!firstHand){
            g.drawString(whoWon(),400,350);
        }
        chip1.drawMe(g);
        chip2.drawMe(g);
        
        hit.drawMe(g);
        stand.drawMe(g);
        win.drawMe(g);
        
   }
   


   private void shuffle(){
       //write code to shuffle your deck
       for(int i = 0; i < deck.size(); i++){
            int loc = (int)(Math.random()*deck.size());
            Card temp = deck.get(i);
            deck.set(i, deck.get(loc));
            deck.set(loc,temp);
       }
   }
   private void createDeck(){
       deck = new ArrayList<Card>();
       for(int i = 0; i<9; i++){
            deck.add(new Card(i+2,(i+2)+"","spade"));
            deck.add(new Card(i+2,(i+2)+"", "club"));
            deck.add(new Card(i+2,(i+2)+"","heart"));
            deck.add(new Card(i+2,(i+2)+"", "diamond"));
            //System.out.println(i);
       }
       deck.add(new Card(10,"J","spade"));
       deck.add(new Card(10,"J","heart"));
       deck.add(new Card(10,"J","club"));
       deck.add(new Card(10,"J","diamond"));

       deck.add(new Card(10,"Q","spade"));
       deck.add(new Card(10,"Q","heart"));
       deck.add(new Card(10,"Q","club"));
       deck.add(new Card(10,"Q","diamond"));

       deck.add(new Card(10,"K","spade"));
       deck.add( new Card(10,"K","heart"));
       deck.add( new Card(10,"K","club"));
       deck.add( new Card(10,"K","diamond"));

       deck.add( new Card(11,"A","spade"));
       deck.add(  new Card(11,"A","heart"));
       deck.add( new Card(11,"A","club"));
       deck.add( new Card(11,"A","diamond"));

       

       
   }
   private int getTotal(ArrayList<Card> hand){
        int total = 0;
        
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).isVisible()){
            total += hand.get(i).getValue();
            }
        }
        return total;
   }

   private void giveCard(ArrayList<Card> hand, int destX, int destY){
        cardAudio.playSound();
        Card card = deck.get(0);
        hand.add(card);
        deck.remove(0);
        destX+=100*hand.size();
        int distX = destX-deckX;
        int distY = destY-deckY;

        double speedX = (double)distX/smoothness;
        double speedY = (double)distY/smoothness;
        
        // card.x+=speedX;
        // card.y+=speedY;
        // repaint();

        // card.x+=speedX;
        // card.y+=speedY;
        // repaint();
        // card.x+=speedX;
        // card.y+=speedY;
        // repaint();
        // card.x+=speedX;
        // card.y+=speedY;
        // repaint();
        
         for(int i = 0; i < (int)smoothness; i++) {
                
             
             card.x+=speedX;
             card.y+=speedY;
             //repaint();
            paintImmediately(getBounds());
            //paintImmediately(0,0,1000,1000);
             try{
             Thread.sleep(10);
             }catch(Exception e){
                 e.printStackTrace();
             }
         }
         
         if(getTotal(hand)>=21 && !endingHand){
            endHand();
         }
         repaint();
    }
        


    private void dealerMove(){
        dealerHand.get(0).setVisible(true);
        if(getTotal(playerHand)<=21){
            while(getTotal(dealerHand)<17){
                giveCard(dealerHand,dealerHandX,dealerHandY);
            }
        }
        playingHand = false;
    }
   
    private void evaluateWin(){
        if(getTotal(dealerHand)>21){
            playerWins++;
            playRandomAudio(win1Audio,win2Audio);
            //winAnimation(300, 800);
            moveChip(chip1,400,1000);
            moveChip(chip2,400,1000);
            chip1.setLoc(400,-150);
        }else if(getTotal(playerHand)>21){
            dealerWins++;
            playRandomAudio(lose1Audio, lose2Audio);
            moveChip(chip1,400,-150);
            moveChip(chip2,400,-150);
            chip2.setLoc(400,1000);
        }else if(getTotal(dealerHand)>getTotal(playerHand)){
            dealerWins++;
            playRandomAudio(lose1Audio, lose2Audio);
            moveChip(chip1,400,-150);
            moveChip(chip2,400,-150);
            chip2.setLoc(400,1000);
        }else if(getTotal(dealerHand)<getTotal(playerHand)){
            playerWins++;
            playRandomAudio(win1Audio,win2Audio);
            //winAnimation(300, 800);
            moveChip(chip1,400,1000);
            moveChip(chip2,400,1000);
            chip1.setLoc(400,-150);
            //winAnimation(300, 800);
        }else{
            tieAudio.playSound();
            moveChip(chip1, 400,-150);
            moveChip(chip2, 400,1000);
        }
    }
    private String whoWon(){
        if(getTotal(dealerHand)>21){
            return "Player Won";
        }else if(getTotal(playerHand)>21){
            return "Dealer Won";
        }
        else if(getTotal(dealerHand)>getTotal(playerHand)){
            return "Dealer Won";
        }else if(getTotal(dealerHand)<getTotal(playerHand)){
            return "Player Won";
        }
        return "Tie";
    }
    private void endHand(){
        if(playingHand){
            endingHand = true;
            firstHand = false;
            dealerMove();
            evaluateWin();
            playingHand = false;
            repaint();
            endingHand = false;
        }
        
    }
   private void startGame(){
        playingHand = true;
        playerHand = new ArrayList<Card>();
        dealerHand = new ArrayList<Card>();
        moveChip(chip2,600,300);
        moveChip(chip1,700,300);
        
        playerHandSize = 2;
        createDeck();
        shuffle();

        //change this
    //    deck.set(0,new Card(11,"A test","diamond"));
    //    deck.set(2,new Card(10,"10 test","diamond"));
       //
        
        giveCard(playerHand,playerHandX,playerHandY);
        deck.get(0).setVisible(false);
        giveCard(dealerHand,dealerHandX,dealerHandY);
        giveCard(playerHand,playerHandX,playerHandY);
        
        if(playingHand){
            giveCard(dealerHand,dealerHandX,dealerHandY);
        }
        
   }

   public void standAnimation(int leftX, int rightX, int endY){

    stand.setVisible(true);
    double startX = stand.getX();
    double startY = stand.getY();
    int distX=leftX-(int)stand.getX();
    int distY=endY-(int)stand.getY();

    double velX = distX/(smoothness/2);
    double velY = distY/(smoothness/2);

    for(int i = 0; i<smoothness/2;i++){
        paintImmediately(getBounds());
        stand.increaseX(velX);
        stand.increaseY(velY);
        try{
            Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
        }
    }

     distX=rightX-(int)stand.getX();
     distY=endY-(int)stand.getY();

     velX = distX/(smoothness/2);
     velY = distY/(smoothness/2);
     for(int i = 0; i<(smoothness/2);i++){
        paintImmediately(getBounds());
        stand.increaseX(velX);
        stand.increaseY(velY);
        try{
            Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
        }
     }
     distX=(int)startX-(int)stand.getX();
     distY=(int)startY-(int)stand.getY();

     velX = distX/(smoothness/2);
     velY = distY/(smoothness/2);
     for(int i = 0; i<(smoothness/2);i++){
        paintImmediately(getBounds());
        stand.increaseX(velX);
        stand.increaseY(velY);
        try{
            Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
        }
     }
     stand.setVisible(false);
   }
   public void hitAnimation(int endX, int endY){
    hit.setVisible(true);
    hit.angle = Math.random()*60-60;
    int distX = endX-(int)hit.getX();
    int distY = endY-(int)hit.getY();

    double velX = distX/smoothness;
    double velY = distY/smoothness;

    for(int i = 0; i<smoothness; i++){
        
        paintImmediately(getBounds());
        hit.increaseX(velX);
        hit.increaseY(velY);
        try{
            Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
        }
    }
    for(int i = 0; i<smoothness; i++){
        
        paintImmediately(getBounds());
        hit.increaseX(-velX);
        hit.increaseY(-velY);
        try{
            Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
        }
    }
        hit.setVisible(false);
    }
    public void moveChip(Chip chip, int endX, int endY){
        chipAudio.playSound();
        int distX = endX-(int)chip.getX();
        int distY = endY-(int)chip.getY();
    
        double velX = distX/smoothness;
        double velY = distY/smoothness;

        for(int i = 0; i<smoothness; i++){
            paintImmediately(getBounds());
            chip.increaseX(velX);
            chip.increaseY(velY);
            try{
                Thread.sleep(10);
                }catch(Exception e){
                    e.printStackTrace();
            }
        }
    }
    public void winAnimation(int endX, int endY){
        win.setChips(true);
        win.setVisible(true);

        
        int distX = endX-(int)hit.getX();
        int distY = endY-(int)hit.getY();
    
        double velX = distX/smoothness;
        double velY = distY/smoothness;

        for(int i = 0; i<smoothness; i++){
            paintImmediately(getBounds());
            win.increaseX(velX);
            win.increaseY(velY);
            try{
                Thread.sleep(10);
                }catch(Exception e){
                    e.printStackTrace();
            }
        }
        win.setChips(false);
        for(int i = 0; i<smoothness; i++){
            paintImmediately(getBounds());
            win.increaseX(-velX);
            win.increaseY(-velY);
            try{
                Thread.sleep(10);
                }catch(Exception e){
                    e.printStackTrace();
            }
        }
        win.setVisible(false);
    }
    public void giveNormalHint(){
        if(getTotal(playerHand)<17){
            playRandomAudio(yesYouShouldHitAudio, noYouShouldHitAudio);
            
        }else{
            playRandomAudio(yesYouShouldStandAudio, noYouShouldStandAudio);
        }
    }
    public void giveCheatHint(){
        
        if(getTotal(playerHand)+deck.get(0).getValue()<=21){
            playRandomAudio(yesYouShouldHitAudio, noYouShouldHitAudio);
            
        }else{
            playRandomAudio(yesYouShouldStandAudio, noYouShouldStandAudio);
        }
    }
    public void playRandomAudio(Audio a1, Audio a2){
        int rand = (int)(Math.random()*2);

        if(rand == 0){
            a1.playSound();
        }
        if(rand == 1){
            a2.playSound();
        }
    }

} 




