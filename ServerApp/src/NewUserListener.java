/**
*
*
*/

package server;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class NewUserListener extends Thread{

	private Socket userSocket, messageSocket;
	private final BufferedReader reader;

	public NewUserListener (Socket userSocket, Socket messageSocket) throws IOException {
		this.userSocket = userSocket;
		this.messageSocket = messageSocket;
		reader = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
	}

	public void run(){
		while (true){

		}
	}
}