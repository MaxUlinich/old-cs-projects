import java.util.Random; 

public class Ball {
    public double friction=.99;
    public Audio sound;
    public boolean loud = false;
    public int width=1440;
    public int height=900;
    public double xvel=5;
    public double yvel=0;
    public boolean weirdGravity=false;
    public boolean randomColors=false;
    public boolean monitor=false;
    public double Ygravity=1;
    public double Xgravity=0;
    public boolean mousedown=false;
    public float vol;
    public double mouseX;
    public double mouseY;
    public int ballWidth = 20;
    public int ballHeight=20;
    public Random rand = new Random();
    public  double x=rand.nextDouble(1400);
    public double y=rand.nextDouble(800);
    public double bouncyness=95;
    public int colorindex=0;
    public double throwPower=1.2;
    
    
}
