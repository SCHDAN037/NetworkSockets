
/** this dumps the content of a bufferedReader to the screen
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
				
				ps.println(line);
				// System.out.println(line);
				if( line.equals("q")) { System.out.println("bye") ; break; }
			}
	}

}
