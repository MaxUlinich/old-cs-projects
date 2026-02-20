import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Location {
    private String name;
    private String abr;
    private int x, y;

    public Location(String name, String abr, int x, int y) {
        this.name = name;
        this.abr = abr;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public String getAbr() {
        return abr;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return name + " - " + abr.toUpperCase();
    }

    public int hashCode() {
        return 676 * (abr.charAt(0) - 97) + 26 * (abr.charAt(1) - 97) + abr.charAt(2) - 97;
    }

    public boolean equals(Object o) {
        Location data = (Location) o;
        if (data.getAbr().equals(abr)) {
            return true;
        }
        return false;
    }

    public void drawMe(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x - 5, y - 5, 10, 10);
        g.setColor(Color.black);
        g.drawOval(x - 5, y - 5, 10, 10);
        g.setFont(new Font("Arial", 0, 15));
        g.drawString(abr.toUpperCase(), x + 4, y);
    }

}
