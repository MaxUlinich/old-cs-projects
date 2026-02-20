import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;

public class GridObject {
    private String name;
    private boolean square;
    private Color color;
    private BufferedImage img;
    private int squareSize;
    private Boolean gridLines = true;

    public GridObject(String name, Color rgb, int squareSize) {
        this.name = name;
        square = true;
        color = rgb;
        this.squareSize = squareSize;
        // this.gridLines = true;
        gridLines = true;
    }

    public GridObject(String name, String file, int squareSize) {
        this.name = name;
        square = false;
        this.squareSize = squareSize;
        try {
            img = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object o) {
        GridObject g = (GridObject) o;
        if (g.getName().equals(name)) {
            return true;
        }
        return false;
    }

    public void drawMe(Graphics g, int x, int y) {
        if (square) {
            g.setColor(color);
            g.fillRect(x, y, squareSize, squareSize);
        } else {
            g.drawImage(img, x, y, squareSize, squareSize, null);

        }
        if (gridLines) {
            g.setColor(Color.black);
            g.drawRect(x, y, squareSize, squareSize);
        }
    }

    public Color getColor() {
        return color;
    }

    public void setSquareSize(int size) {
        this.squareSize = size;
    }

    public void changeGridLines(Boolean b) {
        gridLines = b;
    }
}
