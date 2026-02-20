import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
public class Enemy {
    private Image enemyImg;
    public double x,y;
    private double xvel;
    private double yvel;
    public double width = 200;
    public double height = 100;
    public int health;
    public int maxHealth = 3;
    private int w = 1200;
    private int h = 600;
    public boolean boss = false;

    public int hitBoxWidth = 140;
    public int hitBoxHeight = 60;

    public Enemy(int maxHealth, boolean boss){
        this.boss = boss;
        this.x = w;
        this.y = (int)(Math.random()*(h-height));
        this.maxHealth = maxHealth;
        this.health = maxHealth;

        if(!boss){
            this.hitBoxWidth = 140;
            this.hitBoxHeight = 60;
            width = 200;
            height = 100;
            try {
                enemyImg = ImageIO.read(new File("enemy.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            this.hitBoxWidth = 250;
            this.hitBoxHeight = 200;
            width = 300;
            height = 293;
            try {
            enemyImg = ImageIO.read(new File("boss.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        changeVel();
    }
    public void drawMe(Graphics g){
        if(health>0){
            g.drawImage(enemyImg, (int) x, (int) y, null);
            
            //HitBox box = new HitBox(x,y,width,height, hitBoxWidth, hitBoxHeight);
            g.setColor(Color.red);
            if(health<maxHealth){
                g.fillRect((int)x+20,(int)y-5,(int)(health*100.0/maxHealth),5);
            }
            //g.fillRect((int) box.x, (int) box.y, (int) box.width, (int) box.height);
            //g.fillRect((int) x,(int) y,(int) width,(int)height);
        }
    }
    public void changeVel(){
        xvel = (int)(Math.random()*-5)-1;
        yvel = (int)(Math.random()*10)-5;
    }
    public void move(){
        x+=xvel;
        y+=yvel;

        if(y>600-height){
            y=600-height;
            yvel*=-1;
        }
        if(y<0){
            y=0;
            yvel*=-1;
        }
        
    }

    

    public boolean collideWithRect(double rectx, double recty, double rectWidth, double rectHeight){
        if(rectx>x+width || x>rectx+rectWidth){
            return false;
        }  
        if(recty>y+height || y>recty+rectHeight){
            return false;
        }
        
        return true;
    }

    public boolean collideWithHitBox(HitBox box){
        if(box.x>x+width || x>box.x+box.width){
            return false;
        }  
        if(box.y>y+height || y>box.y+box.height){
            return false;
        }
        
        return true;
    }

    public boolean collideWithEnd(){
        if(x+width<0){
            return true;
        }
        return false;
    }

    public void decreaseHealth(){
        health-=1;
    }

    public void reset(){
        x=w;
    }

    
}
