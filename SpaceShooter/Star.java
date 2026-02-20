import java.awt.Color;
import java.awt.Graphics;

public class Star{
	private int x, y;
    private Color white;
    private int w = 1200;
    private int h = 600;

    public Star(){
        x = (int)(Math.random()*w-2);
        y = (int)(Math.random()*h-2);
        white = new Color(255,255,255);
    }

	public void drawMe(Graphics g){
        g.setColor(white);
        g.fillRect(x,y,3,3);
    }

    public void move(int speed){
        x-=speed;
        if(x<0){
            x=w+(int)(Math.random()*speed);
            y = (int)(Math.random()*h-100)+50;
        }
    }
}
