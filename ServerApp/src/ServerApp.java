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
			// COMMENTED OUT FOR THE DEBUG
			ServerSocket messageListenerServerSocket = new ServerSocket(2017);
			ServerSocket userListenerServerSocket = new ServerSocket(2017);

			//two sockets:
			//one for listening for messages from all users
			//one for listening for new users to connect
			messageSocket = messageListenerServerSocket.accept();
			userSocket = userListenerServerSocket.accept();
			
			NewUserListener userListner = new NewUserListener(userSocket, messageSocket);
			//Starts listening for users
			userListner.start();

			User u1 = new User(messageSocket);
			System.out.println("Created user1");
			User u2 = new User(messageSocket);
			System.out.println("Created user2");
			Server server = new Server(u1, u2);
			System.out.println("Created Server");
		}
		catch (Exception e) { //MODIFIED FROM IOEXCEPTION FOR DEBUG
			e.printStackTrace();
			System.out.println("Error in line 35 of Server");
		}
	}
}
