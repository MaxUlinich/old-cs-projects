

public class Manager {

    private MyArrayList<ServerThread> threadList;
    private Game game;
    private boolean gameHasStarted;

  	
    public Manager(){
        

        threadList = new MyArrayList<ServerThread>();
        game = new Game();
        gameHasStarted = false;
        
      
   		
        
        
    }

    public void add(ServerThread thread){
        
        threadList.add(thread);
        game.addPlayer(getNumPlayers(),new Player(getNumPlayers()));
        //System.out.println(game.getMap().keySet().toDLList().toString());
        
        
    }

   
    
    
    public int getNumPlayers(){
        return threadList.size();
    }
    public void broadcast(Game game){//message){
        
        
        if(gameHasStarted){
            this.game = game;
        }
       //System.out.println("game from manager" + game.getMap().keySet().toDLList().toString());
        for(int i = 0; i < threadList.size(); i++){
            //call send(message) for each thread
            threadList.get(i).send(game);
        }
        try{
            Thread.sleep(10);
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }
    public void broadcast(Game game, ServerThread thread){//message){
     
        if(gameHasStarted){
            this.game = game;
        }
        //System.out.println("game from a client" + game.getMap().keySet().toDLList().toString());
        for(int i = 0; i < threadList.size(); i++){
            //call send(message) for each thread
            threadList.get(i).send(game);
        }
        try{
            Thread.sleep(10);
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }

    public void broadcastExcept(Game game, ServerThread except){
        if(gameHasStarted){
            this.game = game;
        }
        for(int i = 0; i < threadList.size(); i++){
            //call send(message) for each thread
            if(!threadList.get(i).equals(except)){
              threadList.get(i).send(game);
            }
        }
        try{
            Thread.sleep(10);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
  	
  	
    
    
  
    public Game getGame(){
        return game;
    }
    public boolean allStarted(){
        if(threadList.size()>0){
            for(int i = 0; i<threadList.size(); i++){
                if(!threadList.get(i).isStarted()){
                    return false;
                }
            }
            return true;
        }
        return false;
        
    }

    public void removeEnded(){
        for(int i = 0; i<threadList.size(); i++){
            
            if(threadList.get(i).isClosed()){
                threadList.remove(i);
                
            }
        }
    }
  
  	public boolean gameHasStarted(){
        return gameHasStarted;
    }
    
    public void startGame(){
        gameHasStarted = true;
        game.shuffle();
        game.deal();
        broadcast(game);
        

    }
    
}



