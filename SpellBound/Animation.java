public class Animation {
    private Piece p;
    private double Xspeed;
    private double XSquaredTerm;
    private double XTerm;
    private double constant;
    private double endX;
    public Animation(Piece p, double Xspeed, double endX, double XSquaredTerm, double Xterm, double constant){
        this.p = p;
        this.Xspeed = Xspeed;
        
        this.endX = endX;
        this.XSquaredTerm=XSquaredTerm;
        this.XTerm = Xterm;
        this.constant = constant;

    }
    
    public Piece getPiece(){
        return p;
    }
    public double getXspeed(){
        return Xspeed;
    }  
      
    public double getEndX(){
        return endX;
    }  
    public void setY(){
        //System.out.println(p.getX()+","+(p.getX()*p.getX()*XSquaredTerm + p.getX()*XTerm + constant));
        p.setY(p.getX()*p.getX()*XSquaredTerm + p.getX()*XTerm + constant);
    }
}
