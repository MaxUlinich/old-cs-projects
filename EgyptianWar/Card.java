import java.io.Serializable;

public class Card implements Serializable{
    private double x=250;
    private double y=500;
    private String suit, symbol;
    private boolean visible;
    private int value;
    public Card(int value, String symbol, String suit){
        x=250;
        y=500;
        visible = true;
        this.value = value;
        this.symbol = symbol;
        this.suit = suit;
        
    
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public String getSuit(){
        return suit;
    }
    public String getSymbol(){
        return symbol;
    }
    public boolean isVisible(){
        return visible;
    }
    public void setVisible(boolean tf){
        visible = tf;
    }
    public int getValue(){
        return value;
    }


}
