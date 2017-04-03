import java.io.PrintStream;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;


/** this class writes everything it sees to a printstream
 * @Author Oliver Makins
 * @Version 29/03/2017
 */

public class write extends Thread {


		private PrintStream printStream;
		private Scanner scanner ;
		private String user;
		public static boolean quit;
		public static BufferedReader reader; 
		public static PrintStream stream;
		public static Socket userSocket;

		write(PrintStream p, String user, Socket userSocket, BufferedReader reader) {
			this.printStream = p;
			this.scanner = new Scanner(System.in);
			this.user = user;
			this.userSocket = userSocket;
			this.reader = reader;
			printStream.println(user + " has connected");
		}

		public void quit(){
			try{
				reader.close();
				stream.close();
				userSocket.close();
				System.out.println("Quitting client");
				System.exit(0);
			}
			catch(Exception e){
				if(e.getMessage().contains("NullPointerException")){
					//i dunnno
				}
				else{
					e.printStackTrace();
				}
			}
		}

		public void run() {
			String line = "";
			this.quit = false;
			while (true) { // keep looping

				if(line.equals(":q") || line.equals(":quit")) { // this doesn't work 100%, it is meant to allow the user to quit
				 	printStream.println(user + " has disconnected");
				 	System.out.println("Closing connection, exiting");
				 	quit();
				 }

				// Synchronize to prevent other thread's output stream from interfering with this thread.
				// Format the user's input.
				synchronized(this) {
				  //System.out.print( user + " : " );
				  line = scanner.nextLine(); // keep reading what the user says
				  printStream.println(user + ": " + line); // write it to the chat
				}

				 // System.out.println(line);
				 
			}
	}

}
