import javax.swing.JFrame;
import java.util.Scanner;


public class Runner {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean weirdGravity = false;
        boolean randomColors=false;
        boolean monitor=false;
        double bouncyness=95;
        boolean loud=false;

        Ball ball1 = new Ball();
        Ball ball2 = new Ball();
        Ball[] allBalls={ball1,ball2};


        try{
            System.out.println("Do you want weird gravity? (y/n) ");
            String yn=sc.next();
            if(yn.equals("Y")||yn.equals("y")){
                weirdGravity = true;
            }
        }catch(Exception e){}

        try{
            System.out.println("Do you want random colors? (y/n) ");
            String yn=sc.next();
            if(yn.equals("Y")||yn.equals("y")){
                randomColors = true;
            }
        }catch(Exception e){}

        
        
        
        try{
            System.out.println("Do you want sound (not recommended due to lag)? (y/n) ");
            String yn=sc.next();
            if(yn.equals("Y")||yn.equals("y")){
                loud = true;
            }
        }catch(Exception e){}
        
        try{
            System.out.println("How bouncy do you want the ball? (100 = perfectly elastic) ");
            bouncyness=sc.nextDouble();
            
        }catch(Exception e){bouncyness=95;}

        JFrame frame = new JFrame("BOUNCE");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Bounce canvas1 = new Bounce(weirdGravity,randomColors,monitor,bouncyness,loud);
        
        frame.add(canvas1);
        

        frame.pack();

        frame.setVisible(true);
        
        
        canvas1.move();
        
        

    }

}
