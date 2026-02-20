import java.awt.*;


public class Square {
    
    private Color color = Color.white;
    private int x,y;
    private int size;

    public Square(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }


    public void drawMe(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,size,size);
        g.setColor(Color.black);
        g.drawRect(x,y,size,size);
    }
    public void drawMe(Graphics g, Color gridColor){
        g.setColor(color);
        g.fillRect(x,y,size,size);
        if(gridColor != null){
            
            g.setColor(gridColor);
            g.drawRect(x,y,size,size);
        }

        
    }

    public void setColor(Color newColor){
        color = newColor;
    }
    public Color getColor(){
        return color;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
