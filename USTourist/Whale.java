import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Whale implements Runnable, Serializable {

    private transient MyHashTable<Location, GridObject> table;
    private int row, col, squareSize, numSquares;
    // private BufferedImage img;

    public Whale(MyHashTable<Location, GridObject> grid, int r, int c, int ss, int numSquares) {
        table = grid;
        row = r;
        col = c;
        squareSize = ss;
        this.numSquares = numSquares;
        // try{
        // img = ImageIO.read(new File(file));
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // y = r*ss;
        // x = c*ss;
    }

    public void run() {
        while (true) {
            int direction = (int) (4 * Math.random()) + 1;

            if (direction == 1) {
                if (row > 0 && table.get(new Location(row - 1, col)).get(1)
                        .equals(new GridObject("water", Color.black, squareSize))) {
                    row--;
                    // y-=squareSize;
                } else {
                    continue;
                }
            }
            if (direction == 2) {
                if (row < numSquares - 1 && table.get(new Location(row + 1, col)).get(1)
                        .equals(new GridObject("water", Color.black, squareSize))) {
                    row++;
                    // y+=50;
                } else {
                    continue;
                }
            }
            if (direction == 3) {
                if (col < numSquares - 1 && table.get(new Location(row, col + 1)).get(1)
                        .equals(new GridObject("water", Color.black, squareSize))) {
                    col++;
                    // x+=50;
                } else {
                    continue;
                }
            }
            if (direction == 4) {
                if (col > 0 && table.get(new Location(row, col - 1)).get(1)
                        .equals(new GridObject("water", Color.black, squareSize))) {
                    col--;
                    // x-=50;
                } else {
                    continue;
                }
            }

            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // public BufferedImage getImg(){
    // return img;
    // }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int c) {
        col = c;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setSquareSize(int size) {
        this.squareSize = size;
    }

    public void setTable(MyHashTable<Location, GridObject> grid) {
        table = grid;
    }
}
