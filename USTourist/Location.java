public class Location {

    private int row, col;

    public Location(int r, int c) {
        row = r;
        col = c;
    }

    public boolean equals(Object o) {
        Location l = (Location) o;
        if (l.getRow() == row && l.getCol() == col) {
            return true;
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int hashCode() {
        return row * 1000 + col;
    }
}