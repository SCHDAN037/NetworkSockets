import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;

public class ServerApp{

	ArrayList<User> users;

	public static void main(String[] args) {
		System.out.println("Starting server...");
		ServerSocket server;
		Socket clientSocket;

		try {
			server = new ServerSocket(2017);

			UserListener listener = new UserListener(server);
			
			


			ServerInput read = new ServerInput();
			ServerOutput write = new ServerOutput();
			
			read.start();
			write.start();
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}
}