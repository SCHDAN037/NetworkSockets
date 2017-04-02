import java.net.Socket;
import java.io.IOException;

public class User extends Thread{
	
	private Socket clientSocket;


	User(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	public void run(){
		try{
			while(true){
				
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}
}