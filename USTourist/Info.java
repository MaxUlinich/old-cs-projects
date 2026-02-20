import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Color;

public class Info {
    BufferedImage img;
    String s1, s2, s3;

    public Info(String file, String s1, String s2, String s3) {
        try {
            img = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public void drawMe(int x, int y, Graphics g) {
        g.drawImage(img, x, y, 300, 300, null);
        g.setColor(Color.black);
        g.drawString(s1, x, y + 330);
        g.drawString(s2, x, y + 360);
        g.drawString(s3, x, y + 390);

    }

}
