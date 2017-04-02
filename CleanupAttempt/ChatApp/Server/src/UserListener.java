import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class UserListener extends Thread{
	
	Socket listeningSocket;
	ServerSocket server;

	UserListener(ServerSocket server){
		this.server = server;
	}

	public void run(){
		
		try{
			while(true){
				listeningSocket = server.accept();
				User user = new User(listeningSocket);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}