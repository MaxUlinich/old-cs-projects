
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Car implements Runnable {
    private double x, y, startX, startY, endY, endX;
    private int index;
    private BufferedImage originalImage;
    private BufferedImage drawingImage;
    private DLList<Location> locations;

    public Car(DLList<Location> locations) {
        this.startX = locations.get(0).getX();
        this.x = startX;
        this.startY = locations.get(0).getY();
        this.y = startY;
        this.endX = locations.get(1).getX();
        this.endY = locations.get(1).getY();
        index = 0;

        this.locations = locations;
        try {
            originalImage = ImageIO.read(new File("car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawingImage = rotateImage(originalImage, Math.atan((endY - startY) / (endX - startX)));

    }

    public void run() {
        Boolean running = true;
        while (running) {
           
            double angle = Math.atan2(endY - startY, endX - startX);
            x += Math.cos(angle) * 4;
            y += Math.sin(angle) * 4;
            

            try {
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (startX < endX && x > endX || startX > endX && x < endX) {
                if (locations.size() > index + 2) {
                    index++;
                } else {
                    index = 0;
                }
                startX = locations.get(index).getX();
                startY = locations.get(index).getY();
                endX = locations.get(index + 1).getX();
                endY = locations.get(index + 1).getY();
                x = startX;
                y = startY;
                drawingImage = rotateImage(originalImage, Math.atan((endY - startY) / (endX - startX)));

            }
        }
    }

    public void drawMe(Graphics g) {
        g.drawImage(drawingImage, (int) x, (int) y - 10, 40, 40, null);
    }

    public void drawMe(Graphics g, int x, int y) {
        g.drawImage(drawingImage, (int) x, (int) y, null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public BufferedImage rotateImage(BufferedImage image, double rad){
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.rotate(rad, width / 2, height / 2);
        g2d.drawImage(image, 0, 0, null);
        return newImage;
    }
}
