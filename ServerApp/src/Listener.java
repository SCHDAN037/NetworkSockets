/** The listener listens for data from the client and writes it to the
 * 	main server queue for the rest of the of the chat to read
 *	@Author Oliver Makins
 *	@Version 29/03/2017
 */


package server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;



public class Listener extends Thread {

	private final BufferedReader reader;
	private final ArrayBlockingQueue <Message>queue;

	Listener(ArrayBlockingQueue <Message> q, BufferedReader r) {
		reader = r;
		assert ( reader != null);
		queue = q;
		assert (queue != null );
	}


	public void run() {
		String line;
		Message m;
		try {
			while (true) {
				System.out.println("In Listener Loop");
				synchronized (this) {
					while (!reader.ready()) { wait(49);}
				} // spin
				line = reader.readLine();
				// System.out.println(line);
				// w
				//
				// write the line as a message to the queue
				if ( line.equals("") ) { continue; } // and error

				if( line.equals("q")) {
					// for good etiquette the client should say that they are leaving
					// we can implement that later on the server where the quit message is more
					// precise to require that
					break;
				}

				m = new Message(line, System.currentTimeMillis());
//				System.out.println("This is what you yped: " + m);
//				System.out.println("This is the queue" + queue);
				queue.put(m); // add this to the queue

			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("line 56 in Listener");
		}
	}

}
