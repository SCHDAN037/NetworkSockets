/**
*	ServerApp this is where the Server starts. has the main method.
*	@author Oliver Makins
*	@Version 02/04/2017
*/
package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;


public class ServerApp {
	public static void main(String[] args) {
		System.out.println("Server Starting");
		Socket messageSocket, userSocket;
		Scanner scanner = new Scanner(System.in);
		FileReceiver fileReceiver_Thread = null ;

		try {
			Server server = new Server(); // INSTANTIATE THE SERVER
			server.start();				  // START THE THREAD
			System.out.println("Created Server");

			// ServerSocket messageListenerServerSocket = new ServerSocket(2018);
			System.out.println("Please enter the socket you would like to listen on: ");
			int socket = Integer.parseInt(scanner.nextLine());
			// make a new socket to listen for messages
			ServerSocket userListenerServerSocket = new ServerSocket(socket);

			// Make a new socket to listen for file transfers
			try {
				fileReceiver_Thread = new FileReceiver ( "" );
				fileReceiver_Thread.start();
			}
			catch (ClassNotFoundException ex) { ex.printStackTrace(); }
			catch (IOException ex) { ex.printStackTrace(); }
			catch (Exception ex) { ex.printStackTrace(); }

			while (true) { // keep adding new users to the server
				// this is done by giving the server sockets, it will create the user
				// objects itself
				server.addNewUser(userListenerServerSocket.accept(),fileReceiver_Thread);
			}

		}
		catch (Exception e) { //MODIFIED FROM IOEXCEPTION FOR DEBUG
			e.printStackTrace();
		}
	}
}





			//two sockets:
			//one for listening for messages from all users
			//one for listening for new users to connect
			// messageSocket = messageListenerServerSocket.accept();

			// NewUserListener userListner = new NewUserListener(userSocket, messageSocket);
			// userListner.start();
			//Starts listening for users
