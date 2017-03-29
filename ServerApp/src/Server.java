/*
*	The logical model of the Server side of the project
*	@author Oliver Makins
*	@Version 29/03/2017
*/

package server;
import java.util.concurrent.ArrayBlockingQueue;


class Server {
	
	//private queue q; this is a shared concurrent queue which each 'user' writes to.
	private final ArrayBlockingQueue <Message>queue1;
	private final ArrayBlockingQueue <Message>queue2;
	private final User user1;
	private final User user2;

	Server(User u1, User u2) {
		// I have not differentiated which is the main server queue
		// and which is the client queue since with functionality for only
		// two users it is irrelevant
		queue1 = new ArrayBlockingQueue<Message>(1000);
		queue2 = new ArrayBlockingQueue<Message>(1000);
		user1 = u1;
		user2 = u2;
		// give the Users access to their own messages and those of the other user
		user1.setMasterQueue(queue1, queue2);
		user2.setMasterQueue(queue2, queue1);
		System.out.println("Initialised Users and Queues");
		

		// let them loose into the wild
		user1.start();
		user2.start();
		System.out.println("Chat running");
	}

} 
