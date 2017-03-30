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
			while (true) {
				line = s.nextLine();
				ps.println(user + ": " + line);
				 // System.out.println(line);
				 if( line.equals("q")) { 
				 	ps.println(user + " has disconnected");
				 	System.out.println("Closing connection, exiting");
				 }
			}
	}

}
