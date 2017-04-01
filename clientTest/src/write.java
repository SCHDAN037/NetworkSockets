import java.io.PrintStream;
import java.util.Scanner;

/** this class writes everything it sees to a printstream
 * @Author Oliver Makins
 * @Version 29/03/2017
 */

public class write extends Thread {


		private PrintStream ps;
		private Scanner s ;
		private String user;

		write(PrintStream p, String user) {
			ps = p;
			s = new Scanner(System.in);
			this.user = user;
			ps.println(user + " has connected");
		}

		public void run() {
			String line;
			while (true) { // keep looping

				// Synchronize to prevent other thread's output stream from interfering with this thread.
				// Format the user's input.
				synchronized(this) {
				  //System.out.print( user + " : " );
				  line = s.nextLine(); // keep reading what the user says
				  ps.println(user + ": " + line); // write it to the chat
				}

				 // System.out.println(line);
				 if( line.equals("q")) { // this doesn't work 100%, it is meant to allow the user to quit
				 	ps.println(user + " has disconnected");
				 	System.out.println("Closing connection, exiting");
				 }
			}
	}

}
