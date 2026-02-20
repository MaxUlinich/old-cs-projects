
import javax.swing.JButton;

import javax.swing.JPanel;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;



public class ClientScreen extends JPanel implements ActionListener, KeyListener {
   
    private Game game;
    private ObjectInputStream in;
    private ObjectOutputStream out;
	
	private JButton start;
	private JButton placeCard;
	private JButton takeCards;
	private JButton slap;
	private JButton restart;


	private Boolean started;
  
	private BufferedImage spadeImg;
    private BufferedImage heartImg;
    private BufferedImage clubImg;
    private BufferedImage diamondImg;
	private BufferedImage backImg;
	private BufferedImage backgroundImg;
	private BufferedImage startScreenImage;


	private int playerNumber;
	
	private Audio slapAudio;
	private Audio takeAudio;
	private Audio placeAudio;
	

	public ClientScreen(){
		this.setLayout(null);
		this.setFocusable(true);
		addKeyListener(this);

		slapAudio = new Audio("slap.wav");
		takeAudio = new Audio("take.wav");
		placeAudio = new Audio("card.wav");


		MakeFont.makeFont();

		start = new JButton("Start");
		start.setBounds(50,110,100,50);
		this.add(start);
		start.addActionListener(this);
		start.setFocusable(false);
		
		restart = new JButton("Restart");
		restart.setBounds(50,120,100,50);
		this.add(restart);
		restart.addActionListener(this);
		restart.setVisible(false);
		restart.setFocusable(false);


		placeCard = new JButton("Place Card");
		placeCard.setBounds(650,500,100,50);
		this.add(placeCard);
		placeCard.addActionListener(this);
		placeCard.setVisible(false);
		placeCard.setFocusable(false);
		
		takeCards = new JButton("Take Cards");
		takeCards.setBounds(650,400,100,50);
		this.add(takeCards);
		takeCards.addActionListener(this);
		takeCards.setVisible(false);
		takeCards.setFocusable(false);
		
		slap = new JButton("Slap");
		slap.setBounds(650,450,100,50);
		this.add(slap);
		slap.addActionListener(this);
		slap.setVisible(false);
		slap.setFocusable(false);

		game = new Game();
		started = false;

		try {
            spadeImg = ImageIO.read(new File("spade.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            diamondImg = ImageIO.read(new File("diamond.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            heartImg = ImageIO.read(new File("heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clubImg = ImageIO.read(new File("club.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            backImg = ImageIO.read(new File("cardBack2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		try {
            backgroundImg = ImageIO.read(new File("background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		try {
            startScreenImage = ImageIO.read(new File("startBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }






		
       


		
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
         
        
        // for(int i = 0; i<game.getCards().size(); i++){
            
        // }
		
		if(!started){
			g.drawImage(startScreenImage,0,0,850,600,null);
			g.setFont(new Font(MakeFont.fontName,1,40));
			g.setColor(Color.white);
			g.drawString("Press Start",20,220);
			g.setFont(new Font("Arial", 1,20));
			g.drawString("Rules:",300,30);
			g.drawString("Players take turns placing cards one at a time.",300,75);
			g.drawString("If an Ace, King, Queen, or Jack is placed,",300,100);
			g.drawString("then the next player has 4, 3, 2, or 1 chances",300,125);
			g.drawString("to place a face card. Otherwise, the player",300,150);
			g.drawString("who placed the card gets to take the pile.",300,175);
			g.drawString("If any of the following combinations appears at the top",300,200);
			g.drawString("of the pile at any time, players may \"slap\" to",300,225);
			g.drawString("win the pile.",300,250);
			g.drawString("Combinations:",300,275+25);
			g.drawString("Pair - ex. ...4,4",300,300+25);
			g.drawString("Sandwich - ex. ...5,J,5",300,325+25);
			g.drawString("Double Sandwich - ex. ...3,J,K,3",300,350+25);
			g.drawString("Top Bottom - ex. 5,...,5  (the first and last cards placed)",300,375+25);
			g.drawString("Marriage - ex. ...K,Q",300,400+25);
			g.drawString("Divorce - ex. ...K,7,Q",300,425+25);
			g.drawString("Collect all the cards to win the game.",300,475+25);

		}
		else if(game.isGameOver()){
			restart.setVisible(true);
			slap.setVisible(false);
			takeCards.setVisible(false);
			placeCard.setVisible(false);
			g.setFont(new Font(MakeFont.fontName,1,40));
			g.setColor(Color.white);
			g.drawImage(startScreenImage,0,0,850,600,null);
			if(game.getPlayerFromNumber(playerNumber).getVictory()){
				g.drawString("You Won!", 30,100);
				
			}else{
				g.drawString("You Lost!", 30, 100);
				
			}

			g.drawString("Press Restart", 30, 250);

			
			g.setFont(new Font(MakeFont.fontName,1,40));
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 1,20));
			g.drawString("Rules:",300,30);
			g.drawString("Players take turns placing cards one at a time.",300,75);
			g.drawString("If an Ace, King, Queen, or Jack is placed,",300,100);
			g.drawString("then the next player has 4, 3, 2, or 1 chances",300,125);
			g.drawString("to place a face card. Otherwise, the player",300,150);
			g.drawString("who placed the card gets to take the pile.",300,175);
			g.drawString("If any of the following combinations appears at the top",300,200);
			g.drawString("of the pile at any time, players may \"slap\" to",300,225);
			g.drawString("win the pile.",300,250);
			g.drawString("Combinations:",300,275+25);
			g.drawString("Pair - ex. ...4,4",300,300+25);
			g.drawString("Sandwich - ex. ...5,J,5",300,325+25);
			g.drawString("Double Sandwich - ex. ...3,J,K,3",300,350+25);
			g.drawString("Top Bottom - ex. 5,...,5  (the first and last cards placed)",300,375+25);
			g.drawString("Marriage - ex. ...K,Q",300,400+25);
			g.drawString("Divorce - ex. ...K,7,Q",300,425+25);
			g.drawString("Collect all the cards to win the game.",300,475+25);
		}else{
			restart.setVisible(false);
			slap.setVisible(true);
			takeCards.setVisible(true);
			placeCard.setVisible(true);
			
			g.setColor(Color.GREEN);
			g.fillRect(0,0,2000,2000);
			g.drawImage(backgroundImg,0,0,null);
			
			
			Player player = game.getPlayerFromNumber(playerNumber); //draw the player's cards
			if(player!=null){
				DLList<Card> cards = player.getCards();
				for(int i = 0; i<cards.size(); i++){
					
					drawCard(g,cards.get(i),100+5*i,400);
				}
			}
			MyArrayList<Card> deck = game.getDeck(); //draw the central deck/pile
			for(int i = 0; i<deck.size(); i++){
				drawCard(g,deck.get(i),400-20*deck.size()+40*i,200);
			}
			
			g.setColor(Color.white);
			// if(game.isSlapped()){
			// 	g.drawString("Deck was slapped and taken!", 50,100);
			// }else if(game.isTaken()){
			// 	g.drawString("Deck was taken!",50,100);
			// }

			g.setFont(new Font(MakeFont.fontName,1,40));
		
			if(game.getActivePlayerNum()==playerNumber){
				g.drawString("Your Turn", 50,100);
			}
			if(game.getTurnDuration()>0 && game.getActivePlayerNum() == playerNumber){
				g.drawString(game.getTurnDuration()+"",500,100);
			}

			if(game.getWhoShouldTake() == playerNumber){
				g.drawString("<",755,440);
				
			}
		
		
		
		
		
		
		}
	
	
    }
	

	public Dimension getPreferredSize() {
		return new Dimension(850,600);
	}

	public void keyPressed(KeyEvent e){
		//System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 89){
			//System.out.println("restarting");
			game.stopGame(game.getPlayerFromNumber(playerNumber));
			try{
			out.writeObject(game);
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	public void keyReleased(KeyEvent e){

	}
	public void keyTyped(KeyEvent e){

	}

	public void actionPerformed(ActionEvent e){
		try{
		
			if(e.getSource() == start ){
				
				if(!started){	
					started = true;
					start.setVisible(false);
					placeCard.setVisible(true);
					takeCards.setVisible(true);
					slap.setVisible(true);
					out.writeObject(game);
				}

			}

			if(e.getSource() == placeCard){

				boolean success = game.placeCard(playerNumber);
				if(success){
					placeAudio.playSound();
				}
				out.writeObject(game);



			}

			if(e.getSource() == takeCards){
				boolean success = game.takeDeckEnforced(playerNumber);
				if(success){
					takeAudio.playSound();
				}
				//game.takeDeck(playerNumber);
				out.writeObject(game);
			}

			if(e.getSource() == slap){
				boolean success = game.slap(playerNumber);
				if(success){
					slapAudio.playSound();
				}
				out.writeObject(game);
			}

			if(e.getSource() == restart){
				game.restart();
				out.writeObject(game);
			}

			repaint();











		}catch(Exception ex){
			ex.printStackTrace();
		}
		
    }

	

	public void connect() throws IOException{

		String hostName = "localhost";
		//String hostName = "127.0.0.1";
		//String hostName = "10.210.118.69";
		//String hostName = "10.210.15.144";
		int portNumber = 1024;
		System.out.println("attempting connection");
		Socket socket = new Socket(hostName, portNumber);


		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
        
		


		//listens for inputs
		try {
			playerNumber = (int) in.readObject();
			while (true) {

                game = (Game) in.readObject();
				//System.out.println(game.getMap().keySet().toDLList());

			}
		} catch (UnknownHostException e) {
			System.err.println("Host unkown: " + hostName);
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		} catch(ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }
	}
	
	public void drawCard(Graphics g, Card c){

			int x = (int) c.getX();
			int y = (int) c.getY();
			String symbol = c.getSymbol();
			Boolean visible = c.isVisible();
			String suit = c.getSuit();

			g.setColor(Color.white);
			g.fillRoundRect((int)x, (int)y, 120, 150, 10, 10);
			g.setColor(Color.black);
			g.drawRoundRect((int)x,(int)y,120,150,10,10);
			
			Font font = new Font("Arial", Font.PLAIN, 50);
			g.setFont(font);
			g.drawString(symbol, (int)x+50,(int)y+90);
			if(visible){
				if(suit.equals("diamond")){
					g.drawImage(diamondImg,(int)x+10,(int)y+10,null);
					g.drawImage(rotateImage(diamondImg, 180),(int)x+80,(int)y+100,null);
				}
				if(suit.equals("spade")){
					g.drawImage(spadeImg,(int)x+10,(int)y+10,null);
					g.drawImage(rotateImage(spadeImg, 180),(int)x+80,(int)y+100,null);
				}
				if(suit.equals("heart")){
					g.drawImage(heartImg,(int)x+10,(int)y+10,null);
					g.drawImage(rotateImage(heartImg, 180),(int)x+80,(int)y+100,null);
				}
				if(suit.equals("club")){
					g.drawImage(clubImg,(int)x+10,(int)y+10,null);
					g.drawImage(rotateImage(clubImg, 180),(int)x+80,(int)y+100,null);
				}
			} else{
				g.setColor(Color.RED);
				g.fillRoundRect((int)x, (int)y, 120, 150, 10, 10);
				g.drawImage(backImg,(int)x,(int)y, null);
				g.setColor(Color.black);
				g.drawRoundRect((int)x,(int)y,120,150,10,10);
			
				
			}
	}

	public void drawCard(Graphics g, Card c, int x, int y){

		
		String symbol = c.getSymbol();
		Boolean visible = c.isVisible();
		String suit = c.getSuit();

		g.setColor(Color.white);
		g.fillRoundRect((int)x, (int)y, 120, 150, 10, 10);
		g.setColor(Color.black);
		g.drawRoundRect((int)x,(int)y,120,150,10,10);
		
		// Font font = new Font("Arial", Font.PLAIN, 50);
		// g.setFont(font);
		// g.drawString(symbol, (int)x+50,(int)y+90);
		if(suit.equals("diamond")||suit.equals("heart")){
			g.setColor(Color.red);
		}


		Font font2 = new Font("Arial", Font.PLAIN, -40);
		g.setFont(font2);
		g.drawString(symbol, (int)x+110,(int)y+110);

		Font font3 = new Font("Arial", Font.PLAIN, 40);
		g.setFont(font3);
		g.drawString(symbol, (int)x+10,(int)y+40);
		
		g.setColor(Color.black);
		if(visible){
			if(suit.equals("diamond")){
				g.drawImage(diamondImg,(int)x+80,(int)y+10,null);
				g.drawImage(rotateImage(diamondImg, 180),(int)x+10,(int)y+100,null);
			}
			if(suit.equals("spade")){
				g.drawImage(spadeImg,(int)x+80,(int)y+10,null);
				g.drawImage(rotateImage(spadeImg, 180),(int)x+10,(int)y+100,null);
			}
			if(suit.equals("heart")){
				g.drawImage(heartImg,(int)x+80,(int)y+10,null);
				g.drawImage(rotateImage(heartImg, 180),(int)x+10,(int)y+100,null);
			}
			if(suit.equals("club")){
				g.drawImage(clubImg,(int)x+80,(int)y+10,null);
				g.drawImage(rotateImage(clubImg, 180),(int)x+10,(int)y+100,null);
			}
		} else{
			g.setColor(Color.RED);
			g.fillRoundRect((int)x, (int)y, 120, 150, 10, 10);
			g.drawImage(backImg,(int)x,(int)y, null);
			g.setColor(Color.black);
			g.drawRoundRect((int)x,(int)y,120,150,10,10);
		
			
		}
	}



	public BufferedImage rotateImage(BufferedImage image, int degree){
        
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage newImage= new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newImage.createGraphics();
		g2d.rotate(Math.toRadians(degree),width/2,height/2);
		g2d.drawImage(image, 0, 0, null);
		return newImage;
	}












}
