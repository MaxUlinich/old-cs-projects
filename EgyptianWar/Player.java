import java.io.Serializable;

public class Player implements Serializable{
    private DLList<Card> cards;
    private int playerNumber;
    private boolean victory;
    public Player(int playerNum){
        playerNumber = playerNum;
        cards = new DLList<Card>();
        victory = false;
    }

    public int hashCode(){
        return playerNumber;
    }
   
    
    public void giveCard(Card c){
        
        cards.add(c);
       
    }

    public void giveCardBottom(Card c){
        cards.add(0,c);
    }
    public DLList<Card> getCards(){
        return cards;
    }
    public void setVictory(boolean tf){
        victory = tf;
    }
    public boolean getVictory(){
        return victory;
    }


    
    
}
