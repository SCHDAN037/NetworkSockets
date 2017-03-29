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

		try { 
			ServerSocket Listener = new ServerSocket(2017);
			Socket s1 = Listener.accept();
			Socket s2 = Listener.accept();
			

			User u1 = new User(s1);
			User u2 = new User(s2);
			Server server = new Server(u1, u2);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
