import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.Random;



public class Bounce extends JPanel implements KeyListener,MouseListener,MouseMotionListener{

    double friction=.99;
    Audio sound;
    boolean loud = false;
    int width=1440;
    int height=900;
    double xvel=5;
    double yvel=0;
    boolean weirdGravity=false;
    boolean randomColors=false;
    boolean monitor=false;
    double Ygravity=1;
    double Xgravity=0;
    boolean mousedown=false;
    float vol;
    double mouseX;
    double mouseY;
    int ballWidth = 20;
    int ballHeight=20;
    Random rand = new Random();
    double x=rand.nextDouble(1400);
    double y=rand.nextDouble(800);
    double bouncyness=95;
    int colorindex=0;
    double throwPower=1.2; //higher number = more power
    Color[] colors={Color.RED,Color.GREEN,Color.BLUE,Color.CYAN,Color.MAGENTA,Color.BLACK};

    public Bounce(boolean weirdGravity,boolean randomColors,boolean monitor,double bouncyness,boolean loud){
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.weirdGravity = weirdGravity;
        this.randomColors=randomColors;
        this.monitor=monitor;
        if(this.monitor){
            this.height=1440;
            this.width=2560;
        }
        this.bouncyness=bouncyness;
        this.loud=loud;
        if(loud){
            sound = new Audio();
        }
    }
    

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==32){
            do{
            x=rand.nextDouble(width);
            y=rand.nextDouble(height);
            xvel=rand.nextDouble(10);
            yvel=0;
            Ygravity=rand.nextDouble(1);
            if(weirdGravity){
                Ygravity=rand.nextDouble(1.5);
                Xgravity=rand.nextDouble(1.5);
                Ygravity-=.75;
                Xgravity-=.75;
            }
            ballWidth = rand.nextInt(500);
            ballHeight = ballWidth;
            if (randomColors){
                colorindex=rand.nextInt(6);
            }
            }while(x<=0||x+ballWidth>=width||y<=0||y+ballHeight>=height);

        } 
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void mousePressed(MouseEvent e){
        mousedown=true;
       
        mouseX=e.getX();
        mouseY=e.getY();
           
    }
    
    public void mouseReleased(MouseEvent e) {
        mousedown=false;
        xvel=(e.getX()-mouseX)*throwPower;
        yvel=(e.getY()-mouseY)*throwPower;
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseDragged(MouseEvent e){
        mouseX=e.getX();
        mouseY=e.getY();
    }
    public void mouseMoved(MouseEvent e){}






    public Dimension getPreferredSize(){
        return new Dimension(width,height);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (weirdGravity){
            g.setColor(Color.BLUE);
            g.drawLine(width/2,height/2,(int)(width/2-Xgravity*100),(int)(height/2-Ygravity*100));
            g.setColor(Color.RED);
            g.drawLine(width/2,height/2,(int)(width/2+Xgravity*100),(int)(height/2+Ygravity*100));
        }
        g.setColor(colors[colorindex]);
        g.fillOval((int)x,(int)y,ballWidth,ballHeight);
        g.fillOval(width,height,10,10);
        
        
        

    }

    public void move(){
        while(true){
            this.height = this.getHeight();
            this.width = this.getWidth();
            try{
                Thread.sleep(10);
                }
            catch(Exception e){
                    e.printStackTrace();
                }
             
            x+=xvel;
            y+=yvel;
        
            
            

            if(y+ballHeight>height){
                
                if(yvel>1 && loud){
                    
                    

                    sound.playSound();
                }
                
                yvel=yvel*(-bouncyness/100.0);
                y=height-ballHeight;

                if(yvel<.5 && yvel>-.5){
                    
                    xvel*=friction;
                }
            }
            if(y<0){

                
                if(yvel<-1 && loud){
                    
                    

                    sound.playSound();
                }
                yvel=yvel*(-bouncyness/100.0);
                y=0;
                if(yvel<.5 && yvel>-.5){
                    
                    xvel*=friction;
                }

            }
            if (x+ballWidth>width){
                
                if(xvel>1 && loud){
                    

                    sound.playSound();
                }
                
                xvel=xvel*(-bouncyness/100.0);
                x=width-ballWidth;



                if(xvel<.5 && xvel>-.5){
                    
                    yvel*=friction;
                }
            }
            if (x<0){
                
                if(xvel<-1 && loud){
                    
                    

                    sound.playSound();
                }
                
                xvel=xvel*(-bouncyness/100.0);
                x=0;

                if(xvel<.5 && xvel>-.5){
                    
                    yvel*=friction;
                }
            }
            
            yvel+=Ygravity;
            xvel+=Xgravity;
            if (mousedown){
                x=mouseX-ballWidth/2;
                y=mouseY-ballHeight/2;
            }

            repaint();

        }
    }
    
}
