import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;


public class Button {
    private boolean visible = true;
    private Font myFont;
    
    private int x, y;
    private BufferedImage img; 
    private String text;
    private int changeX = 0;
    private int changeY = 0;
    public Button(int x, int y, String file, String text,int changeX, int changeY){
        
        this.x = x;
        this.y = y;
        this.text = text;

        this.changeX = changeX;
        this.changeY = changeY;
        try {
            img = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Button(int x, int y, String file, String text){
        
        this.x = x;
        this.y = y;
        this.text = text;
        try {
            img = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void drawMe(Graphics g){
        if(visible){
        g.setColor(new Color(204, 139, 0));
        g.drawImage(img, x, y, null);
        //g.setColor(Color.black);

        g.setFont(new Font(MakeFont.fontName, 1, 18));

        
        g.drawString(text,x+changeX,y+changeY);
        //g.drawRect(x+15,y+15,177,43);


        }
    }

    public boolean detectPress(int mouseX, int mouseY){
        if(visible){
        if(mouseX > x+15 && mouseX < x+15 + 177){
            if(mouseY>y+15 && mouseY< y + 15+43){
                return true;
            }
        }
        }
        return false;
    }
    public void setVisible(boolean yn){
        visible = yn;
    }
    public boolean getVisible(){
        return visible;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }


  
    
  


    public void listFonts(Graphics g){
        
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        int y = 10;
        int x = 10;
        for( String font : fonts ){
            g.setFont(new Font(font,1,10));
            System.out.println( font );
            g.drawString(font, x, y);
            y+=20;

            if(y>600){
                y=10;
                x+=100;
            }
        }
    }
}
