/*
*	ServerApp this is where the Server starts. has the main method.
*	@author Daniel Schwartz
*	@Version 19/06/2016
*/
package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServerApp {
	public static void main(String[] args) {
		System.out.println("Server Starting");
		Socket messageSocket, userSocket;
		// HERE SO THAT THEY ARE NOT NULL ;; FOR DEBUGGING
		

		try { 
			Server server = new Server(); // INSTANTIATE THE SERVER
			server.start();				  // START THE THREAD
			System.out.println("Created Server");
			// COMMENTED OUT FOR THE DEBUG
			ServerSocket messageListenerServerSocket = new ServerSocket(2018);
			ServerSocket userListenerServerSocket = new ServerSocket(2017);

			//two sockets:
			//one for listening for messages from all users
			//one for listening for new users to connect
			// messageSocket = messageListenerServerSocket.accept();
			
			// NewUserListener userListner = new NewUserListener(userSocket, messageSocket);
			// userListner.start();
			//Starts listening for users


			while (true) { // keep adding new users to the server
				// this is done by giving the server sockets, it will create the user
				// objects itself
				server.addNewUser(userListenerServerSocket.accept()); 
			}

		}
		catch (Exception e) { //MODIFIED FROM IOEXCEPTION FOR DEBUG
			e.printStackTrace();
			System.out.println("Error in line 35 of Server");
		}
	}
}
