
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
					line = reader.readLine();
					System.out.println(line);
					 // System.out.println(line);
					 if( line.equals("q")) { break; }
				}
			} 
			catch (Exception e) { System.out.println(e);}
	}

}
