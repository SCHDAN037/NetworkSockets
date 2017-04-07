
/** this dumps the content of a bufferedReader to the screen
 * @Author Oliver Makins
 * @Version 29/03/2017
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.net.Socket;

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

							// line - input from other clients

							// [00;00;00] : name :send filename.format
							if ( line.toUpperCase().contains(":SEND") ) {
								String startPos = line.substring( line.indexOf(" ")+1 ) ;
								String name = startPos.substring(0, startPos.indexOf(":"));
								String filename = line.substring( line.lastIndexOf(" ")+1 ) ;
								String formatedText = userFormatColor +">>"+ name +" sent an image offer ("+ filename +") : Reply ':Y' to accept offer or ':N' to reject" + clearColor;
								System.out.println( formatedText );
							}

						 // [00;00;00] : name :Y
							else if ( line.toUpperCase().contains(":Y") ) {
									String startPos = line.substring( line.indexOf(" ")+1 ) ;
									String name = startPos.substring(0, startPos.indexOf(":"));

									String formatedText = userFormatColor +">>"+ name +" accepted image offer!" + clearColor ;
						      System.out.println( formatedText );
						  }
							else if ( line.toUpperCase().contains(":N") ) {
								String startPos = line.substring( line.indexOf(" ")+1 ) ;
								String name = startPos.substring(0, startPos.indexOf(":"));

								String formatedText = userFormatColor +">>"+ name + " declined image offer!" + clearColor ;
								System.out.println( formatedText );
							}
							else if ( line.toUpperCase().contains("@port") ) {
							}

						}
				}
					 // System.out.println(line);
					 if( line.equals("q")) { break; }
				}
			}
			catch (Exception e) { System.out.println(e);}
		}

}
