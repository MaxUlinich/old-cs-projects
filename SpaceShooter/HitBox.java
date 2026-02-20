public class HitBox {

    public double x,y,width,height;

    public HitBox(double imageX, double imageY, double imageWidth, double imageHeight, double boxWidth, double boxHeight){
        x = imageX + (imageWidth-boxWidth)/2;
        y = imageY + (imageHeight-boxHeight)/2;
        this.width = boxWidth;
        this.height = boxHeight;
    }
    
    public boolean collideWithHitBox(HitBox box){
        if(box.x>x+width || x>box.x+box.width){
            return false;
        }  
        if(box.y>y+height || y>box.y+box.height){
            return false;
        }
        
        return true;
    }
}
