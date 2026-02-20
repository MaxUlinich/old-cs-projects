import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class EXP {
    private Image nextUpgrade;
    private double expToNextLevel;
    private double exp;
    private int expLevel;
    private Image expImg;
    private Image heartIcon;
    private Image speedIcon;
    private Image laserIcon;
    private Image attackIcon;
    public EXP(){
        exp = 0;
        expLevel = 1;
        expToNextLevel = Math.pow(2,expLevel);
       
        try {
            expImg = ImageIO.read(new File("expbar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            heartIcon = ImageIO.read(new File("hearticon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            speedIcon = ImageIO.read(new File("speedicon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            attackIcon = ImageIO.read(new File("attackicon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            laserIcon = ImageIO.read(new File("lasericon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nextUpgrade = attackIcon;


    }

    public void drawMe(Graphics g,int x,int y){
        //g.fillRect(x+15, y + 15, 220, 22);
        g.setColor(Color.black);
        g.fillRect(x+15,y+13,(int)(270), 24);
        g.setColor(Color.green);
        g.fillRect(x+15,y+15,(int)(exp/expToNextLevel*220), 22);
        g.drawImage(expImg, x, y, null);
        
        //g.setColor(Color.white);
        
        g.drawImage(nextUpgrade, x+257,y+15,null);
        



    }

    public void increaseExp(double amount, Ship ship, Ammo ammo){
        exp+=amount;
        calculateExpLevel(ship,ammo);
    }
    public void resetExp(){
        exp = 0;
        expLevel = 1;
        expToNextLevel = Math.pow(2,expLevel);
    }
    public void decreaseExp(int amount){
        exp-=amount;
        if(exp<0){
            exp = 0;
        }
    }
    public void calculateExpLevel(Ship ship,Ammo ammo){
        if(exp>=expToNextLevel){
            exp -= expToNextLevel;
            expLevel ++;
            expToNextLevel = Math.pow(3,expLevel);

            if(nextUpgrade == laserIcon){
                //System.out.println("LASERS");
                ship.increaseAmountOfLasers(1);
                int num = (int)(Math.random()*3);
                
                switch(num){
                    case 0:
                        nextUpgrade = speedIcon;
                        break;
                    case 1:
                        nextUpgrade = attackIcon;
                        break;
                    case 2:
                        nextUpgrade = heartIcon;
                        break;
                }
                //System.out.println("next exp level gives you" + nextUpgrade);
                
            }
            else if(nextUpgrade == speedIcon){
                //System.out.println("SPEED");
                ammo.increaseMaxAmmo(5);
                ship.reloadSpeed+=.05;
                int num = (int)(Math.random()*3);
                switch(num){
                    case 0:
                        nextUpgrade = laserIcon;
                        break;
                    case 1:
                        nextUpgrade = attackIcon;
                        break;
                    case 2:
                        nextUpgrade = heartIcon;
                        break;
                }
                // System.out.println("next exp level gives you" + nextUpgrade);
            }
            else if(nextUpgrade == attackIcon){
                //System.out.println("ATTACK");
                ship.decreaseCoolDown(1);
                int num = (int)(Math.random()*3);
                switch(num){
                    case 0:
                        nextUpgrade = speedIcon;
                        break;
                    case 1:
                        nextUpgrade = laserIcon;
                        break;
                    case 2:
                        nextUpgrade = heartIcon;
                        break;
                }
                 //System.out.println("next exp level gives you" + nextUpgrade);
            }
            else if(nextUpgrade == heartIcon){
                //System.out.println("HEART");
                ship.increaseHealth(1);
                int num = (int)(Math.random()*3);
                switch(num){
                    case 0:
                        nextUpgrade = speedIcon;
                        break;
                    case 1:
                        nextUpgrade = attackIcon;
                        break;
                    case 2:
                        nextUpgrade = laserIcon;
                        break;
                }
                 //System.out.println("next exp level gives you" + nextUpgrade);
            }
        }
    }

    
}
