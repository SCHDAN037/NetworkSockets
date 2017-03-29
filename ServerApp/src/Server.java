/*
*	The logical model of the Server side of the project
*	@author Oliver Makins
*	@Version 29/03/2017
*/

package server;
import java.util.concurrent.ConcurrentLinkedQueue;


class Server {
	
	//private queue q; this is a shared concurrent queue which each 'user' writes to.
	private final ConcurrentLinkedQueue <Message>queue1;
	private final ConcurrentLinkedQueue <Message>queue2;
	private final User user1;
	private final User user2;

	Server(User u1, User u2) {
		queue1 = new ConcurrentLinkedQueue<Message>();
		queue2 = new ConcurrentLinkedQueue<Message>();
		user1 = u1;
		user2 = u2;
		// give the Users access to their own messages and those of the other user
		user1.setMasterQueue(queue1, queue2);
		user2.setMasterQueue(queue2, queue1);

		// let them loose into the wild
		user1.run();
		user2.run();
	}

} 
