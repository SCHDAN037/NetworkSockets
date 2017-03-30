
/** this dumps the content of a bufferedReader to the screen
 * @Author Oliver Makins
 * @Version 29/03/2017
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;


public class read extends Thread {

		
		private BufferedReader reader;

		read(BufferedReader r) {
			reader = r;
		}

		public void run() {
			String line;
			try { 
				while (true) {
					synchronized( this) {
						while (!reader.ready()) {
							wait(1000); // spin until we can print to the screen
						}
					}
					line = reader.readLine(); // read what was sent to the client
					System.out.println(line); // print it to the terminal
					 // System.out.println(line);
					 if( line.equals("q")) { break; }
				}
			} 
			catch (Exception e) { System.out.println(e);}
	}

}
