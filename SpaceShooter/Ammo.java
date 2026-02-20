import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;


public class Ammo {

    
    private Image ammoImage;
    private int height = 30;
    private int width = 300;
    private double maxAmmo = 10;
    private double ammo = 10;
    public Ammo(){
        

        try {
            ammoImage = ImageIO.read(new File("ammobar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawMe(Graphics g, int x, int y){
        
        Color color;
        if(ammo>=maxAmmo/2.0){
            color = Color.green;
        }else if(ammo>=maxAmmo/4.0){
            color = Color.orange;
        }else{
            color = Color.red;
        }
        g.setColor(color);
        g.fillOval(x-height/2,y,height,height);
        g.setColor(Color.black);
        g.fillRect(x,y,(int)(width-height/2),height);
        g.fillOval((int)(x+width-height),y,height,height);
        g.setColor(color);
        g.fillRect(x,y,(int)(ammo*width/maxAmmo-height/2.0),height);
        if(x+ammo*width/maxAmmo-height>=x){
            //g.fillArc((int)(x+ammo*width/maxAmmo-height),y,height,height,-90,180);
            g.fillOval((int)(x+ammo*width/maxAmmo-height),y,height,height);
        }
        for(int i = (int)ammo; i>0; i--){
            g.setColor(Color.black);
            //g.drawOval((int)(x+i*width/maxAmmo-height),y,height,height);
            g.drawArc((int)(x+i*width/maxAmmo-height),y,height,height,-90,180);
            g.setColor(color);
            
            //g.fillRect((int)(x+i*width/maxAmmo-height),y,(int)(height/2.0),height);
        }
        g.drawImage(ammoImage,x-22,y-10,null);

    }

    public void increaseAmmo(double reloadSpeed){
        ammo+=reloadSpeed;
        

        if(ammo>maxAmmo){
            ammo = maxAmmo;
        }
    }

    public boolean hasAmmo(){
        if(ammo-1>=0){
            return true;
        }
        return false;
    }

    public void decreaseAmmo(){
        ammo-=1;
        if(ammo<0){
            ammo = 0;
        }
    }
    public void setAmmo(int amount){
        ammo = amount;
    }
    
    public void increaseMaxAmmo(int amount){
        maxAmmo+=amount;
    }
    public void setMaxAmmo(int amount){
        maxAmmo = 10;
    }
    
}
