/* 
 * This class contains all the info about a given user in a chat
 * it is alwasy made outside the Server, but the Server provisions the
 * User with all of it's data
 *
 * Author: Oliver Makins
 * Version: 29/03/2017
 */

package server;

import java.util.concurrent.ArrayBlockingQueue;
import java.lang.Thread;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;



class User extends Thread {
	private ArrayBlockingQueue <Message>ownQueue; // queue with messages for the slient
	private ArrayBlockingQueue <Message>serverQueue; // queue to send messages elsewhere
	
	// I will add objects for reading and writing once they are finished
	private Socket socket; // This is a connection to the User
	private Writer writer;
	private Listener listener;
	
	User(Socket s) {
		socket = s;
	}

	protected void setMasterQueue( ArrayBlockingQueue <Message> s, ArrayBlockingQueue <Message> o) {
		assert( o != null );
		ownQueue = o;
		assert (s != null && !(o.equals(s))); // make sure it isn't messaging itself
		serverQueue = s;

		try {
		writer =  new Writer ( ownQueue, new PrintStream(socket.getOutputStream()) );
		listener = new Listener ( serverQueue, new BufferedReader(new InputStreamReader(socket.getInputStream()))) ;

		}
		catch (IOException e) { // MODIFIED FROM AND IOException
			e.printStackTrace();
			System.out.println(e);
			System.out.println("Line 45 in user");
		}



		//DEBUG: I am testing this to run without sockets, to see if
		//the server logic is correct
		//writer =  new Writer ( ownQueue, new PrintStream(System.out));
		//listener = new Listener ( serverQueue, new BufferedReader(new InputStreamReader(System.in))) ;
	}

	public void run() {
		// run the basic Threads -- these will read and write everything to eachother
		// they just dump to the screen
		listener.start();
		System.out.println("Listener is running");
		writer.start();
		System.out.println("Writer is running");
		// write messages from socket
		// read messages and send to them home
	}

	/** Method allows the user to send a message to the other users in the chat
	 */
	protected void write(Message m){
		// send the message
	}

	protected void read() {
		// then send it via the socket
	}
	


}
