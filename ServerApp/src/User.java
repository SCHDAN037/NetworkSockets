/* 
 * This class contains all the info about a given user in a chat
 * it is alwasy made outside the Server, but the Server provisions the
 * User with all of it's data
 *
 * Author: Oliver Makins
 * Version: 29/03/2017
 */

package server;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.lang.Thread;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;



class User extends Thread {
	private ConcurrentLinkedQueue <Message>writeQueue;
	private ConcurrentLinkedQueue <Message>readQueue;
	// I will add objects for reading and writing once they are finished
	private Socket socket;
	private PrintStream ps;
	private BufferedReader re;
	
	User(Socket s) {
		socket = s;
		try {
			ps = new PrintStream(socket.getOutputStream());
			re = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void setMasterQueue( ConcurrentLinkedQueue <Message> w, ConcurrentLinkedQueue <Message> r) {
		assert( w != null );
		writeQueue = w;
		assert (r != null && !(r.equals(w))); // make sure it isn't messaging itself
		readQueue = r;
	}

	public void run() {
		// write messages from socket
		// read messages and send to them home
	}

	/** Method allows the user to send a message to the other users in the chat
	 */
	protected void write(Message m){
		// send the message
		assert(writeQueue.offer(m));
	}

	protected void read() {
		Message m = readQueue.poll();
		// then send it via the socket
	}
	


}
