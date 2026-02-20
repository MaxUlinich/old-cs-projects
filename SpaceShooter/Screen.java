import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
public class Screen extends JPanel implements KeyListener{
    private boolean gameOver = false;
    private boolean drawEnd = false;
    private HighScore hs = new HighScore();
    private int w = 1200;
    private int h = 600;
    int i = 0;
    private int level = 1;
    private Color backgroundColor = Color.black;
    private int shade = 150;
    private int enemiesLeft=3;
    private int starSpeed = 5;
    //private int asteroidSpeed = 4;
    private int shotsHit = 0;
    private int totalShots = 0;
    
    //Arrays of objects
    private Asteroid[] asteroids;
    private Star[] stars;
    private Enemy[] enemies;
    private Laser[] lasers = new Laser[0];
    private Image heartImg;
    private Image borderImg;

    private EXP expBar = new EXP();
    private Ammo ammoBar = new Ammo();
    public Ship ship= new Ship(0,h/2);

    private EndScreen endScreen = new EndScreen();
    private Tutorial title = new Tutorial();

    private boolean upArrowPressed = false;
    private boolean downArrowPressed = false;
    private boolean rightArrowPressed = false;
    private boolean leftArrowPressed = false;
    private boolean spaceButtonPressed = false;
    
    private boolean showTitle = true;
    int counter = 0;
    private int lastShot=-20;


