/*
*	Hello World
*	@author Daniel Schwartz
*	@Version 19/06/2016
*/
package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServerApp {
	public static void main(String[] args) {
		System.out.println("Hello World");
		Socket s1, s2;
		// HERE SO THAT THEY ARE NOT NULL ;; FOR DEBUGGING
		s1 = new Socket();
		s2 = new Socket();

		try { 
			// COMMENTED OUT FOR THE DEBUG
			ServerSocket Listener = new ServerSocket(2017);
			s1 = Listener.accept();
			s2 = Listener.accept();
			

			User u1 = new User(s1);
			System.out.println("Created user1");
			User u2 = new User(s2);
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
