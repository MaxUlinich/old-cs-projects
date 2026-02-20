import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Map implements Runnable {
    private int x, y, numSquares, squareSize, screenSize;
    private boolean goUp;
    private BufferedImage mapImg, solImg, rushImg, ggImg, needleImg;
    private Tourist player;

    public Map(int x, int y, String file, Tourist t, int num, int squareSize, int screenSize) {
        this.x = x;
        this.y = y;
        try {
            mapImg = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            solImg = ImageIO.read(new File("sol.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ggImg = ImageIO.read(new File("bridge.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rushImg = ImageIO.read(new File("rush.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            needleImg = ImageIO.read(new File("needle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        numSquares = num;
        this.squareSize = squareSize;
        this.screenSize = screenSize;
        player = t;

    }

    public void run() {
        if (goUp) {
            while (y > -400) {
                y--;
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    break;
                }
            }
        } else {
            while (y < 0) {
                y++;
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    break;
                }
            }
        }

    }

    public void drawMe(Graphics g) {
        g.drawImage(mapImg, x, y, 400, 400, null);

        g.setColor(Color.white);
        // 91, 176
        g.fillOval(x + 176 * 400 / numSquares - 12, y + 91 * 400 / numSquares - 12, 24, 24);
        // 107, 9
        g.fillOval(x + 9 * 400 / numSquares - 12, y + 107 * 400 / numSquares - 12, 24, 24);
        // 62,14
        g.fillOval(x + 14 * 400 / numSquares - 12, y + 62 * 400 / numSquares - 12, 24, 24);
        // 77,60
        g.fillOval(x + 60 * 400 / numSquares - 12, y + 77 * 400 / numSquares - 12, 24, 24);

        // 91, 176
        g.drawImage(solImg, x + 176 * 400 / numSquares - 12, y + 91 * 400 / numSquares - 12, 24, 24, null);
        // 107, 9
        g.drawImage(ggImg, x + 9 * 400 / numSquares - 12, y + 107 * 400 / numSquares - 12, 24, 24, null);
        // 62,14
        g.drawImage(needleImg, x + 14 * 400 / numSquares - 12, y + 62 * 400 / numSquares - 12, 24, 24, null);
        // 77,60
        g.drawImage(rushImg, x + 60 * 400 / numSquares - 12, y + 77 * 400 / numSquares - 12, 24, 24, null);

        g.setColor(Color.red);
        int width = screenSize / squareSize * 400 / numSquares;
        int height = screenSize / squareSize * 400 / numSquares;
        int drawX = x + player.getCol() * 400 / numSquares - screenSize / squareSize * 400 / numSquares / 2;
        int drawY = y + player.getRow() * 400 / numSquares - screenSize / squareSize * 400 / numSquares / 2;
        if (drawX + width > x + 400) {
            width = x + 400 - drawX;
        }
        if (drawY + height > y + 400) {
            height = y + 400 - drawY;
        }
        g.drawRect(drawX, drawY, width, height);
        g.fillOval(x + player.getCol() * 400 / numSquares - 5, y + player.getRow() * 400 / numSquares - 5, 10, 10);
        // g.drawRect(x+player.getCol()*400/numSquares-screenSize/squareSize*400/numSquares/2,y+player.getRow()*400/numSquares-screenSize/squareSize*400/numSquares/2,screenSize/squareSize*400/numSquares,screenSize/squareSize*400/numSquares);
    }

    public void setGoUp(boolean tf) {
        goUp = tf;
    }

    public void changeDirection() {
        goUp = !goUp;
    }

    public int getMouseCol(int mouseX, int mouseY) {
        if (mouseX > x && mouseX - x < 400) {
            if (mouseY > y && mouseY - y < 400) {
                return (int) ((mouseX - x) / 400.0 * numSquares);
            }
        }

        return -1;
    }

    public int getMouseRow(int mouseX, int mouseY) {
        if (mouseX > x && mouseX - x < 400) {
            if (mouseY > y && mouseY - y < 400) {
                return (int) ((mouseY - y) / 400.0 * numSquares);
            }
        }

        return -1;
    }

    public void setSquareSize(int size) {
        this.squareSize = size;
    }

}
