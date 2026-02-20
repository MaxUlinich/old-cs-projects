import java.io.Serializable;


public class Game implements Serializable{
    
   
    
    private MyArrayList<Card> deck;
    private DLList<Player> players;




    private MyHashMap<Integer,Player> map;
    private boolean slapped;
    private boolean taken;
    private int activePlayerNum;
    private int turnDuration;
    private int canTake;
    private boolean gameOver;
    private int deckSize;
    private int previousPlayerNum;
    public Game(){
        activePlayerNum = 1;
        previousPlayerNum = 0;
        canTake = 0;
        deck = new MyArrayList<Card>();
        map = new MyHashMap<Integer, Player>();
        players = new DLList<Player>();
        createDeck();
        deckSize = deck.size();
        
    }
    
    public MyArrayList<Card> getDeck(){
        return deck;
    }

    private void createDeck(){
       deck = new MyArrayList<Card>();
       for(int i = 0; i<9; i++){
        //for(int i = 0; i<5; i++){
            deck.add(new Card(i+2,(i+2)+"","spade"));
            deck.add(new Card(i+2,(i+2)+"", "club"));
            deck.add(new Card(i+2,(i+2)+"","heart"));
            deck.add(new Card(i+2,(i+2)+"", "diamond"));
            //System.out.println(i);
       }
       deck.add(new Card(11,"J","spade"));
       deck.add(new Card(11,"J","heart"));
       deck.add(new Card(11,"J","club"));
       deck.add(new Card(11,"J","diamond"));

       deck.add(new Card(12,"Q","spade"));
       deck.add(new Card(12,"Q","heart"));
       deck.add(new Card(12,"Q","club"));
       deck.add(new Card(12,"Q","diamond"));

      deck.add(new Card(13,"K","spade"));
       deck.add( new Card(13,"K","heart"));
       deck.add( new Card(13,"K","club"));
       deck.add( new Card(13,"K","diamond"));

       deck.add( new Card(14,"A","spade"));
       deck.add(  new Card(14,"A","heart"));
       deck.add( new Card(14,"A","club"));
       deck.add( new Card(14,"A","diamond"));

    
       
   }
   public void shuffle(){
   
    for(int i = 0; i < deck.size(); i++){
         int loc = (int)(Math.random()*deck.size());
         Card temp = deck.get(i);
         deck.set(i, deck.get(loc));
         deck.set(loc,temp);
    }
}

   public void addPlayer(int i,Player p){
        map.put(i,p);
        //System.out.println(map.get(i));
        players.add(p);
   }
   public void deal(){
        for(int i = 0; i<deck.size(); i++){
            
            Card card = deck.get(i);

            card.setVisible(false);
            players.get(i%players.size()).giveCard(card);
            
        }
        deck.clear();
   }

   public Player getPlayerFromNumber(int num){
        

        return map.get(num);
   }
   public MyHashMap getMap(){
        return map;
   }

   public boolean placeCard(int playerNum){
        
        if(activePlayerNum == playerNum){
            if(getPlayerFromNumber(playerNum)!=null && getPlayerFromNumber(playerNum).getCards().size()>0){
                Card card = getPlayerFromNumber(playerNum).getCards().remove(getPlayerFromNumber(playerNum).getCards().size()-1);
                card.setVisible(true);
                deck.add(card);
                slapped = false;
                taken = false;
                previousPlayerNum = activePlayerNum;
                
                
                if(card.getValue()==11 || card.getValue()==12 || card.getValue()==13 || card.getValue()==14){ // if you place a face card, set up a new duration for the next player
                    canTake = playerNum;
                    turnDuration = card.getValue()-10;
                    nextPlayer();
                }else if(turnDuration>0){ // if you don't place a face card but you have to--> reduce the number of chances you have
                    turnDuration--;
                    if(getPlayerFromNumber(activePlayerNum).getCards().size() == 0){ // if you run out of cards --> next player's turn
                        nextPlayer();
                    }
                    if(activePlayerNum == canTake){ // if the person who placed the original face card is going, it means that they won
                        stopGame(getPlayerFromNumber(activePlayerNum));
                    }
                    if(turnDuration == 0){ //if you run out of chances --> no one's turn
                        activePlayerNum = 0; //no one can place a card
                    }
                }else{ //don't place face, don't need to
                    nextPlayer();
                    if(activePlayerNum == previousPlayerNum){
                        stopGame(getPlayerFromNumber(activePlayerNum));
                    }
                }
            
                return true;
                
            }
        }
        return false;
   }

