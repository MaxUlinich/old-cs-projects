
import java.awt.Font;
import java.io.InputStream;
import java.awt.GraphicsEnvironment;
import java.io.File;
public class MakeFont {
  public static String fontName = "Endor";
  public static void makeFont(){
    try {
        GraphicsEnvironment ge = 
        GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("myFont.ttf")));

   } catch (Exception e) {
        //Handle exception
        e.printStackTrace();
   }
  }
}