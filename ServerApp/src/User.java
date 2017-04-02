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
import java.util.concurrent.BlockingQueue;
import java.lang.Thread;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;



class User extends Thread {
	private final BlockingQueue <Message>queue; // queue to send messages elsewhere
	
	// I will add objects for reading and writing once they are finished
	private Socket socket; // This is a connection to the User
	private Writer writer;
	private Listener listener;
	private final int id;
	private final PrintStream ps;
	private final BufferedReader reader;
	
	User(Socket s, BlockingQueue <Message> q, int i) throws IOException { 
		socket = s;
		queue = q;
		id = i;
		ps = new PrintStream(socket.getOutputStream()) ;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}


	/** this method write back to the client
	 */
	protected void write ( Message m) {
		// just dump it to the client
		ps.println(m.toString());
	}

	protected int getID() { return id; } 

	public void run() {
		String line;
		Message m;
		try {
			while (true) {
				while (!reader.ready()) { wait(49);}  // spin until the reader is ready
				line = reader.readLine();
				// System.out.println(line);

				// write the line as a message to the queue
				if ( line.equals("") ) { continue; } // an error

				if( line.equals("q")) {
					// for good etiquette the client should say that they are leaving
					// we can implement that later on the server where the quit message is more
					// precise to require that
					break;
				}

				// We have now made sure it is a valid message, so we add it to the queue
				m = new Message(line, System.currentTimeMillis(), id);
//				System.out.println("This is what you yped: " + m);
//				System.out.println("This is the queue" + queue);
				queue.put(m); // add this to the queue

			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}


}
