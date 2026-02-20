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
    private Font myFont;
    
    private int x, y;
    private BufferedImage img; 
    private String text;
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
        g.drawImage(img, x, y, null);
        g.setColor(Color.black);

        g.setFont(new Font(MakeFont.fontName, 1, 20));

        int changeX=0, changeY=0;
        
        if(text.equals("Hit")){
            changeX = 82;
            changeY = 47;
        }
        if(text.equals("Stand")){
            changeX = 65;
            changeY = 35;
        }
        if(text.equals("Deal")){
            changeX = 75;
            changeY = 42;
        }

        if(text.equals("Hint")){
            changeX = 80;
            changeY = 47;
        }

        if(text.equals("Cheat")){
            changeX=70;
            changeY=45;
        }
        g.drawString(text,x+changeX,y+changeY);
    }

    public boolean detectPress(int mouseX, int mouseY){
        if(mouseX > x && mouseX < x + 200){
            if(mouseY>y && mouseY< y + 70){
                return true;
            }
        }

        return false;
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
