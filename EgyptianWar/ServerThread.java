import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;

public class ServerThread implements Runnable{
    
    
    private Socket socket;
    private ObjectInputStream in;
	private ObjectOutputStream out;
   
    private Manager manager;
    
    private boolean started;
    private boolean closed;
    public ServerThread( Socket s,Manager m){
        
        socket = s;
        manager = m;
        started = false;
        closed = false;

        try{

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e){

        }

    }
    
    public void send(Game g){
        try{
            out.writeObject(g);  
              
          }catch(Exception e){
  
          }
     }
    public void run(){
        try{
            out.writeObject(manager.getNumPlayers()); //give each player a number
            
            while(!closed){
                Game tempGame = (Game) in.readObject();
                if(!started){
                    started = true;
                }
                //System.out.println(tempGame);
                if(tempGame == null){
                    //System.out.println("this client should have closed");
                    closed = true;
                }else{
                    manager.broadcast(tempGame,this); 
                } 
            }
        }catch(Exception e){
            //e.printStackTrace();
            closed = true;
        }
            
        
    }
    public boolean isStarted(){
        return started;
    }
    public boolean isClosed(){
        return closed;
    }
}