   public void takeDeck(int playerNum){
        Player player = getPlayerFromNumber(playerNum);
        for(int i = 0; i<deck.size(); i++){
            deck.get(i).setVisible(false);
            player.giveCardBottom(deck.get(i));
            //player.giveCard(deck.get(i));
            taken = true;

        }
        activePlayerNum = playerNum;
        deck.clear();
        turnDuration = -1;
        previousPlayerNum = 0;
        canTake = 0;
        checkVictory();
        
   }
   public boolean takeDeckEnforced(int playerNum){
        if(canTake == playerNum && turnDuration == 0){
            takeDeck(playerNum);
            return true;
        }
        return false;
   }

   public boolean slap(int playerNum){
        boolean shouldTake = false;
        if(deck.size()>=2){ //only check for a combination if you have at least 2 cards
            if(deck.get(deck.size()-1).getValue() == deck.get(deck.size()-2).getValue()){ //pair
                shouldTake = true;
            }else if(deck.size()>=3 && deck.get(deck.size()-1).getValue() == deck.get(deck.size()-3).getValue()){ //sandwich
                shouldTake = true;
            }else if(deck.size()>=4 && deck.get(deck.size()-1).getValue() == deck.get(deck.size()-4).getValue()){ //double sandwich
                shouldTake = true;
            }else if(deck.size()>=4 && deck.get(0).getValue() == deck.get(deck.size()-1).getValue()){ //top bottom
                shouldTake = true;
            }else if(deck.get(deck.size()-1).getValue() == 13){ //if top card is king
                if(deck.get(deck.size()-2).getValue() == 12){ //marriage
                    shouldTake = true;
                }if(deck.size()>=3 && deck.get(deck.size()-3).getValue() == 12){ //divorce
                    shouldTake = true;
                }
            }else if(deck.get(deck.size()-1).getValue() == 12){ //if top card is Queen
                if(deck.get(deck.size()-2).getValue() == 13){ //marriage
                    shouldTake = true;
                }if(deck.size()>=3 && deck.get(deck.size()-3).getValue() == 13){ //divorce
                    shouldTake = true;
                }
            }
        }




        if(shouldTake){
            takeDeck(playerNum);
            taken = false;
            slapped = true;
            activePlayerNum = playerNum;
            
        }
        return shouldTake;




   }

   public boolean isSlapped(){
        return slapped;
   }
   public boolean isTaken(){
        return taken;
   }

   public void nextPlayer(){
        activePlayerNum++;
        if(activePlayerNum>players.size()){
            activePlayerNum = 1;
        }
        if(getPlayerFromNumber(activePlayerNum).getCards().size() == 0){
            nextPlayer();
        }
   }

   public int getActivePlayerNum(){
        return activePlayerNum;
   }
   public int getTurnDuration(){
        return turnDuration;
   }
   public int getWhoShouldTake(){
        if(activePlayerNum == 0){
            return canTake;
        }else{
            return -1;
        }
   }

   public boolean checkVictory(){
    for(int i = 0; i<players.size(); i++){
        if(players.get(i).getCards().size() == deckSize){
            //victory
            stopGame(players.get(i));
            return true;
        }
    }
    return false;
   }
   public void stopGame(Player p){
        p.setVictory(true);
        gameOver = true;
   }

   public void restart(){
        for(int i = 0; i<players.size(); i++){
            players.get(i).setVictory(false);
            players.get(i).getCards().clear();
        }
        canTake = 0;
        previousPlayerNum = 0;
        activePlayerNum = 1;
        turnDuration = -1;
        createDeck();
        shuffle();
        deal();
        slapped = false;
        taken = false;
        gameOver = false;

   }

   public boolean isGameOver(){
        return gameOver;
   }

}
