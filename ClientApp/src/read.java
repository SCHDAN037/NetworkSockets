
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
			String line="";
			try {
				while (true) {
					synchronized( this) {
						while (!reader.ready()) {
							wait(1000); // spin until we can print to the screen
						}

						if (reader.ready())
						{
						  line = reader.readLine(); // read what was sent to the client
						  String userFormatColor = (char)27 + "[36m";
						  String clearColor  = (char)27 + "[37m" ;
						  System.out.println( userFormatColor + line + clearColor ); // print it to the terminal
						}
				}
					 // System.out.println(line);
					 if( line.equals("q")) { break; }
				}
			}
			catch (Exception e) { System.out.println(e);}
	}

}
