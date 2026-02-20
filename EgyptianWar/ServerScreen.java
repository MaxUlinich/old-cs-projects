import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.MouseEvent;

import java.io.*;
import java.net.*;



public class ServerScreen extends JPanel {

	
	
    private Manager manager;
    


	
	
	public ServerScreen(){
		this.setLayout(null);
		manager= new Manager();
       
		


		
	}




	public Dimension getPreferredSize() {


		return new Dimension(800,600);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
        try {
            g.drawString("IP: " + InetAddress.getLocalHost().getHostAddress(), 50,100);
        } catch (UnknownHostException ex) {
           // System.out.println("Could not find IP address for this host");
        }
		manager.removeEnded();
		if(!manager.gameHasStarted() && manager.allStarted()){
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
			manager.startGame();
			
		}


		if(manager.getNumPlayers() == 1){
			g.drawString( "1 Player", 50,150);
		}else{
			g.drawString( manager.getNumPlayers() + " Players", 50,150);
		}
		g.drawString("Run the Server again if you would like to add/remove players after pressing \"start\".",50,200);
        g.drawString("Game can be played with more than 2 clients.",50,225);
        

    
	}
    
   		
          
     
	
		

	

	// public void startServer() throws IOException {
	// 	int portNumber = 1023;


	// 	ServerSocket server = new ServerSocket(portNumber);
	// 	Socket socket = server.accept();
	// 	out = new PrintWriter(socket.getOutputStream(), true);
	// 	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


	// 	out.println("You are connected to the server!");


	// }
    
	public void connect() throws IOException{


		int portNumber = 1024;

        
		//ServerSocket server = new ServerSocket(portNumber);
		//Socket socket = server.accept();
		// out = new ObjectOutputStream(socket.getOutputStream());
		// in = new ObjectInputStream(socket.getInputStream());
        //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		
		//repaint();

		
		//listens for inputs
		try {
			ServerSocket server = new ServerSocket(portNumber);
            while(true){
                	System.out.println("Waiting for a connection");
        
                	//Wait for a connection.
        
                   
                	Socket socket = server.accept();
                	System.out.println("accepted");
        
                	//Once a connection is made, run the socket in a ServerThread.
                	ServerThread serverThread = new ServerThread(socket,manager);
                    manager.add(serverThread);
                    Thread thread = new Thread(serverThread);
                	thread.start();

        

		

			}
		} catch (UnknownHostException e) {
			//System.err.println("Host unkown: " + hostName);
			e.printStackTrace();
			//System.exit(1);
		} catch (IOException e) {
			//System.err.println("Couldn't get I/O for the connection to " + hostName);
			e.printStackTrace();
			//System.exit(1);
		}//catch(ClassNotFoundException e ){
			//System.exit(1);
		//}
	}


}