    public Screen(){

        
       
        setFocusable(true);
        addKeyListener(this);

        try {
            heartImg = ImageIO.read(new File("heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            borderImg = ImageIO.read(new File("border.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        stars = new Star[50];
        for(int i = 0; i<stars.length; i++){
            stars[i] = new Star();
        }

        asteroids = new Asteroid[5];
        for(int i = 0; i<asteroids.length; i++){
            asteroids[i] = new Asteroid();
        }

        createEnemies();
    }


	public Dimension getPreferredSize(){	
		return new Dimension(w,h);
	}



	public void paintComponent(Graphics g){
		super.paintComponent(g);
        
        //draw background

        g.setColor(backgroundColor);
        
        g.fillRect(0,0,w,h);
        //draw Stars on background
        for(Star star : stars){
            star.drawMe(g);
        }
        for(Asteroid asteroid : asteroids){
            asteroid.drawMe(g);
        }
        
        //draw the lasers
        for(Laser laser : lasers){
            laser.drawMe(g);
        }

        synchronized(this) {
            //draw the enemies
            for(Enemy enemy : enemies){
                enemy.drawMe(g);
            }
        }
        
        //draw the ship
        ship.drawMe(g);

        //draw the health
        for(int i = 0; i<ship.getHealth(); i++){
            g.drawImage(heartImg, 50*i+10, 10, null);
        }
        //draw the level
        g.setColor(Color.white);
        g.drawString("Wave: " + level, 10, 70);
        //draw the exp bar
        expBar.drawMe(g, w-300, 10);
        ammoBar.drawMe(g, w-650,20);

        
        if(gameOver){
            
            g.drawImage(borderImg,0,0,null);
            g.setColor(Color.cyan);
            g.setFont(new Font("Serif", Font.BOLD, 30));
            g.drawString("High Score: " + hs.getHighestScore(), 100,110);
            g.drawString("You Made It To Wave " + level, 100,200);
            g.drawString("And Fired " + totalShots + " Shots", 100,300);
            g.drawString("With An Accuracy Of " + (int)((double)shotsHit/totalShots*100.0)+"%", 100,400);
            g.drawString("You Got A Score Of " + (level*1000+shotsHit-totalShots),100,500);
            g.drawString("Enter/Return To Play Again",500,110);
            g.drawString("Delete/Backspace To Reset High Score", 500,200);
            if(drawEnd){
                
                endScreen.drawMe(g,i);
                if(i>w/2.0-10){
                    drawEnd = false;

                }
                i+=4;
            }
        }
        if(showTitle){
            title.drawMe(g);
        }
        
        
	}



    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==38){
            //move up
           upArrowPressed=true;
           
        }
        if(e.getKeyCode() == 40){
            //down
            downArrowPressed=true;
            
           
        }
        if(e.getKeyCode() == 39){
            //right
            rightArrowPressed=true;
            
           
        }
        if(e.getKeyCode() == 37){
            //left
            leftArrowPressed=true;
            
           
        }

        //shoot the laser
        if(e.getKeyCode() == 32){   
            spaceButtonPressed = true;         
            
        }

        //cheat key

        if(e.getKeyCode() == 79){
            goToNextLevel();

        }
       //reverse cheat key (to die)
        if(e.getKeyCode() == 27){
            ship.setHealth(0);
        }
        //upgrade cheat keys:
        //z
        if(e.getKeyCode() == 90){
            ship.increaseAmountOfLasers(1);
        }
        //x
        if(e.getKeyCode() == 88){
            ammoBar.increaseMaxAmmo(5);
            ship.reloadSpeed+=.05;
            
        }
        //c
        if(e.getKeyCode() == 67){
            ship.decreaseCoolDown(1);
            
        }
        //v
        if(e.getKeyCode() == 86){
            ship.increaseHealth(1);
        }
        // enter to restart

        if(e.getKeyCode() == 10 || e.getKeyCode() == 82){
            if(showTitle){
                title.increaseStage();
                if(title.getStage()>5){
                    title.resetStage();
                    showTitle = false;
                    restartGame();
                }
                
            }
            if(gameOver){
                //restartGame();
                showTitle = true;


            }
        }
        if(e.getKeyCode() == 8 || e.getKeyCode() == 127){
            if(gameOver){
                hs.resetScore();
                repaint();
            }
        }

    }
    
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()==38){
            //move up
           upArrowPressed=false;
           
        }
        if(e.getKeyCode() == 40){
            //down
            downArrowPressed=false;
            
           
        }
        if(e.getKeyCode() == 39){
            //right
            rightArrowPressed=false;
            
           
        }
        if(e.getKeyCode() == 37){
            //left
            leftArrowPressed=false;
              
        }
        if(e.getKeyCode() == 32){
            //space
            spaceButtonPressed = false;
        }
    }
    
    public void keyTyped(KeyEvent e){}

    public void createEnemies(){
        synchronized(this) {
            if(level %3 == 0){
                enemies = new Enemy[1];
                for(int i=0; i<enemies.length; i++){
                    enemies[i] = new Enemy(10*(level),true);
                
                }
            }else{
                enemies = new Enemy[level*2+1];
                for(int i=0; i<enemies.length; i++){
                    enemies[i] = new Enemy(level*2,false);
                
                }
            }
        }
    }

    public void goToNextLevel(){
        Asteroid.newAsteroidType();
        level+=1;
        starSpeed = 5*level;
        enemiesLeft = 2*level+1;
        backgroundColor = new Color((int)(Math.random()*shade),(int)(Math.random()*shade),(int)(Math.random()*shade));
        createEnemies();
    }

    public void resetLevel(){
        for(Enemy enemy : enemies){
            enemy.reset();
        }
    }
    public void restartGame(){
        ship.resetPowers(ammoBar);
        expBar.resetExp();
        gameOver = false;
    
        ship.setHealth(3); 
        shotsHit = 0;
        totalShots = 0;
        level = 1;
        createEnemies();
        enemiesLeft = 2*level+1;
        starSpeed=5;

    }
    public void fire(){
        if(counter-lastShot>ship.coolDown && ammoBar.hasAmmo()){

            Laser[] newLasers = new Laser[lasers.length+ship.getAmountOfLasers()];
            for(int i=0; i<lasers.length; i++){
                newLasers[i] = lasers[i];
            }
            for(int i = 0; i<ship.getAmountOfLasers(); i++){
                newLasers[newLasers.length-1-i] = new Laser(ship.x+ship.width,ship.y+ship.height/2-ship.getAmountOfLasers()*10/2+10*i);
                lasers = newLasers;
                totalShots++;
                lastShot = counter;
            }
            ammoBar.decreaseAmmo();
        }
    }

    public void play(){
        while(true){
            //System.out.println(gameOver);
            try{
                    Thread.sleep(30);
                }catch(Exception e){
                    e.printStackTrace();
                }
            repaint();
            while(! gameOver && ! showTitle){
                
                //Move Ship Based on Arrow Keys
                if(downArrowPressed){
                    ship.moveDown();
                }
                if(upArrowPressed){
                    ship.moveUp();
                }
                if(rightArrowPressed){
                    ship.moveRight();
                }
                if(leftArrowPressed){
                    ship.moveLeft();
                }
                if(ship.stun>0){
                    ship.speed = ship.maxSpeed/4.0;
                }
                if(ship.stun<=0){
                    if(spaceButtonPressed){
                        fire();
                        ship.speed = ship.maxSpeed/2.0;
                    }else{
                        ship.speed = ship.maxSpeed;
                    }
                }   
                for(Star star : stars){
                    star.move(starSpeed);
                }
                for(Asteroid asteroid : asteroids){
                    asteroid.move();
                }

                if(!spaceButtonPressed){
                    ammoBar.increaseAmmo(ship.reloadSpeed);
                }
                
                synchronized(this) {
                    //Variable for how many enemies are left
                    enemiesLeft = 0;
                    for(int i = 0; i<enemies.length; i++){
                        if(enemies[i].health>0){
                            enemiesLeft+=1;
                        }
                    }

                    for(Enemy enemy : enemies){

                        if(enemy.health>0){
                            enemy.move();  

                            for(Laser laser : lasers){
                                if(laser.active){
                                    
                                        if(enemy.collideWithRect(laser.x, laser.y, laser.width, laser.height)){
                                            enemy.decreaseHealth();
                                            if(enemy.health <= 0){
                                                if(enemy.boss){
                                                    expBar.increaseExp(level*5,ship,ammoBar);
                                                }
                                                else{
                                                expBar.increaseExp(level,ship,ammoBar);
                                                }
                                            }
                                            shotsHit++;
                                            laser.disable();
                                        }
                                    
                                }
                            }
                            if(new HitBox(enemy.x, enemy.y, enemy.width, enemy.height,enemy.hitBoxWidth,enemy.hitBoxHeight).collideWithHitBox(new HitBox(ship.x,ship.y,ship.width,ship.height, 140, 40))){
                            //if(enemy.collideWithRect(ship.x,ship.y,ship.width,ship.height) || enemy.collideWithEnd()){
                                expBar.decreaseExp(enemy.health);
                                ship.decreaseHealth();
                                resetLevel();
                            }
                            else if(enemy.collideWithEnd()){
                                ship.decreaseHealth();
                                resetLevel();
                            }
                        }
                    }


                    for(Laser laser : lasers){
                        if(laser.x>w+400)
                            laser.disable();
                        if(laser.active)
                            laser.move();
                    }


                    //change the velocity of the enemies every 20 counts
                    if(counter%20 == 0){
                        for(Enemy enemy : enemies){
                            enemy.changeVel();
                        }
                    }
                }

                for(Asteroid asteroid : asteroids){
                    if(asteroid.active){
                        if(new HitBox(asteroid.x, asteroid.y, asteroid.width, asteroid.height,asteroid.width,asteroid.height).collideWithHitBox(new HitBox(ship.x,ship.y,ship.width,ship.height, 140, 40))){
                            asteroid.disable();
                            ship.increaseStun(asteroid.width);
                        }
                    }
                }

                if(ship.getHealth()<=0){
                    gameOver = true;
                    hs.addScore(level*1000+shotsHit-totalShots);
                    i = 0;
                    drawEnd = true;
                }

                if(enemiesLeft<=0){
                    goToNextLevel();
                }
                //incrememt the counter;
                counter++;
                ship.decreaseStun();
                
                
                if(counter>=100000){
                    counter = 0;
                }
                //slow down and repaint
                try{
                    Thread.sleep(30);
                }catch(Exception e){
                    e.printStackTrace();
                }
                repaint();
            }

        }
    }
}
    

