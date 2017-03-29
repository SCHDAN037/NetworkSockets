
/** This class takes messages from the client's queue at writes them
 * 	back to the client's socket
 * @Author Oliver Makins
 * @Version 29/03/2017
 */


package server;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Writer extends Thread {

		
		private PrintStream ps;
		private ArrayBlockingQueue <Message>queue;

		Writer(ArrayBlockingQueue<Message> q , PrintStream p) {
			ps = p;
			queue = q;

		}

		public void run() {
			String line = "" ;
			Message m; 
			while (true) {
				// System.out.println(line);
				if( line.equals("q")) { System.out.println("bye") ; break; }
				try { 
					m = queue.take(); // wait for the next element in the queue
				} catch (Exception e) { System.out.println(e); break;}
				ps.println(m.toString()); // write it to the socket
			}
	}

}
