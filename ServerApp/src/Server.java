/*
*	The logical model of the Server side of the project
*	@author Oliver Makins
*	@Version 29/03/2017
*/

package server;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.net.Socket;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.lang.InterruptedException;

class Server extends Thread {

	//private queue q; this is a shared concurrent queue which each 'user' writes to.
	private final BlockingQueue <Message>queue;
	private final CopyOnWriteArrayList <User>users;
	private int id = 1; 			// unique identifier for each user

	Server(){

		// Create the queue of all the messages
		queue = new ArrayBlockingQueue<Message>(1000);

		// make a list of all the users in the chat
		users = new CopyOnWriteArrayList<User>();
		// give the Users access to their own messages and those of the other user
		System.out.println("Initialised Users and Queues");


		// let them loose into the wild
		System.out.println("Chat running");
	}


	public void run () {
		User u;
		Message m;

		while (true) { // loop to keep getting new messages

			try {
				m = queue.take(); // wait for the new message
			} catch (InterruptedException e) { e.printStackTrace(); continue; } // keep going

			for ( int i = 0; i < users.size() ; i++ ) { // loop through all the users and send them the message
				u = users.get(i);
				if ( !u.isAlive()) { // check if the thread is alive
					users.remove(i); // remove the dead threads
					i -= 1; // so that we actually go to the next element
				}

				if ( u.getID() != m.getUserID()) {

					u.write(m); // write the message to the user

					 // don't send the client its own messages
				}

			}
		}
	}


	protected void addNewUser(Socket s,FileReceiver fileReceiver_Thread) {
		// increment the User's id
		try {
			User u = new User(s, queue, id, fileReceiver_Thread); // make another user to the chat
		u.start(); // start the thread
		users.add(u); // add it

		/** add this functionality if we add usernames on the server; this will print out
		 * who is online
		 */
		/*
		for ( int i = 0; i < id ; i++ ) { // tell the user who else is in the chat
		}
		*/

		id += 1;

		} catch (IOException e) {e.printStackTrace();}
	}

}
