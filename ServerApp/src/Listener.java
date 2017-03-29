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

	private BufferedReader reader;
	private ArrayBlockingQueue <Message>queue;
	
	Listener(ArrayBlockingQueue <Message> q, BufferedReader r) {
		reader = r;
		queue = q;
	}


	public void run() {
		String line;
		Message m;
		try { 
			while (true) {
				synchronized (this) { while (!reader.ready()) { wait(1000);}} // spin
				line = reader.readLine();
				// System.out.println(line);
				// w
				//
				// write the line as a message to the queue
				
				if( line.equals("q")) { 
					// for good etiquette the client should say that they are leaving
					// we can implement that later on the server where the quit message is more
					// precise to require that
					break; 
				}

				m = new Message(line, System.currentTimeMillis());
				queue.put(m); // add this to the queue

			}
		} 
		catch (Exception e) { System.out.println(e);}
	}

}
