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
import java.nio.file.*;


class User extends Thread {
	private final BlockingQueue <Message>queue; // queue to send messages elsewhere

	// I will add objects for reading and writing once they are finished
	private Socket socket; // This is a connection to the User
	private final int id;
	private final PrintStream ps;
	private final BufferedReader reader;
	private String name; // the User's name
	private Thread fileReceiver_Thread;
	private Thread fileTransfer_Thread;

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

	public void startFileReceiverConnection (String filePath)
	{
		// ":send /media/image.png"
		Path fR_filePath = Paths.get( filePath );

		try {
			fileReceiver_Thread = new FileReceiver ( fR_filePath.toString() );
			fileReceiver_Thread.start();
		}
		catch (ClassNotFoundException ex) { ex.printStackTrace(); }
		catch (IOException ex) { ex.printStackTrace(); }
		catch (Exception ex) { ex.printStackTrace(); }
	}

	public void transferFileToClient(String hostname,int portnumber)
	{
		// String filename = line.split(" ")[1] ;
		// String hostname = userSocket.getInetAddress().getHostName();
		try {
			fileTransfer_Thread = new FileTransfer ( hostname,portnumber);
			fileTransfer_Thread.start();
		}
		catch (ClassNotFoundException ex) { ex.printStackTrace(); }
		catch (IOException ex) { ex.printStackTrace(); }
		catch (Exception ex) { ex.printStackTrace(); }
	}

	public void run() {
		String line;
		Message m;
		try {
			// get the User's name
			while (!reader.ready()) { sleep(49);}  // spin until the reader is ready
			name = reader.readLine();
			queue.put( new Message(name + " has connected", System.currentTimeMillis(), id) );

			// now send the messages to the server
			while (true) {
				while (!reader.ready()) { sleep(49);}  // spin until the reader is ready
				line = reader.readLine();
				// System.out.println(line);

				// write the line as a message to the queue
				if ( line.equals("") ) { continue; } // an error

				if( line.equals(":q") || line.equals(":quit")) {
					// for good etiquette the client should say that they are leaving
					queue.put(new Message(name + " has left the chat.", System.currentTimeMillis(), id));
					break;
				}

				// e.g user: ":send kitty.png"
				else if ( line.toUpperCase().contains(":SEND") ) {
					// SAVE FILE ON file_database
					String filename = line.substring( line.lastIndexOf(" ")+1 ) ;
					startFileReceiverConnection("file_database/"+filename);
					// System.out.println( line.substring(0, line.indexOf(":")) + " sending image transfer offer.." );
					m = new Message(line, System.currentTimeMillis(), id);
					queue.put(m); // add this to the queue
				}
				//e.g. user: ":Y"
				else if ( line.toUpperCase().contains(":Y") ) {
					String name = line.substring(0, line.indexOf(":")) ;
					System.out.println( name + " Accepted image offer!" );

					// // e.g. [00:00:00] name : :Y @hostname:127.0.0.1,port:2020
					// m = new Message(line, System.currentTimeMillis(), id);
					// queue.put(m); // add this to the queue

					String receiver = line.substring(line.indexOf("@")+1);
					String r_portnumber = receiver.substring( receiver.indexOf(":")+1, receiver.indexOf(",") ) ;
					String r_hostname = receiver.substring( receiver.lastIndexOf(":")+1) ;

					// TRANSFER FILE FROM SERVER TO EACH ACCEPTED CLIENT.

					transferFileToClient(r_hostname, Integer.parseInt(r_portnumber));
				}

				else {
					// We have now made sure it is a valid message, so we add it to the queue
					m = new Message(line, System.currentTimeMillis(), id);
	//				System.out.println("This is what you yped: " + m);
	//				System.out.println("This is the queue" + queue);
					queue.put(m); // add this to the queue
			}

			}
			// close all the streams and the socket when quitting or dying
			socket.close();
			reader.close();
			ps.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}


}
