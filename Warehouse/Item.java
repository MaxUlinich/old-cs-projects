import java.awt.Graphics;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class Item {
    private double x, y, width, height;
    private boolean visible;
    private String file;
    private BufferedImage img;
    private double velY = 0;
    private double velX = 0;
    private double friction = .99;

    public Item(double x, double y, double width, double height, String file, Boolean visible, double friction) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.file = file;
        this.visible = visible;
        this.friction = friction;
        try {
            img = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMe(Graphics g) {
        if (visible) {
            g.drawImage(img, (int) x, (int) y, null);
        }
    }

    public boolean detectPress(int mouseX, int mouseY) {
        if (visible) {
            if (mouseX > x && mouseX < x + width) {
                if (mouseY > y && mouseY < y + height) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setVisible(boolean yn) {
        visible = yn;
    }

    public boolean getVisible() {
        return visible;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelX(double amount) {
        velX = amount;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelY(double amount) {
        velY = amount;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getFile() {
        return file;
    }

    public double getFriction() {
        return friction;
    }

    public void setFriction(Double amount) {
        friction = amount;
    }

    public boolean equals(Object o) {
        try {
            Item newItem = (Item) o;
            if (newItem.getX() == x && newItem.getY() == y && newItem.getFile().equals(file)) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean intersection(Item item) {

        if (x + width > item.getX() && x < item.getX() + item.getWidth()) {
            if (y + height > item.getY() && y < item.getY() + item.getWidth()) {

                return true;

            }
        }
        return false;

    }
}
