import java.io.PrintStream;
import java.util.Scanner;

/** this class writes everything it sees to a printstream
 * @Author Oliver Makins
 * @Version 29/03/2017
 */

public class write extends Thread {

		
		private PrintStream ps;
		private Scanner s ;

		write(PrintStream p) {
			ps = p;
			s = new Scanner(System.in);

		}

		public void run() {
			String line;
			while (true) {
				line = s.nextLine();
				ps.println(line);
				 // System.out.println(line);
				 if( line.equals("q")) { break; }
			}
	}

}
