import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
public class Ship {
    private int health = 3;
    public int width = 173;
    public int height = 86;
    private Image shipImg;
    public double x,y;
    public double speed;
    public double maxSpeed;
    private int w = 1200;
    private int h = 600;
    private int amountOfLasers = 1;
    public int coolDown=7;
    private int exp = 0;
    public double stun = 0;
    private int recoverySpeed = 2;
    public double reloadSpeed = .2;
    public Ship(int x, int y){
        this.x = x;
        this.y = y;
        maxSpeed = 6;
        try {
            shipImg = ImageIO.read(new File("spaceship.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawMe(Graphics g){
        g.drawImage(shipImg, (int) x, (int) y, null);
        HitBox box = new HitBox(x,y,width,height, 140, 40);
        
        g.setColor(Color.red);
        //g.fillRect((int) box.x, (int) box.y, (int) box.width, (int) box.height);
        //g.fillRect((int) x,(int) y,(int) width,(int)height);
    }
    
    public void moveUp(){
        y-=speed;
        if(y<0){
            y=0;
        }
    }
    public void moveDown(){
        y+=speed;
        if(y+height>h){
            y=h-height;
        }
    }
    public void moveLeft(){
        x-=speed;
        if(x<=0){
            x=0;
        }
    }
    public void moveRight(){
        x+=speed;
        if(x>300){
            x=300;
        }
    }

    public int getHealth(){
        return health;
    }
    public void decreaseHealth(){
        health-=1;
    }
    public void setHealth(int amount){
        health = amount;
    }

    public void increaseHealth(int amount){
        health += amount;
    }
    public int getAmountOfLasers(){
        return amountOfLasers;
    }
    public void increaseAmountOfLasers(int amount){
        amountOfLasers+=amount;
    }
    public void increaseSpeed(int amount){
        if(maxSpeed<100){
            speed+=amount;
        }
    }
    public void decreaseCoolDown(int amount){
        
        coolDown-=amount;
        if(coolDown<0){
            coolDown = 0;
        }
    }
    public void resetPowers(Ammo ammo){
        amountOfLasers=1;
        coolDown = 7;
        maxSpeed = 5;
        ammo.setMaxAmmo(10);
        ammo.setAmmo(10);
        reloadSpeed = .2;
    }
    public int getEXP(){
        return exp;
    }
    public void increaseStun(int amount){
        stun += amount;
    }
    public void decreaseStun(){
        stun-=recoverySpeed;
        if(stun<0){
            stun = 0;
        }
    }

    public void increaseReload(){
        this.recoverySpeed*=2;
    }
}
