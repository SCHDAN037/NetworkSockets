import java.io.PrintStream;
import java.util.Scanner;

public class ClientOutput extends Thread{
	
	private PrintStream outputStream;
	private Scanner scanner;
	private String username;

	ClientOutput(PrintStream oS, String username){
		this.outputStream = oS;
		this.username = username;
		Scanner scanner = new Scanner(System.in);
		outputStream.println(username + " has connected");
	}

	public void run(){
		String line;
		while (true) { // keep looping

			// Synchronize to prevent other thread's output stream from interfering with this thread.
			// Format the user's input.
			synchronized(this) {
			  //System.out.print( user + " : " );
			  line = scanner.nextLine(); // keep reading what the user says
			  outputStream.println(username + ": " + line); // write it to the chat
			}

			 // System.out.println(line);
			 if(line.equals("q")) { // this doesn't work 100%, it is meant to allow the user to quit
			 	outputStream.println(user + " has disconnected");
			 	System.out.println("Closing connection, exiting");
			 	System.exit(0);
			 }
		}
	}
}