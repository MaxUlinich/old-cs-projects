import java.io.Serializable;

public class Tourist implements Serializable {
    private int r, c;

    // private Screen sc;
    // private MyHashTable<Location, GridObject> grid;
    public Tourist(int r, int c) {
        this.r = r;
        this.c = c;
        // this.sc = sc;
        // grid = sc.getGrid();
        // System.out.println(grid);

    }

    public int getRow() {
        return r;
    }

    public void setRow(int r) {
        this.r = r;
    }

    public void setCol(int c) {
        this.c = c;
    }

    public int getCol() {
        return c;
    }

    // public void moveUp(){
    // if(r>0 && !grid.get(new Location(r-1,c)).get(1).getName().equals("water")&&
    // grid.get(new Location(r-1,c)).size()<3){
    // r--;
    // }
    // }
    // public void moveDown(){
    // if(r<sc.numSquares-1 && !grid.get(new
    // Location(r+1,c)).get(1).getName().equals("water")&& grid.get(new
    // Location(r+1,c)).size()<3){
    // r++;
    // }
    // }
    // public void moveLeft(){
    // if(c>0 && !grid.get(new Location(r,c-1)).get(1).getName().equals("water") &&
    // grid.get(new Location(r,c-1)).size()<3){
    // c--;
    // }
    // }
    // public void moveRight(){
    // if(c<sc.numSquares-1 && !grid.get(new
    // Location(r,c+1)).get(1).getName().equals("water")&& grid.get(new
    // Location(r,c+1)).size()<3){
    // c++;
    // }
    // }

}
